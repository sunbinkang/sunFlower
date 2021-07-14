package com.kang.sunflower.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kang.sunflower.data.GardenPlantingRepository;

/**
 * Created by BinKang on 2021/7/13.
 * Des :
 */
public class GardenPlantingListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private GardenPlantingRepository repository;

    public GardenPlantingListViewModelFactory(GardenPlantingRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new GardenPlantingListViewModel(repository);
    }
}
