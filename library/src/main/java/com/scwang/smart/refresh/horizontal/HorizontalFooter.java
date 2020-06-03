package com.scwang.smart.refresh.horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smart.refresh.layout.api.RefreshFooter;

public class HorizontalFooter extends HorizontalComponent implements RefreshFooter {

    public HorizontalFooter(View view) {
        this(view.getContext());
        this.addView(view);
    }

    public HorizontalFooter(Context context) {
        this(context, null);
    }

    public HorizontalFooter(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }


}
