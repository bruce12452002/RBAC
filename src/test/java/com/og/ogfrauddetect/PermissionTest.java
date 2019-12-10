package com.og.ogfrauddetect;

import com.og.ogfrauddetect.entity.OGPermission;
import com.og.ogfrauddetect.service.OGPermissionService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PermissionTest extends BaseJUnit {
    @Resource
    private OGPermissionService oGPermissionService;

    @Test
    public void testPermissionByList() {
        List<OGPermission> aaa = oGPermissionService.getPermissionByUser("aaa");
        System.out.println(aaa);
    }

//    @Test
//    public void testPermissionByList() {
//        Map<Integer, OGPermission> permissionMap = new HashMap<>();
//        List<OGPermission> ogPermissions = oGPermissionService.selectList();
//        for (OGPermission p : ogPermissions) {
//            permissionMap.put(p.getId(), p);
//        }
//        System.out.println(permissionMap);
//    }

}
