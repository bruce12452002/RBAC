package com.og.ogfrauddetect;

import com.og.ogfrauddetect.dao.blocklist.BlocklistMapper;
import com.og.ogfrauddetect.entity.ApiBlocklist;
import com.og.ogfrauddetect.service.BlocklistService;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;


public class BlocklistTest extends BaseJUnit {
    @Resource
    private BlocklistService blocklistServiceImpl;

    @Resource
    private BlocklistMapper blocklistMapper;

    @Test
    @Ignore
    public void testSelect() {
        ApiBlocklist apiBlocklist = blocklistServiceImpl.selectOne("by_u0ylibinbin", "aaa");
        System.out.println(apiBlocklist);
    }

//    @Test
//    public void test() {
//        Integer[] ids = {455, 494, 4612};
//        List<ApiBlocklist> blocklistByIds = blocklistMapper.getBlocklistByIds(ids);
//        System.out.println(blocklistByIds);
//    }

    @Test
    @Ignore
    public void testSelectAll() {
//        IPage<ApiBlocklist> page = blocklistServiceImpl.selectAll(3, 5);
//        System.out.println(page.getTotal()); // 總數 12
//        System.out.println(page.getPages()); // 當前頁 3
//        System.out.println(page.getSize()); // 一頁幾筆 5
//        System.out.println(page.getCurrent()); // 目前在第幾頁 3
//        System.out.println(page.getRecords().size()); // 此頁筆數 2
//        page.getRecords().forEach(x -> System.out.println(x.getId() + "=" + x.getUsername()));
    }

}
