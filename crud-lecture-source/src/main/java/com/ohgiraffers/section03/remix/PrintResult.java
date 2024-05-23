package com.ohgiraffers.section03.remix;

import java.util.List;

class PrintResult {
    public void printMenuList(List<MenuDTO> menuList) {
        for(MenuDTO menu : menuList) {
            System.out.println("Menu Code: " + menu.getCode());
            System.out.println("Menu Name: " + menu.getName());
            System.out.println("Menu Price: " + menu.getPrice());
            System.out.println("Category Code: " + menu.getCategoryCode());
            System.out.println("Orderable Status: " + menu.getOrderblestatus());
            System.out.println();
        }
    }

    public void printErrorMessage(String selectList) {
        System.out.println();
    }

    public void printSuccessMessage(String successCode) {
        String successMessage = "";
        switch (successCode){
            case "insert" : successMessage = "신규 메뉴 등록완료"; break;
            case "update" : successMessage = "메뉴 수정 완료"; break;
            case "delete" : successMessage = "메뉴 삭제 완료"; break;
        }
    }
}