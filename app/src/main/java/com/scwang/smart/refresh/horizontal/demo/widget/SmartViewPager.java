package com.scwang.smart.refresh.horizontal.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.scwang.smartrefresh.horizontal.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 智能分页器
 */
public class SmartViewPager extends ViewPager {


    private SmartPagerAdapter mAdapter;

    public SmartViewPager(Context context) {
        super(context);
    }

    public SmartViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param id the id of the view to be found
     * @return the view of the specified id, null if cannot be found
     */
    @SuppressWarnings({"unchecked", "unused"})
    protected <T extends View> T findViewTraversal(@IdRes int id) {
        if (mAdapter != null && mAdapter.views != null) {
            for (View view : mAdapter.views) {
                View find = view.findViewById(id);
                if (find != null) {
                    return (T)find;
                }
            }
        }
        for (int i = 0, len = getChildCount(); i < len; i++) {
            View child = getChildAt(i);
            View find = child.findViewById(id);
            if (find != null) {
                return (T)find;
            }
        }
        if (getId() == id) {
            return (T)this;
        }
        return null;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (getAdapter() == null) {
            if (childCount > 0) {
                setAdapter(mAdapter = new SmartPagerAdapter());
                removeAllViews();
            }
        } else if (childCount > 0) {
            removeAllViews();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        int childCount = getChildCount();
        if (getAdapter() == null) {
            if (childCount > 0) {
                setAdapter(mAdapter = new SmartPagerAdapter());
                removeAllViews();
            }
        } else if (childCount > 0) {
            removeAllViews();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAdapter != null) {
            setAdapter(null);
            for (View view : mAdapter.views) {
                addView(view);
            }
            mAdapter = null;
        }
    }

    //<editor-fold desc="布局参数 LayoutParams">
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        final View thisView = this;
        return new LayoutParams(thisView.getContext(), attrs);
    }

    public static class LayoutParams extends ViewPager.LayoutParams {

        LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SmartViewPager_Layout);
            title = ta.getString(R.styleable.SmartViewPager_Layout_layout_svpTitle);
            ta.recycle();
        }

        String title;
    }
    //</editor-fold>

    class SmartPagerAdapter extends PagerAdapter {

        List<View> views;

        SmartPagerAdapter() {
            int count = getChildCount();
            views = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                views.add(getChildAt(i));
            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            View view = views.get(position);
            ViewGroup.LayoutParams lp = view.getLayoutParams();
            if (lp instanceof LayoutParams) {
                if (((LayoutParams) lp).title != null) {
                    return ((LayoutParams) lp).title;
                }
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }
}
