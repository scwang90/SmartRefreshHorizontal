package com.scwang.smart.refresh.horizontal;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.scwang.smart.refresh.layout.simple.SimpleBoundaryDecider;
import com.scwang.smart.refresh.layout.util.SmartUtil;

/**
 * 滚动边界
 * Created by SCWANG on 2017/7/8.
 */
@SuppressWarnings("WeakerAccess")
public class ScrollBoundaryHorizontal extends SimpleBoundaryDecider {

    //<editor-fold desc="刷新判断">
    @Override
    public boolean canRefresh(View content) {
        return canRefresh(content, mActionEvent);
    }
    @Override
    public boolean canLoadMore(View content) {
        return canLoadMore(content, mActionEvent, mEnableLoadMoreWhenContentNotFull);
    }
    //</editor-fold>

    //<editor-fold desc="滚动判断">
    /**
     * 判断内容是否可以刷新
     * @param targetView 内容视图
     * @param touch 按压事件位置
     * @return 是否可以刷新
     */
    public static boolean canRefresh(@NonNull View targetView, PointF touch) {
        if (canScrollLeft(targetView) && targetView.getVisibility() == View.VISIBLE) {
            return false;
        }
        //touch == null 时 canRefresh 不会动态递归搜索
        if (targetView instanceof ViewGroup && touch != null) {
            ViewGroup viewGroup = (ViewGroup) targetView;
            final int childCount = viewGroup.getChildCount();
            PointF point = new PointF();
            for (int i = childCount; i > 0; i--) {
                View child = viewGroup.getChildAt(i - 1);
                if (SmartUtil.isTransformedTouchPointInView(viewGroup, child, touch.x, touch.y, point)) {
                    if ("fixed".equals(child.getTag())) {
                        return false;
                    }
                    touch.offset(point.x, point.y);
                    boolean can = canRefresh(child, touch);
                    touch.offset(-point.x, -point.y);
                    return can;
                }
            }
        }
        return true;
    }

    /**
     * 判断内容视图是否可以加载更多
     * @param targetView 内容视图
     * @param touch 按压事件位置
     * @param contentFull 内容是否填满页面 (未填满时，会通过canScrollUp自动判断)
     * @return 是否可以刷新
     */
    public static boolean canLoadMore(@NonNull View targetView, PointF touch, boolean contentFull) {
        if (canScrollRight(targetView) && targetView.getVisibility() == View.VISIBLE) {
            return false;
        }
        //touch == null 时 canLoadMore 不会动态递归搜索
        if (targetView instanceof ViewGroup && touch != null && !SmartUtil.isScrollableView(targetView)) {
            ViewGroup viewGroup = (ViewGroup) targetView;
            final int childCount = viewGroup.getChildCount();
            PointF point = new PointF();
            for (int i = 0; i < childCount; i++) {
                View child = viewGroup.getChildAt(i);
                if (SmartUtil.isTransformedTouchPointInView(viewGroup, child, touch.x, touch.y, point)) {
                    if ("fixed".equals(child.getTag())) {
                        return false;
                    }
                    touch.offset(point.x, point.y);
                    boolean can = canLoadMore(child, touch, contentFull);
                    touch.offset(-point.x, -point.y);
                    return can;
                }
            }
        }
        return (contentFull || canScrollLeft(targetView));
    }

    public static boolean canScrollLeft(@NonNull View targetView) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (targetView instanceof AbsListView) {
                final ViewGroup viewGroup = (ViewGroup) targetView;
                final AbsListView absListView = (AbsListView) targetView;
                return viewGroup.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0
                        || viewGroup.getChildAt(0).getTop() < targetView.getPaddingTop());
            } else {
                return targetView.getScrollY() > 0;
            }
        } else {
            return targetView.canScrollHorizontally(-1);
        }
    }

    public static boolean canScrollRight(@NonNull View targetView) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (targetView instanceof AbsListView) {
                final ViewGroup viewGroup = (ViewGroup) targetView;
                final AbsListView absListView = (AbsListView) targetView;
                final int childCount = viewGroup.getChildCount();
                return childCount > 0 && (absListView.getLastVisiblePosition() < childCount - 1
                        || viewGroup.getChildAt(childCount - 1).getBottom() > targetView.getPaddingBottom());
            } else {
                return targetView.getScrollY() < 0;
            }
        } else {
            return targetView.canScrollHorizontally(1);
        }
    }

    //</editor-fold>

}
