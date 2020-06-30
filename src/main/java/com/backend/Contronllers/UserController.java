package com.backend.Contronllers;

import com.backend.Models.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @PostMapping("/login")
    public ApiResponse login(@RequestBody ){

    }
}
