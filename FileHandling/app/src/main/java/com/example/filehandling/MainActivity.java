package com.example.filehandling;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText et_Text;
    Button b_read, b_write;
    TextView tv_Text;
    Button b_delete; // Delete button is now declared correctly
    String file_name = "data.txt"; // A more descriptive filename is better

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize all the views
        et_Text = findViewById(R.id.txtcontent);
        b_read = findViewById(R.id.btnread);
        b_write = findViewById(R.id.btnwrite);
        tv_Text = findViewById(R.id.textView);
        b_delete = findViewById(R.id.btn_delete);

        // --- Set up the OnClickListener for the WRITE button ---
        b_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text from EditText and save it to the file
                String content = et_Text.getText().toString();
                saveFile(file_name, content);
            }
        });

        // --- Set up the OnClickListener for the READ button ---
        b_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read the content from the file and display it
                String content = readFile(file_name);
                tv_Text.setText(content);
            }
        });

        // The clear and delete buttons are handled via `android:onClick` in XML,
        // so we don't need to define listeners for them here.
    }

    // --- Method to save data to a file in internal storage ---
    public void saveFile(String fileName, String text) {
        try {
            FileOutputStream fos = openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "Text saved successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error saving file", Toast.LENGTH_SHORT).show();
        }
    }

    // --- Method to read data from a file in internal storage ---
    public String readFile(String fileName) {
        String text = "";
        try {
            FileInputStream fis = openFileInput(fileName);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error reading file or file not found.", Toast.LENGTH_SHORT).show();
        }
        return text;
    }

    // --- Method to clear the EditText content (linked via android:onClick) ---
    public void clearFile(View view) {
        et_Text.getText().clear();
    }

    // --- Method to delete the file (linked via android:onClick) ---
    public void deleteFile(View view) {
        File file = new File(getFilesDir(), file_name);
        if (file.exists()) {
            file.delete();
            Toast.makeText(this, "File deleted successfully!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "File not found.", Toast.LENGTH_SHORT).show();
        }
    }
}