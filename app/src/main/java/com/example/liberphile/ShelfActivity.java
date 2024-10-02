package com.example.liberphile;

import android.app.Dialog;
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

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ShelfActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Bitmap selectedImageBitmap;
    private ImageButton currentImageSelector;
    private LinearLayout readBooksList, toReadBooksList, favBooksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Shelf");
        }
        // Predefined shelves
        readBooksList = findViewById(R.id.readBooksList);
        toReadBooksList = findViewById(R.id.toReadBooksList);
        favBooksList = findViewById(R.id.favBooksList);

        Button addReadBookButton = findViewById(R.id.addReadBookButton);
        Button addToReadBookButton = findViewById(R.id.addToReadBookButton);
        Button addFavBookButton = findViewById(R.id.addFavBookButton);

        // Set up the add book actions
        addReadBookButton.setOnClickListener(v -> showAddBookDialog(readBooksList, "Add Book to Read"));
        addToReadBookButton.setOnClickListener(v -> showAddBookDialog(toReadBooksList, "Add Book to To Be Read"));
        addFavBookButton.setOnClickListener(v -> showAddBookDialog(favBooksList, "Add Book to Favorites"));

        // Custom shelf actions
        Button createShelfButton = findViewById(R.id.createShelfButton);
        EditText addShelfTitle = findViewById(R.id.addShelfTitle);
        createShelfButton.setOnClickListener(v -> {
            String shelfName = addShelfTitle.getText().toString().trim();
            if (!shelfName.isEmpty()) {
                addNewShelf(shelfName);
                addShelfTitle.setText("");  // Clear input
            } else {
                Toast.makeText(ShelfActivity.this, "Please enter a shelf name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to show dialog for adding a book
    private void showAddBookDialog(LinearLayout bookList, String title) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_book);

        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
        EditText bookTitleInput = dialog.findViewById(R.id.bookTitleInput);
        ImageButton bookImageButton = dialog.findViewById(R.id.bookImageButton);
        Button addBookButton = dialog.findViewById(R.id.addBookDialogButton);

        dialogTitle.setText(title);

        bookImageButton.setOnClickListener(v -> {
            currentImageSelector = bookImageButton;
            openImageSelector();
        });

        addBookButton.setOnClickListener(v -> {
            String bookTitle = bookTitleInput.getText().toString().trim();
            if (!bookTitle.isEmpty() && selectedImageBitmap != null) {
                addBookToList(bookList, bookTitle, selectedImageBitmap);
                dialog.dismiss();  // Close dialog after adding
            } else {
                Toast.makeText(ShelfActivity.this, "Please enter a book title and select an image", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    // Method to open the image selector for choosing book covers
    private void openImageSelector() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Book Cover"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                selectedImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                if (currentImageSelector != null) {
                    currentImageSelector.setImageBitmap(selectedImageBitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to dynamically add a new shelf
    private void addNewShelf(String shelfName) {
        LinearLayout shelfContainer = findViewById(R.id.shelfContainer);
        LinearLayout newShelfLayout = new LinearLayout(this);
        newShelfLayout.setOrientation(LinearLayout.VERTICAL);

        TextView shelfTitle = new TextView(this);
        shelfTitle.setText(shelfName);

        Button addBookButton = new Button(this);
        addBookButton.setText("Add Book");

        LinearLayout newBookList = new LinearLayout(this);
        newBookList.setOrientation(LinearLayout.VERTICAL);

        addBookButton.setOnClickListener(v -> showAddBookDialog(newBookList, "Add Book to " + shelfName));

        newShelfLayout.addView(shelfTitle);
        newShelfLayout.addView(addBookButton);
        newShelfLayout.addView(newBookList);

        shelfContainer.addView(newShelfLayout);
    }

    // Method to add a book to a given list
    private void addBookToList(LinearLayout bookList, String title, Bitmap imageBitmap) {
        LinearLayout bookLayout = new LinearLayout(this);
        bookLayout.setOrientation(LinearLayout.HORIZONTAL);

        ImageView bookCover = new ImageView(this);
        bookCover.setLayoutParams(new LinearLayout.LayoutParams(150, 200));
        bookCover.setImageBitmap(imageBitmap);

        TextView bookTitle = new TextView(this);
        bookTitle.setText(title);
        bookTitle.setTextColor(getResources().getColor(R.color.black));
        bookTitle.setPadding(16, 0, 0, 0);
        bookTitle.setTextSize(18);

        bookLayout.addView(bookCover);
        bookLayout.addView(bookTitle);
        bookList.addView(bookLayout);
    }
}