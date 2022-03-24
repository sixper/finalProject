package com.softtech.graduationproject.usr.converter;

import com.softtech.graduationproject.usr.dto.UsrUserDto;
import com.softtech.graduationproject.usr.dto.UsrUserSaveDto;
import com.softtech.graduationproject.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {

    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    List<UsrUserDto> convertToUsrUserDtoList(List<UsrUser> usrUserList);

    UsrUserDto convertToUsrUserDto(UsrUser usrUser);

    UsrUser convertToUsrUser(UsrUserSaveDto usrUserSaveDto);
}
