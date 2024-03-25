package com.example.monprojet.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MessageManager {
    private DatabaseReference messagesRef;

    public MessageManager() {
        messagesRef = FirebaseDatabase.getInstance().getReference("Messages");
    }

    public void sendMessage(Message message) {
        String messageId = messagesRef.push().getKey();
        message.setMessageId(messageId);
        messagesRef.child(messageId).setValue(message);
    }

    // Méthode pour recevoir des messages, si nécessaire
}
