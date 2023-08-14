package com.android.demo1;

import android.view.View;

import com.android.demo1.Models.Shoe;

public interface IOnClickShoe {
    void clickMore(Shoe shoe, int position, View view);
}
