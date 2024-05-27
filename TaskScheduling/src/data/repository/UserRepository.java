package data.repository;

import data.model.UserModel;
import util.StringToArray;

import java.io.*;
import java.util.List;

public class UserRepository {
    private static final String route = "D:\\CODE\\Java OOP\\TaskScheduling\\src\\data\\database\\UserTable.txt";

    public static String PostUser(UserModel user) {
        try(BufferedWriter bfWriter = new BufferedWriter(new FileWriter(route, true))){
            if(user.getUsername().equals("") || user.getUsername().equals("")){
                return "Lengkapi semua data!";
            }
            bfWriter.write(user.getStringArray());
            bfWriter.newLine();
            return "Insert data success";
        } catch (Exception e){
            return e.getLocalizedMessage();
        }
    }

    public static String LoginUser(String username, String password){
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(route));
            String line;
            String result = "NOT FOUND";

            while((line = bfReader.readLine()) != null){
                String[] arr = StringToArray.convert(line);
                UserModel user = new UserModel(arr[0], arr[1], arr[2]);
                if(user.getUsername().replace(" ","").equals(username) && user.getPassword().replace(" ","").equals(password)){
                    result = line;
                }
            }

            System.out.println(result);
            return result;
        } catch (Exception e){
            return "ERROR";
        }
    }

    public static List<String> GetUsers(List<String> list){
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(route));
            String line;

            while((line = bfReader.readLine()) != null){
                list.add(line);
            }

            return list;
        } catch (Exception e){
            return list;
        }
    }

    public static String GetUserByID(String id){
        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(route));
            String line;
            String result = "";

            while((line = bfReader.readLine()) != null){
                if(line.contains(id)){
                    result = line;
                }
            }

            return result;
        } catch (Exception e){
            return "";
        }
    }
}
