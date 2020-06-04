package com.scwang.smart.refresh.horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smart.refresh.layout.api.RefreshHeader;

/**
 * 由于 {@link SmartRefreshHorizontal} 是通过选装 90 度来达到横向效果的
 * 之前所有 Header 虽然可以直接使用，但是他们也都被旋转了 90 度
 * 如果项自定义 不会被旋转的 Header 可以继承 HorizontalHeader
 * 也可以把 HorizontalHeader 套在 普通 Header 外面达到效果
 */
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
