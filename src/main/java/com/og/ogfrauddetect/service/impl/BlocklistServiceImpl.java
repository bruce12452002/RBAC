package com.og.ogfrauddetect.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.og.ogfrauddetect.dao.blocklist.BlocklistMapper;
import com.og.ogfrauddetect.entity.ApiBlocklist;
import com.og.ogfrauddetect.service.BlocklistService;
import com.og.ogfrauddetect.service.FraudDetectLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class BlocklistServiceImpl implements BlocklistService {
    @Resource
    private BlocklistMapper blocklistMapper;

    @Resource
    private FraudDetectLogService fraudDetectLogServiceImpl;

    @Override
    @Transactional(rollbackFor = {Exception.class}, transactionManager = "blocklistTransactionManager")
    public String addBlocklist(String username, String remark, String ogUsername) {
        int insertResult;
        try {
            fraudDetectLogServiceImpl.insertAddLog(ogUsername, username);
            ApiBlocklist apiBlocklist = blocklistMapper.selectOne(new QueryWrapper<>(getApiBlicklist(username)));
            if (apiBlocklist == null) {
                insertResult = blocklistMapper.insert(getApiBlicklist(username).setRemark(remark));
                return String.valueOf(insertResult);
            } else {
                return "不可重覆新增已有的帳號";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, transactionManager = "blocklistTransactionManager")
    public String deleteBLocklist(String usernames, String ogUsername) {
        String[] usernameArray = usernames.split(",");
        int deleteResult;
        try {
            fraudDetectLogServiceImpl.insertDeleteLog(ogUsername, usernameArray);
            deleteResult = blocklistMapper.deleteBlocklistByUsernames(usernameArray);
            return String.valueOf(deleteResult);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public ApiBlocklist selectOne(String username, String ogUsername) {
        fraudDetectLogServiceImpl.insertQueryLog(ogUsername, username);
        return blocklistMapper.selectOne(new QueryWrapper<>(getApiBlicklist(username)));
    }

    @Override
    public IPage<ApiBlocklist> selectAll(long current, long size, String ogUsername) {
        fraudDetectLogServiceImpl.insertQueryLog(ogUsername, "ALL_BLOCKLIST");

        Page<ApiBlocklist> page = new Page<>(current, size);
        AbstractWrapper<ApiBlocklist, String, QueryWrapper<ApiBlocklist>> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("createtime");
        return blocklistMapper.selectPage(page, wrapper);
    }

    private ApiBlocklist getApiBlicklist(String username) {
        return new ApiBlocklist().setUsername(username);
    }
}
