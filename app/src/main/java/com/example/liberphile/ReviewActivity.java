package com.example.liberphile;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReviewActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int STORAGE_PERMISSION_CODE = 100;
    private EditText bookTitleEditText, ratingEditText, reviewEditText;
    private Button addPhotoButton, submitReviewButton;
    private ImageView bookImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        // Initialize views
        bookTitleEditText = findViewById(R.id.bookTitleEditText);
        ratingEditText = findViewById(R.id.ratingEditText);
        reviewEditText = findViewById(R.id.reviewEditText);
        addPhotoButton = findViewById(R.id.addPhotoButton);
        bookImageView = findViewById(R.id.bookImageView);
        submitReviewButton = findViewById(R.id.submitReviewButton);

        // Request storage permission
        requestStoragePermission();


        // Handle Add Photo button click
        addPhotoButton.setOnClickListener(v -> openGallery());

        // Handle Submit Review button click
        submitReviewButton.setOnClickListener(v -> submitReview());
    }

    // Method to request storage permission
    private void requestStoragePermission() {
        // Check if permission is needed (for devices with Android 6.0+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Request the permission
                requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            }
        }
    }

    // Handle the permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Open Gallery to select an image
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Handle the result of image selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            bookImageView.setImageURI(imageUri); // Display the selected image
        }
    }

    // Method to handle navigation
    private void navigateTo(Class<?> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    // Method to handle review submission
    private void submitReview() {
        String bookTitle = bookTitleEditText.getText().toString();
        String rating = ratingEditText.getText().toString();
        String review = reviewEditText.getText().toString();

        // Input validation
        if (bookTitle.isEmpty() || rating.isEmpty() || review.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Display the submitted details
            TextView submittedBookTitle = findViewById(R.id.submittedBookTitle);
            TextView submittedRating = findViewById(R.id.submittedRating);
            TextView submittedReview = findViewById(R.id.submittedReview);

            submittedBookTitle.setText("Book title submitted: " + bookTitle);
            submittedRating.setText("Your rating: " + rating);
            submittedReview.setText("Your review: " + review);

            // Hide the input layout and show the confirmation layout
            findViewById(R.id.reviewInputLayout).setVisibility(View.GONE);
            findViewById(R.id.confirmationLayout).setVisibility(View.VISIBLE);

            // Handle "Edit it" button click
            findViewById(R.id.editReviewButton).setOnClickListener(v -> {
                // Show input layout and hide confirmation layout
                findViewById(R.id.reviewInputLayout).setVisibility(View.VISIBLE);
                findViewById(R.id.confirmationLayout).setVisibility(View.GONE);
            });
        }
    }
    private void editReview() {
        // Show the input layout and hide the confirmation layout
        findViewById(R.id.reviewInputLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.confirmationLayout).setVisibility(View.GONE);
    }

}
