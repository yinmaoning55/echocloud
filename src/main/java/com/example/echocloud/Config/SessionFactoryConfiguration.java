package com.example.echocloud.Config;

import lombok.ToString;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.example.echocloud.mapper")
@ToString
public class SessionFactoryConfiguration {

    @Value("${mybatis.config-location}")
    private String mybatisConfigLocation;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Value("${mybatis.type-aliases-package}")
    private String entityPackage;


   @Autowired
   @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean
    public SqlSessionFactory createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigLocation));

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        String packagePath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperLocations;
       sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packagePath));
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackage);

        sqlSessionFactoryBean.setDataSource(dataSource);
        System.out.println("------------------------------");

        System.out.println(this.toString());

        System.out.println("------------------------------");


        return sqlSessionFactoryBean.getObject();
    }



}
