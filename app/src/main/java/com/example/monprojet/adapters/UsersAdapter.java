package com.example.monprojet.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monprojet.MessageActivity;
import com.example.monprojet.R;
import com.example.monprojet.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>{
    ArrayList<User> list;
    Context context;

    public UsersAdapter(ArrayList<User> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = list.get(position);
        Picasso.get().load(user.getProfile()).placeholder(R.drawable.baseline_account_circle_24).into(holder.circleImageView);
        holder.textViewSurname.setText(user.getSurname());
        holder.textViewName.setText(user.getName());

        holder.itemView.setOnClickListener(v -> {
        Intent intent = new Intent(context, MessageActivity.class);
        intent.putExtra("userId", user.getUserId());
        intent.putExtra("name", user.getName());
        intent.putExtra("surname", user.getSurname());
        intent.putExtra("profile", user.getProfile());
        intent.putExtra("lastMessage", user.getLastMessage());
        context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewSurname;
        TextView textViewMessage;
        de.hdodenhof.circleimageview.CircleImageView circleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name_user_item);
            textViewSurname = itemView.findViewById(R.id.text_view_surname_user_item);
            textViewMessage = itemView.findViewById(R.id.text_view_message_user_item);
            circleImageView = itemView.findViewById(R.id.circle_image_view_user_item);
        }
    }
}
