package com.og.ogfrauddetect.dao.frauddetectlog;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.og.ogfrauddetect.entity.OGPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OGPermissionMapper extends BaseMapper<OGPermission> {
    List<OGPermission> queryPermissionByUser(String username);
}
