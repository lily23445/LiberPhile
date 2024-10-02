package com.example.liberphile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class BabelActivity extends AppCompatActivity {

    private EditText messageInput;
    private Button sendMessageButton;
    private LinearLayout messageContainer;
    private ScrollView chatScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babel);

        messageInput = findViewById(R.id.messageInput);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        messageContainer = findViewById(R.id.messageContainer);
        chatScrollView = findViewById(R.id.chatScrollView);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Babel");
        }


        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString().trim();
                if (!message.isEmpty()) {
                    addMessage("Me", message);
                    messageInput.setText("");  // Clear the input field
                }
            }
        });
    }

    private void addMessage(String sender, String message) {
        // Create a new layout to hold the message
        LinearLayout messageLayout = new LinearLayout(this);
        messageLayout.setOrientation(LinearLayout.VERTICAL);
        messageLayout.setPadding(8, 8, 8, 8);
        messageLayout.setBackgroundColor(getResources().getColor(android.R.color.white));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, 8);  // Margin between messages
        messageLayout.setLayoutParams(layoutParams);

        // Add a TextView for the sender's name
        TextView senderText = new TextView(this);
        senderText.setText(sender);
        senderText.setTextSize(Typeface.BOLD);
        messageLayout.addView(senderText);

        // Add a TextView for the message content
        TextView messageText = new TextView(this);
        messageText.setText(message);
        messageText.setTextSize(16);
        messageLayout.addView(messageText);

        // Add the new message to the container
        messageContainer.addView(messageLayout);

        // Scroll to the bottom after adding a new message
        chatScrollView.post(new Runnable() {
            @Override
            public void run() {
                chatScrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
