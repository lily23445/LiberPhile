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

        // Set the title for the Action Bar if it's not null
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Liberphile");
        }

        // Set onClickListeners for buttons
        findViewById(R.id.button_add_books).setOnClickListener(this::openAddBooks);
        findViewById(R.id.button_shelf).setOnClickListener(this::openShelf);
         findViewById(R.id.button_review).setOnClickListener(this::openReview);
        findViewById(R.id.button_my_profile).setOnClickListener(this::openProfile);
        findViewById(R.id.button_readers).setOnClickListener(this::openReaders);
        findViewById(R.id.button_babel).setOnClickListener(this::openBabel);
    }

    public void openBabel(View view) {
        startActivity(new Intent(this, BabelActivity.class));
    }

    public void openAddBooks(View view) {
        startActivity(new Intent(this, AddBooksActivity.class));
    }

    public void openShelf(View view) {
        startActivity(new Intent(this, ShelfActivity.class));
    }

   public void openReview(View view) {
        startActivity(new Intent(this, ReviewActivity.class));
   }

    public void openProfile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void openReaders(View view) {
        startActivity(new Intent(this, ReadersActivity.class));
    }
}
