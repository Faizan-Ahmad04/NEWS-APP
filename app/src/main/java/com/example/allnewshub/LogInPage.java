package com.example.allnewshub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends AppCompatActivity {

    EditText uEmail, uPassword;
    Button loginButton;

    DBHelper DB;
    public static int hide =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        Intent intent = getIntent();

        uEmail = findViewById(R.id.userEmail);
        uPassword = findViewById(R.id.userPassword);
        loginButton = findViewById(R.id.mainLoginButton);

        DB = new DBHelper(this);

    }

    public void onSignupClicked(View view) {
        // Build an Intent to open another activity
        hide=-1;
        Intent signupIntent = new Intent(this,SignUpPage.class);
        startActivity(signupIntent);
    }

    public void onMainLoginClicked(View view) {
        hide=-1;

        String email = uEmail.getText().toString();
        String pass = uPassword.getText().toString();

        if(email.equals("") || pass.equals("")) {
            Toast.makeText(this, "Please enter all field", Toast.LENGTH_SHORT).show();
        } else {
            Boolean checkuserpass = DB.checkusernamepassword(email, pass);
            if(checkuserpass == true) {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,NewsCategoriesActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
            }
        }

    }
}