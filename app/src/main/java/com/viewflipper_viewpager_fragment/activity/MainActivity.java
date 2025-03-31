package com.viewflipper_viewpager_fragment.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.viewflipper_viewpager_fragment.R;
import com.viewflipper_viewpager_fragment.adapter.ViewPager2Adapter;
import com.viewflipper_viewpager_fragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPager2Adapter viewPager2Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
// ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Toolbar
        setSupportActionBar(binding.toolBar);

        FloatingActionButton fab = binding.fabAction;
        fab.setOnClickListener(view ->
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
        );

        // Thêm các tab
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Xác nhận"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Lấy hàng"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đang giao"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Đánh giá"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Hủy"));

        // Khởi tạo ViewPager2
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(viewPager2Adapter);

        // Lắng nghe sự kiện chọn tab
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
                changeFabIcon(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        // Đồng bộ ViewPager2 với TabLayout
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSearch) {
            Toast.makeText(this, "Bạn đang chọn search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menuNewGroup) {
            Toast.makeText(this, "Bạn đang chọn more", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menuNewBroadcast) {
            Toast.makeText(this, "Bạn đang chọn more", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menuWhatsAppWeb) {
            Toast.makeText(this, "Bạn đang chọn more", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menuMessages) {
            Toast.makeText(this, "Bạn đang chọn more", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.menuSettings) {
            Toast.makeText(this, "Bạn đang chọn Setting", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    // Hàm thay đổi icon của FloatingActionButton theo tab
    private void changeFabIcon(final int index) {
        new Handler().postDelayed(() -> {
            switch (index) {
                case 0:
                    binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_2));
                    break;
                case 1:
                    binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_1));
                    break;
                case 2:
                    binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_3));
                    break;
            }
            binding.fabAction.show();
        }, 0);

    }
}