package com.softtech.graduationproject.sec.security;

import com.softtech.graduationproject.usr.entity.UsrUser;
import com.softtech.graduationproject.usr.service.entityservice.UsrUserEntityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService{

    private final UsrUserEntityService usrUserEntityService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsrUser usrUser = usrUserEntityService.findByUserName(username);

        return JwtUserDetails.create(usrUser);
    }

    public UserDetails loadUserByUserId(Long id){

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(usrUser);
    }
}