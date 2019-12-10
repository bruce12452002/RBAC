package com.og.ogfrauddetect.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.og.ogfrauddetect.entity.FraudDetectLog;

public interface FraudDetectLogService {
    void insertAddLog(String ogUsername, String apiBlockUsername);

    void insertDeleteLog(String ogUsername, String[] apiBlockUsername);

    void insertQueryLog(String ogUsername, String apiBlockUsername);

    IPage<FraudDetectLog> show(long currentPage, long size, String ogUsername);
}
