package com.example.monprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpUserActivity extends AppCompatActivity {

    // variables
    TextInputLayout textInputLayoutFirstName, textInputLayoutLastName, textInputLayoutEmail, textInputLayoutUsername, textInputLayoutPassword, textInputLayoutConfirmPassword,textInputLayoutLevel, textInputLayoutSchool, textInputLayoutLanguage, textInputLayoutBirthday, textInputLayoutCountry, textInputLayoutProfile;
    TextInputEditText textInputEditTextFirstName, textInputEditTextLastName, textInputEditTextEmail, textInputEditTextUsername, textInputEditTextPassword, textInputEditTextConfirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
    }

    public void login(View view){
        startActivity(new Intent(SignUpUserActivity.this, ConnexionActivity.class));

    }
}