# Android横向智能刷新框架-SmartRefreshHorizontal

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2012%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=12)
[![JCenter](https://img.shields.io/badge/%20JCenter%20-0.0.1-5bc0de.svg)](https://bintray.com/scwang90/maven/SmartRefreshHorizontal/_latestVersion)
[![Author](https://img.shields.io/badge/Author-scwang90-11bbff.svg)](https://github.com/scwang90)

## [English](https://github.com/scwang90/SmartRefreshHorizontal/blob/master/README_EN.md) | 中文

SmartRefreshHorizontal 作为 SmartRefreshLayout 的扩展库，实现了横向刷新和加载的功能。
虽然作为一个独立的开源库，但是并没有重复做实现，
而是对 SmartRefreshLayout 做了封装和转换，使其支持横向刷新。
所以 SmartRefreshHorizontal 继承了 SmartRefreshLayout 所有的特性，只是方向改成了横向。

## 特点功能:

 - 支持 横向滚动
 - 支持 [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout) 的所有特性
 - 支持 AndroidX

## 传送门

 - [属性文档](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md)
 - [常见问题](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_faq.md)
 - [更新日志](https://github.com/scwang90/SmartRefreshHorizontal/blob/master/art/md_update.md)
 - [博客文章(尽请期待)](#)
 - [源码下载](https://github.com/scwang90/SmartRefreshHorizontal/releases)
 - [自定义Header](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_custom.md)

## Demo
[下载 APK-Demo](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/app-release.apk)

![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/png_apk_rqcode.png)

#### 效果演示
|商品详情|ViewPager|
|:---:|:---:|
|![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/gif_goods.gif)|![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/gif_pager.gif)|

|数据演示|
|:---:|
|![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/gif_basic.gif)|

## 简单用例

### 1. 在 build.gradle 中添加依赖
```gradle
    implementation 'com.scwang.smartrefresh:SmartRefreshHorizontal:1.0.0-beta-1'
    implementation 'com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-beta-1'//必须依赖 版本 1.1.0-beta-1 以上
```

### 2. 在XML布局文件中添加 SmartRefreshHorizontal
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.scwang.smartrefresh.horizontal.SmartRefreshHorizontal xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/refreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:overScrollMode="never"
        android:orientation="horizontal"
        android:background="#fff" />
</com.scwang.smartrefresh.horizontal.SmartRefreshHorizontal>
```
### 3. 在 Activity 或者 Fragment 中添加代码
```java
   RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayout);
   refreshLayout.setRefreshHeader(new MaterialHeader(root.getContext()));
   refreshLayout.setRefreshFooter(new RefreshFooterWrapper(new MaterialHeader(root.getContext())), -1, -2);
```

## 混淆

SmartRefreshHorizontal 不需要添加混淆过滤代码，并且已经混淆测试通过，如果你在项目的使用中混淆之后出现问题，请及时通知我。

## 其他作品
[MultiWaveHeader](https://github.com/scwang90/MultiWaveHeader)  
[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)  
[诗和远方](http://android.myapp.com/myapp/detail.htm?apkName=com.poetry.kernel)

License
-------

    Copyright 2019 scwang90

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
