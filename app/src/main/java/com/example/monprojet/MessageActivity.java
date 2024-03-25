package com.example.monprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.monprojet.adapters.ChatAdapter;
import com.example.monprojet.models.Chat;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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
    TextView textViewFirstName, textViewLastName;
    RecyclerView recyclerView;
    ShapeableImageView shapeableImageView;
    CircleImageView circleImageView;
    TextInputLayout textInputLayout;
    TextInputEditText textInputEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

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


}