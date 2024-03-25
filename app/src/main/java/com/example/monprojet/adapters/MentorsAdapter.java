package com.example.monprojet.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.monprojet.R;
import com.example.monprojet.models.Mentor;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MentorsAdapter extends RecyclerView.Adapter<MentorsAdapter.MentorViewHolder> {

    private final Context mContext;
    private List<Mentor> mMentors;

    public MentorsAdapter(Context context, List<Mentor> mentors) {
        mContext = context;
        mMentors = mentors;
    }

    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mentor_item_recycler_row, parent, false);
        return new MentorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {
        Mentor mentor = mMentors.get(position);
        holder.bind(mentor);
    }

    @Override
    public int getItemCount() {
        return mMentors.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMentors(List<Mentor> mentors) {
        mMentors = mentors;
        notifyDataSetChanged();
    }

    public static class MentorViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTextViewName;
        private final CircleImageView mImageViewProfile;

        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.text_view_mentor_name_item);
            mImageViewProfile = itemView.findViewById(R.id.circle_image_view_mentor_item);
        }

        public void bind(Mentor mentor) {
            mTextViewName.setText(mentor.getSurname());
            Glide.with(itemView.getContext())
                    .load(mentor.getProfile())
                    .into(mImageViewProfile);
        }
    }
}
