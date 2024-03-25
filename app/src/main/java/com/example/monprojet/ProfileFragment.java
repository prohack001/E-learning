package com.example.monprojet;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

import static utils.FirebaseUtil.currentUserId;
import static utils.FirebaseUtil.database;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.monprojet.R.id;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import utils.FirebaseUtil;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    ShapeableImageView imageView;
    FloatingActionButton floatingActionButton;
    ImageButton imageButton;
    TextView nameTextView;
    TextView surnameTextView;
    TextView emailTextView;
    TextView logoutTextView;

    private TextView editProfileButton;
    private TextView deleteAccountBtn ;
    LinearLayout updateProfileLayout;
    LinearLayout notificationLayout;
    LinearLayout languageLayout;
    LinearLayout modeLayout;
    LinearLayout logoutLayout;
    LinearLayout deleteLayout;
    ImageView imageViewUpdateProfile;
    ImageView imageViewNotificationProfile;
    ImageView imageViewLanguageProfile;
    ImageView imageViewModeProfile;

    String currentName ;
    String currentSurname;
    String currentEmail;
    String currentPhone ;
    String currentSchool ;
    String currentLevel ;
    String currentBirthday ;

    FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseAuth auth;
    FirebaseDatabase database;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View rootView =  inflater.inflate(R.layout.fragment_profile, container, false);

         imageView = rootView.findViewById(R.id.image_view_profile_profile);
         imageViewUpdateProfile = rootView.findViewById(R.id.image_view_update_profile);
         imageViewNotificationProfile = rootView.findViewById(R.id.image_view_notification_profile);
         imageViewLanguageProfile = rootView.findViewById(R.id.image_view_language_profile);
         imageViewModeProfile = rootView.findViewById(R.id.image_view_mode_profile);
         imageButton = rootView.findViewById(id.image_button_back);


         floatingActionButton = rootView.findViewById(R.id.floating_action_button_profile);

         nameTextView = rootView.findViewById(R.id.text_view_name_profile);
         surnameTextView = rootView.findViewById(R.id.text_view_surname_profile);
         emailTextView = rootView.findViewById(R.id.text_view_email_profile);
         logoutTextView = rootView.findViewById(R.id.text_view_logout_profile);

        deleteAccountBtn=rootView.findViewById(id.text_view_delete_profile);
        editProfileButton = rootView.findViewById(R.id.edit_profile_button);

         updateProfileLayout = rootView.findViewById(R.id.linear_layout_update_profile);
         notificationLayout = rootView.findViewById(R.id.linear_layout_notification_profile);
         languageLayout = rootView.findViewById(R.id.linear_layout_language_profile);
         modeLayout = rootView.findViewById(R.id.linear_layout_mode_profile);
         logoutLayout = rootView.findViewById(R.id.linear_layout_logout_profile);
         deleteLayout = rootView.findViewById(R.id.linear_layout_delete_profile);


        auth = FirebaseAuth.getInstance();
         database = FirebaseDatabase.getInstance();

         String userId = auth.getUid();
        getUserData();

        database.getReference("Users")
                .child(userId)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String name = snapshot.child("name").getValue(String.class);
                                    String surname = snapshot.child("surname").getValue(String.class);
                                    String email = snapshot.child("email").getValue(String.class);
                                    String profileImageUrl = snapshot.child("profile").getValue(String.class);

                                    /*nameTextView.setText(name);
                                    surnameTextView.setText(surname);
                                    emailTextView.setText(email);*/
                                    Picasso.get().load(profileImageUrl).placeholder(R.drawable.baseline_account_circle_24).into(imageView);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("ProfileFragment", "Erreur de lecture des données utilisateur", error.toException());
                            }
                        });

         // back to home fragment
         imageButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 AccueilActivity accueilActivity = (AccueilActivity) getActivity();

                 if (accueilActivity != null) {
                     accueilActivity.switchToHomeFragment();
                 }

             }
         });


         // logout
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        // Delete


        //edit
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditProfileModal();
            }
        });

        // choose image profile on gallery or camera
         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 ImagePicker.with(ProfileFragment.this)
                         .crop()
                         .compress(1024)
                         .maxResultSize(1080, 1080)
                         .start();
             }
         });
         delete();
         return rootView;
    }

    // Choisir la photo de profile
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            uploadImageToFirebaseStorage(uri, "profil");
        }
    }

    public void delete() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // Ajouter un écouteur d'événements (listener) au bouton de suppression de compte
        deleteAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( currentUser !=null){
                    // Supprimer les données du compte utilisateur dans Firebase Realtime Database
                    String userId = currentUser.getUid();
                    FirebaseDatabase.getInstance().getReference("Users").child(userId).removeValue().addOnSuccessListener(aVoid -> {
                        // Supprimer le compte utilisateur dans Firebase Authentication
                        currentUser .delete().addOnSuccessListener(aVoid1 -> {
                            Toast.makeText(getContext(), "Compte supprimé avec succès", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(getContext(), "Échec de la suppression du compte", Toast.LENGTH_SHORT).show();
                        });
                    }).addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Échec de la suppression des données du compte", Toast.LENGTH_SHORT).show();
                    });
                } else{
                    Toast.makeText(getContext(), "Aucun utilisateur connecté", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    void getUserData(){

        FirebaseUtil.currentUserDetails().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method will be called once with the initial value and again
                // whenever data at this location is updated.

                currentName = dataSnapshot.child("name").getValue(String.class);
                currentSurname = dataSnapshot.child("surname").getValue(String.class);
                currentEmail = dataSnapshot.child("email").getValue(String.class);
                currentPhone = dataSnapshot.child("phone").getValue(String.class);
                currentSchool = dataSnapshot.child("school").getValue(String.class);
                currentLevel = dataSnapshot.child("level").getValue(String.class);
                currentBirthday = dataSnapshot.child("birthday").getValue(String.class);

                nameTextView.setText(currentName);
                surnameTextView.setText(currentSurname);
                emailTextView.setText(currentEmail);

                Object value = dataSnapshot.getValue();
                System.out.println("Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value." + error.toException());
            }
        });
    }

    private void uploadImageToFirebaseStorage(Uri imageUri, String imageName) {
        StorageReference storageRef = storage.getReference().child("images/" + imageName);

        storageRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();
                // Enregistrez l'URL de l'image dans Firebase
                saveImageUrlToFirebase(imageUrl);
            }).addOnFailureListener(e -> {
                Log.e(TAG, "Error getting image URL", e);
            });
        }).addOnFailureListener(e -> {
            Log.e(TAG, "Error uploading image", e);
        });
    }

    private void saveImageUrlToFirebase(String imageUrl) {


        String userId = currentUserId();
        database.getReference("Users").child(userId).child("profile").setValue(imageUrl);

    }





    private void showEditProfileModal() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_edit_profile);
        dialog.setTitle("Edit Profile");

        EditText editTextName = dialog.findViewById(R.id.edit_text_name);
        EditText editTextSurname = dialog.findViewById(R.id.edit_text_surname);
        EditText editTextEmail = dialog.findViewById(R.id.edit_text_email);
        EditText editTextPhone = dialog.findViewById(R.id.edit_text_phone);
        EditText editTextSchool = dialog.findViewById(R.id.edit_text_school);
        EditText editTextLevel = dialog.findViewById(R.id.edit_text_level);
        EditText editTextBirthday = dialog.findViewById(R.id.edit_text_birthday);
        Button buttonSave = dialog.findViewById(R.id.button_save);

        // Remplissez les EditText avec les valeurs actuelles de l'utilisateur

        editTextName.setText(currentName);
        editTextSurname.setText(currentSurname);
        editTextEmail.setText(currentEmail);
        editTextPhone.setText(currentPhone);
        editTextSchool.setText(currentSchool);
        editTextLevel.setText(currentLevel);
        editTextBirthday.setText(currentBirthday);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = editTextName.getText().toString().trim();
                String newSurname = editTextSurname.getText().toString().trim();
                String newEmail = editTextEmail.getText().toString().trim();
                String newPhone = editTextPhone.getText().toString().trim();
                String newSchool = editTextSchool.getText().toString().trim();
                String newLevel = editTextLevel.getText().toString().trim();
                String newBirthday = editTextBirthday.getText().toString().trim();

                if (!newName.isEmpty() && !newSurname.isEmpty() && !newEmail.isEmpty() && !newPhone.isEmpty() && !newSchool.isEmpty() && !newLevel.isEmpty() && !newBirthday.isEmpty()) {
                    // Mettre à jour les informations de l'utilisateur dans Firebase Realtime Database
                    String userId = currentUserId();
                    database.getReference("Users").child(userId).child("name").setValue(newName);
                    database.getReference("Users").child(userId).child("surname").setValue(newSurname);
                    database.getReference("Users").child(userId).child("email").setValue(newEmail);
                    database.getReference("Users").child(userId).child("phone").setValue(newPhone);
                    database.getReference("Users").child(userId).child("school").setValue(newSchool);
                    database.getReference("Users").child(userId).child("level").setValue(newLevel);
                    database.getReference("Users").child(userId).child("birthday").setValue(newBirthday).addOnSuccessListener(aVoid -> {
                      /*  Intent intent = getActivity().getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getActivity().finish();
                        startActivity(intent);*/
                        getUserData();

                        Toast.makeText(getContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }).addOnFailureListener(e -> {
                        Toast.makeText(getContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Toast.makeText(getContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                // Annuler la modification du profil
            }
        });
        Window window = dialog.getWindow();
        if (window != null) {
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        dialog.show();
    }

}