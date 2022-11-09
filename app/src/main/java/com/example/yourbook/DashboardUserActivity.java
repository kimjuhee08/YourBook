package com.example.yourbook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.yourbook.databinding.ActivityDashboardUserBinding;
import com.example.yourbook.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardUserActivity extends AppCompatActivity {

    private ActivityDashboardUserBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth = firebaseAuth.getInstance();
        checkUser();

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUser();
            }
        });
    }
    private void checkUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser==null) {
            //not logged in, goto main screen
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        else {
            String email = firebaseUser.getEmail();
            binding.subtitletv.setText(email);
        }
    }
}