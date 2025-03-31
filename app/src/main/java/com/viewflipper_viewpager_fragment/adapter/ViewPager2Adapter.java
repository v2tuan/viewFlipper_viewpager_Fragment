package com.viewflipper_viewpager_fragment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.viewflipper_viewpager_fragment.fragment.NewOrderFragment;
import com.viewflipper_viewpager_fragment.fragment.PickUpFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new NewOrderFragment();
            case 1:
                return new PickUpFragment();
            case 2:
                return new PickUpFragment();
            case 3:
                return new PickUpFragment();
            case 4:
                return new PickUpFragment();
            default:
                return new NewOrderFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
