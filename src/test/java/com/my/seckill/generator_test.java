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

        //ȫ�ֲ���
        GlobalConfig gc = new GlobalConfig();
        gc.setActiveRecord(true);//�Ƿ�ARģʽ
        gc.setAuthor("WQL");//��������
        gc.setOutputDir("C:\\Users\\wql\\Desktop\\fq");//���ɹ��̵�·��


        //����Դ����
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);//�������ݿ������
        dataSourceConfig.setUsername("root");//�û���
        dataSourceConfig.setPassword("123");;//�������ݿ�����
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/seckill?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");//url
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");//���ݿ�����


        //���Բ���
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true);//�Ƿ�ȫ�ִ�д����
        strategyConfig.setTablePrefix("wql_");//���ɱ��ǰ׺
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);//���ݿ��ӳ�䵽ʵ�������������
        strategyConfig.setInclude("goods","t_seckill_goods","t_seckill_order","t_order");//��Ҫ���ɵı���


        //�������Բ���
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.generator");//���ø���·��
        packageConfig.setEntity("pojo");//ʵ����İ���
        packageConfig.setMapper("mapper");//����mapper�ӿڰ���
        packageConfig.setXml("mapper");//���ǽӿڵ�ӳ���ļ��������ͽӿ�һ��
        packageConfig.setServiceImpl("service");//service�ӿ��ļ�
        packageConfig.setService("service");//server�ļ�
        packageConfig.setController("controller");//controller�ļ�����

        //���ϲ��ֲ�����
        AutoGenerator a = new AutoGenerator();
        a.setGlobalConfig(gc);//����ȫ������
        a.setDataSource(dataSourceConfig);//��������Դ
        a.setPackageInfo(packageConfig);//���������
        a.setStrategy(strategyConfig);//�����������

        a.execute();//ִ��

    }
}
