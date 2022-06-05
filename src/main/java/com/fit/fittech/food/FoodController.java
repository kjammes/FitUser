package com.fit.fittech.food;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/food")
public class FoodController {

    @GetMapping("/ola")
    public String sayHello() {
        return "Ola";
    }
}
