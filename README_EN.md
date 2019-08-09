# SmartRefreshHorizontal

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2012%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=12)
[![JCenter](https://img.shields.io/badge/%20JCenter%20-0.0.1-5bc0de.svg)](https://bintray.com/scwang90/maven/SmartRefreshHorizontal/_latestVersion)
[![Author](https://img.shields.io/badge/Author-scwang90-11bbff.svg)](https://github.com/scwang90)

## English | [中文](README.md)

As an extension library of SmartRefreshLayout, SmartRefreshHorizontal realizes the function of horizontal refresh and loading.
Although it is an independent open source library, it has not been implemented repeatedly.
Instead, SmartRefreshLayout is encapsulated and converted to support horizontal refresh.
So SmartRefreshHorizontal inherits all the features of SmartRefreshLayout, but the direction changes to horizontal.

## Features

 - Support horizontal scrol.
 - Support all features of [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout).
 - Support AndroidX
 
## Gateway

 - [Update log](https://github.com/scwang90/SmartRefreshHorizontal/blob/master/art/md_update.md)
 - [Attribute method](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md)
 - [Blog posts](https://segmentfault.com/a/1190000010066071) 
 - [Download the source code](https://github.com/scwang90/SmartRefreshHorizontal/releases)


## Demo
[Download APK-Demo](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/app-release.apk)

![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/png_apk_rqcode.png)

#### Preview

|Goods|ViewPager|
|:---:|:---:|
|![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/gif_goods.gif)|![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/gif_pager.gif)|

|LoadData|
|:---:|
|![](https://github.com/scwang90/SmartRefreshHorizontal/raw/master/art/gif_basic.gif)|

## Usage
#### 1.Add a gradle dependency.
```
    implementation 'com.scwang.smartrefresh:SmartRefreshHorizontal:1.0.0-beta-1'
    implementation 'com.scwang.smartrefresh:SmartRefreshLayout:1.1.0-beta-1'//must

```

#### 2.Add SmartRefreshLayout in the layout xml.
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

#### 3.Coding in the Activity or Fragment.
```java
   RefreshLayout refreshLayout = root.findViewById(R.id.refreshLayout);
   refreshLayout.setRefreshHeader(new MaterialHeader(root.getContext()));
   refreshLayout.setRefreshFooter(new RefreshFooterWrapper(new MaterialHeader(root.getContext())), -1, -2);
```

## ProGuard

This library is no need to add confusing filtering code, and it has been confusing tests pass, if you after the confusion in the use of the project appear problem, please inform me.

## Donate

If you like this library's design, feel it help to you, you can point the upper right corner "Star" support Thank you! ^ _ ^
You can also click the PayPal below to ask the author to drink a cup of coffee.

[![](https://www.paypalobjects.com/webstatic/i/logo/rebrand/ppcom.svg 'click to donate')](https://www.paypal.me/scwang90)

## Discuss

Contact me: scwang90@hotmail.com 

## Other Works
[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout)  
[MultiWaveHeader](https://github.com/scwang90/MultiWaveHeader)

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

