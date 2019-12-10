package com.og.ogfrauddetect.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.og.ogfrauddetect.dao.frauddetectlog.FraudDetectLogMapper;
import com.og.ogfrauddetect.entity.FraudDetectLog;
import com.og.ogfrauddetect.enums.OperatorEnum;
import com.og.ogfrauddetect.service.FraudDetectLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class FraudDetectLogServiceImpl implements FraudDetectLogService {
    @Resource
    private FraudDetectLogMapper fraudDetectLogMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class}, transactionManager = "fraudDetectLogTransactionManager")
    public void insertAddLog(String ogUsername, String apiBlockUsername) {
        FraudDetectLog fraudDetectLog = getFraudDetectLog(ogUsername, apiBlockUsername);
        fraudDetectLog.setOperator(OperatorEnum.INSERT.operatorNum);
        fraudDetectLogMapper.insert(fraudDetectLog);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, transactionManager = "fraudDetectLogTransactionManager")
    public void insertDeleteLog(String ogUsername, String[] apiBlockUsername) {
        fraudDetectLogMapper.insertDeleteBatch(ogUsername, OperatorEnum.DELETE.operatorNum, apiBlockUsername);
    }

    @Override
    public void insertQueryLog(String ogUsername, String apiBlockUsername) {
        FraudDetectLog fraudDetectLog = getFraudDetectLog(ogUsername, apiBlockUsername);
        fraudDetectLog.setOperator(OperatorEnum.QUERY.operatorNum);
        fraudDetectLogMapper.insert(fraudDetectLog);
    }

    @Override
    public IPage<FraudDetectLog> show(long currentPage, long size, String accountName) {
        Page<FraudDetectLog> page = new Page<>(currentPage, size);
        AbstractWrapper<FraudDetectLog, String, QueryWrapper<FraudDetectLog>> wrapper = new QueryWrapper<>();
        if (!accountName.isEmpty()) {
            wrapper.eq("username", accountName);
        }
        wrapper.orderByDesc("createtime");
        return fraudDetectLogMapper.selectPage(page, wrapper);
    }

    private FraudDetectLog getFraudDetectLog(String ogUsername, String apiBlockUsername) {
        return new FraudDetectLog().setUsername(ogUsername).setApiBlocklistUsername(apiBlockUsername);
    }
}
