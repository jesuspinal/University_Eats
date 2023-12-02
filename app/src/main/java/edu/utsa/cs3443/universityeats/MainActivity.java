/**
 * Jesus Pinales owj958
 * MainActivity handles user login and registration
 */
package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usernameEditText;
    private EditText passwordEditText;

    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String PREFS_NAME = "userPrefs";

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            usernameEditText = findViewById(R.id.username);
            passwordEditText = findViewById(R.id.password);
            Button loginButton = findViewById(R.id.loginButton);
            Button signupButton = findViewById(R.id.signupButton);

            loginButton.setOnClickListener(this);
            signupButton.setOnClickListener(this);



        // Initialize user data
            new User().loadUsers(this);

        }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Username or Password cannot be empty", Toast.LENGTH_LONG).show();
                return;
            }
            List<User> users = User.getUsers();
            //Validate User
            if(User.validate(users, username, password)){

                    //Success, Create session, Next window
                    User loggedInUser = findUserByUsername(users, username);
                    SessionManager.setLoggedInUser(loggedInUser);
                    passwordEditText.setText("");
                    saveUserEmailToPreferences(username);
                    launchActivityHome();

            } else {
                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId() == R.id.signupButton){
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "To Register Type Your Email, Then Desired Password And Press Register Again", Toast.LENGTH_SHORT).show();
                return;
            }
            //Check if user exists
            List<User> users = User.getUsers();
            if(findUserByUsername(users, username) != null){
                Toast.makeText(this, "Account already exists. Check password and try again.", Toast.LENGTH_SHORT).show();
                return;
            }
            //Register new user

            User newUser = new User(username, password);
            User.addUser(newUser, this);
            saveUserEmailToPreferences(username);


            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

            usernameEditText.setText("");
            passwordEditText.setText("");
            Log.d("MainActivity", "Number of users after registration: " + User.getUsers().size());

        }
    }

        private User findUserByUsername (List <User> users, String username){
            for (User user : users) {
                if (user.getUsername() != null && user.getUsername().equals(username)) {
                    return user;
                }
            }
            return null; // User not found
        }

        private void launchActivityHome () {
            Intent intent = new Intent(this, ScrollHomeActivity.class);
            startActivity(intent);
        }

    private void saveUserEmailToPreferences(String userEmail) {
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_email", userEmail);
        editor.apply();


        Log.d("MainActivity", "User email saved: " + userEmail);
        Toast.makeText(MainActivity.this, "User saved: " + userEmail, Toast.LENGTH_SHORT).show();
    }


}