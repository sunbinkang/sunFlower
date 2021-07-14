package com.kang.sunflower.bindings;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.kang.sunflower.R;

/**
 * Created by BinKang on 2021/7/13.
 * Des :
 */
public class PlantDetailBindingAdapters {

    /**
     * 显示图片用的
     * 谁在使用此BindingAdapter =====
     *  PlantDetailFragment---fragment_plant_detail.xml(   app:imageFromUrl="@{viewModel.plant.imageUrl}" )
     *                        list_item_garden_planting.xml( app:imageFromUrl="@{viewModel.imageUrl}" )
     *                        list_item_plant.xml( app:imageFromUrl="@{plant.imageUrl}" )
     */
    @BindingAdapter("imageFromUrl")
    public static void bindImageFromUrl(ImageView view, String imageUrl) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Glide.with(view.getContext())
                    .load(imageUrl)
                    .apply(new RequestOptions().error(R.drawable.ic_launcher_background))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(view);
        }
    }

}
