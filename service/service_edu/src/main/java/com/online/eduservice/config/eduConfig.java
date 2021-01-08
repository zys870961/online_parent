package com.online.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.online.eduservice.mapper")
public class eduConfig {
    //逻辑删除  数据不删除 页面不展示
    @Bean
    public ISqlInjector sqlInjector(){
    return new LogicSqlInjector();
}

    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return  new PaginationInterceptor();

}
}
