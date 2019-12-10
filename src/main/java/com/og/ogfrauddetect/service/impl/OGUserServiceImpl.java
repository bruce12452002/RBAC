package com.og.ogfrauddetect.service.impl;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.og.ogfrauddetect.dao.frauddetectlog.OGUserMapper;
import com.og.ogfrauddetect.dao.frauddetectlog.OGUserRoleMapper;
import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.entity.OGUserRole;
import com.og.ogfrauddetect.entity.PermissionInfo;
import com.og.ogfrauddetect.enums.OGRoleEnum;
import com.og.ogfrauddetect.service.OGUserService;
import com.og.ogfrauddetect.util.MD5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OGUserServiceImpl implements OGUserService {
    @Resource
    private OGUserMapper oGUserMapper;

    @Resource
    private OGUserRoleMapper oGUserRoleMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class}, transactionManager = "fraudDetectLogTransactionManager")
    public Integer insertOrUpdateUser(PermissionInfo info) {
        Boolean addDelete = Boolean.valueOf(info.getAddDelete());
        Boolean queryLog = Boolean.valueOf(info.getQueryLog());

        OGUser ogUser = getOgUser(info.getName());
        if (ogUser == null) { // 新增
            OGUser user = new OGUser();
            user.setName(info.getName());
            user.setPassword(MD5Utils.getSaltMD5(info.getPassword()));
            oGUserMapper.insert(user);
            return insertOgUserRole(user.getId(), addDelete, queryLog);
        } else { // 修改
            ogUser.setName(info.getName());
            ogUser.setPassword(MD5Utils.getSaltMD5(info.getPassword()));
            oGUserMapper.updateById(ogUser);

            QueryWrapper<OGUserRole> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", ogUser.getId());
            oGUserRoleMapper.delete(wrapper);
            return insertOgUserRole(ogUser.getId(), addDelete, queryLog);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, transactionManager = "fraudDetectLogTransactionManager")
    public Integer updatePwd(OGUser ogUser, String password) {
        ogUser.setPassword(MD5Utils.getSaltMD5(password));
        return oGUserMapper.updateById(ogUser);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, transactionManager = "fraudDetectLogTransactionManager")
    public Integer deleteUser(String name) {
        OGUser ogUser = getOgUser(name);
        if (ogUser == null) {
            return null;
        }

        QueryWrapper<OGUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", ogUser.getId());
        oGUserRoleMapper.delete(wrapper);
        return oGUserMapper.deleteById(ogUser.getId());
    }

    @Override
    public PermissionInfo queryUser(String name) {
        OGUser ogUser = getOgUser(name);
        if (ogUser == null) {
            return null;
        }

        AbstractWrapper<OGUserRole, String, QueryWrapper<OGUserRole>> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", ogUser.getId());
        wrapper.orderByDesc("role_id");

        List<OGUserRole> ogUserRoles = oGUserRoleMapper.selectList(wrapper);
        PermissionInfo info = new PermissionInfo();
        info.setName(name);
        for (var role : ogUserRoles) {
            if (role.getRoleId() == OGRoleEnum.LOG.roleId) {
                info.setQueryLog("true");
                info.setAddDelete("true");
                break;
            }

            List<Integer> roleIds = Arrays.asList(OGRoleEnum.INSERT.roleId, OGRoleEnum.DELETE.roleId);
            if (roleIds.contains(role.getRoleId())) {
                info.setAddDelete("true");
                break;
            }
        }
        return info;
    }

    private OGUser getOgUser(String name) {
        QueryWrapper<OGUser> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        return oGUserMapper.selectOne(wrapper);
    }

    @Transactional(rollbackFor = {Exception.class}, transactionManager = "fraudDetectLogTransactionManager")
    public Integer insertOgUserRole(Integer uid, boolean addDelete, boolean queryLog) {
        List<OGRoleEnum> list = getOgRoleEnum(addDelete, queryLog);
        OGUserRole ogUserRole = new OGUserRole();
        ogUserRole.setUserId(uid);
        int count = 0;
        for (var role : list) {
            ogUserRole.setRoleId(role.roleId);
            count += oGUserRoleMapper.insert(ogUserRole);
        }
        return count;
    }

    private List<OGRoleEnum> getOgRoleEnum(boolean addDelete, boolean queryLog) {
        List<OGRoleEnum> list = new ArrayList<>();
        list.add(OGRoleEnum.QUERY);
        if (queryLog) {
            list.addAll(Arrays.asList(OGRoleEnum.values()));
            return list;
        }

        if (addDelete) {
            list.add(OGRoleEnum.INSERT);
            list.add(OGRoleEnum.DELETE);
        }
        return list;
    }
}
