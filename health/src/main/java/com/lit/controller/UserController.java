package com.lit.controller;

import com.lit.entity.R;
import com.lit.service.SpringSecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {
    @RequestMapping("toMain")
    public String toMain() {
        System.out.println("888");
        return "redirect:/pages/main.html";
    }


}
