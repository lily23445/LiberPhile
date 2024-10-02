package com.example.liberphile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recents);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Recents");
        }
        // Set up static book review data
        setUpBookReviews();
    }



    private void setUpBookReviews() {
        // Book 1
        ImageView bookCover1 = findViewById(R.id.bookCover1);
        bookCover1.setImageResource(R.drawable.thegreatgatsby); // Change to your actual drawable

        TextView bookTitle1 = findViewById(R.id.bookTitle1);
        bookTitle1.setText("The Great Gatsby");

        TextView reviewText1 = findViewById(R.id.reviewText1);
        reviewText1.setText("A classic tale of wealth and betrayal.");

        TextView reviewerInfo1 = findViewById(R.id.reviewerInfo1);
        reviewerInfo1.setText("Reviewed by User123");

        // Book 2
        ImageView bookCover2 = findViewById(R.id.bookCover2);
        bookCover2.setImageResource(R.drawable.tokillamockingbird); // Change to your actual drawable

        TextView bookTitle2 = findViewById(R.id.bookTitle2);
        bookTitle2.setText("To Kill a Mockingbird");

        TextView reviewText2 = findViewById(R.id.reviewText2);
        reviewText2.setText("A profound commentary on race and injustice.");

        TextView reviewerInfo2 = findViewById(R.id.reviewerInfo2);
        reviewerInfo2.setText("Reviewed by User456");

        // Book 3
        ImageView bookCover3 = findViewById(R.id.bookCover3);
        bookCover3.setImageResource(R.drawable.img_1984); // Change to your actual drawable

        TextView bookTitle3 = findViewById(R.id.bookTitle3);
        bookTitle3.setText("1984");

        TextView reviewText3 = findViewById(R.id.reviewText3);
        reviewText3.setText("A chilling dystopian novel that warns of totalitarianism.");

        TextView reviewerInfo3 = findViewById(R.id.reviewerInfo3);
        reviewerInfo3.setText("Reviewed by User789");

        // Book 4
        ImageView bookCover4 = findViewById(R.id.bookCover4);
        bookCover4.setImageResource(R.drawable.prideandprejudice); // Change to your actual drawable

        TextView bookTitle4 = findViewById(R.id.bookTitle4);
        bookTitle4.setText("Pride and Prejudice");

        TextView reviewText4 = findViewById(R.id.reviewText4);
        reviewText4.setText("A romantic story that explores themes of class and society.");

        TextView reviewerInfo4 = findViewById(R.id.reviewerInfo4);
        reviewerInfo4.setText("Reviewed by User101");

        // Book 5
        ImageView bookCover5 = findViewById(R.id.bookCover5);
        bookCover5.setImageResource(R.drawable.catcherintherye); // Change to your actual drawable

        TextView bookTitle5 = findViewById(R.id.bookTitle5);
        bookTitle5.setText("The Catcher in the Rye");

        TextView reviewText5 = findViewById(R.id.reviewText5);
        reviewText5.setText("A story about teenage alienation and loss.");

        TextView reviewerInfo5 = findViewById(R.id.reviewerInfo5);
        reviewerInfo5.setText("Reviewed by User202");
    }
}
