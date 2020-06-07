package com.scwang.smartrefresh.horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

@SuppressWarnings("unused")
public class SmartRefreshHorizontal extends SmartRefreshLayout {

    protected static DefaultRefreshHeaderCreator sHeaderCreator = null;
    protected static DefaultRefreshFooterCreator sFooterCreator = null;
    protected static DefaultRefreshInitializer sRefreshInitializer = null;

    protected boolean isInLayout = false;

    public SmartRefreshHorizontal(Context context) {
        this(context, null);
    }

    public SmartRefreshHorizontal(Context context, AttributeSet attrs) {
        super(wrapper(context), attrs);
        setScrollBoundaryDecider(new ScrollBoundaryHorizontal());
    }

    protected static Context wrapper(Context context) {
        DefaultRefreshInitializer old = SmartRefreshLayout.sRefreshInitializer;
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultHorizontalInitializer(sRefreshInitializer, old));
        return context;
    }


    //<editor-fold desc="开发接口">
    /**
     * 设置默认 Header 构建器
     * @param creator Header构建器
     */
    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultRefreshHeaderCreator creator) {
        sHeaderCreator = creator;
    }

    /**
     * 设置默认 Footer 构建器
     * @param creator Footer构建器
     */
    public static void setDefaultRefreshFooterCreator(@NonNull DefaultRefreshFooterCreator creator) {
        sFooterCreator = creator;
    }

    /**
     * 设置默认 Refresh 初始化器
     * @param initializer 全局初始化器
     */
    public static void setDefaultRefreshInitializer(@NonNull DefaultRefreshInitializer initializer) {
        sRefreshInitializer = initializer;
    }
    //</editor-fold>

    //<editor-fold desc="重写方法">

    @Override
    protected void onAttachedToWindow() {
        DefaultRefreshHeaderCreator oldHeaderCreator = SmartRefreshLayout.sHeaderCreator;
        DefaultRefreshFooterCreator oldFooterCreator = SmartRefreshLayout.sFooterCreator;
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(sHeaderCreator);
        SmartRefreshLayout.setDefaultRefreshFooterCreator(sFooterCreator);
        super.onAttachedToWindow();
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(oldHeaderCreator);
        SmartRefreshLayout.setDefaultRefreshFooterCreator(oldFooterCreator);

        final View thisView = this;
        if (mRefreshContent != null && !(mRefreshContent instanceof RefreshContentHorizontal)) {
            mRefreshContent = new RefreshContentHorizontal(mRefreshContent.getView());
            View fixedHeaderView = mFixedHeaderViewId > 0 ? thisView.findViewById(mFixedHeaderViewId) : null;
            View fixedFooterView = mFixedFooterViewId > 0 ? thisView.findViewById(mFixedFooterViewId) : null;

            mRefreshContent.setScrollBoundaryDecider(mScrollBoundaryDecider);
            mRefreshContent.setEnableLoadMoreWhenContentNotFull(mEnableLoadMoreWhenContentNotFull);
            mRefreshContent.setUpComponent(mKernel, fixedHeaderView, fixedFooterView);
        }

        thisView.setRotation(-90);
    }


    protected boolean isRefreshComponent(View child) {
        RefreshInternal header = mRefreshHeader;
        RefreshInternal footer = mRefreshFooter;
        return (header != null && (child == header || child == header.getView())) ||
                (footer != null && (child == footer || child == footer.getView())) ;
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        for (int i = 0, len = getChildCount(); i < len; i++) {
            View child = getChildAt(i);
            child.setTag(R.string.srl_component_falsify, isRefreshComponent(child) ? child : "VISIBLE");
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.EXACTLY);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY);
        }
        for (int i = 0, len = getChildCount(); i < len; i++) {
            View child = getChildAt(i);
            child.setTag(R.string.srl_component_falsify, isRefreshComponent(child) ? "VISIBLE" : child);
        }
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int width = right - left;
        int height = bottom - top;
        int div = (height - width) / 2;
        if (isInLayout) {
            RefreshInternal header = mRefreshHeader;
            RefreshInternal footer = mRefreshFooter;

            final View thisView = this;
            int paddingLeft = thisView.getPaddingLeft();
            int paddingRight = thisView.getPaddingRight();
            int paddingTop = thisView.getPaddingTop();
            int paddingBottom = thisView.getPaddingBottom();

            for (int i = 0, len = getChildCount(); i < len; i++) {
                View child = getChildAt(i);
                if (!isRefreshComponent(child) && child.getVisibility() != GONE) {
                    int t = paddingLeft;
                    int w = child.getMeasuredWidth();
                    int h = child.getMeasuredHeight();
                    int r = width - paddingTop;
//
                    ViewGroup.LayoutParams params = child.getLayoutParams();
                    if (params instanceof MarginLayoutParams) {
                        MarginLayoutParams lp = (MarginLayoutParams) params;
                        t += lp.leftMargin;
                        r -= lp.topMargin;
                    }
//
                    div = (h - w) / 2;
                    t -= div;
                    r -= div;
//
                    child.setRotation(90);
                    child.setTag(R.string.srl_component_falsify, child);
                    child.layout(r - w, t, r, t + h);
                }
            }
            super.onLayout(changed, left, top, right, bottom);
        } else {
            top -= div;
            left += div;
            isInLayout = true;
            super.layout(left, top, left + width, top + height);
            isInLayout = false;
        }

    }

    //</editor-fold>

//    //<editor-fold desc="问题修复">
//
//    /**
//     * 问题修复
//     * https://github.com/scwang90/SmartRefreshHorizontal/issues/9
//     * @param visibility 显示状态
//     */
//    @Override
//    public void setVisibility(int visibility) {
//        mRefreshLayout.setVisibility(visibility);
//        super.setVisibility(visibility);
//    }
//    //</editor-fold>

    @SuppressWarnings("WeakerAccess")
    protected static class DefaultHorizontalInitializer implements DefaultRefreshInitializer {
        protected DefaultRefreshInitializer initializer;
        protected DefaultRefreshInitializer oldInitializer;

        protected DefaultHorizontalInitializer(DefaultRefreshInitializer initializer, DefaultRefreshInitializer old) {
            this.oldInitializer = old;
            this.initializer = initializer;
        }

        @Override
        public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
            layout.setEnableLoadMore(true);
            if (initializer != null) {
                initializer.initialize(context, layout);
            }
            if (oldInitializer != null) {
                SmartRefreshLayout.setDefaultRefreshInitializer(oldInitializer);
            }
        }
    }
}
