package com.example.binder.activity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.binder.databinding.ActivityVedioBinding;

import java.io.IOException;

public class VedioActivity extends AppCompatActivity {

    private MediaPlayer player;
    private ActivityVedioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVedioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnStart.setOnClickListener(v -> {
            if (binding.btnStart.getText().equals("开始播放")) {
                binding.btnStart.setText("停止播放");
                player.start();

            } else {
                binding.btnStart.setText("开始播放");
                player.pause();
            }
        });
        setSurface();
    }

    private void setSurface() {
        binding.surfaceVedio.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                try {
                    player = new MediaPlayer();
                    player.setLooping(true);
                    AssetFileDescriptor descriptor = getAssets().openFd("test.mp4");
                    player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
                    player.setDisplay(holder);
                    player.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                if (player != null) {
                    player.stop();
                    player.release();
                    player = null;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
            player = null;
        }
    }
}