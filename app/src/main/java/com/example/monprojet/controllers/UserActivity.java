package com.example.monprojet.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.monprojet.R;
import com.example.monprojet.models.User;

public class UserActivity extends AppCompatActivity {
    private User mUser;
    private Button mSaveButton;
    private EditText mNomInput, mPrenomInput, mEmailInput, mPasswordInput, mCpasswordInput, mPhoneInput, mBirthdayInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
}