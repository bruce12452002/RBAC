package com.og.ogfrauddetect.dao.frauddetectlog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.og.ogfrauddetect.entity.FraudDetectLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudDetectLogMapper extends BaseMapper<FraudDetectLog> {
    int insertDeleteBatch(@Param("ogUsername") String ogUsername, @Param("operatorNum") int operatorNum,
                          @Param("apiBlockUsername") String[] apiBlockUsername);
}