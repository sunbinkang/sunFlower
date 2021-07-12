package com.kang.sunflower.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kang.sunflower.databinding.FragmentGardenBinding;

/**
 * Created by BinKang on 2021/7/12.
 * Des :
 */
public class GardenFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 使用 DataBinding的布局文件

        FragmentGardenBinding binding = FragmentGardenBinding.inflate(inflater, container, false);

        

        return binding.getRoot();
    }
}
