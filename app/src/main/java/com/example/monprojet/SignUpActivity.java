package com.example.monprojet;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.monprojet.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    TextView login;
    private TextInputEditText editTextName, editTextSurname, editTextEmail, editTextPassword, editTextConfirmPassword, editTextPhone, editTextBirthday, editTextSchool, editTextLevel;
    private Button buttonSignUp;
    private ShapeableImageView profile;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private FirebaseStorage storage;
    private ActivitySignUpBinding binding;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = findViewById(R.id.text_input_edit_text_name_signUp);
        editTextSurname = findViewById(R.id.text_input_edit_text_surname_signUp);
        editTextEmail = findViewById(R.id.text_input_edit_text_email_signUp);
        editTextPassword = findViewById(R.id.text_input_edit_text_password_signUp);
        editTextConfirmPassword = findViewById(R.id.text_input_edit_text_confirm_password_signUp);
        editTextPhone = findViewById(R.id.text_input_edit_text_phone_signUp);
        editTextBirthday = findViewById(R.id.text_input_edit_text_birthday_signUp);
        editTextSchool = findViewById(R.id.text_input_edit_text_school_signUp);
        editTextLevel = findViewById(R.id.text_input_edit_text_level_signUp);
        buttonSignUp = findViewById(R.id.button_signUp_signUp);
        profile = findViewById(R.id.image_view_signUp);



        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance("gs://e-learning-e62ac.appspot.com");
        StorageReference storageRef = storage.getReference();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://e-learning-e62ac-default-rtdb.firebaseio.com/");
        FirebaseDatabase database;
        DatabaseReference reference;

        login = findViewById(R.id.text_view_btn_connected_signUp);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");
        buttonSignUp.setOnClickListener(view -> {
            final String name = editTextName.getText().toString().trim();
            final String surname = editTextSurname.getText().toString().trim();
            final String email = editTextEmail.getText().toString().trim();
            final String password = editTextPassword.getText().toString().trim();
            String confirmPassword = editTextConfirmPassword.getText().toString().trim();
            final String phone = editTextPhone.getText().toString().trim();
            final String birthday = editTextBirthday.getText().toString().trim();
            final String school = editTextSchool.getText().toString().trim();
            final String level = editTextLevel.getText().toString().trim();

            // Vérifiez que les champs ne sont pas vides
            if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phone.isEmpty() || birthday.isEmpty() || school.isEmpty() || level.isEmpty()) {
                Toast.makeText(SignUpActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(SignUpActivity.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            } else {
                // Créer l'utilisateur avec l'email et le mot de passe



                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, task -> {
                            if (task.isSuccessful()) {

                                // L'utilisateur a été créé avec succès
                                Toast.makeText(SignUpActivity.this, "Utilisateur créé avec succès", Toast.LENGTH_SHORT).show();

                                // Récupérer l'ID de l'utilisateur créé
                                String userId = firebaseAuth.getCurrentUser().getUid();
                                HelperClass helperClass = new HelperClass( name,  surname,  email,  phone, school,  level, birthday);
                                reference.child(userId).setValue(helperClass);
                                Toast.makeText(SignUpActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                // Créer un nouvel utilisateur avec les autres informations dans Firestore



                            } else {
                                // Erreur lors de la création de l'utilisateur
                                Toast.makeText(SignUpActivity.this, "Erreur lors de la création de l'utilisateur", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        // Lancer la page de connexion
        login.setOnClickListener(v -> startActivity(new Intent(SignUpActivity.this, MainActivity.class)));

    }
}