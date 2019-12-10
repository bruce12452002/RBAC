package com.og.ogfrauddetect.dao.blocklist;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.og.ogfrauddetect.entity.ApiBlocklist;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlocklistMapper extends BaseMapper<ApiBlocklist> {
    int deleteBlocklistByUsernames(@Param("usernames") String[] usernames);
}