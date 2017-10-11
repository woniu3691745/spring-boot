package com.lidl.httpclient;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloHttpClient {

    @RequestMapping(value = "get/{username}/{password}", method = RequestMethod.GET)
    public String successful(@PathVariable String username, @PathVariable String password){
        System.out.println("username is " + username);
        System.out.println("password is " + password);
        return "123456";
    }
}
