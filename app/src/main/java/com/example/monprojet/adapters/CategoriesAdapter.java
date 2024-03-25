package com.example.monprojet.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.monprojet.R;
import com.example.monprojet.models.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private final Context mContext;
    private List<Category> mCategories;

    public CategoriesAdapter(Context context, List<Category> categories) {
        mContext = context;
        mCategories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_item_recycler_row, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mCategories.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCategories(List<Category> categories) {
        mCategories = categories;
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {


        private final TextView mTextViewLibelle;
        private final ImageView mImageViewProfile;

        @SuppressLint("WrongViewCast")
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewProfile = itemView.findViewById(R.id.image_view_category_item);
            mTextViewLibelle = itemView.findViewById(R.id.text_view_category_item);
        }

        public void bind(Category category) {
            mTextViewLibelle.setText(category.getLibelle());
            Glide.with(itemView.getContext())
                    .load(category.getProfile())
                    .into(mImageViewProfile);
        }
    }
}
