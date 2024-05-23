package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuService {
    private final MenuDAO menuDAO;
    public MenuService(){
        menuDAO = new MenuDAO();
    }
    public List<MenuDTO> selectAllmenu() {
        SqlSession sqlSession = getSqlSession();

        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession, code);
        sqlSession.close();
        return menuList;
    }

    public boolean insertMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.insertMenu(sqlSession, menu){
//            return sqlSession.insert("MenuMapper.insertMenu", menu);
            if(result > 0 ){
                sqlSession.commit();
            }else {
                sqlSession.rollback();
            }
            sqlSession.close();
            return result > 0? true : false;
        }
    }

    public boolean updateMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.updateMenu(sqlSession, menu){
//            return sqlSession.insert("MenuMapper.insertMenu", menu);
            if(result > 0 ){
                sqlSession.commit();
            }else {
                sqlSession.rollback();
            }
            sqlSession.close();
            return result > 0? true : false;
        }
    }

    public boolean deleteMenu(int code) {
        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.updateMenu(sqlSession, code){
//            return sqlSession.insert("MenuMapper.insertMenu", menu);
            if(result > 0 ){
                sqlSession.commit();
            }else {
                sqlSession.rollback();
            }
            sqlSession.close();
            return result > 0? true : false;
        }
    }
}
