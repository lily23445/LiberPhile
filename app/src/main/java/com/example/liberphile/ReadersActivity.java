package com.example.liberphile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class ReadersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Liberphile");
        }
        // Set the content view to the correct layout file
        setContentView(R.layout.activity_readers); // Make sure this is the correct layout file name

        // Find the LinearLayout by its ID
        LinearLayout linearLayout = findViewById(R.id.linearLayout);

        // Set an OnClickListener for the LinearLayout
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to SecondActivity
                Intent intent = new Intent(ReadersActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
