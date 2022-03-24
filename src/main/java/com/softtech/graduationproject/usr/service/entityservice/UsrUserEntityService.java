package com.softtech.graduationproject.usr.service.entityservice;

import com.softtech.graduationproject.gen.service.BaseEntityService;
import com.softtech.graduationproject.usr.dao.UsrUserDao;
import com.softtech.graduationproject.usr.entity.UsrUser;
import org.springframework.stereotype.Service;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {

    public UsrUserEntityService(UsrUserDao dao) {
        super(dao);
    }

    public UsrUser findByUserName(String username){

        return getDao().findByUsername(username);
    }

    public UsrUser findByUsernameAndPassword(String username, String password){

        return getDao().findByUsernameAndPassword(username, password);
    }

}
