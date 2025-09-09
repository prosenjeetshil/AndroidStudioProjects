package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Not strictly needed if using findViewById and onClick XML
import android.widget.EditText;
import android.widget.TextView; // Not strictly needed if using findViewById and onClick XML
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // No need to declare these as instance variables if you find them inside performOperation
    // private EditText num1, num2, result;
    // private Button addBtn, subBtn, mulBtn, divBtn;
    // The current implementation finds them on each click, which is fine for this simple app.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // View initialization is not explicitly done here as operations are handled
        // via android:onClick directly on the button clicks
        // and views are found within performOperation.
    }

    // Methods linked to android:onClick in XML
    public void ADD(View view) {
        performOperation(view, Operation.ADD);
    }

    public void SUB(View view) {
        performOperation(view, Operation.SUBTRACT);
    }

    public void MUL(View view) {
        performOperation(view, Operation.MULTIPLY);
    }

    public void DIV(View view) {
        performOperation(view, Operation.DIVIDE);
    }

    // Central method to handle all arithmetic operations
    private void performOperation(View view, Operation operation) {
        // Find the EditTexts and TextView on each button click
        // This is okay for a simple app, but for more complex UIs,
        // you'd typically initialize them once in onCreate.
        EditText num1EditText = findViewById(R.id.num1);
        EditText num2EditText = findViewById(R.id.num2);
        EditText resultEditText = findViewById(R.id.result); // Changed to EditText for result display

        // Get input text
        String num1Str = num1EditText.getText().toString();
        String num2Str = num2EditText.getText().toString();

        // Input validation
        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return; // Exit the method if input is missing
        }

        try {
            // Parse input to integers
            int x = Integer.parseInt(num1Str);
            int y = Integer.parseInt(num2Str);
            float result_num = 0; // Use float to handle division results accurately

            switch (operation) {
                case ADD:
                    result_num = x + y;
                    break;
                case SUBTRACT:
                    result_num = x - y;
                    break;
                case MULTIPLY:
                    result_num = x * y;
                    break;
                case DIVIDE:
                    if (y != 0) {
                        result_num = (float) x / y; // Cast x to float for float division
                    } else {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        return; // Exit the method if division by zero
                    }
                    break;
            }
            // Display the result
            resultEditText.setText(String.valueOf(result_num));
        } catch (NumberFormatException e) {
            // Handle cases where input is not a valid number
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    // Enum to clearly define operations
    private enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}