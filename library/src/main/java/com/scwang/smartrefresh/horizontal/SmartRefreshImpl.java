package com.scwang.smartrefresh.horizontal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;

public class SmartRefreshImpl extends SmartRefreshLayout {

    public SmartRefreshImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mRefreshContent != null && !(mRefreshContent instanceof RefreshContentHorizontal)) {
            mRefreshContent = new RefreshContentHorizontal(mRefreshContent.getView());
            View fixedHeaderView = mFixedHeaderViewId > 0 ? findViewById(mFixedHeaderViewId) : null;
            View fixedFooterView = mFixedFooterViewId > 0 ? findViewById(mFixedFooterViewId) : null;

            mRefreshContent.setScrollBoundaryDecider(mScrollBoundaryDecider);
            mRefreshContent.setEnableLoadMoreWhenContentNotFull(mEnableLoadMoreWhenContentNotFull);
            mRefreshContent.setUpComponent(mKernel, fixedHeaderView, fixedFooterView);
        }
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    static DefaultRefreshInitializer getRefreshInitializer() {
        return SmartRefreshImpl.sRefreshInitializer;
    }

    static void setRefreshInitializer(DefaultRefreshInitializer sRefreshInitializer) {
        SmartRefreshImpl.sRefreshInitializer = sRefreshInitializer;
    }
}
