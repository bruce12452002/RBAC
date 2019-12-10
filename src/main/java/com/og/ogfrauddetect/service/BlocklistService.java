package com.og.ogfrauddetect.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.og.ogfrauddetect.entity.ApiBlocklist;

public interface BlocklistService {
    String addBlocklist(String username, String remark, String ogUsername);

    String deleteBLocklist(String usernames, String ogUsername);

    ApiBlocklist selectOne(String username, String ogUsername);

    IPage<ApiBlocklist> selectAll(long currentPage, long size, String ogUsername);
}
