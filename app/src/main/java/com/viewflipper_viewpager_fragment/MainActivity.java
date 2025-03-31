package com.viewflipper_viewpager_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.service.controls.actions.FloatAction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.viewflipper_viewpager_fragment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPager2Adapter viewPager2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Kích hoạt SplashScreen API
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(binding.toolBar);

        FloatingActionButton button = binding.fabAction;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        binding.tabLayout.addTab((binding.tabLayout.newTab().setText("Xác nhận")));
        binding.tabLayout.addTab((binding.tabLayout.newTab().setText("Lấy hàng")));
        binding.tabLayout.addTab((binding.tabLayout.newTab().setText("Đang giao")));
        binding.tabLayout.addTab((binding.tabLayout.newTab().setText("Đánh giá")));
        binding.tabLayout.addTab((binding.tabLayout.newTab().setText("Hủy")));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(viewPager2Adapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
                changeFabIcon(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuSearch) {
            Toast.makeText(this, "Bạn đang chọn Search", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuNewGroup) {
            Toast.makeText(this, "Bạn đang chọn New Group", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuNewBroadcast) {
            Toast.makeText(this, "Bạn đang chọn New Broadcast", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuWhatsAppWeb) {
            Toast.makeText(this, "Bạn đang chọn WhatsApp Web", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuMessages) {
            Toast.makeText(this, "Bạn đang chọn Messages", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuSettings) {
            Toast.makeText(this, "Bạn đang chọn Settings", Toast.LENGTH_SHORT).show();
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }



    private void changeFabIcon(final int index){
        binding.fabAction.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (index){
                    case 0:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
                        break;
                    case 1:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_launcher_background));
                        break;
                    case 2:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_launcher_foreground));
                        break;
                }
                binding.fabAction.show();
            }
        }, 2000);
    }
}