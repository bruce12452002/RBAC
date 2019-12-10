package com.og.ogfrauddetect.service;

import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.entity.PermissionInfo;

public interface OGUserService {
    Integer insertOrUpdateUser(PermissionInfo info);

    Integer updatePwd(OGUser ogUser, String password);

    Integer deleteUser(String name);

    PermissionInfo queryUser(String name);

    Integer insertOgUserRole(Integer uid, boolean addDelete, boolean queryLog);
}
