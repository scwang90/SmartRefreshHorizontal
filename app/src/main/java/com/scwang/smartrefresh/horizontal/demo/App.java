package com.scwang.smartrefresh.horizontal.demo;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.horizontal.SmartRefreshHorizontal;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.*;
import com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper;

public class App extends Application {
    static {
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            }
        });

        SmartRefreshHorizontal.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableAutoLoadMore(false);
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            }
        });
        SmartRefreshHorizontal.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableHeaderTranslationContent(true);
                return new MaterialHeader(context);
            }
        });
        SmartRefreshHorizontal.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableFooterTranslationContent(true);
                return new RefreshFooterWrapper(new MaterialHeader(context));
            }
        });
    }
}
