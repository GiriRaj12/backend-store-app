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
import java.util.Collections;

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

    @PostMapping("/edit/store")
    public ApiResponse editStore(@RequestBody StoreModel storeModel){
        ApiResponse apiResponse = new ApiResponse();
        try{
            System.out.println(new Gson().toJson(storeModel));
            apiResponse.setResponse(new StoreService().editStore(storeModel));
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
            apiResponse.setDatas(Collections.singletonList(new StoreService().getAllStores()));
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/add")
    public ApiResponse addCategory(@PathParam("category") String category){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResponse(new StoreService().addCategory(category));
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/get/categoris")
    public ApiResponse geAllCategoris(){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResponse(true);
            apiResponse.setDatas(new StoreService().getAllCategory());
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

    @GetMapping("/add/rating")
    public ApiResponse addRating(@PathParam("storeId") String storeId, @PathParam("userName") String userName, @PathParam("rating") Integer rating){
        ApiResponse apiResponse = new ApiResponse();
        try{
            apiResponse.setResponse(new StoreService().addRating(storeId,userName, rating));
        }
        catch (Exception e){
            apiResponse.setResponse(false);
            apiResponse.setMessage(e.getMessage());
        }
        return apiResponse;
    }

}
