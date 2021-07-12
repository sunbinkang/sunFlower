package com.kang.sunflower.bindings;

import android.view.View;

/**
 * Created by BinKang on 2021/7/12.
 * Des :
 */
public class BindingAdapter {

    @androidx.databinding.BindingAdapter("isGone")
    public static void bindISGone(View view, boolean isGone) {
        view.setVisibility(isGone ? View.GONE : View.VISIBLE);
    }

}
