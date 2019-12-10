package com.og.ogfrauddetect.service.impl;

import com.og.ogfrauddetect.dao.frauddetectlog.OGPermissionMapper;
import com.og.ogfrauddetect.entity.OGPermission;
import com.og.ogfrauddetect.service.OGPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OGPermissionServiceImpl implements OGPermissionService {
    @Resource
    private OGPermissionMapper oGPermissionMapper;

    @Override
    public List<OGPermission> getPermissionByUser(String username) {
        return oGPermissionMapper.queryPermissionByUser(username);
    }

    @Override
    public String getPermissionIdByUser(String username) {
        StringBuilder permissions = new StringBuilder();
        oGPermissionMapper.queryPermissionByUser(username).forEach(x ->
                permissions.append(",").append(x.getId())
        );
        return permissions.substring(1);
    }
}
