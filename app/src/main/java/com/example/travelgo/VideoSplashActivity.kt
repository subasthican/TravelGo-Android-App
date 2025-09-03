package com.example.travelgo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class VideoSplashActivity : AppCompatActivity() {

    private lateinit var videoView: FullScreenVideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // fullscreen
        setContentView(R.layout.activity_video_splash)

        videoView = findViewById(R.id.video_view)
        

        val videoPath = "android.resource://" + packageName + "/" + R.raw.lanchinglogo
        videoView.setVideoURI(Uri.parse(videoPath))
        

        videoView.setOnCompletionListener {

            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
        
        // Set error listener
        videoView.setOnErrorListener { mp, what, extra ->

            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
            true
        }
        

        videoView.setOnPreparedListener { mp ->

            mp.setVideoScalingMode(android.media.MediaPlayer.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING)

            videoView.start()
        }
    }

    override fun onResume() {
        super.onResume()

        if (!videoView.isPlaying) {
            videoView.start()
        }
    }

    override fun onPause() {
        super.onPause()

        if (videoView.isPlaying) {
            videoView.pause()
        }
    }
}
