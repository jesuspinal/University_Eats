package edu.utsa.cs3443.universityeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText username;

    EditText password;

    Button loginButton;

    private static final String KEY_USER_EMAIL = "userEmail";
    private static final String PREFS_NAME = "userPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String enteredEmail = username.getText().toString();
            if(username.getText().toString().contains("my.utsa.edu") && password.getText().toString().equals("1234")){
                saveUserEmailToPreferences(enteredEmail);
                launchActivityHome();

            }
            else {
                Toast.makeText(MainActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void saveUserEmailToPreferences(String userEmail) {
        SharedPreferences preferences = getSharedPreferences("UserPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_email", userEmail);
        editor.apply();

        // Add a log statement or toast to check the value
        Log.d("MainActivity", "User email saved: " + userEmail);
        // or
        Toast.makeText(MainActivity.this, "User saved: " + userEmail, Toast.LENGTH_SHORT).show();
    }






    private void launchActivityHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

}