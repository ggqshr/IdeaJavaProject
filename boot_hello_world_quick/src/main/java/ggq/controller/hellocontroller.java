package ggq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hellocontroller {
    @RequestMapping(path = "/hello")
    public String hello() {
        return "hello world";
    }
}
