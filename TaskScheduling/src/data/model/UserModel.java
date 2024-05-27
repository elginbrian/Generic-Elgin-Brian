package data.model;

import java.util.Arrays;
import java.util.UUID;

public class UserModel {
    private String userID = UUID.randomUUID().toString();
    private String username;
    private String password;

    public UserModel(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserModel(String userID, String username, String password){
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    public String getUserID(){
        return this.userID;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void updateData(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getStringArray(){
        String[] userArr = {this.userID, this.username, this.password};
        String userStringArr = Arrays.toString(userArr);

        return userStringArr;
    }
}
