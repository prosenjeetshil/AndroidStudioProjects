package com.example.intent;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent; // Required for using Intent
import android.os.Bundle;
import android.view.View; // Required for the View parameter in the Intenter method

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the Button with android:onClick="Intenter" is clicked.
     * It starts a new Activity (MainActivity2).
     * @param view The View (Button) that was clicked.
     */
    public void Intenter(View view) {
        // Create an Intent:
        // - 'this' refers to the current Context (MainActivity)
        // - MainActivity2.class specifies the target Activity to start
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent); // Start the new Activity
    }
}