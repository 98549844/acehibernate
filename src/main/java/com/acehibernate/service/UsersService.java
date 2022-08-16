package com.acehibernate.service;

import com.acehibernate.dao.UsersDao;
import com.acehibernate.entity.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname: UsersService
 * @Date: 2022/8/15 下午 02:31
 * @Author: kalam_au
 * @Description:
 */

@Service
public class UsersService {
    private static final Logger log = LogManager.getLogger(UsersService.class.getName());

    private UsersDao usersDao;

    @Autowired
    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }


    public Users findById(long id) {
        return usersDao.findById(id);
    }

    public List<Users> findAll() {
        return usersDao.findAll();
    }

    public Users findByUserAccount(String userAccount) {
        Users user = usersDao.findByUserAccount(userAccount.toLowerCase());
        return user;
    }

    @Transactional
    public Users saveAndFlush(Users users) {
        return usersDao.saveAndFlush(users);
    }
}

