package com.softtech.graduationproject.usr.service;

import com.softtech.graduationproject.gen.enums.GenErrorMessage;
import com.softtech.graduationproject.gen.exceptions.*;
import com.softtech.graduationproject.prd.converter.PrdProductMapper;
import com.softtech.graduationproject.prd.entity.PrdProduct;
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

        if(usrUserList.isEmpty()){
            throw new EntityNotFoundException(GenErrorMessage.ENTITIES_NOT_FOUND);
        }

        return UsrUserMapper.INSTANCE.convertToUsrUserDtoList(usrUserList);

    }

    public UsrUserDto findById(Long id){

        UsrUser usrUser = usrUserEntityService.getByIdWithControl(id);

        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUserDto save(UsrUserSaveDto usrUserSaveDto){

        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserSaveDto);

        validateUser(usrUser);

        usrUser = usrUserEntityService.save(usrUser);
        return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);
    }

    public UsrUserDto update(Long id, UsrUserSaveDto usrUserSaveDto) {

        if(usrUserEntityService.existsById(id)){

            UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserSaveDto);
            usrUser.setId(id);

            validateUsername(usrUser.getUsername());
            validatePassword(usrUser.getPassword());
            validateNameSurname(usrUser.getName(), usrUser.getSurname());

            usrUser = usrUserEntityService.save(usrUser);

            return UsrUserMapper.INSTANCE.convertToUsrUserDto(usrUser);

        }else{
            throw new EntityNotFoundException(GenErrorMessage.ENTITY_NOT_FOUND);
        }
    }


    public void delete(String username, String password){

        UsrUser usrUser = usrUserEntityService.findByUsernameAndPassword(username, password);

        if(usrUser != null){
            usrUserEntityService.delete(usrUser);
        }else{
            throw new EntityNotFoundException(GenErrorMessage.ENTITY_NOT_FOUND);
        }

    }

    private void validateUser(UsrUser usrUser) {

        validateUsername(usrUser.getUsername());
        validatePassword(usrUser.getPassword());
        validateNameSurname(usrUser.getName(), usrUser.getSurname());
        checkDuplicateUser(usrUser.getUsername());
    }

    private void checkDuplicateUser(String username) {

        UsrUser usrUser = usrUserEntityService.findByUserName(username);

        if(usrUser != null){
            throw new DuplicateUserException(UsrUserErrorMessage.DUPLICATE_USERNAME);
        }
    }

    public boolean validateUsername(String username){

        String regex = "[a-zA-Z0-9]+";

        if(username.length() >= 3 && username.length() <= 20 && username.matches(regex)){
            return true;
        }else{
            throw new InvalidUsernameException(UsrUserErrorMessage.INVALID_USERNAME);
        }


    }

    public boolean validatePassword(String password){

        if(password.length() >= 7 && password.length() <= 30){
            return true;
        }else{
            throw new InvalidPasswordException(UsrUserErrorMessage.INVALID_USER_PASSWORD);
        }

    }

    public boolean validateNameSurname(String name, String surname){

        if(name.length() <= 30 && surname.length() <= 30){
            return true;
        }else{
            throw new InvalidFieldException(UsrUserErrorMessage.INVALID_FIELD);
        }


    }

}
