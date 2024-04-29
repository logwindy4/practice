package com.example.ajax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AjaxViewController {
    @GetMapping("/ajax-ex-01")  // html 파일과 이름이 같아야 한다
    public String ajaxEx01() {
        return "ajax-ex-01";
    }
}
