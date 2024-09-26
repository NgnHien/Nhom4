package com.example.socialmediaproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ImageView avatartv;
    TextView name, email;
    RecyclerView postrecycle;
    FloatingActionButton fab;
    ProgressDialog pd;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        firebaseAuth = FirebaseAuth.getInstance();

        // getting current user data
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        // Initialising the text view and imageview
        avatartv = view.findViewById(R.id.avatartv);
        name = view.findViewById(R.id.nametv);
        email = view.findViewById(R.id.emailtv);
        fab = view.findViewById(R.id.fab);
        pd = new ProgressDialog(requireActivity());
        pd.setCanceledOnTouchOutside(false);

        // Query to get the user's data based on their email
        Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    // Retrieving Data from firebase
                    String nameValue = "" + dataSnapshot1.child("name").getValue();
                    String emailValue = "" + dataSnapshot1.child("email").getValue();
                    String image = "" + dataSnapshot1.child("image").getValue();

                    // Setting data to TextViews
                    name.setText(nameValue);
                    email.setText(emailValue);

                    // Load image into avatartv using Glide
                    try {
                        Glide.with(requireActivity()).load(image).placeholder(R.drawable.default_avatar).into(avatartv);
                    } catch (Exception e) {
                        Glide.with(requireActivity()).load(R.drawable.default_avatar).into(avatartv);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error here
            }
        });

        // On click we will open EditProfileActivity
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireActivity(), EditProfilePage.class));
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }
}
