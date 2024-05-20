package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Date;

public class Application {
    private static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/menudb";
    private static String USER = "ohgiraffers";
    private static String PASSWORD = "ohgiraffers";

    public static void main(String[] args) {

        Environment environment = new Environment(
                "dev"   // 환경정보 이름
                ,new JdbcTransactionFactory()   // 트랜젝션 매니저 종류 설정
                ,new PooledDataSource(DRIVER, URL, USER, PASSWORD)  // 사용 여부
        );

        // 생성된 환경 설정 정보로 Mybaits 설정 객체 생성
        Configuration configuration = new Configuration(environment);
        // 설정 객체에 매퍼 등록
        configuration.addMapper(Mapper.class);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        Date date = mapper.slelctSysdate();
        System.out.println("date = " + date);
        sqlSession.close();
    }

}
