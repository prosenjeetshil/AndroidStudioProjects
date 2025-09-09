package com.example.uicomponents;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup; // Important: Use RadioGroup for managing RadioButtons
import android.widget.RatingBar;
import android.widget.Toast; // For user feedback

public class MainActivity extends AppCompatActivity {
    EditText e1;
    RadioGroup rgGender; // Use RadioGroup for proper gender selection handling
    RadioButton r1, r2; // Individual radio buttons
    CheckBox c1, c2;
    Button b1;
    RatingBar rb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views by finding their IDs from activity_main.xml
        e1 = findViewById(R.id.et1);
        rgGender = findViewById(R.id.radiogp1); // Initialize RadioGroup
        r1 = findViewById(R.id.rb1); // Initialize individual RadioButtons
        r2 = findViewById(R.id.rb2);
        c1 = findViewById(R.id.cb1);
        c2 = findViewById(R.id.cb2);
        b1 = findViewById(R.id.b1);
        rb1 = findViewById(R.id.ratingbar1);

        // Set up the button click listener
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. Get Name
                String name = e1.getText().toString().trim(); // .trim() removes leading/trailing spaces
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return; // Stop execution if name is empty
                }

                // 2. Get Gender
                String gender = "Not Selected";
                int selectedGenderId = rgGender.getCheckedRadioButtonId(); // Get ID of selected radio button
                if (selectedGenderId != -1) { // Check if any radio button is selected
                    RadioButton selectedRadioButton = findViewById(selectedGenderId);
                    gender = selectedRadioButton.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "Please select your gender", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 3. Get Course (Multiple selection possible with CheckBoxes)
                StringBuilder courseBuilder = new StringBuilder();
                if (c1.isChecked()) {
                    courseBuilder.append(c1.getText().toString());
                }
                if (c2.isChecked()) {
                    if (courseBuilder.length() > 0) {
                        courseBuilder.append(" , "); // Add comma if more than one course selected
                    }
                    courseBuilder.append(c2.getText().toString());
                }
                String course = courseBuilder.toString();
                if (course.isEmpty()) {
                    course = "None Selected"; // Default if no courses are checked
                }

                // 4. Get Rating
                String rating = String.valueOf(rb1.getRating());

                // Create Intent to start MainActivity2
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                // Put data as Extras into the Intent
                intent.putExtra("NAME", name);
                intent.putExtra("GENDER", gender);
                intent.putExtra("COURSE", course);
                intent.putExtra("RATING", rating);

                // Start the new Activity
                startActivity(intent);
            }
        });
    }
}