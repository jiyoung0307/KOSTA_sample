package com.example.smaple;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class TestRestController {
    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

//    안주면 기본으로 ResponseBody로 넘어감
//    Model도 의미 없음
    @GetMapping("/hello2")
    public String hello2(@RequestParam(value = "msg", required = true, defaultValue = "default hello") String msg) {
        return msg;
    }

    @PostMapping("/hello3/{id}")
    public String hello3(@PathVariable("id") int id) {
        return "hello " + id;
    }

    @GetMapping("/hello4")
    public HashMap<String, String> hello4(){
        HashMap<String, String> map = new HashMap<>();
        map.put("id","hong");
        map.put("name","홍길동");
        map.put("nationality","Korea");
        return map;
    }
}
