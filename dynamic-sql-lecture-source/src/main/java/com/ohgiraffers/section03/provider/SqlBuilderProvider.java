package com.ohgiraffers.section03.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.jdbc.SQL;

public class SqlBuilderProvider {

    public String registMenu(MenuDTO menu){
        SQL sql = new SQL();

         sql.INSERT_INTO("TBL_MENU")
            .VALUES("MENU_NAME", "#{ name }")
                 .VALUES("MENU_PRICE", "#{ price }")
                 .VALUES("CATEGORY_CODE", "#{ categoryCode }")
                 .VALUES("ORDERABLE_STATUS", "#{ orderableStatus }");
        System.out.println("sql = " + sql);
                 return sql.toString();
    }

    public String modifyMenu(MenuDTO menu){
        SQL sql = new SQL();

//        sql.UPDATE("TBL_MENU")
//                .SET("MENU_NAME = #{name}",
//                        "MENU_PRICE = #{price}",
//                        "CATEGORY_CODE = #{categoryCode}",
//                        "ORDERABLE_STATUS = #{orderableStatus}")
//                .WHERE("MENU_CODE = #{code}");
            sql.UPDATE("TBL_MENU");
            if(menu.getName() != null && !"".equals(menu.getName())){
                sql.SET("MENU_NAME = #{name}");
            }
            if(menu.getPrice() > 0){
                sql.SET("MENU_PRICE = #{price}");
            }
            if(menu.getCategoryCode() > 0){
                sql.SET("CATEGORY_CODE = #{categoryCode}");
            }
            if(menu.getOrderableStatus() != null && !"".equals(menu.getOrderableStatus())){
                sql.SET("ORDERABLE_STATUS = #{orderableStatus}");
            }
            return sql.toString();
    }

    public String deleteMenu(){
        SQL sql = new SQL();

        sql.DELETE_FROM("TBL_MENU")
                .WHERE("MENU_CODE = #{code}" );
    return sql.toString();
    }
}
