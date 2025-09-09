package com.example.toast;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View; // Required for the View parameter in the Toaster method
import android.widget.Toast; // Required for using the Toast class

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Toaster(View view) {
        Toast.makeText(this, "Welcome, Prosenjeet!", Toast.LENGTH_LONG).show();
    }
}