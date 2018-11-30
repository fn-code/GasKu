package com.funcode.funcode.gasku.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.funcode.funcode.gasku.PesananActivity;
import com.funcode.funcode.gasku.R;
import com.funcode.funcode.gasku.google.playservices.placepicker.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by funcode on 10/31/17.
 */

public class HomeFragment extends Fragment {

    CardView btnPesan;
    String idUsers;
    FirebaseAuth mAuthDriver;
    FirebaseDatabase mUser;
    DatabaseReference dbUser;
    TextView nmUser;
    ImageView imgView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.home_fragment, container, false);

        btnPesan = (CardView) home.findViewById(R.id.pesan);
        mAuthDriver = FirebaseAuth.getInstance();
        mUser = FirebaseDatabase.getInstance();
        idUsers = mAuthDriver.getCurrentUser().getUid();
        nmUser = (TextView) home.findViewById(R.id.nm_user);
        imgView = (ImageView) home.findViewById(R.id.users_profil);
        dbUser = mUser.getReference("users").child(idUsers);



        dbUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Nama = dataSnapshot.child("Nama").getValue(String.class);
                String urlPhoto = dataSnapshot.child("Photo").getValue(String.class);


                Glide.with(getActivity()).load(urlPhoto).into(imgView);
                nmUser.setText(Nama);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), PesananActivity.class);
                i.putExtra("idGas", "1");
                startActivity(i);
            }
        });

        return home;

    }
}
