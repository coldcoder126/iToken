package com.coldcoder.iToken.web.admin.controller;

import com.coldcoder.iToken.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: DX
 * @Description: ${description}
 * @Date: 2020/2/20 12:39
 * @Version: 1.0
 */
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
