package com.sparta.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovePageController {

    @GetMapping("/post/detail")
    public String detailForm() {
        return "detail";
    }

    @GetMapping("/post/modify")
    public String modifyForm() {return "modify"; }
}
