package com.bigsea.controller;

import com.bigsea.entity.BootUser;
import com.bigsea.service.BootUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = {"/bootUser"})
public class BootUserController {

    @Autowired
    private BootUserService bootUserService;

    @RequestMapping(value = {"/toLogin.action"})
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = {"/login.action"})
    public String login(@RequestParam("name") String name, @RequestParam("password") String password) {
        BootUser bootUser = bootUserService.login(name, password);
        if(bootUser == null) {
            return "error";
        }
        return "helloSpringBoot";
    }
}
