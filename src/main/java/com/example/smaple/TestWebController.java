package com.example.smaple;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestWebController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @GetMapping("/hello2")
    @ResponseBody
    public String hello2(@RequestParam("msg") String msg) {
        return msg;
    }

    //    삭제나 수정에 유용
    @PostMapping("/hello3/{id}")
    @ResponseBody
    public String hello3(@PathVariable("id") int id) {
        return "hello " + id;
    }

//    Model이 있다는 건 객체를 보내는 것이기 때문에 데이터를 보내는 구나~ 하고 알고 있으면 됨
    @GetMapping("/hello4")
    public String hello4(@RequestParam("msg") String msg, Model model) {
        model.addAttribute("msg", msg);
        return "hello";
    }
}
