package com.og.ogfrauddetect.datasource;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.og.ogfrauddetect.util.PageUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BlocklistDatasource {

    @Bean("blocklistDS")
    @ConfigurationProperties("spring.datasource.blocklist-datasource")
    public DataSource getBlocklistDatasource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("blocklistSqlSessionFactory")
    public SqlSessionFactory blocklistSqlSessionFactory(
            @Qualifier("blocklistDS") DataSource datasource) throws Exception {
        var resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/blocklist/**/*.xml");

        var bean = new MybatisSqlSessionFactoryBean(); // myBatis-plus 的 factoryBean
        bean.setDataSource(datasource);
        bean.setMapperLocations(resources);

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(PageUtil.SIZE_OF_PAGE);
        Interceptor[] plugins = {paginationInterceptor};
        bean.setPlugins(plugins);
        return bean.getObject();
    }

    @Bean("blocklistSqlSessionTemplate")
    public SqlSessionTemplate blocklistSqlSessionTemplate(
            @Qualifier("blocklistSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean("blocklistTransactionManager")
    public DataSourceTransactionManager getBlocklistTransactionManager(
            @Qualifier("blocklistDS") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("blocklistMapperScan")
    public MapperScannerConfigurer getBlocklistMapperScan() {
        var mapper = new MapperScannerConfigurer();
        mapper.setBasePackage("com.og.ogfrauddetect.dao.blocklist");
        mapper.setSqlSessionFactoryBeanName("blocklistSqlSessionFactory");
        return mapper;
    }
}
