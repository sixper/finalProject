package com.softtech.graduationproject.sec.service;

import com.softtech.graduationproject.sec.dto.SecLoginRequestDto;
import com.softtech.graduationproject.sec.enums.EnumJwtConstant;
import com.softtech.graduationproject.sec.security.JwtTokenGenerator;
import com.softtech.graduationproject.sec.security.JwtUserDetails;
import com.softtech.graduationproject.usr.dto.UsrUserDto;
import com.softtech.graduationproject.usr.dto.UsrUserSaveDto;
import com.softtech.graduationproject.usr.entity.UsrUser;
import com.softtech.graduationproject.usr.service.UsrUserService;
import com.softtech.graduationproject.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsrUserService usrUserService;
    private final UsrUserEntityService usrUserEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public UsrUserDto register(UsrUserSaveDto usrUserSaveDto){

        UsrUserDto usrUserDto = usrUserService.save(usrUserSaveDto);

        return usrUserDto;
    }

    public String login(SecLoginRequestDto secLoginRequestDto){

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getUsername(), secLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        return bearer + token;
    }

    public UsrUser getCurrentUser(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        UsrUser usrUser = null;
        if(jwtUserDetails != null){
            usrUser = usrUserEntityService.getByIdWithControl(jwtUserDetails.getId());
        }

        return usrUser;
    }

    public Long getCurrentUserId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserDetailsId = null;
        if(jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }

        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
