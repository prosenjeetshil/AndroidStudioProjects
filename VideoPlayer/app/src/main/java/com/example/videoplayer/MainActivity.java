package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize VideoView and MediaController
        videoView = findViewById(R.id.videoView1);
        mediaController = new MediaController(this);

        try {
            // Construct the URI for the video resource. R.raw.tom corresponds to res/raw/please.mp4
            Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.please);

            // Set the video URI to the VideoView
            videoView.setVideoURI(videoUri);

            // Attach the MediaController to the VideoView
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);

            // Start video playback
            videoView.start();
        } catch (Exception e) {
            // Handle cases where the video file might not be found
            e.printStackTrace();
            Toast.makeText(this, "Error: Video file not found or could not be loaded.", Toast.LENGTH_LONG).show();
        }
    }
}