package com.my.seckill;

import com.baomidou.mybatisplus.annotation.DbType;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class generator_test {

    @Test
    public void generator() {

        //全局部分
        GlobalConfig gc = new GlobalConfig();
        gc.setActiveRecord(true);//是否AR模式
        gc.setAuthor("WQL");//设置作者
        gc.setOutputDir("C:\\Users\\wql\\Desktop\\fq");//生成工程的路径


        //数据源部分
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);//设置数据库的类型
        dataSourceConfig.setUsername("root");//用户名
        dataSourceConfig.setPassword("123");;//设置数据库密码
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/seckill?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");//url
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");//数据库驱动


        //策略部分
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true);//是否全局大写命名
        strategyConfig.setTablePrefix("wql_");//生成表的前缀
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体类的命名策略
        strategyConfig.setInclude("goods","t_seckill_goods","t_seckill_order","t_order");//需要生成的表名


        //包名策略部分
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.generator");//设置父包路径
        packageConfig.setEntity("pojo");//实体类的包名
        packageConfig.setMapper("mapper");//设置mapper接口包名
        packageConfig.setXml("mapper");//这是接口的映射文件，包名和接口一样
        packageConfig.setServiceImpl("service");//service接口文件
        packageConfig.setService("service");//server文件
        packageConfig.setController("controller");//controller文件包名

        //整合部分并运行
        AutoGenerator a = new AutoGenerator();
        a.setGlobalConfig(gc);//加入全局配置
        a.setDataSource(dataSourceConfig);//加入数据源
        a.setPackageInfo(packageConfig);//加入包配置
        a.setStrategy(strategyConfig);//加入策略配置

        a.execute();//执行

    }
}
