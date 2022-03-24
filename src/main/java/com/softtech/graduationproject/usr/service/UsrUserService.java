package com.softtech.graduationproject.usr.service;

import com.softtech.graduationproject.gen.exceptions.DuplicateUserException;
import com.softtech.graduationproject.gen.exceptions.InvalidPasswordException;
import com.softtech.graduationproject.usr.converter.UsrUserMapper;
import com.softtech.graduationproject.usr.dto.UsrUserDto;
import com.softtech.graduationproject.usr.dto.UsrUserSaveDto;
import com.softtech.graduationproject.usr.entity.UsrUser;
import com.softtech.graduationproject.usr.enums.UsrUserErrorMessage;
import com.softtech.graduationproject.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsrUserService {

    private final UsrUserEntityService usrUserEntityService;

    public List<UsrUserDto> findAll(){

        List<UsrUser> usrUserList = usrUserEntityService.findAll();

        return UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUserList);
    }

    public UsrUserDto findById(Long id){

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUserDto save(UsrUserSaveDto usrUserSaveDto){

        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserSaveDto);

        String username = usrUser.getUsername();

        if(usrUserEntityService.findByUserName(username) == null){

            String password = usrUser.getPassword();

            if(password.length() >= 6){

                usrUser = usrUserEntityService.save(usrUser);
                return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
            }else{
                throw new InvalidPasswordException(UsrUserErrorMessage.INVALID_USER_PASSWORD);
            }

        }else{
            throw new DuplicateUserException(UsrUserErrorMessage.DUPLICATE_USERNAME);
        }
    }

    public void delete(Long id){

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        usrUserEntityService.delete(usrUser);
    }
}
