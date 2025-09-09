package com.example.uicomponents;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    // Declare TextViews as instance variables for clarity
    TextView tvName, tvGender, tvCourse, tvRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize TextViews
        tvName = findViewById(R.id.tv_name); // Corresponds to the tv_name in activity_main2.xml
        tvGender = findViewById(R.id.tv_gender);
        tvCourse = findViewById(R.id.tv_Course);
        tvRating = findViewById(R.id.tv_rating);

        // Get the Intent that started this Activity
        Intent intent = getIntent();

        // Retrieve the data using the same keys used in MainActivity
        String name = intent.getStringExtra("NAME");
        String gender = intent.getStringExtra("GENDER");
        String course = intent.getStringExtra("COURSE");
        String rating = intent.getStringExtra("RATING"); // Rating is passed as String from MainActivity

        // Set the retrieved data to the TextViews
        tvName.setText("Name : " + name);
        tvGender.setText("Gender : " + gender);
        tvCourse.setText("Courses : " + course);
        tvRating.setText("Rating : " + rating + " Stars"); // Added "Stars" for clarity
    }
}