package utils;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;

import android.content.Intent;
import android.widget.Toast;

import com.example.monprojet.MainActivity;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.List;


public class FirebaseUtil {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();


    public static String currentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }

    public static boolean isLoggedIn(){
        if(currentUserId()!=null){
            return true;
        }
        return false;
    }

    public static DatabaseReference currentUserDetails(){
        return database.getReference("Users").child(currentUserId());
    }





}
