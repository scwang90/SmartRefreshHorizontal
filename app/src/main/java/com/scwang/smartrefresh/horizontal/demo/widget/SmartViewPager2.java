package com.scwang.smartrefresh.horizontal.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class SmartViewPager2 extends FrameLayout {

    protected ViewPager2 mPager;
    protected SmartPager2Adapter mAdapter;

    public SmartViewPager2(@NonNull Context context) {
        this(context, null);
    }

    public SmartViewPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPager = new ViewPager2(context, attrs);
        addView(mPager, -1, -1);
    }

    protected List<View> breakViews() {
        int count = getChildCount();
        List<View> views = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            if (view != mPager) {
                views.add(view);
                removeViewAt(i--);
                count--;
            }
        }
        return views;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (mPager.getAdapter() == null) {
            List<View> views = breakViews();
            if (views.size() > 0) {
                mPager.setAdapter(mAdapter = new SmartPager2Adapter(views));
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPager.getAdapter() == null) {
            List<View> views = breakViews();
            if (views.size() > 0) {
                mPager.setAdapter(mAdapter = new SmartPager2Adapter(views));
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAdapter != null) {
            mPager.setAdapter(null);
            for (View view : mAdapter.views) {
                addView(view);
            }
            mAdapter = null;
        }
    }
    static class SmartPager2Adapter extends RecyclerView.Adapter<PagerHolder> {

        List<View> views;

        SmartPager2Adapter(List<View> views) {
            this.views = views;
        }

        @NonNull
        @Override
        public PagerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return PagerHolder.create(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PagerHolder holder, int position) {
            holder.bindView(views.get(position));
        }

        @Override
        public int getItemCount() {
            return views.size();
        }
    }

    public static final class PagerHolder extends RecyclerView.ViewHolder {

        private PagerHolder(@NonNull FrameLayout container) {
            super(container);
        }

        @NonNull static PagerHolder create(@NonNull ViewGroup parent) {
            FrameLayout container = new FrameLayout(parent.getContext());
            container.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
            container.setId(ViewCompat.generateViewId());
            container.setSaveEnabled(false);
            return new PagerHolder(container);
        }

        @NonNull FrameLayout getContainer() {
            return (FrameLayout) itemView;
        }

        public void bindView(View view) {
            FrameLayout frame = getContainer();
            int len = frame.getChildCount();
            if (len > 0) {
                for (int i = 0; i < len; i++) {
                    if (frame.getChildAt(i) == view) {
                        return;
                    }
                }
                frame.removeAllViews();
            }
            ensureAddView(frame, view);
        }

        private void ensureAddView(FrameLayout frame, View view) {
            if (view.getParent() instanceof ViewGroup) {
                ((ViewGroup)view.getParent()).removeView(view);
            }
            frame.addView(view, -1, -1);
        }
    }
}
