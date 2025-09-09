package com.example.textdisplay;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Added for an optional enhancement

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private EditText ed1;
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the views by finding them by their IDs from activity_main.xml
        btn1 = findViewById(R.id.button); // The ID of your button is "button"
        ed1 = findViewById(R.id.editTextTextPersonName); // The ID of your EditText is "editTextTextPersonName"
        txt1 = findViewById(R.id.textview1); // The ID of your TextView is "textview1"

        // Set up the button click listener
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the text from EditText and convert it to a String
                String msg = ed1.getText().toString();

                // Check if the EditText is not empty
                if (!msg.isEmpty()) {
                    txt1.setText(msg); // Set the TextView's text to the entered message
                    // Optional: Clear the EditText after setting the text
                    ed1.setText("");
                } else {
                    txt1.setText("Please enter text"); // Display a message if EditText is empty
                    // Optional: Show a short Toast message as well
                    Toast.makeText(MainActivity.this, "Input field is empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}