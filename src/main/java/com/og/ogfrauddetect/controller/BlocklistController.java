package com.og.ogfrauddetect.controller;

import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.service.BlocklistService;
import com.og.ogfrauddetect.util.PageUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/blocklist")
public class BlocklistController {
    @Resource
    private BlocklistService blocklistServiceImpl;

    @PostMapping("/add")
    public String add(String username, String remark, HttpSession session) {
        return blocklistServiceImpl.addBlocklist(username, remark, getOgUser(session).getName());
    }

    @PostMapping("/delMany")
    public String delMany(String usernames, HttpSession session) {
        return blocklistServiceImpl.deleteBLocklist(usernames, getOgUser(session).getName());
    }

    @PostMapping("/showOne")
    public Object showOne(String username, HttpSession session) {
        return blocklistServiceImpl.selectOne(username, getOgUser(session).getName());
    }

    @PostMapping("/showAll")
    public Object showAll(int currentPage, HttpSession session) {
        return blocklistServiceImpl.selectAll(currentPage, PageUtil.SIZE_OF_PAGE, getOgUser(session).getName());
    }

    private OGUser getOgUser(HttpSession session) {
        return (OGUser) session.getAttribute("loginUser");
    }
}
