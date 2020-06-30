package com.backend.Contronllers;

import com.backend.Models.ApiResponse;
import com.backend.Models.StoreModel;
import com.backend.Services.StoreService;
import com.backend.Services.UserService;
import com.backend.Utilities.FirebaseUtils;
import com.backend.Utilities.StringUtilites;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin
@Controller
@RestController
public class StoreController {
    @PostMapping("/add/store")
    public ApiResponse addStore(@RequestBody StoreModel storeModel){
        ApiResponse apiResponse = new ApiResponse();
        try{
            System.out.println(new Gson().toJson(storeModel));
            apiResponse.setResponse(new StoreService().addStore(storeModel));
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/get/stores")
    public ApiResponse getAllStores(){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResponse(true);
            apiResponse.setDatas(new StoreService().getAllStores());
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }



//    @PostMapping("/add/rating")
//    public ApiResponse addStore(@PathParam("userName")){
//        ApiResponse apiResponse = new ApiResponse();
//        try{
//            apiResponse.setResponse(new StoreService().addStore(storeModel));
//        }
//        catch (Exception e){
//            apiResponse.setResponse(false);
//            apiResponse.setData(e.getMessage());
//        }
//        return apiResponse;
//    }
//
//    @GetMapping("/add/bookmark")
//    public ApiResponse addBookMark(@PathParam("username") String userName, @PathParam("id") String id){
//        ApiResponse apiResponse = new ApiResponse();
//        try{
//            apiResponse.setResponse();
//        }
//        catch (Exception e){
//            apiResponse.setResponse(false);
//            apiResponse.setData(e.getMessage());
//        }
//        return apiResponse;
//    }

}
