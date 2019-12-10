package com.og.ogfrauddetect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.og.ogfrauddetect.dao.frauddetectlog.OGUserMapper;
import com.og.ogfrauddetect.entity.OGUser;
import com.og.ogfrauddetect.service.LoginService;
import com.og.ogfrauddetect.util.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private OGUserMapper oGUserMapper;

    @Override
    public OGUser login(OGUser user) {
        OGUser copyOgUser = new OGUser().setName(user.getName());

        OGUser ogUser = oGUserMapper.selectOne(new QueryWrapper<>(copyOgUser));
        if (ogUser != null) {
            if (MD5Utils.getSaltverifyMD5(user.getPassword(), ogUser.getPassword())) {
                return ogUser;
            }
        }
        return null;
    }
}
