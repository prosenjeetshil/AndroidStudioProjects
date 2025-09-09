package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.content.Context;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Mview(this)); // Sets the content view to an instance of our custom Mview class
    }

    // A custom inner class that extends View to create a custom drawing surface
    private class Mview extends View {
        public Mview(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // Get the width and height of the view
            int x = getWidth();
            int y = getHeight();

            int radius = 100; // Define the radius of the circle

            // Create a Paint object to define the drawing style and color
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);

            // Set the background color to white
            p.setColor(Color.WHITE);
            canvas.drawPaint(p);

            // Set the circle color to yellow
            p.setColor(Color.parseColor("yellow"));

            // Draw a circle in the center of the view with the defined radius
            canvas.drawCircle(x / 2, y / 2, radius, p);

            // This line draws a rectangle, but the coordinates are likely incorrect as provided
            // canvas.drawRect(300, 250, 100, 100, p);
            // I've commented this out as it may not produce the intended result.
        }
    }
}
