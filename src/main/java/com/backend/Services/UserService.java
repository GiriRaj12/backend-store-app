package com.backend.Services;

import com.backend.Models.UserDTO;
import com.backend.Models.UserModel;
import com.backend.Utilities.FirebaseUtils;
import com.backend.Utilities.StringUtilites;
import com.google.gson.Gson;

public class UserService {
    public boolean loginService(UserDTO userDTO){
        System.out.println(new Gson().toJson(userDTO));
        validateUserDTO(userDTO);
        UserModel userModel = new FirebaseUtils().getUser(userDTO.getUserName());
        if(userModel == null)
            throw new IllegalArgumentException((userDTO.isAdmin() ? "Admin" : "User") +" not exists");
        else{
            if(userDTO.isAdmin() != userModel.isAdminLogin())
                throw new IllegalArgumentException("You are not allowed to proceed with this login");
            if(!userDTO.getPassWord().equals(userModel.getPassWord()))
                throw new IllegalArgumentException("Invalid Credentials");
        }
        return true;
    }

    private void validateUserDTO(UserDTO userDTO) {
        if(!StringUtilites.isNotNullOrEmpty(userDTO.getUserName()))
            throw new IllegalArgumentException("User Name cannot be null");

        if(!StringUtilites.isNotNullOrEmpty(userDTO.getPassWord()))
            throw new IllegalArgumentException("Password cannot be null");
    }

    public boolean addUserAndAdmin(){
        UserModel userModel = new UserModel();
        FirebaseUtils fireBaseUtils = new FirebaseUtils();
        fireBaseUtils.save(saveAdmin(userModel));
        fireBaseUtils.save(saveUser(userModel));
        return true;
    }

    private UserModel saveAdmin(UserModel userModel){
        userModel.setAdminLogin(true);
        userModel.setPassWord("admin@1234");
        userModel.setUserName("admin@app.com");
        return userModel;
    }

    private UserModel saveUser(UserModel userModel){
        userModel.setAdminLogin(false);
        userModel.setPassWord("user@1234");
        userModel.setUserName("user1@app.com");
        return userModel;
    }
}
