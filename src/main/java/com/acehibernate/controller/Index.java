package com.acehibernate.controller;

import com.acehibernate.entity.Users;
import com.acehibernate.service.UsersService;
import com.acehibernate.util.NullUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname: Index
 * @Date: 2022/8/15 下午 02:30
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class Index {
    private static final Logger log = LogManager.getLogger(Index.class.getName());

    private UsersService usersService;

    @Autowired
    public Index(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/userId", method = RequestMethod.GET)
    public ModelAndView getUserById(@RequestParam(value = "userId") Long userId) {
        Users user = usersService.findById(userId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        log.info("user: {}", user.getUserAccount());
        return modelAndView;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        List<Users> users = usersService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);

        log.info("total: {}", users.size());
        for (Users user : users) {
            log.info("user: {}", user.getUserAccount());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView insert() {
        Users admin = usersService.findByUserAccount("admin");
        if (NullUtil.isNull(admin)) {
            admin = new Users();
            admin.setPassword("$2a$11$gNnG0zfbKr8c7M3YX9Frn.HaKPS1hFsmgKPt4F6LXnEmmE0FAhV8C");//password 909394
            admin.setUserAccount("admin");
            admin.setUsername("administrator");
            admin.setDescription("administrator");
            admin.setEmail("admin@ace.com");
            admin.setMobile("0000 0000");
            admin.setGender(null);
            admin.setBirthday(LocalDateTime.now());
            admin.setLoginDateTime(LocalDateTime.now());
            admin.setStatus("ACTIVE");
            admin.setDomain("ace.com");
            admin.setIp("127.0.0.1");
            admin.setDomain("ace.com");
            admin.setRemark("ACE APPLICATION");
            admin.setEnabled(true);
            usersService.saveAndFlush(admin);
        }

        log.info("UserId: {}", admin.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", admin);
        return modelAndView;
    }
}

