package com.backend.Contronllers;

import com.backend.Models.ApiResponse;
import com.backend.Models.UserDTO;
import com.backend.Services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RestController
@CrossOrigin
public class UserController {

    @GetMapping("/")
    public String init(){
        return "Hello World";
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody UserDTO userDTO){
        ApiResponse apiResponse = new ApiResponse();
        try{
           apiResponse.setResponse(true);
           apiResponse.setDatas(Collections.singletonList(new UserService().loginService(userDTO)));
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/register")
    public ApiResponse registerUser(){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResponse(new UserService().addUserAndAdmin());
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }
}
