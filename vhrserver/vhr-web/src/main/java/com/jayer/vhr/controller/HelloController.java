package com.jayer.vhr.controller;

import com.jayer.vhr.model.Hr;
import com.jayer.vhr.model.Menu;
import com.jayer.vhr.service.MenuService;
import com.jayer.vhr.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {
    @Autowired
    TestService ts;
    @Autowired
    MenuService ms;
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
    @GetMapping("/helloMenu")
    public List<Menu> getAllMenusWithRoles(){
        List<Menu> menus= ms.getMenusByHrId();
//        Menu menu = menus.get(0);
        return menus;
    }
//
//    @GetMapping("/employee/basic")
//    public String helloEmployee(){
//        return "Hello employee!";
//    }
    @GetMapping("/personnel/emp")
    public String helloAdmin(){
        return "Hello admin!";
    }




}
