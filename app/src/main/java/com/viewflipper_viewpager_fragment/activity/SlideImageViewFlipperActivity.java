package com.viewflipper_viewpager_fragment.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.viewflipper_viewpager_fragment.R;

import java.util.ArrayList;
import java.util.List;

public class SlideImageViewFlipperActivity extends AppCompatActivity {
    ViewFlipper viewFlipperMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_image_view_flipper);
        viewFlipperMain = findViewById(R.id.viewFlipperMain);
        actionViewFlipperMain();
    }
    private void actionViewFlipperMain() {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://i.ytimg.com/vi/Cy6aw9ND1QE/maxresdefault.jpg");
        imageUrls.add("https://i.pinimg.com/originals/db/d4/d1/dbd4d1c40f3a03ffd7108cf099f5c6d8.jpg");
        imageUrls.add("https://img.freepik.com/premium-vector/abstract-wave-banner-with-colorful-design_336924-5743.jpg");

        for (String url : imageUrls) {
            ImageView imageView = new ImageView(this);
            // Sử dụng Glide để load ảnh từ URL
            Glide.with(this)
                    .load(url)
                    .into(imageView);

            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);
        }

        viewFlipperMain.setFlipInterval(3000); // 3 giây/lần lật
        viewFlipperMain.setAutoStart(true);

        // Thiết lập animation
        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        viewFlipperMain.setInAnimation(slideIn);
        viewFlipperMain.setOutAnimation(slideOut);
    }
}