package com.kang.sunflower.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.kang.sunflower.databinding.FragmentPlantListBinding;

/**
 * Created by BinKang on 2021/7/12.
 * Des :
 */
public class PlantListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
