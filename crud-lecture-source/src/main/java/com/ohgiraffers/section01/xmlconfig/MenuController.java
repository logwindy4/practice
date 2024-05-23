package com.ohgiraffers.section01.xmlconfig;

import java.util.List;
import java.util.Map;

public class MenuController {

    private final PrintResult printResult;
    private final MenuService menuService;

    public MenuController() {
        printResult = new PrintResult();
        menuService = new MenuService();
    }

    public static void selectMenuByCode(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));

        MenuDTO menu = menuService.selectMenuByCode(code);

        if(menu != null){
            printResult.
        }
    }

    public void selectAllMenu() {
        List<MenuDTO> menuList = menuService.selectAllmenu();

        if(menuList != null) {
            printResult.printMenuList(menuList);
        }else{
            printResult.printErrorMessage("selectList");
        }
    }

    public void insertMenu(Map<String, String> parameter) {
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setName(name);
        menu.setCode(price);
        menu.setCategoryCode(categoryCode);

        System.out.println("menu = " + menu);

        if(menuService.insertMenu(menu)){
            printResult.printSuccessMessage("insert");

        }else{
            printResult.printErrorMessage("insert");
        }
    }

    public void modifyMenu(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setCode(code);
        menu.setName(name);
        menu.setCode(price);
        menu.setCategoryCode(categoryCode);

        System.out.println("menu = " + menu);

        if(menuService.updateMenu(menu)){
            printResult.printSuccessMessage("update");

        }else{
            printResult.printErrorMessage("update");
        }
    }

    public void deletMenu(Map<String, String> parameter) {
        int code = Integer.parseInt(parameter.get("code"));

        if(menuService.deleteMenu(code)){
            printResult.printSuccessMessage("update");

        }else{
            printResult.printErrorMessage("update");
        }
    }
}