package edu.utsa.cs3443.universityeats;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private static List<User> users;

    static {
        users = new ArrayList<>();
    }
    public User() {
        // Default constructor
    }

    public static List<User> getUsers() {
        return users;
    }
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public static void addUser(User newUser, Context context){
        users.add(newUser);
        saveUsersToFile(context);

    }

    private static void saveUsersToFile(Context context) {
        String filename = "users.csv";

        try {
            FileOutputStream fileOutputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            //PrintWriter writer = new PrintWriter(new FileWriter(filename));
            PrintWriter writer = new PrintWriter(fileOutputStream);
            for(User user : users){
                writer.println(user.getUsername() + ", " + user.getPassword());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadUsers(Context context){
        AssetManager manager = context.getAssets();
        if(users == null)
            users = new ArrayList<>();
        Scanner scan = null;
        String filename = "users.csv";

        try {
            InputStream file = manager.open(filename);
            scan = new Scanner(file);
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] userInfo = line.split(",");
                if(userInfo.length >= 2){
                    String username = userInfo[0].trim();
                    String password = userInfo[1].trim();
                    Log.d("User", "Username: " + username + " Password: " + password);


                    User user = new User(username, password);
                    users.add(user);
                    Log.d("User", "Users loaded: " + users.size());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (scan != null) {
                scan.close();
            }
        }
    }

    /**
     * Our validate method.
     * @param users     Validate User
     * @param inputUsername     Validate the input from user for username.
     * @param inputPassword     Validate the input password from user.
     * @return
     */
    public static boolean validate(List<User> users, String inputUsername, String inputPassword) {
        for (User user : users) {
            if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                return true; // Successful
            }
        }
        return false; // No match
    }
}
