package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
    }

    // Method called by the "Move" button
    public void move(View view) {
        startAnimation(R.anim.move);
    }

    // Method called by the "Fade" button
    public void fade(View view) {
        startAnimation(R.anim.fade_in);
    }

    // Method called by the "Rotate" button
    public void rotate(View view) {
        startAnimation(R.anim.rotate);
    }

    // Method called by the "Blink" button
    public void blink(View view) {
        startAnimation(R.anim.blink);
    }

    // Helper method to load and start an animation
    private void startAnimation(int animationResource) {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), animationResource);
        imageView.startAnimation(animation);
    }
}