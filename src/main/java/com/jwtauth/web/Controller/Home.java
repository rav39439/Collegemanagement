package com.jwtauth.web.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://collegecoursemanagement.netlify.app/")

public class Home {


    @RequestMapping("/")
    public String welcome() {
        String text = "this is private page ";
        return text;
    }

   
//    @RequestMapping("/getusers")
//    public String getUser() {
//        return "{\"name\":\"Ravish\"}";
//    }

}
