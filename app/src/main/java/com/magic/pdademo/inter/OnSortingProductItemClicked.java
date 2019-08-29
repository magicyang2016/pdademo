package com.magic.pdademo.inter;

import android.view.View;

/**
 * create by zj on 2019/5/22
 */
public interface OnSortingProductItemClicked {
    void onMoreClicked(int position, View view);
    void onDel(int position);
}
