package com.example.allnewshub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
   EditText name, email, password, cPassword;
   Button signupButton;

   DBHelper DB;
    public void onLoginClicked(View view) {
        // Build an Intent to open another activity
        Intent loginIntent = new Intent(this,LogInPage.class);
        startActivity(loginIntent);
    }

    public void onSignupClicked(View view) {
        String user = email.getText().toString();
        String pass = password.getText().toString();
        String repass = cPassword.getText().toString();

        if(user.equals("") || pass.equals("") || repass.equals("") ){
            Toast.makeText(this, "Please Enter All Area", Toast.LENGTH_SHORT).show();
        } else {
            if(pass.equals(repass)) {
                Boolean checkuser = DB.checkusername(user);
                if(checkuser == false) {
                    Boolean insert = DB.insertData(user, pass);
                    if(insert == true) {
                        Toast.makeText(this, "Successfully Signup", Toast.LENGTH_SHORT).show();
                        Intent signupIntent = new Intent(this, LogInPage.class);
                        startActivity(signupIntent);
                    } else {
                        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "user already exist please login", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "password not match", Toast.LENGTH_SHORT).show();
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.userPassword);
        cPassword = findViewById(R.id.confrimPassword);
        signupButton = findViewById(R.id.signup_button);

        DB = new DBHelper(this);

    }
}