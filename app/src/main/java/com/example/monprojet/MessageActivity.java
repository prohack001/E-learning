package com.example.monprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.monprojet.adapters.ChatAdapter;
import com.example.monprojet.models.Chat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    ImageButton back;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    TextView textViewFirstName, textViewLastName;
    RecyclerView recyclerView;
    ShapeableImageView shapeableImageView;
    CircleImageView circleImageView;
    TextInputLayout textInputLayout;

    private FusedLocationProviderClient fusedLocationClient;
    TextInputEditText textInputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        back = findViewById(R.id.image_button_back_user);
        textViewFirstName = findViewById(R.id.text_view_firstname_user);
        textViewLastName = findViewById(R.id.text_view_lastname_user);
        recyclerView = findViewById(R.id.recycler_view_message_user);
        shapeableImageView = findViewById(R.id.shapeable_image_view_user);
        circleImageView = findViewById(R.id.circle_image_view_send_message_btn);
        textInputLayout = findViewById(R.id.text_input_layout_message_user);
        textInputEditText = findViewById(R.id.text_input_edit_text_message_user);


        back.setOnClickListener(e-> finish());

        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        String senderId = auth.getUid();
        String receiverId = getIntent().getStringExtra("userId");
        String name = getIntent().getStringExtra("name");
        String surname = getIntent().getStringExtra("surname");
        String profile = getIntent().getStringExtra("profile");

        textViewFirstName.setText(surname);
        textViewLastName.setText(name);
        Picasso.get().load(profile).placeholder(R.drawable.baseline_account_circle_24).into(shapeableImageView);;

        final ArrayList<Chat>  chats = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(chats, this, receiverId);

        recyclerView.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final String senderRoom = senderId+receiverId;
        final  String receiverRoom = receiverId+senderId;

        firebaseDatabase.getReference()
                        .child("Chats")
                        .child(senderRoom)
                        .addValueEventListener(new ValueEventListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                chats.clear();
                                for (DataSnapshot snapshot1: snapshot.getChildren()){
                                    Chat chat = snapshot1.getValue(Chat.class);
                                    chat.setChatId(snapshot1.getKey());
                                    chats.add(chat);
                                }
                                chatAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
        findViewById(R.id.getLocationButton).setOnClickListener(v -> getLocation());

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = textInputEditText.getText().toString();
                final Chat chat = new Chat(senderId, message);
                chat.setTimestamp(new Date().getTime());
                textInputEditText.setText("");

                firebaseDatabase.getReference().child("Chats")
                        .child(senderRoom)
                        .push()
                        .setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                firebaseDatabase.getReference().child("Chats")
                                        .child(receiverRoom)
                                        .push()
                                        .setValue(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }
                        });
            }
        });

    }

    private void getLocation() {

        checkLocationServices();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            //textInputEditText.setText("2");
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) { //
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        String url = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;

                        textInputEditText.setText(url);
                        //textInputEditText.setText("test");
                    }
                    //textInputEditText.setText("3");
                }
            });
        } else {
            // Demandez les autorisations de localisation si elles ne sont pas accordées
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
            //textInputEditText.setText("ici");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // L'autorisation a été accordée, vous pouvez maintenant récupérer la localisation
                getLocation();
            } else {
                // L'autorisation a été refusée, vous pouvez afficher un message ou gérer cette situation selon vos besoins
            }
        }
    }



    private void checkLocationServices() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            new AlertDialog.Builder(this)
                    .setTitle("Activer les services de localisation")
                    .setMessage("Les services de localisation doivent être activés pour utiliser cette fonctionnalité. Voulez-vous activer les services de localisation ?")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // L'utilisateur ne souhaite pas activer les services de localisation, vous pouvez gérer cette situation selon vos besoins
                        }
                    })
                    .show();
        }
    }



}