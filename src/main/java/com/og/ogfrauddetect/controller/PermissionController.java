package com.og.ogfrauddetect.controller;

import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.entity.PermissionInfo;
import com.og.ogfrauddetect.service.OGUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/perm")
public class PermissionController {
    @Resource
    private OGUserService oGUserService;

    @GetMapping("/add")
    public String userPermPage() {
        return "userPerm";
    }

    @PostMapping("/addOrUpdateUser")
    @ResponseBody
    public Integer addUser(PermissionInfo info) {
        return oGUserService.insertOrUpdateUser(info);
    }

    @PostMapping("/deleteUser")
    @ResponseBody
    public Integer deleteUser(String name) {
        return oGUserService.deleteUser(name);
    }

    @PostMapping("/queryUser")
    @ResponseBody
    public Object queryUser(String name) {
        return oGUserService.queryUser(name);
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    public Object updatePwd(String password, HttpSession session) {
        OGUser ogUser = (OGUser) session.getAttribute("loginUser");
        return oGUserService.updatePwd(ogUser, password);
    }

}
