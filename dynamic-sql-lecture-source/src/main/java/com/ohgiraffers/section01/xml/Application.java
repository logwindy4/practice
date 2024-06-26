package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.SearchCriteria;

import java.util.*;


public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do{
            System.out.println("------마이바티스 동적 SQL(조건문)-------");
            System.out.println("1. if 확인하기");
            System.out.println("2. choose(when, otherwise) 확인하기");
            System.out.println("3. foreach 확인하기");
            System.out.println("4. trim(where, set) 확인하기");
            System.out.println("9. 종료하기");
            System.out.println("메뉴를 선택해주세요");

            int no = sc.nextInt();
            switch (no){
                case 1 : ifSubMenu(); break;
                case 2 : chooseSubMenu(); break;
                case 3 : foreachSubMenu(); break;
                case 4 : break;
                case 9 : System.out.println("프로그램을 종료합니다.");break;
            }
        }while (true);
    }

    private static void foreachSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do{
            System.out.println("=============foreach 서브 메뉴==============");
            System.out.println("1. 랜덤한 메뉴 5개를 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();
            switch (no){
                case 1: menuService.searchMenuByRandomMenuCode(creatRandomMenuCodeList()); break;
                case 2: return;
            }
        }while (true);
    }

    private static List<Integer> creatRandomMenuCodeList() {
        Set<Integer> set = new HashSet<>();
        while (set.size()<5){
            int temp = ((int)(Math.random()*21)) + 1;
            set.add(temp);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);

        return list;
    }

    private static void chooseSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do{
            System.out.println("===========choose 서브 메뉴============");
            System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기(식사, 음료, 디저트)");
            System.out.println("9. 이전메뉴로");
            System.out.println("메뉴 번호를 입력해주세요 : ");

            int no = sc.nextInt();

            switch (no){
                case 1 : menuService.searchMenuBySubCategory(inputSubCategory()); break;
                case 2 : return;
            }
        }while (true);
    }

    private static SearchCriteria inputSubCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("상위 분류를 입력하세요(식사, 음료, 디저트) : ");
        String value = sc.nextLine();

        return new SearchCriteria("category", value);
    }

    private static void ifSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do{
            System.out.println("=============if 서브메뉴 만들기=============");
            System.out.println("1. 원하는 금액대에 적합한 추천메뉴 목록 보여주기");
            System.out.println("2. 메뉴 이름 혹은 카테고리명으로 검색");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();
            switch (no){
                case 1 : menuService.selectMenuByPrice(inputPrice()); break;
                case 2 : menuService.searchMenu(inputSearchCriteria()); break;
                case 9 : return;
            }
        }while (true);
    }

    private static SearchCriteria inputSearchCriteria() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색 기준을 입력해주세요 : ");
        String condition = sc.nextLine();
        System.out.println("검색어를 입력해주세요 : ");
        String value = sc.nextLine();

        return new SearchCriteria(condition,value);
    }

    private static int inputPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색하실 가격대의 최대 금액을 입력해주세요");
        int price = sc.nextInt();
        return price;
    }
}
