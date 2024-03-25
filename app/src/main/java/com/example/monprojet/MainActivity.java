package com.example.monprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monprojet.databinding.ActivityMainBinding;
import com.example.monprojet.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
//    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private ProgressBar progressBar;
    private Button btnLogin;
    TextView signUp, textViewForgotten;
    private FirebaseAuth mAuth;

    private ActivityMainBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textInputEditTextEmail = findViewById(R.id.text_input_edit_text_email_connexion);
//        textInputEditTextPassword = findViewById(R.id.text_input_edit_text_password_connexion);

        textInputLayoutEmail = findViewById(R.id.text_input_layout_email_connexion);
        textInputLayoutPassword = findViewById(R.id.text_input_layout_password_connexion);

        progressBar = findViewById(R.id.progress_bar_connexion);

        mAuth =FirebaseAuth.getInstance();


        signUp = findViewById(R.id.text_view_btn_create_account_connexion);

        btnLogin = findViewById(R.id.button_login_connexion);

        textViewForgotten = findViewById(R.id.text_view_btn_forgotten_connexion);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputLayoutEmail.getEditText().getText().toString().trim();
                String password = textInputLayoutPassword.getEditText().getText().toString().trim();

//                String mail = textInputEditTextEmail.getText().toString().trim();
//                String pw = textInputEditTextPassword.getText().toString().trim();

                // Vérifier si l'email et le mot de passe ne sont pas vides
                if (email.isEmpty() || password.isEmpty()) {
                    // Afficher un message d'erreur si l'email ou le mot de passe est vide
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Afficher la barre de progression pendant la tentative de connexion
                    progressBar.setVisibility(View.VISIBLE);

                    // Authentifier l'utilisateur avec l'email et le mot de passe
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // Masquer la barre de progression après la tentative de connexion
                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        // La connexion est réussie, rediriger l'utilisateur vers l'activité principale
                                        startActivity(new Intent(MainActivity.this, AccueilActivity.class));
                                        finish();
                                    } else {
                                        // Une erreur s'est produite lors de la tentative de connexion, afficher un message d'erreur
                                        Toast.makeText(MainActivity.this, "Échec de la connexion. Vérifiez vos identifiants.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

    }
}