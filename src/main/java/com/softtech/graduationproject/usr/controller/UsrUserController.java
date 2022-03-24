package com.softtech.graduationproject.usr.controller;

import com.softtech.graduationproject.gen.dto.RestResponse;
import com.softtech.graduationproject.usr.dto.UsrUserDto;
import com.softtech.graduationproject.usr.dto.UsrUserSaveDto;
import com.softtech.graduationproject.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsrUserController {

    private final UsrUserService usrUserService;

    @GetMapping
    public ResponseEntity findAll(){

        List<UsrUserDto> usrUserDtoList = usrUserService.findAll();

        return ResponseEntity.ok(RestResponse.go(usrUserDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){

        UsrUserDto usrUserDto = usrUserService.findById(id);

        return ResponseEntity.ok(RestResponse.go(usrUserDto));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody UsrUserSaveDto usrUserSaveDto){

        UsrUserDto usrUserDto = usrUserService.save(usrUserSaveDto);

        return ResponseEntity.ok(RestResponse.go(usrUserDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UsrUserSaveDto usrUserSaveDto){

        UsrUserDto usrUserDto = usrUserService.update(id, usrUserSaveDto);

        return ResponseEntity.ok(RestResponse.go(usrUserDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        usrUserService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }
}
