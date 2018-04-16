package com.security.security.controller;

import com.security.security.configuration.annotation.AppRestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author prajyot on 16/4/18.
 * @project security.
 */
@AppRestController
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
}
