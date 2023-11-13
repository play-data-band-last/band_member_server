package com.example.user.service;

import com.example.user.common.RestError;
import com.example.user.common.RestResult;
import com.example.user.config.JwtService;
import com.example.user.domain.entity.Interest;
import com.example.user.domain.entity.User;
import com.example.user.domain.request.*;
import com.example.user.domain.response.LoginResponse;
import com.example.user.domain.response.UserResponse;
import com.example.user.kafka.*;
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
    //private final CommunityMemberClient communityMemberClient;
    private final JwtService jwtService;
    //private final AlbumClient albumClient;
    //private final BoardClient boardClient;
    //private final ChattingClient chattingClient;
    //private final ScheduleClient scheduleClient;
    private final InterestRepository interestRepository;
    private final CommunityMemberUpdateProducer communityMemberProducer;
    private final AlbumUpdateProducer albumProducer;
    private final BoardUpdateProducer boardProducer;
    private final ChattingUpdateProducer chattingProducer;
    private final ScheduleUpdateProducer scheduleProducer;
    private final CommentUpdateProducer commentUpdateProducer;
    private Optional<User> byId;

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
                .location(byEmailAndPassword.get().getLocation())
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


    @Transactional
    public void updateUser(Long id, SignupRequest request){
        userRepository.updateUser(id, request);

        communityMemberProducer.send(new CommunityMemberRequest(id,null
                , request.getName(), request.getImgPath(), null,null, id));

        albumProducer.send(new AlbumUpdateRequest(
                request.getName(), request.getImgPath(), id));

        boardProducer.send(new AlbumUpdateRequest(
                request.getName(), request.getImgPath(), id));

        scheduleProducer.send(new AlbumUpdateRequest(
                request.getName(), request.getImgPath(), id));

        chattingProducer.send(new AlbumUpdateRequest(
                request.getName(), request.getImgPath(), id));

        commentUpdateProducer.send(new AlbumUpdateRequest(
                request.getName(), request.getImgPath(), id));
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

    public void updateUserLocation(UserLocationRequest userLocationRequest) {
        Optional<User> byId = userRepository.findById(userLocationRequest.getId());

        if (byId.isPresent()) {
            User user = byId.get();
            System.out.println(userLocationRequest.getLocation());
            user.setLocation(userLocationRequest.getLocation());

            // 위치 변경사항을 저장
            userRepository.save(user);
        } else {
            // 사용자를 찾을 수 없는 경우 예외처리 또는 로깅 등을 수행
            System.out.println("User not found with id: " + userLocationRequest.getId());
        }

    }


}
