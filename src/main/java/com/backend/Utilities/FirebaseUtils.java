package com.backend.Utilities;

import com.backend.Models.StoreModel;
import com.backend.Models.UserModel;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public List<StoreModel> getStores() {
        try {
            ApiFuture<QuerySnapshot> future =
                    db.collection("Stores").get();
            return future.get().getDocuments().stream().map(e -> e.toObject(StoreModel.class)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}