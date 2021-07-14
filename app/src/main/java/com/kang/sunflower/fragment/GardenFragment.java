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

import com.kang.sunflower.adapters.GardenPlantingAdapter;
import com.kang.sunflower.data.GardenPlanting;
import com.kang.sunflower.data.PlantAndGardenPlantings;
import com.kang.sunflower.databinding.FragmentGardenBinding;
import com.kang.sunflower.utils.InjectorUtils;
import com.kang.sunflower.viewmodels.GardenPlantingListViewModel;
import com.kang.sunflower.viewmodels.GardenPlantingListViewModelFactory;

import java.util.List;

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

        GardenPlantingAdapter adapter = new GardenPlantingAdapter();
        binding.gardenList.setAdapter(adapter);

        // 数据的关联：极其复杂
        subScribeUi(adapter, binding);

        return binding.getRoot();
    }

    private void subScribeUi(GardenPlantingAdapter adapter, FragmentGardenBinding binding) {

        GardenPlantingListViewModelFactory factory = InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext());

        GardenPlantingListViewModel gardenPlantingListViewModel = ViewModelProviders.of(this, factory).get(GardenPlantingListViewModel.class);

        gardenPlantingListViewModel.gardenPlantings.observe(getViewLifecycleOwner(), new Observer<List<GardenPlanting>>() {
            @Override
            public void onChanged(List<GardenPlanting> gardenPlantings) {
                binding.setHasPlantings(gardenPlantings != null && !gardenPlantings.isEmpty());
            }
        });

        gardenPlantingListViewModel.plantAndGardenPlantings.observe(getViewLifecycleOwner(), new Observer<List<PlantAndGardenPlantings>>() {
            @Override
            public void onChanged(List<PlantAndGardenPlantings> plantAndGardenPlantings) {
                if (plantAndGardenPlantings != null && !plantAndGardenPlantings.isEmpty()) {
                    adapter.submitList(plantAndGardenPlantings);
                }
            }
        });
    }
}
