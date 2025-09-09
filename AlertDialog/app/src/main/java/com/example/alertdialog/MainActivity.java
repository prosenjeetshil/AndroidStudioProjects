package com.example.alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.widget.Button;
import android.view.View;
import android.widget.Toast; // Added for a more user-friendly "NO" action

public class MainActivity extends AppCompatActivity {
    private Button buttonSbm; // Declared as an instance variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the button here.
        buttonSbm = findViewById(R.id.Button);

        // Set the click listener on the button.
        onButtonClickListener();
    }

    public void onButtonClickListener() {
        buttonSbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new AlertDialog.Builder instance.
                AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);

                // Set the message and title of the alert dialog.
                a_builder.setMessage("Do you Want to close this app?")
                        .setTitle("Alert!!") // Title can be set here or after .create()
                        .setCancelable(false); // Prevents the user from dismissing the dialog by tapping outside it.

                // Set the "YES" button (Positive action).
                a_builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // This code runs when the user clicks "YES".
                        finish(); // Closes the current Activity, effectively closing the app in this case.
                    }
                });

                // Set the "NO" button (Negative action).
                a_builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // This code runs when the user clicks "NO".
                        // The dialog automatically dismisses when a button is clicked.
                        // We can add a Toast here for user feedback.
                        Toast.makeText(MainActivity.this, "Action cancelled.", Toast.LENGTH_SHORT).show();
                        dialog.cancel(); // Dismisses the dialog (though it would have already done so)
                    }
                });

                // Create the AlertDialog object and show it.
                AlertDialog alert = a_builder.create();
                alert.show();
            }
        });
    }
}