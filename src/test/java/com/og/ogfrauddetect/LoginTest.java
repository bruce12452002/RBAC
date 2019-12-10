package com.og.ogfrauddetect;

import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.service.LoginService;
import org.junit.Test;

import javax.annotation.Resource;


public class LoginTest extends BaseJUnit {
    @Resource
    private LoginService loginServiceImpl;

    @Test
    public void test() {
        OGUser user = new OGUser().setName("aaa").setPassword("xxx");
        OGUser login = loginServiceImpl.login(user);
        System.out.println(login);
    }

}
