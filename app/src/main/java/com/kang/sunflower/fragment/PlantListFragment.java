package com.kang.sunflower.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kang.sunflower.adapters.PlantAdapter;
import com.kang.sunflower.data.Plant;
import com.kang.sunflower.databinding.FragmentPlantListBinding;
import com.kang.sunflower.utils.InjectorUtils;
import com.kang.sunflower.viewmodels.PlantListViewModel;
import com.kang.sunflower.viewmodels.PlantListViewModelFactory;

import java.util.List;

/**
 * Created by BinKang on 2021/7/12.
 * Des :
 */
public class PlantListFragment extends Fragment {

    // 植物目录 列表 的 ViewModel
    private PlantListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentPlantListBinding binding = FragmentPlantListBinding.inflate(inflater, container, false);

        // 暴漏 植物 列表 ViewModel 工厂 (数据初始化的起点)      // 开启WM任务 insert room数据库
        PlantListViewModelFactory factory = InjectorUtils.providePlantListViewModelFactory(getContext());

        PlantAdapter adapter = new PlantAdapter();

        binding.plantList.setAdapter(adapter);

        this.viewModel = ViewModelProviders.of(this, factory).get(PlantListViewModel.class);

        subscribeUi(adapter);

        return binding.getRoot();
    }

    private void subscribeUi(PlantAdapter adapter) {
        this.viewModel.plants.observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
                adapter.submitList(plants);
            }
        });
    }
}
