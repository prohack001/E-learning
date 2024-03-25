//package com.example.monprojet.adapters;
//
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.monprojet.MessageActivity;
//import com.example.monprojet.R;
//import com.example.monprojet.models.Message;
//
//import java.util.List;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//
//
//public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {
//    private final Context context;
//    private final List<Message> messageList;
//
//    public MessagesAdapter(Context context, List<Message> messageList) {
//        this.context = context;
//        this.messageList = messageList;
//
//    }
//
//    @NonNull
//    @Override
//    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user, parent, false);
//        return new MessageViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
//        Message message = messageList.get(position);
//        holder.bind(message);
//
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, MessageActivity.class);
//            intent.putExtra("senderId", message.getExpediteur());
//            intent.putExtra("lastMessage", message.getContent());
//            intent.putExtra("receiverId", message.getDestinataire());
//            context.startActivity(intent);
//
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return messageList.size();
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    public void setMessages(List<Message> messages) {
//        messageList.clear();
//        messageList.addAll(messages);
//        notifyDataSetChanged();
//    }
//
//    public static class MessageViewHolder extends RecyclerView.ViewHolder {
//
//        private final TextView textViewName;
//        private final TextView textViewSurname;
//        private final TextView textViewMessage;
//        private final TextView textViewDate;
//        private final de.hdodenhof.circleimageview.CircleImageView circleImageView;
//
//        public MessageViewHolder(@NonNull View itemView) {
//            super(itemView);
//            textViewName = itemView.findViewById(R.id.text_view_name_user_item);
//            textViewSurname = itemView.findViewById(R.id.text_view_surname_user_item);
//            textViewMessage = itemView.findViewById(R.id.text_view_message_user_item);
//            textViewDate = itemView.findViewById(R.id.text_view_date_user_item);
//            circleImageView = itemView.findViewById(R.id.circle_image_view_user_item);
//        }
//
//        public void bind(Message message) {
//            DatabaseReference expRef = FirebaseDatabase.getInstance().getReference("Users").child(message.getExpediteur());
//            expRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if (snapshot.exists()) {
//                        String name = snapshot.child("name").getValue(String.class);
//                        String surname = snapshot.child("surname").getValue((String.class));
//                        String profileImageUrl = snapshot.child("profile").getValue(String.class);
//                        textViewName.setText(name);
//                        textViewSurname.setText(surname);
//
//                        if (profileImageUrl != null && !profileImageUrl.isEmpty()) {
//                            Picasso.get().load(profileImageUrl).placeholder(R.drawable.baseline_account_circle_24).into(circleImageView);
//                        } else {
//                            circleImageView.setImageResource(R.drawable.baseline_account_circle_24);
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
//            textViewMessage.setText(message.getContent());
//            textViewDate.setText(message.getDate_envoie());
//        }
//    }
//
//}
