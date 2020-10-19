package com.czf.recyclerviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class MTTableView extends HorizontalScrollView {

    private ScrollView mScrollView;

    public MTTableView(Context context) {
        this(context, null);
    }

    public MTTableView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScrollView = (ScrollView) LayoutInflater.from(context).inflate(R.layout.layout_mt_tableview, null);
        addView(mScrollView);
    }

}
