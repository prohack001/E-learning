package com.example.monprojet.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.monprojet.R;
import com.example.monprojet.models.Course;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>  {

    private final Context mContext;
    private List<Course> mCourses;

    public CoursesAdapter(Context context, List<Course> courses) {
        mContext = context;
        mCourses = courses;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_item_recycler_row, parent, false);
        return new CoursesAdapter.CourseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.CourseViewHolder holder, int position) {
        Course course = mCourses.get(position);
        holder.bind(course);
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCourses(List<Course> courses) {
        mCourses = courses;
        notifyDataSetChanged();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {


        private final TextView mTextViewLibelle, mTextViewCategory, mTextViewoldPrice, mTextViewnewPrice, mTextViewnbStars, mTextViewnbStudents;
        private final ImageView mImageViewProfile;

        @SuppressLint("WrongViewCast")
        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageViewProfile = itemView.findViewById(R.id.image_view_profile_course_item);
            mTextViewLibelle = itemView.findViewById(R.id.text_view_name_course_item);
            mTextViewCategory = itemView.findViewById(R.id.text_view_category_course_item);
            mTextViewnbStars = itemView.findViewById(R.id.text_view_stars_course_item);
            mTextViewoldPrice = itemView.findViewById(R.id.text_view_false_price_course_item);
            mTextViewnewPrice = itemView.findViewById(R.id.text_view_true_price_course_item);
            mTextViewnbStudents = itemView.findViewById(R.id.text_view_student_course_item);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Course course) {
            mTextViewLibelle.setText(course.getLibelle());
            mTextViewoldPrice.setText(String.valueOf(course.getOldPrice()));
            mTextViewnewPrice.setText(String.valueOf(course.getNewPrice()));
            mTextViewCategory.setText(course.getCategory());
            mTextViewnbStudents.setText(String.valueOf(course.getNbStudents()));
            mTextViewnbStars.setText(String.valueOf(course.getStars()));

            // Barre le texte de mTextViewoldPrice
            mTextViewoldPrice.setPaintFlags(mTextViewoldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            Glide.with(itemView.getContext())
                    .load(course.getProfile())
                    .into(mImageViewProfile);
        }
    }
}

