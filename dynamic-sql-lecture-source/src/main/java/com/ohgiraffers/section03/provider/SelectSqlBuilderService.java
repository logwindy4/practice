package com.ohgiraffers.section03.provider;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class SelectSqlBuilderService {
    private SelectSqlBuliderMapper mapper;
    public void testSimpleStatement(){
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SelectSqlBuliderMapper.class);

        List<MenuDTO> menuList = mapper.selectAllMenu();

        if(menuList != null && menuList.size() > 0){
            for (MenuDTO menu: menuList){
                System.out.println(menu);
            }
        }else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }
        sqlSession.close();
    }

    public void testDynamicStatment(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SelectSqlBuliderMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByCondition(searchCriteria);
        if(menuList != null && menuList.size() > 0){
            for (MenuDTO menu: menuList){
                System.out.println(menu);
            }
        }else {
            System.out.println("존재하지 않습니다");
        }
        sqlSession.close();
    }
}
