package com.example.liberphile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 100;
    private ImageView profilePicture;
    private EditText usernameInput, bioInput, favoriteGenresInput;
    private Button saveButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Liberphile");
        }
        // Initialize views
        profilePicture = findViewById(R.id.profile_picture);
        usernameInput = findViewById(R.id.username_input);
        bioInput = findViewById(R.id.bio_input);
        favoriteGenresInput = findViewById(R.id.favorite_genres_input);
        Button changePictureButton = findViewById(R.id.change_picture_button);
        saveButton = findViewById(R.id.save_button);
        cancelButton = findViewById(R.id.cancel_button);

        // Change profile picture
        changePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Save profile changes
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfileChanges();
            }
        });

        // Cancel profile changes
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelProfileChanges();
            }
        });
    }

    // Method to open the gallery and select a profile picture
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            profilePicture.setImageURI(selectedImageUri); // Display the selected image
        }
    }

    // Method to save profile changes
    private void saveProfileChanges() {
        String username = usernameInput.getText().toString();
        String bio = bioInput.getText().toString();
        String favoriteGenres = favoriteGenresInput.getText().toString();

        // Save the profile information (in a real app, save to the database)
        Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();

        // You can add the logic to save these details to a database or shared preferences here.
    }

    // Method to cancel profile changes and revert to the previous state
    private void cancelProfileChanges() {
        finish(); // Close the activity and return to the previous screen
    }
}
