package com.example.videoplayer

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.videoplayer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val videoList = listOf(
        "https://videocdn.cdnpk.net/excite/content/video/premium/video0524/large_watermarked/BOSTON_BOSTON_AERIAL_00085_preview.mp4",
        "https://videocdn.cdnpk.net/videos/c895ee54-18ea-4967-991c-e9fb209bc5d2/horizontal/previews/videvo_watermarked/large.mp4",
        "https://v5.cdnpk.net/videvo_files/video/premium/video0510/large_watermarked/DAYLIGHT_HAMBURG_AERIAL_00048_preview.mp4",
        "https://videocdn.cdnpk.net/joy/content/video/free/2014-12/large_preview/Raindrops_Videvo.mp4?token=exp=1731789328~hmac=af35a11e955d271ddd02e0d19b87ba13713403cff9496840e3889a2bd58020ed"
    )
    private var videoIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttons()
        playVideo(videoIndex)
    }

    private fun buttons() {
        binding.prevBTN.setOnClickListener {
            if (videoIndex > 0) {
                videoIndex--
                playVideo(videoIndex)
            } else {
                videoIndex = videoList.size - 1
            }
        }

        binding.pauseBTN.setOnClickListener {
            if (binding.video.isPlaying) {
                binding.video.pause()
            }
        }

        binding.playBTN.setOnClickListener {
            if (!binding.video.isPlaying) {
                binding.video.start()
            }
        }

        binding.stopBTN.setOnClickListener {
            binding.video.stopPlayback()
            videoIndex = 0
            playVideo(videoIndex)
        }

        binding.nextBTN.setOnClickListener {
            if (videoIndex < videoList.size - 1) {
                videoIndex++
                playVideo(videoIndex)
            } else {
                videoIndex = 0
                playVideo(videoIndex)
            }
        }
    }

    private fun playVideo(index: Int) {
        binding.video.setVideoURI(Uri.parse(videoList[index]))
        binding.video.setOnPreparedListener { binding.video.start() }
    }
}