package com.og.ogfrauddetect.controller;

import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.service.LoginService;
import com.og.ogfrauddetect.service.OGPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    private LoginService loginServiceImpl;

    @Resource
    private OGPermissionService oGPermissionServiceImpl;

    @PostMapping("/userLogin")
    @ResponseBody
    public boolean login(OGUser user, HttpSession session) {
        OGUser ogUser = loginServiceImpl.login(user);

        if (ogUser != null) {
            String permissionIdsByUser = oGPermissionServiceImpl.getPermissionIdByUser(ogUser.getName());
            session.setAttribute("loginUser", ogUser);
            session.setAttribute("userPermission", permissionIdsByUser);
            return true;
        }
        return false;
    }

    @RequestMapping("/userLogout")
    public String login(HttpSession session) {
        session.invalidate();
        return "redirect:login.html";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
