package com.example.liberphile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class HomepageActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        // Ensure this matches the layout file name

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Liberphile");
        }
        // Corrected button IDs
        findViewById(R.id.button_add_books).setOnClickListener(this::openViewBooks);
        findViewById(R.id.button_shelf).setOnClickListener(this::openShelf);
        findViewById(R.id.button_view_reviews).setOnClickListener(this::openViewReviews);
        findViewById(R.id.button_my_profile).setOnClickListener(this::openProfile);
        findViewById(R.id.button_readers).setOnClickListener(this::openReaders);
        findViewById(R.id.button_babel).setOnClickListener(this::openBabel);
    }

    public void openBabel(View view) {
        Intent intent = new Intent(this, BabelActivity.class);
        startActivity(intent);
    }

    public void openViewBooks(View view) {
        Intent intent = new Intent(this, AddBooksActivity.class);
        startActivity(intent);
    }

    public void openShelf(View view) {
        Intent intent = new Intent(this, ShelfActivity.class);
        startActivity(intent);
    }

    public void openViewReviews(View view) {
        Intent intent = new Intent(this, ViewReviewsActivity.class);
        startActivity(intent);
    }

    public void openProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openReaders(View view) {
        Intent intent = new Intent(this, ReadersActivity.class);
        startActivity(intent);
    }



}


