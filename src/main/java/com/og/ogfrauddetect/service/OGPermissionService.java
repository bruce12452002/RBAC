package com.og.ogfrauddetect.service;

import com.og.ogfrauddetect.entity.OGPermission;

import java.util.List;

public interface OGPermissionService {
    List<OGPermission> getPermissionByUser(String username);

    String getPermissionIdByUser(String username);
}
