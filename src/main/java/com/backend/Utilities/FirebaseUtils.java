package com.backend.Utilities;

import com.backend.Models.CategoryModel;
import com.backend.Models.StoreModel;
import com.backend.Models.UserModel;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class FirebaseUtils {

    private static Firestore db = null;

    static {
        try {
            FirebaseUtils.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/configurations/firebase.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://translater-d5a94.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    public boolean save(UserModel users){
        try {
            DocumentReference docRef = FirebaseUtils.db.collection("BackendUsers").document(users.getUserName());
            ApiFuture<WriteResult> result = docRef.set(users);
            System.out.println(result.get().getUpdateTime());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public UserModel getUser(String userName) {
        try {
            DocumentReference docRef = FirebaseUtils.db.collection("BackendUsers").document(userName);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if(document.exists())
                return document.toObject(UserModel.class);
            else
                return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean save(StoreModel storeModel){
        try {
            DocumentReference docRef = FirebaseUtils.db.collection("Stores").document(storeModel.getId());
            ApiFuture<WriteResult> result = docRef.set(storeModel);
            System.out.println(result.get().getUpdateTime());
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Map<String,StoreModel> getStores() {
        try {
            Map<String,StoreModel> resultMap = new HashMap<>();
            ApiFuture<QuerySnapshot> future =
                    db.collection("Stores").get();
            future.get().getDocuments().forEach(e -> addToMap(resultMap,e));
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
    public StoreModel getStores(String storeId) {
        try {
            DocumentReference docRef = FirebaseUtils.db.collection("Stores").document(storeId);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot documentSnapshot = future.get();
            if(documentSnapshot.exists())
               return documentSnapshot.toObject(StoreModel.class);
            else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addToMap(Map<String, StoreModel> resultMap, QueryDocumentSnapshot e) {
        StoreModel storeModel = e.toObject(StoreModel.class);
        resultMap.put(storeModel.getId(),storeModel);
    }

    public void addCategory(String category) throws ExecutionException, InterruptedException {
        DocumentReference docRef = FirebaseUtils.db.collection("Categories").document("Category");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if(document.exists()) {
            CategoryModel categoryModel = document.toObject(CategoryModel.class);
            categoryModel.getCategories().add(category);
            docRef.set(categoryModel);
        }
        else {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.setCategories(Collections.singletonList(category));
            docRef.set(categoryModel);
        }
    }

    public List<?> getAllCategories() throws ExecutionException, InterruptedException {
        DocumentReference docRef = FirebaseUtils.db.collection("Categories").document("Category");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if(document.exists())
           return document.toObject(CategoryModel.class).getCategories();
        else
            return new ArrayList<>();
    }
}