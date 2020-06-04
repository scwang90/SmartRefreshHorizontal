package com.scwang.smartrefresh.horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshFooter;

/**
 * 由于 {@link SmartRefreshHorizontal} 是通过选装 90 度来达到横向效果的
 * 之前所有 Footer 虽然可以直接使用，但是他们也都被旋转了 90 度
 * 如果项自定义 不会被旋转的 Footer 可以继承 HorizontalFooter
 * 也可以把 HorizontalFooter 套在 和 Footer 外面达到效果
 */
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