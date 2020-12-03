package com.learn.controller;
import com.learn.model.A_0001;
import com.learn.services.A_0001Sv;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 *@author Ay
 * @date 2018/04/02
 */
@Controller
@RequestMapping(value = "/test")
public class Test {

    @Resource
    A_0001Sv a_0001Sv;

    @GetMapping("/sayHello")
    public String sayHello(Model model){
        return "hello";
    }

    @GetMapping("/findAll")
    public String findAll(Model model){
        List<A_0001> aList = a_0001Sv.findAll();
        for(A_0001 a_0001 : aList){
            System.out.println("id: " + a_0001.getA1());
        }
        return "hello";
    }
}
