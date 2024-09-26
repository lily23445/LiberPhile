package com.example.liberphile;// Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView profilePicture;
    private TextView username, bio, favoriteGenres, joinedDate;
    private Button editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);  // This should match your layout file name

        profilePicture = findViewById(R.id.profile_picture);
        username = findViewById(R.id.username);
        bio = findViewById(R.id.bio);
        favoriteGenres = findViewById(R.id.favorite_genres);
        joinedDate = findViewById(R.id.joined_date);
        editProfileButton = findViewById(R.id.edit_profile_button);

        // Sample data for demonstration
        loadProfileData();

        // Set up click listener for the edit button
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfileActivity();
            }
        });
    }

    private void loadProfileData() {
        // Here you can set your profile data, this is just sample data
        username.setText("John Doe");
        bio.setText("Bio: Avid reader and book lover.");
        favoriteGenres.setText("Favorite Genres: Fiction, Mystery, Science Fiction");
        joinedDate.setText("Joined: January 1, 2023");
        // You can set the profile picture using a drawable resource or an image from the web
        profilePicture.setImageResource(R.drawable.img);  // Update with your image
    }

//    private void openEditProfileActivity() {
//        Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);  // Create an EditProfileActivity for editing
//        startActivity(intent);
//    }
}
