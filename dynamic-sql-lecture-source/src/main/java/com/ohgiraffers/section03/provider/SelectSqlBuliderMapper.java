package com.ohgiraffers.section03.provider;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SelectSqlBuliderMapper {

    @Results(id = "menuResultMap", value = {
            @Result(id=true, property = "code", column = "MENU_CODE"),
            @Result(property = "name", column = "MENU_NAME"),
            @Result(property = "price", column = "MENU_PRICE"),
            @Result(property = "categoryCode", column = "CATEGORY_CODE"),
            @Result(property = "orderableStatus", column = "ORDERBLE_STATUS")
    })
    @SelectProvider(type = SelectBilderProvider.class , method = "selectAllMenu")
    List<MenuDTO> selectAllMenu();

    @ResultMap("menuResultMap")
    @SelectProvider(type = SelectBilderProvider.class , method = "searchMenuByCondition")
    List<MenuDTO> searchMenuByCondition(SearchCriteria searchCriteria);
}
