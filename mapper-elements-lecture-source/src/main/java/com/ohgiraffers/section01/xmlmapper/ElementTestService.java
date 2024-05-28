package com.ohgiraffers.section01.xmlmapper;

import com.ohgiraffers.common.CategoryAndMenuDTO;
import com.ohgiraffers.common.MenuAndCategoryDTO;
import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

public class ElementTestService {

    private ElementTestMapper mapper;
    public void selectCacheTest() {
        SqlSession sqlSession = getSqlSession();

        mapper = sqlSession.getMapper(ElementTestMapper.class);

        for(int i = 0; i<10; i++){

            Long startTime = System.currentTimeMillis();

            List<String> menuList = mapper.selectCacheTest();

            System.out.println("menuList = " + menuList);

            Long lastTime = System.currentTimeMillis();
            Long interval = lastTime - startTime;
            System.out.println("수행시간 = " + interval + "(ms");

        }
    }

    public void selectResultMapTest() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapTest();
        for(MenuDTO menu : menuList){
            System.out.println(menu);
        }
        sqlSession.close();
    }

    public void selectResultMapConstructor() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapConstructor();
        for(MenuDTO menu : menuList){
            System.out.println(menu);
        }
        sqlSession.close();
    }

    public void selectResultMapAssociation() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuAndCategoryDTO> menuList = mapper.selectResultMapAssociation();
        for(MenuAndCategoryDTO menu : menuList){
            System.out.println(menu);
        }
        sqlSession.close();
    }

    public void selectResultMapCollection() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<CategoryAndMenuDTO> categoryList = mapper.selectResultMapCollection();

        for(CategoryAndMenuDTO category : categoryList){
            System.out.println(category);
        }
        sqlSession.close();
    }

    public void selectSqlTest() {
        SqlSession sqlSession = getSqlSession();
        mapper=sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectSqlTest();

        for(MenuDTO menu : menuList){
            System.out.println(menu);
        }
        sqlSession.close();
    }

    public void insertMenuTest(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        int result = mapper.insertMenuTest(menu);

        if(result > 0){
            System.out.println("메뉴 등록 성공!");
            sqlSession.commit();
        }else {
            System.out.println("메뉴 등록 실패!");
            sqlSession.rollback();
        }
        sqlSession.close();
    }

    public void insertCategoryAndMenuTest(MenuAndCategoryDTO menuAndCategory) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        int result1 = mapper.insertNewCategory(menuAndCategory);
        int result2 = mapper.insertNewMenu(menuAndCategory);

        if(result1 > 0 && result2 >0){
            System.out.println("신규 카테고리와 메뉴 등록 성공!");
            sqlSession.commit();
        }else {
            System.out.println("신규 카테고리와 메뉴 등록 실패!");
            sqlSession.rollback();
        }
        sqlSession.close();
    }
}
