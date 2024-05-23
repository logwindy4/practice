package com.ohgiraffers.section03.remix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        MenuController menuController = new MenuController();
        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("============ 메뉴 관리 ==============");
            System.out.println("============ 1. 메뉴 전체 조회 ==============");
            System.out.println("============ 2. 메뉴 코드로 메뉴 조회 ==============");
            System.out.println("============ 1. 신규 메뉴 등록 ==============");
            System.out.println("============ 1. 메뉴 수정 ==============");
            System.out.println("============ 1. 메뉴 삭제 ==============");
            System.out.println("============ 메뉴 관리 번호를 선택하세요 : ==============");
            int no = sc.nextInt();
            
            switch (no){
                case 1 : menuController.selectAllMenu(); break;
                case 2 : menuController.selectMenuByCode(inputMenuCode()); break;
                case 3 : menuController.insertMenu(inputMenu()); break;
                case 4 : menuController.modifyMenu(inputModifyMenu()); break;
                case 5 : menuController.deletMenu(inputMenuCode()); break;
                default:
                    System.out.println("잘못된 번호입니다"); break;
            }
        }while (true);
    }

    private static Map<String, String> inputModifyMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 코드를 입력하세요");
        String code = sc.nextLine();
        System.out.print("수정할 코드를 입력하세요");
        String name = sc.nextLine();
        System.out.print("수정할 메뉴 가격을 입력하세요");
        String price = sc.nextLine();
        System.out.print("수정할 메뉴 카테고리를 입력하세요");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("name", name);
        parameter.put("price", price);
        parameter.put("categoryCode", categoryCode);

        return parameter;
    }

    private static Map<String, String> inputMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 이름을 입력하세요");
        String name = sc.nextLine();
        System.out.print("메뉴 가격을 입력하세요");
        String price = sc.nextLine();
        System.out.print("메뉴 카테고리를 입력하세요");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("name", name);
        parameter.put("price", price);
        parameter.put("categoryCode", categoryCode);

        return parameter;
    }

    private static Map<String, String> inputMenuCode() {
        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴 코드를 입력하세요 : ");
        String code = sc.nextLine();
        Map<String, String> parameter = new HashMap<>();
        parameter.put(code);
        return parameter;
    }
}
