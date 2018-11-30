package com.funcode.funcode.gasku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.funcode.funcode.gasku.Fragment.HistoryFragment;
import com.funcode.funcode.gasku.Fragment.HomeFragment;
import com.funcode.funcode.gasku.Fragment.SettingsFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private TextView mTextMessage;
    private Button keluar;
    FirebaseAuth mAuthDriver;
    FirebaseDatabase mUser;
    String idUsers, nama;
    int level;
    GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference dbUser;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportActionBar().setTitle(R.string.title_home);

                    Fragment homeFrag = new HomeFragment();
                    FragmentTransaction transac = getSupportFragmentManager().beginTransaction();
                    transac.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    transac.replace(R.id.content, homeFrag);
                    transac.commit();

                    return true;
                case R.id.navigation_dashboard:

                    getSupportActionBar().setTitle(R.string.title_dashboard);
                    Fragment historyFrag = new HistoryFragment();
                    FragmentTransaction transacHistory = getSupportFragmentManager().beginTransaction();
                    transacHistory.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    transacHistory.replace(R.id.content, historyFrag);
                    transacHistory.commit();
                    return true;
                case R.id.navigation_notifications:

                    getSupportActionBar().setTitle(R.string.title_notifications);

                    Fragment settingFrag = new SettingsFragment();
                    FragmentTransaction transacSet = getSupportFragmentManager().beginTransaction();
                    transacSet.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                    transacSet.replace(R.id.content, settingFrag);
                    transacSet.commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*mTextMessage = (TextView) findViewById(R.id.nama);
        keluar = (Button) findViewById(R.id.keluar);*/
        getSupportActionBar().setTitle(R.string.title_home);
        mAuthDriver = FirebaseAuth.getInstance();
        mUser = FirebaseDatabase.getInstance();


        Fragment homeFrag = new HomeFragment();
        FragmentTransaction transac = getSupportFragmentManager().beginTransaction();
        transac.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transac.replace(R.id.content, homeFrag);
        transac.commit();


        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() == null){

                    //Toast.makeText(HomeActivity.this, "awal checked", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    /*idUsers = mAuthDriver.getCurrentUser().getUid();
                    dbUser = mUser.getReference("users").child(idUsers);
                    dbUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //nama = dataSnapshot.child("Nama").getValue(String.class);
                            //level = dataSnapshot.child("Level").getValue(Integer.class);
                            //mTextMessage.setText("Nama : "+ nama);

                            if(level == 1) {

                                keluar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mAuthDriver.signOut();
                                        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                            } else if(level == 2) {

                                keluar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mAuthDriver.signOut();
                                        // Google sign out
                                        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                                new ResultCallback<Status>() {
                                                    @Override
                                                    public void onResult(@NonNull Status status) {

                                                    }

                                                });
                                        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                                        startActivity(i);
                                        finish();
                                    }

                                });

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });*/
                }
            }
        };



        mAuth = FirebaseAuth.getInstance();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
