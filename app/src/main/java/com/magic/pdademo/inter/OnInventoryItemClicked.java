package com.magic.pdademo.inter;

/**
 * create by zj on 2019/5/24
 */
public interface OnInventoryItemClicked {
    void onItemClicked(int pos, String id);
    void onDelClicked(int pos);
}
