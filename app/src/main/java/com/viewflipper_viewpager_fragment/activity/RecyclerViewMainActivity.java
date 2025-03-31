package com.viewflipper_viewpager_fragment.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viewflipper_viewpager_fragment.LinePagerIndicatorDecoration;
import com.viewflipper_viewpager_fragment.R;
import com.viewflipper_viewpager_fragment.adapter.IconAdapter;
import com.viewflipper_viewpager_fragment.model.IconModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewMainActivity extends AppCompatActivity {
    private RecyclerView rcIcon;
    private IconAdapter iconAdapter;
    private SearchView searchView;

    // ✅ Khai báo arrayList1 là biến toàn cục
    private ArrayList<IconModel> arrayList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rcIcon = findViewById(R.id.rcIcon);
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();

        // ✅ Khởi tạo arrayList1
        arrayList1 = new ArrayList<>();
        arrayList1.add(new IconModel(R.drawable.ic_7, "fjdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.ic_1, "sdfdf sfdsf"));
        arrayList1.add(new IconModel(R.drawable.ic_2, "sdfdf sfds"));
        arrayList1.add(new IconModel(R.drawable.ic_3, "dfgfhyh sxdff"));
        arrayList1.add(new IconModel(R.drawable.ic_4, "fjdjfdjf djfdh"));
        arrayList1.add(new IconModel(R.drawable.ic_5, "sdfdf sfdsf"));
        arrayList1.add(new IconModel(R.drawable.ic_6, "sdfdf sfds"));
        arrayList1.add(new IconModel(R.drawable.ic_8, "dfgfhyh sxdff"));
        arrayList1.add(new IconModel(R.drawable.ic_1, "dfgfhyh sxdff"));

        // Thiết lập RecyclerView với GridLayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                RecyclerViewMainActivity.this,
                1,
                GridLayoutManager.HORIZONTAL,
                false
        );
        rcIcon.setLayoutManager(gridLayoutManager);

        // Khởi tạo Adapter và gán vào RecyclerView
        iconAdapter = new IconAdapter(arrayList1, getApplicationContext());
        rcIcon.setAdapter(iconAdapter);

        // Thêm LinePagerIndicatorDecoration
        rcIcon.addItemDecoration(new LinePagerIndicatorDecoration());

        // Sự kiện tìm kiếm
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    // ✅ Phương thức filterList
    private void filterList(String text) {
        List<IconModel> filteredList = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if (iconModel.getDesc().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(iconModel);
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            iconAdapter.setFilteredList(filteredList);
        }
    }
}