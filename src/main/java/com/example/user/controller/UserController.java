package com.example.user.controller;

import com.example.user.common.RestResult;
import com.example.user.config.JwtService;
import com.example.user.config.TokenInfo;
import com.example.user.domain.entity.User;
import com.example.user.domain.request.LoginRequest;
import com.example.user.domain.request.SignupRequest;
import com.example.user.domain.request.TeacherRequest;
import com.example.user.domain.response.LoginResponse;
import com.example.user.domain.response.UserResponse;
import com.example.user.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(originPatterns = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    //회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RestResult<Object>> signup(@RequestBody SignupRequest request) {
        return userService.signupCheck(request);
    }

    @PostMapping("/teacher")
    public ResponseEntity<RestResult<Object>> teacherLogin(@RequestBody TeacherRequest request) {
        return userService.teacherLogin(request);
    }

    //로그인
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestResult<Object>> login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

    @GetMapping("/teacherAccountInfo/{userEmail}")
    public LoginResponse teacherAccountInfo(@PathVariable String userEmail) {
        return userService.teacherAccountInfo(userEmail);
    }

    @GetMapping("/me")
    public void getMe(@RequestHeader(name = "Authorization") String token1){
        String token=token1.replace("Bearer ", "");
        System.out.println(token);
        TokenInfo tokenInfo = jwtService.parseToken(token);
        System.out.println(tokenInfo.getId());
//        return userService.getMe(tokenInfo.getId());
    }

    @PutMapping("/update")
    public void updateUser(/*@RequestHeader(name = "Authorization") String token1,*/ @RequestBody SignupRequest request){
//        String token = token1.replace("Bearer", "");
//        TokenInfo tokenInfo = jwtService.parseToken(token);
//        System.out.println(tokenInfo.getId());
//        userService.updateUser(tokenInfo.getId(), request);
        userService.updateUser(1l, request);

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
    }


}
