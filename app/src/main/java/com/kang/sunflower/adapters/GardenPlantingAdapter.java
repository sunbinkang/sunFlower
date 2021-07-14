package com.kang.sunflower.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kang.sunflower.R;
import com.kang.sunflower.data.PlantAndGardenPlantings;
import com.kang.sunflower.databinding.ListItemGardenPlantingBinding;
import com.kang.sunflower.viewmodels.PlantAndGardenPlantingsViewModel;

/**
 * Created by BinKang on 2021/7/12.
 * Des :
 */
public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {

    public GardenPlantingAdapter() {
        super(new GardenPlantDiffCallback());
    }

    @NonNull
    @Override
    public GardenPlantingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item_garden_planting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GardenPlantingAdapter.ViewHolder holder, int position) {
        PlantAndGardenPlantings item = getItem(position);
        holder.itemView.setTag(item);
        holder.bindData(item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ListItemGardenPlantingBinding binding; // item 每一项

        public ViewHolder(@NonNull ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(PlantAndGardenPlantings item) {
            this.binding.setViewModel(new PlantAndGardenPlantingsViewModel(item));
            this.binding.executePendingBindings();
        }
    }

    public static class GardenPlantDiffCallback extends DiffUtil.ItemCallback<PlantAndGardenPlantings> {

        @Override
        public boolean areItemsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
            return oldItem.getPlant().getPlantId().equals(newItem.getPlant().getPlantId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
            return oldItem.equals(newItem);
        }
    }
}
