package com.acehibernate.controller;

import com.acehibernate.entity.Users;
import com.acehibernate.service.UsersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
}

