package com.ohgiraffers.section03.provider;

import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.jdbc.SQL;

public class SelectBilderProvider {
    public String selectAllMenu(){
        return new SQL()
                .SELECT("A.MENU_CODE")
                .SELECT("A.MENU_NAME")
                .SELECT("A.MENU_PRICE")
                .SELECT("A.CATEGORY_CODE")
                .SELECT("ORDERABLE_STATUS")
                .FROM("TBL_MENU A")
                .WHERE("ORDERABLE_STATUS = 'Y'").toString();
    }

    public String searchMenuByCondition(SearchCriteria searchCriteria){
        SQL sql = new SQL();
        sql.SELECT("A.MENU_CODE")
           .SELECT("A.MENU_NAME")
           .SELECT("A.MENU_PRICE")
           .SELECT("A.CATEGORY_CODE")
           .SELECT("ORDERABLE_STATUS")
           .FROM("TBL_MENU A");
        if ("category".equals(searchCriteria.getCondition())){
            sql.JOIN("TBL_GATEGORY B ON (A.CATEGORY_CODE = B.CATEGORY_CODE)")
                    .WHERE("ORDERABLE_STATUS = 'Y'")
                    .AND()
                    .WHERE("B.CATEGORY_NAME = #{ value }");
        }else if("name".equals(searchCriteria.getCondition())){
            sql.WHERE("ORDERABLE_STATUS = 'Y'", "A.MENU_NAME LIKE CONCAT ('%', #{ value }, '%')");

        }
        System.out.println("sql = " +sql);
        return sql.toString();
    }
}
