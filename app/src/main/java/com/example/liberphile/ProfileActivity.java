package com.example.liberphile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private Button editProfileButton; // Declare the Edit Profile button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the title for the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Profile");
        }

        // Set the content view to the correct layout file
        setContentView(R.layout.activity_my_profile); // Make sure this matches your XML file

        // Initialize the Edit Profile button
        editProfileButton = findViewById(R.id.edit_profile_button);

        // Set an OnClickListener for the Edit Profile button to handle navigation
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to EditProfileActivity
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // Initialize other views if you still want to display them
        ImageView profilePicture = findViewById(R.id.profile_picture);
        TextView username = findViewById(R.id.username);
        TextView bio = findViewById(R.id.bio);
        TextView favoriteGenres = findViewById(R.id.favorite_genres);
        TextView joinedDate = findViewById(R.id.joined_date);

        // Mock data for user profile (in practice, fetch from database or backend)
        username.setText("Laura ");
        bio.setText("Avid reader and book lover.");
        favoriteGenres.setText("Favorite Genres: Fiction, Mystery, Science Fiction");
        joinedDate.setText("Joined: January 1, 2023");
    }
}
