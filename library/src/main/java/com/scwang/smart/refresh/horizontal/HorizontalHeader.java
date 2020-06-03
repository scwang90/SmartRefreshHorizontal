package com.scwang.smart.refresh.horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smart.refresh.layout.api.RefreshHeader;

public class HorizontalHeader extends HorizontalComponent implements RefreshHeader {

    public HorizontalHeader(View view) {
        this(view.getContext());
        this.addView(view);
    }

    public HorizontalHeader(Context context) {
        this(context, null);
    }

    public HorizontalHeader(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }


}
