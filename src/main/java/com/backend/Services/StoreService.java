package com.backend.Services;

import com.backend.Models.StoreModel;
import com.backend.Utilities.FirebaseUtils;
import com.backend.Utilities.StringUtilites;

import java.util.List;
import java.util.UUID;

public class StoreService {

    public boolean addStore(StoreModel storeModel){
        validateStoreModel(storeModel);
        return new FirebaseUtils().save(getStoreModel(storeModel));
    }

    public List<StoreModel> getAllStores(){
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
}
