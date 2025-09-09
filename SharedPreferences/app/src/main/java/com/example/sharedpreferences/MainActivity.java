package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText; // Use EditText instead of TextView
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Class variables
    SharedPreferences sharedPreferences;
    EditText password, email;
    CheckBox remember;

    public static final String MY_PREFERENCES = "my_pref";
    public static final String EMAIL_KEY = "emailKey";
    public static final String PASSWORD_KEY = "passwordKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        email = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        remember = findViewById(R.id.checkBox);

        // Get the SharedPreferences object
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);

        // Check if credentials are saved and pre-fill fields
        if (sharedPreferences.contains(EMAIL_KEY)) {
            email.setText(sharedPreferences.getString(EMAIL_KEY, ""));
        }
        if (sharedPreferences.contains(PASSWORD_KEY)) {
            password.setText(sharedPreferences.getString(PASSWORD_KEY, ""));
        }
    }

    // Method called by the "Get Data" button
    public void Get(View view) {
        // Get credentials from SharedPreferences and display them
        if (sharedPreferences.contains(EMAIL_KEY) && sharedPreferences.contains(PASSWORD_KEY)) {
            email.setText(sharedPreferences.getString(EMAIL_KEY, ""));
            password.setText(sharedPreferences.getString(PASSWORD_KEY, ""));
            Toast.makeText(this, "Data retrieved from preferences", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No data found in preferences", Toast.LENGTH_SHORT).show();
        }
    }

    // Method called by the "Sign In" button
    public void Save(View view) {
        // Check if the "Remember Me" checkbox is checked
        if (remember.isChecked()) {
            // Get the text from the EditText fields
            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

            // Get a SharedPreferences.Editor to modify the data
            SharedPreferences.Editor editor = sharedPreferences.edit();

            // Put the email and password into the editor
            editor.putString(EMAIL_KEY, userEmail);
            editor.putString(PASSWORD_KEY, userPassword);

            // Commit the changes
            editor.apply(); // Use apply() for asynchronous saving

            Toast.makeText(this, "Value stored in shared preference", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Remember Me is not checked", Toast.LENGTH_SHORT).show();
        }
    }

    // Method called by the "Clear" button
    public void clear(View view) {
        // Clear the text fields
        email.setText("");
        password.setText("");

        // Clear the data from SharedPreferences as well
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(this, "Cleared fields and data", Toast.LENGTH_SHORT).show();
    }
}