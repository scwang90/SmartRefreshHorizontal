package com.scwang.smart.refresh.horizontal.demo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smart.refresh.horizontal.HorizontalFooter;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.horizontal.demo.R;

public class DetailHorizontalFooter extends HorizontalFooter {

    private TextView mTvTitle;
    private ImageView mIvIcon;
    private RefreshKernel mRefreshKernel;

    public DetailHorizontalFooter(Context context) {
        this(context, null);
    }

    public DetailHorizontalFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.widget_footer_detail_horizontal, this);
        mIvIcon = findViewById(R.id.footer_icon);
        mTvTitle = findViewById(R.id.footer_title);
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        super.onInitialized(kernel, height, maxDragHeight);
        mRefreshKernel = kernel;
        kernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    @Override
    public boolean setNoMoreData(boolean noMoreData) {
        return false;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        if (mRefreshKernel != null) {
            mRefreshKernel.setState(RefreshState.None);
            //onReleased 的时候 调用 setState(RefreshState.None); 并不会立刻改变成 None
            //而是先执行一个回弹动画，LoadFinish 是介于 Refreshing 和 None 之间的状态
            //LoadFinish 用于在回弹动画结束时候能顺利改变为 None
            mRefreshKernel.setState(RefreshState.LoadFinish);
        }
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 0;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (newState == RefreshState.ReleaseToLoad) {
            mIvIcon.animate().rotation(270);
            mTvTitle.setText(R.string.footer_detail_horizontal_release);
        } else {
            mIvIcon.animate().rotation(90);
            mTvTitle.setText(R.string.footer_detail_horizontal_pulling);
        }
    }
}
