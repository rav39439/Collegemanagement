package com.jwtauth.web.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class Home {

    @RequestMapping("/welcome")
    public String welcome() {
        String text = "this is private page ";
        text+= "this page is not allowed to unauthenticated users";
        return text;
    }

   
    @RequestMapping("/getusers")
    public String getUser() {
        return "{\"name\":\"Ravish\"}";
    }

}
