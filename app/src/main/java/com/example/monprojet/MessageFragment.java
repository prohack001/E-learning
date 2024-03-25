package com.example.monprojet;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.monprojet.adapters.UsersAdapter;
import com.example.monprojet.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<User> list = new ArrayList<>();
    FirebaseDatabase db;
    ImageButton imageButton;

    public MessageFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_message, container, false);

        db = FirebaseDatabase.getInstance();

        imageButton = rootView.findViewById(R.id.image_button_back_message);
        recyclerView = rootView.findViewById(R.id.recycler_view_message_message);


        UsersAdapter adapter = new UsersAdapter(list, getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        db.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User users = dataSnapshot.getValue(User.class);
                    users.setUserId(dataSnapshot.getKey());

                    if (! users.getUserId().equals(FirebaseAuth.getInstance().getUid())){
                        list.add(users);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        // recyclerView for message
//        recyclerView = rootView.findViewById(R.id.recycler_view_message_message);
//        messageList = new ArrayList<>();
//        messageAdapter = new MessagesAdapter(getContext(), messageList);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        int verticalSpacingInPixels = getResources().getDimensionPixelSize(R.dimen.space_between_items);
//        recyclerView.addItemDecoration(new MessageItemDecoration(getContext(), verticalSpacingInPixels));
//        recyclerView.setAdapter(messageAdapter);
//        // Get current user ID
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//        if (currentUser != null) {
//            currentUserId = currentUser.getUid();
//        }
//        messagesRef = FirebaseDatabase.getInstance().getReference("Messages");
//
//        // Load messages from Firebase database
//        loadMessages();

        // back to home fragment
        imageButton.setOnClickListener(v -> {
            AccueilActivity accueilActivity = (AccueilActivity) getActivity();

            if (accueilActivity != null) {
                accueilActivity.switchToHomeFragment();
            }

        });

        return rootView;
    }

//    private void loadMessages() {
//        messagesRef.addValueEventListener(new ValueEventListener() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                messageList = new ArrayList<>();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    Message message = snapshot.getValue(Message.class);
//                    if (message.getExpediteur().equals(currentUserId) || message.getDestinataire().equals(currentUserId)) {
//                        messageList.add(message);
//                    }
//                }
//                messageAdapter.setMessages(messageList);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e(TAG, "Error getting messages from Realtime Database", databaseError.toException());
//            }
//        });
//    }

}