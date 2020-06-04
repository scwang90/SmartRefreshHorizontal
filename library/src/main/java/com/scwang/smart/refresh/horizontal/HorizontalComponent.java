package com.scwang.smart.refresh.horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.scwang.smart.refresh.layout.api.RefreshComponent;
import com.scwang.smart.refresh.layout.simple.SimpleComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * 由于 {@link SmartRefreshHorizontal} 是通过选装 90 度来达到横向效果的
 * 之前所有 Header Footer 虽然可以直接使用，但是他们也都被旋转了 90 度
 * 如果项自定义 不会被旋转的 Header 或者 Footer 可以继承 HorizontalComponent
 * 也可以把 HorizontalComponent 套在 普通 Header 和 Footer 外面达到效果
 */
public abstract class HorizontalComponent extends SimpleComponent {

    private List<View> views = new ArrayList<>();

    protected HorizontalComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mWrappedView = this;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof RefreshComponent) {
                this.mWrappedInternal = (RefreshComponent)child;
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mWrappedInternal == null) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (child instanceof RefreshComponent) {
                    this.mWrappedInternal = (RefreshComponent)child;
                }
            }
        }
    }

    @Override
    public boolean isLayoutRequested() {
        return views.size() > 0 || super.isLayoutRequested();
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        views.clear();
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        for (int i = 0, len = getChildCount(); i < len; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() == VISIBLE) {
                views.add(view);
                view.setVisibility(GONE);
            }
        }
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (View child : views) {
            child.setVisibility(VISIBLE);
        }
        views.clear();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int height = right - left;
        int width = bottom - top;
        super.onLayout(changed, left, top, right, bottom);
        for (int i = 0, len = getChildCount(); i < len; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == VISIBLE) {
                int childLeft = child.getLeft();
                int childTop = child.getTop();
                int childRight = child.getRight();
                int childBottom = child.getBottom();
                int childWidth = childRight - childLeft;
                int childHeight = childBottom - childTop;
                int distLeft = height - childBottom;
                //noinspection SuspiciousNameCombination,UnnecessaryLocalVariable,UnnecessaryLocalVariable
                int distTop = childLeft;
                int rotLeft = (distLeft + childHeight + distLeft) / 2 - childWidth / 2;
                int rotTop = (distTop + childWidth + distTop) / 2 - childHeight / 2;
                child.setRotation(90);
                child.layout(rotLeft, rotTop, rotLeft + childWidth, rotTop + childHeight);
            }
        }

    }

}
