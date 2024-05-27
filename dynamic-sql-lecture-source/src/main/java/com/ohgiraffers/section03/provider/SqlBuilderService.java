package com.ohgiraffers.section03.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import static com.ohgiraffers.common.Template.getSqlSession;
import static java.awt.SystemColor.menu;

public class SqlBuilderService {
    private SqlBuilderMapper mapper;
    public void registMenu(MenuDTO menuDTO) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.registMenu(menu);

        if(result > 0){
            System.out.println("신규메뉴 등록 성공");
            sqlSession.commit();
        }else {
            System.out.println("메뉴등록 실패");
            sqlSession.rollback();
        }

    }

    public void modifyMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.modifyMenu(menu);

        if(result > 0){
            System.out.println("메뉴 수정 성공");
            sqlSession.commit();
        }else {
            System.out.println("메뉴 수정 실패");
            sqlSession.rollback();
        }
    }

    public void deletMenu(int code) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SqlBuilderMapper.class);

        int result = mapper.deleteMenu(code);

        if(result > 0){
            System.out.println("메뉴 수정 성공");
            sqlSession.commit();
        }else {
            System.out.println("메뉴 수정 실패");
            sqlSession.rollback();
        }
    }
}
