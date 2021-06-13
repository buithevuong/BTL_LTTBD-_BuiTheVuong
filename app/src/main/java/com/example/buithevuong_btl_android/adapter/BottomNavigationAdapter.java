package com.example.buithevuong_btl_android.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.buithevuong_btl_android.fragment.AccountFragment;
import com.example.buithevuong_btl_android.fragment.HistoryFragment;
import com.example.buithevuong_btl_android.fragment.HomeFragment;
import com.example.buithevuong_btl_android.fragment.SearchFragment;

public class BottomNavigationAdapter extends FragmentStatePagerAdapter {
    private int numPage = 4;

    public BottomNavigationAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:return new HomeFragment();
            case 1:return new SearchFragment();
            case 2:return new HistoryFragment();
            case 3:return new AccountFragment();
            default:return  new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
