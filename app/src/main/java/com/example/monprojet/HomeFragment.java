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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.monprojet.adapters.CategoriesAdapter;
import com.example.monprojet.adapters.CoursesAdapter;
import com.example.monprojet.adapters.MentorsAdapter;
import com.example.monprojet.models.Category;
import com.example.monprojet.models.Course;
import com.example.monprojet.models.Mentor;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ShapeableImageView shapeableImageView;
    TextView firstname, lastname;
    RecyclerView recyclerViewCategory;
    CategoriesAdapter categoriesAdapter;
    MaterialToolbar toolbar;
    MentorsAdapter mentorsAdapter;
    CoursesAdapter coursesAdapter;
    RecyclerView recyclerViewMentor, recyclerViewCourse;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth auth;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);

        // toolbar
        toolbar = rootView.findViewById(R.id.material_tool_bar_home);
        shapeableImageView = rootView.findViewById(R.id.shapeable_image_view_profile_home);
        firstname = rootView.findViewById(R.id.text_view_firstname_home);
        lastname = rootView.findViewById(R.id.text_view_lastname_home);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        String userId = auth.getUid();

        firebaseDatabase.getReference("Users")
                .child(userId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String name = snapshot.child("name").getValue(String.class);
                            String surname = snapshot.child("surname").getValue(String.class);
                            String profileImageUrl = snapshot.child("profile").getValue(String.class);

                            lastname.setText(name);
                            firstname.setText(surname);
                            Picasso.get().load(profileImageUrl).placeholder(R.drawable.baseline_account_circle_24).into(shapeableImageView);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("ProfileFragment", "Erreur de lecture des données utilisateur", error.toException());
                    }
                });


        // category
        recyclerViewCategory = rootView.findViewById(R.id.recycler_view_category_home);
        categoriesAdapter = new CategoriesAdapter(getContext(), new ArrayList<>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        int spaceBetweenItems = getResources().getDimensionPixelSize(R.dimen.space_between_items);
        recyclerViewCategory.addItemDecoration(new SpaceItemDecoration(spaceBetweenItems));
        recyclerViewCategory.setAdapter(categoriesAdapter);

        // mentor
        recyclerViewMentor = rootView.findViewById(R.id.recycler_view_mentor_home);
        mentorsAdapter = new MentorsAdapter(getContext(), new ArrayList<>());
        LinearLayoutManager layoutManagerMentor = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMentor.setLayoutManager(layoutManagerMentor);
        int spaceBetweenItemsMentor = getResources().getDimensionPixelSize(R.dimen.space_between_items);
        recyclerViewMentor.addItemDecoration(new SpaceItemDecoration(spaceBetweenItemsMentor));
        recyclerViewMentor.setAdapter(mentorsAdapter);

        // course
        recyclerViewCourse = rootView.findViewById(R.id.recycler_view_courses_home);
        coursesAdapter = new CoursesAdapter(getContext(), new ArrayList<>());
        LinearLayoutManager layoutManagerCourse = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCourse.setLayoutManager(layoutManagerCourse);
        int spaceBetweenItemsCourse = getResources().getDimensionPixelSize(R.dimen.space_between_items);
        recyclerViewCourse.addItemDecoration(new SpaceItemDecoration(spaceBetweenItemsCourse));
        recyclerViewCourse.setAdapter(coursesAdapter);

        fetchMentorsFromRealtimeDatabase();
        fetchCategoriesFromRealtimeDatabase();
        fetchCoursesFromRealtimeDatabase();

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menuSearch) {
                Toast.makeText(requireContext(), "vous avez cliquer sur l'icon rechercher", Toast.LENGTH_LONG).show();
                return true;
            }else if (item.getItemId() == R.id.menuNotification){
                Toast.makeText(requireContext(), "vous avez cliquer sur l'icon notification", Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        });
        return rootView;
    }

    // categories
    private void fetchCategoriesFromRealtimeDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Categories");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Category> categoriesList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String categoryId = snapshot.getKey();
                    String libelle = snapshot.child("libelle").getValue(String.class);
                    String profile = snapshot.child("profile").getValue(String.class);
                    Category category = new Category(categoryId, libelle, profile);
                    categoriesList.add(category);
                }
                categoriesAdapter.setCategories(categoriesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error getting categories from Realtime Database", databaseError.toException());
            }
        });
    }


    // Mentors
    private void fetchMentorsFromRealtimeDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Mentors");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Mentor> mentorsList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String mentorId = snapshot.getKey();
                    String nom = snapshot.child("nom").getValue(String.class);
                    String prenom = snapshot.child("prenom").getValue(String.class);
                    String profile = snapshot.child("profile").getValue(String.class);
                    Mentor mentor = new Mentor(mentorId, nom , prenom, null, null, null, null, profile, null, null, 0, 0);
                    mentorsList.add(mentor);
                }
                mentorsAdapter.setMentors(mentorsList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error getting mentors from Realtime Database", databaseError.toException());
            }
        });
    }


    // Courses
    private void fetchCoursesFromRealtimeDatabase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Courses");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Course> coursesList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String courseId = snapshot.getKey();
                    String category = snapshot.child("category").getValue(String.class);
                    String libelle = snapshot.child("libelle").getValue(String.class);
                    String profile = snapshot.child("profile").getValue(String.class);
                    int nbStars = snapshot.child("nbStars").getValue(Integer.class);
                    int nbStudent = snapshot.child("nbStudent").getValue(Integer.class);
                    Double oldPrice = snapshot.child("oldPrice").getValue(Double.class);
                    Double newPrice = snapshot.child("newPrice").getValue(Double.class);
                    Course course = new Course(courseId, libelle , profile, null, category, null, null, nbStars, oldPrice, newPrice, nbStudent, null);
                    coursesList.add(course);
                }
                coursesAdapter.setCourses(coursesList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Error getting mentors from Realtime Database", databaseError.toException());
            }
        });
    }
}