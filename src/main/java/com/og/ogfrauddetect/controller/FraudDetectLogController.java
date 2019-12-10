package com.og.ogfrauddetect.controller;

import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.service.FraudDetectLogService;
import com.og.ogfrauddetect.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/log")
public class FraudDetectLogController {
    @Resource
    private FraudDetectLogService fraudDetectLogServiceImpl;

    @PostMapping("/show")
    @ResponseBody
    public Object show(int currentPage, String ogUserName, HttpSession session) {
        fraudDetectLogServiceImpl.insertQueryLog(getOgUser(session).getName(),
                ogUserName.isEmpty() ? "ALL_LOG" : ogUserName);
        return fraudDetectLogServiceImpl.show(currentPage, PageUtil.SIZE_OF_PAGE, ogUserName);
    }

    @GetMapping("/fraudDetect")
    public String log() {
        return "log";
    }

    private OGUser getOgUser(HttpSession session) {
        return (OGUser) session.getAttribute("loginUser");
    }
}
