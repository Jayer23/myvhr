package com.jayer.vhr.controller;

import com.jayer.vhr.model.Hr;
import com.jayer.vhr.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @Autowired
    TestService ts;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin!";
    }
    @GetMapping("/user/hello")
    public String user(){
        return "hello user!";
    }
    @GetMapping("/db/hello")
    public String db(){
        return "hello db!";
    }
    @GetMapping("/hr/{id}")
    public String getHr(@PathVariable Integer id){
        Hr hr = ts.selectByPrimaryKey(id);
        String name = hr.getName();
        return name;
    }






}
