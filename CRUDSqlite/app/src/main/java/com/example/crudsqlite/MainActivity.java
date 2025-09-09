package com.example.crudsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Class variables for the database and UI elements
    SQLiteDatabase db;
    EditText editSearchContact, editName, editEmailAddress, editMessage;
    Button btnSave, btnDelete, btnModify, btnView, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create or open the database. It will be created if it doesn't exist.
        db = openOrCreateDatabase("ContactDb", Context.MODE_PRIVATE, null);

        // Create the Contact table if it doesn't exist.
        // cId is the primary key and will auto-increment.
        db.execSQL("CREATE TABLE IF NOT EXISTS Contact(cId INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR, Mail VARCHAR, CMessage VARCHAR);");

        // Initialize all the views by finding their IDs from the XML layout.
        editSearchContact = findViewById(R.id.editTextSearch);
        editName = findViewById(R.id.editTextName);
        editEmailAddress = findViewById(R.id.editTextEmailAddress);
        editMessage = findViewById(R.id.editTextMessage);
        btnSave = findViewById(R.id.btnsave);
        btnDelete = findViewById(R.id.btndel);
        btnModify = findViewById(R.id.btnupdate);
        btnView = findViewById(R.id.btnselect);
        btnSearch = findViewById(R.id.btnselectperticular);

        // Set the click listeners for all the buttons.
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    // Helper method to display a Toast message.
    private void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // The main click handler for all buttons.
    @Override
    public void onClick(View v) {
        int id = v.getId(); // Get the ID of the clicked button

        if (id == R.id.btnsave) {
            saveData();
        } else if (id == R.id.btnupdate) {
            updateData();
        } else if (id == R.id.btndel) {
            deleteData();
        } else if (id == R.id.btnselect) {
            selectAllData();
        } else if (id == R.id.btnselectperticular) {
            selectParticularData();
        }
    }

    // --- CRUD Operation Methods ---

    private void saveData() {
        // Check if any of the required fields are empty.
        if (isAnyFieldEmpty(editName, editEmailAddress, editMessage)) {
            showMessage(this, "Please enter all values");
            return;
        }

        // SQL INSERT statement with '?' to prevent SQL injection.
        String sql = "INSERT INTO Contact(Name, Mail, CMessage) VALUES(?, ?, ?)";
        // Execute the SQL statement with the values from the EditTexts.
        db.execSQL(sql, new Object[]{
                editName.getText().toString(),
                editEmailAddress.getText().toString(),
                editMessage.getText().toString()
        });

        clearFields();
        showMessage(this, "Response Noted, Thanks!!");
    }

    private void updateData() {
        // Check if the search field is empty.
        if (editSearchContact.getText().toString().trim().isEmpty()) {
            showMessage(this, "Enter Name in the Search field to update");
            return;
        }

        // Check if the record exists before updating.
        Cursor c = db.rawQuery("SELECT * FROM Contact WHERE Name=?", new String[]{editSearchContact.getText().toString()});
        if (c.moveToFirst()) {
            // SQL UPDATE statement
            String sql = "UPDATE Contact SET Name=?, Mail=?, CMessage=? WHERE Name=?";
            db.execSQL(sql, new Object[]{
                    editName.getText().toString(),
                    editEmailAddress.getText().toString(),
                    editMessage.getText().toString(),
                    editSearchContact.getText().toString()
            });
            clearFields();
            editSearchContact.getText().clear();
            showMessage(this, "Record Modified");
        } else {
            showMessage(this, "Invalid Name or Record not found");
        }
        c.close();
    }

    private void deleteData() {
        // Check if the search field is empty.
        if (editSearchContact.getText().toString().trim().isEmpty()) {
            showMessage(this, "Please enter Name in the Search field to delete");
            return;
        }

        // Check if the record exists before deleting.
        Cursor c = db.rawQuery("SELECT * FROM Contact WHERE Name=?", new String[]{editSearchContact.getText().toString()});
        if (c.moveToFirst()) {
            // SQL DELETE statement
            db.execSQL("DELETE FROM Contact WHERE Name=?", new String[]{editSearchContact.getText().toString()});
            clearFields();
            editSearchContact.getText().clear();
            showMessage(this, "Record Deleted");
        } else {
            showMessage(this, "Invalid Name or Record not found");
        }
        c.close();
    }

    private void selectAllData() {
        // Get all records from the Contact table.
        Cursor c = db.rawQuery("SELECT * FROM Contact", null);
        if (c.getCount() == 0) {
            showMessage(this, "No records found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (c.moveToNext()) {
            buffer.append("Name: ").append(c.getString(1)).append("\n");
            buffer.append("Mail: ").append(c.getString(2)).append("\n");
            buffer.append("Message: ").append(c.getString(3)).append("\n\n");
        }
        // Display all records in a single Toast.
        // Note: This may be problematic for a large number of records.
        showMessage(this, buffer.toString());
        c.close();
    }

    private void selectParticularData() {
        // Check if the search field is empty.
        if (editSearchContact.getText().toString().trim().isEmpty()) {
            showMessage(this, "Enter Name in the Search field");
            return;
        }

        // Select a specific record by name.
        Cursor c = db.rawQuery("SELECT * FROM Contact WHERE Name=?", new String[]{editSearchContact.getText().toString()});
        if (c.moveToFirst()) {
            // Populate the EditText fields with the found data.
            editName.setText(c.getString(1));
            editEmailAddress.setText(c.getString(2));
            editMessage.setText(c.getString(3));
            showMessage(this, "Record found");
        } else {
            showMessage(this, "Invalid Name or Record not found");
        }
        c.close();
    }

    // --- Helper Methods ---
    private boolean isAnyFieldEmpty(EditText... fields) {
        for (EditText field : fields) {
            if (field.getText().toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void clearFields() {
        editName.getText().clear();
        editEmailAddress.getText().clear();
        editMessage.getText().clear();
    }
}