package com.scwang.smartrefresh.horizontal.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.horizontal.HorizontalFooter;
import com.scwang.smartrefresh.horizontal.demo.R;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

public class DetailHorizontalFooter extends HorizontalFooter {

    private TextView mTvTitle;
    private ImageView mIvIcon;
    private RefreshKernel mRefreshKernel;

    public DetailHorizontalFooter(Context context) {
        this(context, null);
    }

    /**
     * 构造函数中 加载xml布局，并获取控件
     */
    public DetailHorizontalFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.widget_footer_detail_horizontal, this);
        mIvIcon = findViewById(R.id.footer_icon);
        mTvTitle = findViewById(R.id.footer_title);
    }

    /**
     * 初始化中 保存 kernel 核心对象，并关闭自动加载功能
     */
    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        super.onInitialized(kernel, height, maxDragHeight);
        mRefreshKernel = kernel;
        kernel.getRefreshLayout().setEnableAutoLoadMore(false);
    }

    /**
     * 重写 onStartAnimator，在开始动画的时候，直接关闭刷新，
     * 因为本Footer不需要任何加载，仅仅触发事件之后直接关闭
     */
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

    /**
     * 状态改变时 更新界面
     */
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
