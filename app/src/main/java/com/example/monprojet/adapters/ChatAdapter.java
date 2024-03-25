package com.example.monprojet.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monprojet.R;
import com.example.monprojet.models.Chat;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    ArrayList<Chat> chats;
    Context context;
    String receiverId;
    private static final int VIEW_TYPE_SENDER = 1;
    private static final int VIEW_TYPE_RECEIVER = 2;

    public ChatAdapter(ArrayList<Chat> chats, Context context, String receiverId) {
        this.chats = chats;
        this.context = context;
        this.receiverId = receiverId;
    }

    public ChatAdapter(ArrayList<Chat> chats, Context context) {
        this.chats = chats;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==VIEW_TYPE_SENDER){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender_message, parent, false);
            return new SenderViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_receiver_message, parent, false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Chat chat = chats.get(position);
        String currentUserId = FirebaseAuth.getInstance().getUid();
        if (chat.getuId()!=null && chat.getuId().equals(currentUserId)) {
            return VIEW_TYPE_SENDER;
        } else {
            return VIEW_TYPE_RECEIVER;

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = chats.get(position);
        if (holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMessage.setText(chat.getMessage());
            ((SenderViewHolder)holder).senderTime.setText(formatTime(chat.getTimestamp()));

        }else {
            ((ReceiverViewHolder)holder).receiverMessage.setText(chat.getMessage());
            ((ReceiverViewHolder)holder).receiverTime.setText(formatTime(chat.getTimestamp()));

        }
    }

    private String formatTime(Long timestamp) {
        Date date = new Date(timestamp);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public static class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView receiverMessage, receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverMessage = itemView.findViewById(R.id.text_view_receiver_message);
            receiverTime = itemView.findViewById(R.id.text_view_receiver_date);

        }
    }

    public static class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView senderMessage, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMessage = itemView.findViewById(R.id.text_view_sender_message);
            senderTime = itemView.findViewById(R.id.text_view_sender_date);
        }
    }
}
