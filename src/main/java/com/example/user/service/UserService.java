package com.example.user.service;

import com.example.user.common.RestError;
import com.example.user.common.RestResult;
import com.example.user.config.JwtService;
import com.example.user.domain.entity.DeleteUser;
import com.example.user.domain.entity.Interest;
import com.example.user.domain.entity.User;
import com.example.user.domain.request.*;
import com.example.user.domain.response.LoginResponse;
import com.example.user.domain.response.UserResponse;
import com.example.user.kafka.*;
import com.example.user.repository.DeleteUserRepository;
import com.example.user.repository.InterestRepository;
import com.example.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DeleteUserRepository deleteUserRepository;
    //private final CommunityMemberClient communityMemberClient;
    private final JwtService jwtService;
    //private final AlbumClient albumClient;
    //private final BoardClient boardClient;
    //private final ChattingClient chattingClient;
    //private final ScheduleClient scheduleClient;
    private final InterestRepository interestRepository;
    private final ChattingUpdateProducer chattingProducer;
    private final MemberUpdateProducer memberUpdateProducer;


    //중복이메일 검사 후 회원가입
    public ResponseEntity<RestResult<Object>> signupCheck(SignupRequest request) {
        Optional<User> byEmail = userRepository.findByEmail(request.getEmail());

        if(byEmail.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RestResult<>("CONFLICT",new RestError("EMAIL_CONFLICT","이미 존재하는 이메일 입니다.")));
       }

        User save = userRepository.save(request.toEntity());

        return ResponseEntity.ok(new RestResult<>("success", save.getId()));
    }

    public ResponseEntity<RestResult<Object>> login(LoginRequest request){
        Optional<User> byEmailAndPassword = userRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());

        if (!byEmailAndPassword.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new RestResult<>("BAD_REQUEST",new RestError("BAD_REQUEST","존재하지 않는 정보입니다.")));
        }

        List<Interest> interests = interestRepository.findInterestsByUserId(byEmailAndPassword.get().getId());


        String s = jwtService.makeToken(byEmailAndPassword.get());

        LoginResponse loginResponse = LoginResponse.builder()
                .token(s)
                .email(byEmailAndPassword.get().getEmail())
                .username(byEmailAndPassword.get().getName())
                .mbti(byEmailAndPassword.get().getMbti())
                .profileImgPath(byEmailAndPassword.get().getImgPath())
                .userId(byEmailAndPassword.get().getId())
                .interests(interests)
                .build();

        return ResponseEntity.ok(new RestResult<>("success",loginResponse));
    }

    public UserResponse getMe(Long id){
        Optional<UserResponse> interestById = userRepository.findInterestById(id);
        return interestById.orElseThrow(IllegalArgumentException::new);

    }

    public ResponseEntity<RestResult<Object>> teacherLogin(TeacherRequest teacherRequest) {
        Optional<User> byEmail = userRepository.findByEmail(teacherRequest.getEmail());

        if(byEmail.isPresent()){
            return ResponseEntity.ok(new RestResult<>("CONFLICT","이미 존재하는 이메일 입니다."));
        }

        return ResponseEntity.ok(new RestResult<>("SUCCESS","존재하지 않는 회원 입니다."));
    }


    //토픽 memberUpdate에서(단일파티션) command, album, board, communityMember, schedule, chatting? 에다가 kafka로 병렬적으로 업데이트를 처리함
    @Transactional
    public void updateUser(Long id, SignupRequest request){
        userRepository.updateUser(id, request);

        chattingProducer.send(new AlbumUpdateRequest(
                request.getName(), request.getImgPath(), id));

        memberUpdateProducer.send(new CommunityMemberRequest(id,null
                , request.getName(), request.getImgPath(), null,null, id));
    }

    public LoginResponse teacherAccountInfo(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);

        List<Interest> interests = interestRepository.findInterestsByUserId(byEmail.get().getId());

        String s = jwtService.makeToken(byEmail.get());

        LoginResponse loginResponse = LoginResponse.builder()
                .token(s)
                .email(byEmail.get().getEmail())
                .username(byEmail.get().getName())
                .mbti(byEmail.get().getMbti())
                .profileImgPath(byEmail.get().getImgPath())
                .userId(byEmail.get().getId())
                .interests(interests)
                .username(byEmail.get().getName())
                .build();

        return loginResponse;
    }

    //회원가입
    private void signup(SignupRequest request){
        userRepository.save(request.toEntity());
    }


    // 멤버 isValid False로 만들고, kafka produce
    @Transactional
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).get();
        deleteUserRepository.save(DeleteUser.builder()
                .interest(user.getInterest())
                .imgPath(user.getImgPath())
                .name(user.getName())
                .email(user.getEmail())
                .mbti(user.getMbti())
                .build());
        user.setIsVailid(Boolean.FALSE);

    }

}
