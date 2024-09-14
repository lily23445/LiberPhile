package com.example.liberphile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class ShelfActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Bitmap selectedImageBitmap;
    private LinearLayout readBooksList, toReadBooksList, favBooksList;

    private int readBooksCount = 0;
    private int toReadBooksCount = 0;
    private int favBooksCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shelf);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Liberphile");
        }
        // Apply insets to section header
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sectionHeader), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the UI elements for each section
        EditText addReadBookTitle = findViewById(R.id.addReadBooksTitle);
        ImageButton selectReadBookImage = findViewById(R.id.selectReadBooksImage);
        Button addReadBookButton = findViewById(R.id.addReadBooksButton);
        readBooksList = findViewById(R.id.readBooksList);

        EditText addToReadBookTitle = findViewById(R.id.addToBeReadBookTitle);
        ImageButton selectToReadBookImage = findViewById(R.id.selectToBeReadBookImage);
        Button addToReadBookButton = findViewById(R.id.addToBeReadBookButton);
        toReadBooksList = findViewById(R.id.toReadBooksList);

        EditText addFavBookTitle = findViewById(R.id.addFavBookTitle);
        ImageButton selectFavBookImage = findViewById(R.id.selectFavBookImage);
        Button addFavBookButton = findViewById(R.id.addFavBookButton);
        favBooksList = findViewById(R.id.favBooksList);

        // Set listener for selecting an image for "Read Books"
        selectReadBookImage.setOnClickListener(v -> openImageSelector());
        addReadBookButton.setOnClickListener(v -> {
            String bookTitle = addReadBookTitle.getText().toString().trim();
            if (readBooksCount < 3 && !bookTitle.isEmpty() && selectedImageBitmap != null) {
                addBookToList(readBooksList, bookTitle, selectedImageBitmap);
                addReadBookTitle.setText("");
                selectedImageBitmap = null;
                selectReadBookImage.setImageResource(android.R.drawable.ic_menu_gallery);
                readBooksCount++;
            } else if (readBooksCount >= 3) {
                Toast.makeText(ShelfActivity.this, "You can only add up to 3 books in this section", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShelfActivity.this, "Please enter a book title and select an image", Toast.LENGTH_SHORT).show();
            }
        });

        // Set listener for selecting an image for "To Be Read Books"
        selectToReadBookImage.setOnClickListener(v -> openImageSelector());
        addToReadBookButton.setOnClickListener(v -> {
            String bookTitle = addToReadBookTitle.getText().toString().trim();
            if (toReadBooksCount < 3 && !bookTitle.isEmpty() && selectedImageBitmap != null) {
                addBookToList(toReadBooksList, bookTitle, selectedImageBitmap);
                addToReadBookTitle.setText("");
                selectedImageBitmap = null;
                selectToReadBookImage.setImageResource(android.R.drawable.ic_menu_gallery);
                toReadBooksCount++;
            } else if (toReadBooksCount >= 3) {
                Toast.makeText(ShelfActivity.this, "You can only add up to 3 books in this section", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShelfActivity.this, "Please enter a book title and select an image", Toast.LENGTH_SHORT).show();
            }
        });

        // Set listener for selecting an image for "Favorite Books"
        selectFavBookImage.setOnClickListener(v -> openImageSelector());
        addFavBookButton.setOnClickListener(v -> {
            String bookTitle = addFavBookTitle.getText().toString().trim();
            if (favBooksCount < 3 && !bookTitle.isEmpty() && selectedImageBitmap != null) {
                addBookToList(favBooksList, bookTitle, selectedImageBitmap);
                addFavBookTitle.setText("");
                selectedImageBitmap = null;
                selectFavBookImage.setImageResource(android.R.drawable.ic_menu_gallery);
                favBooksCount++;
            } else if (favBooksCount >= 3) {
                Toast.makeText(ShelfActivity.this, "You can only add up to 3 books in this section", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ShelfActivity.this, "Please enter a book title and select an image", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to open the image selector
    private void openImageSelector() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Book Cover"), PICK_IMAGE_REQUEST);
    }

    // Handling the selected image result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                ImageButton selectReadBookImage = findViewById(R.id.selectReadBooksImage);
                selectReadBookImage.setImageBitmap(selectedImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to add a book to the specified list
    private void addBookToList(LinearLayout bookList, String title, Bitmap imageBitmap) {
        LinearLayout bookLayout = new LinearLayout(this);
        bookLayout.setOrientation(LinearLayout.HORIZONTAL);
        bookLayout.setPadding(0, 0, 0, 16);

        ImageView bookCover = new ImageView(this);
        bookCover.setLayoutParams(new LinearLayout.LayoutParams(150, 200));
        bookCover.setImageBitmap(imageBitmap);

        TextView bookTitle = new TextView(this);
        bookTitle.setText(title);
        bookTitle.setTextColor(getResources().getColor(R.color.black));
        bookTitle.setPadding(16, 0, 0, 0);
        bookTitle.setTextSize(16);

        bookLayout.addView(bookCover);
        bookLayout.addView(bookTitle);

        bookList.addView(bookLayout);
    }
}