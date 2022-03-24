package com.softtech.graduationproject.sec.controller;

import com.softtech.graduationproject.gen.dto.RestResponse;
import com.softtech.graduationproject.sec.dto.SecLoginRequestDto;
import com.softtech.graduationproject.sec.service.AuthenticationService;
import com.softtech.graduationproject.usr.dto.UsrUserDto;
import com.softtech.graduationproject.usr.dto.UsrUserSaveDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecLoginRequestDto secLoginRequestDto){


        String token = authenticationService.login(secLoginRequestDto);

        return ResponseEntity.ok(RestResponse.go(token));
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UsrUserSaveDto usrUserSaveDto){

        UsrUserDto usrUserDto = authenticationService.register(usrUserSaveDto);

        return ResponseEntity.ok(RestResponse.go(usrUserDto));
    }
}
