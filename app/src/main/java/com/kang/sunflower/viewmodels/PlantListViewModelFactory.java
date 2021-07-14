package com.kang.sunflower.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.kang.sunflower.data.PlantRepository;

/**
 * Created by BinKang on 2021/7/14.
 * Des :
 */
public class PlantListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    // 仓库数据
    private PlantRepository repository;

    public PlantListViewModelFactory(@NonNull PlantRepository repository) {
        super();
        this.repository = repository;
    }

    // 重写 系统ViewModelProvider 相关的 create函数
    // 以前: 反射
    // 现在： new PlantListViewModel(仓库);  我们自己控制
    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantListViewModel(repository);
    }

}
