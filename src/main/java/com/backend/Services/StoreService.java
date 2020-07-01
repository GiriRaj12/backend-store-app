package com.backend.Services;

import com.backend.Models.StoreModel;
import com.backend.Utilities.FirebaseUtils;
import com.backend.Utilities.StringUtilites;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class StoreService {

    public boolean addStore(StoreModel storeModel){
        validateStoreModel(storeModel);
        return new FirebaseUtils().save(getStoreModel(storeModel));
    }

    public boolean editStore(StoreModel storeModel){
        validateStoreModel(storeModel);
        return new FirebaseUtils().save(storeModel);
    }

    public Map<String, StoreModel> getAllStores(){
        return new FirebaseUtils().getStores();
    }

    private StoreModel getStoreModel(StoreModel storeModel) {
        storeModel.setId(UUID.randomUUID().toString());
        return storeModel;
    }

    private void validateStoreModel(StoreModel storeModel) {
        if(!StringUtilites.isNotNullOrEmpty(storeModel.getAddress()))
            throwException("Store address cannot be null");
        if(!StringUtilites.isNotNullOrEmpty(storeModel.getCategories()))
            throwException("Store Categories cannot be null");
    }

    public void throwException(String message){
        throw new IllegalArgumentException(message);
    }

    public boolean addCategory(String category) throws ExecutionException, InterruptedException {
        new FirebaseUtils().addCategory(category);
        return true;
    }

    public List<?> getAllCategory() throws ExecutionException, InterruptedException {
        return  new FirebaseUtils().getAllCategories();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new StoreService().addCategory("Shopping Mall");
    }

    public boolean addRating(String storeId, String userName, Integer rating) {
        StoreModel storeModel = new FirebaseUtils().getStores(storeId);
        if(storeModel == null)
            throwException("Store not exists");

        if(storeModel.getRatingsMap() == null)
            storeModel.setRatingsMap(new HashMap<>());

        storeModel.getRatingsMap().put(userName,rating);
        new FirebaseUtils().save(storeModel);
        return true;
    }
}
