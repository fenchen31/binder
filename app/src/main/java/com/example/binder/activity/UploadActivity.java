package com.example.binder.activity;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.binder.R;
import com.example.binder.databinding.ActivityUploadBinding;
import com.example.common.utils.DpPx;

/**
 * @author 黎亮亮
 * @Date 2024/12/9
 * @describe
 */
public class UploadActivity extends AppCompatActivity {

    private static final String TAG = UploadActivity.class.getSimpleName();
    private ActivityUploadBinding binding;
    private Handler handler;
    public static final int SEND_PROGRESS = 1001;
    private int progress = 0;
    private AsyncTask task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
        setProgress();
        setPictureAndScan();
    }

    private void setPictureAndScan() {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{ContextCompat.getColor(this, R.color.color_FF000000),
                        ContextCompat.getColor(this, R.color.color_FFFFFF),
                        ContextCompat.getColor(this, R.color.color_FF000000)});
        binding.viewScan.setBackground(drawable);
        ObjectAnimator animator = ObjectAnimator.ofFloat(binding.viewScan, "translationY", 0, DpPx.dp2px(this, 400));
        animator.setRepeatMode(ObjectAnimator.REVERSE);
        animator.setRepeatCount(-1);
        animator.setDuration(3000);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_beautiful_girl);
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedDrawable.setCornerRadius(DpPx.dp2px(this, 4));
        binding.ivPic.setImageDrawable(roundedDrawable);
    }

    private void setProgress() {
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == SEND_PROGRESS) {
                    if (msg.arg1 == 110) {
                        progress = 0;
                        binding.tvProgress.setText(0 + "%");
                    } else {
                        binding.tvProgress.setText(msg.arg1 + "%");
                    }
                    binding.progressBar.setProgress(progress);
                }
            }
        };
        task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                while (true) {
                    Message message = handler.obtainMessage();
                    message.what = SEND_PROGRESS;
                    message.arg1 = progress += 10;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        task.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        task.cancel(true);
        handler.removeCallbacksAndMessages(SEND_PROGRESS);
    }
}
