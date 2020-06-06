# Android横向智能刷新框架-SmartRefreshHorizontal

[![License](https://img.shields.io/badge/License%20-Apache%202-337ab7.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![MinSdk](https://img.shields.io/badge/%20MinSdk%20-%2012%2B%20-f0ad4e.svg)](https://android-arsenal.com/api?level=12)
[![JCenter](https://img.shields.io/badge/%20JCenter%20-1.1.1-5bc0de.svg)](https://bintray.com/scwang90/maven/SmartRefreshHorizontal/_latestVersion)
[![Author](https://img.shields.io/badge/Author-scwang90-11bbff.svg)](https://github.com/scwang90)

## [English](https://github.com/scwang90/SmartRefreshHorizontal/blob/master/README_EN.md) | 中文

SmartRefreshHorizontal 作为 SmartRefreshLayout 的扩展库，实现了横向刷新和加载的功能，
虽然是独立的开源库，但是并没有重复做实现，
而是对 SmartRefreshLayout 做了封装和转换，使其支持横向刷新。
所以 SmartRefreshHorizontal 继承了 SmartRefreshLayout 所有的特性，只是方向改成了横向。

## 特点:

 - 支持 横向滚动
 - 支持 [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout) 的所有特性
 - 支持 AndroidX

## 由来

SmartRefreshLayout 的设计灵活多样，扩展性高，我想这应该是它受欢迎的原因之一。
在 issue 区有不少人建议让 SmartRefreshLayout 支持横向刷新。
其实大家都知道横向刷新的库已经有了不少，我给他们的回复是使用其他现有的横向刷新库。
但这在 issue 引发热议，可能是那些横向刷新库的使用、功能、扩展与 SmartRefreshLayout有很大差距。
所以我自己认为要做横向刷新库也需要能够像 SmartRefreshLayout 一样的多功能与易扩展。
如果直接扩展 SmartRefreshLayout 的功能使其直接支持横向，将会增加代码量。
然而当此时它已经足够庞大，并且也有不少人抱怨它太大需要分包细化功能。所以一开始的时候我几乎没有开发横向刷新功能的想法。
因为用到横向刷新的应用场景比竖向刷新少很多，我自己也很少会用到这样的场景。
如果为了实现一个我自己不常用的功能库要花费我太多时间和精力实在划不来，仅仅实现一个简单的横向刷新又没有必要...

## 原理

终于！有一天我在开发旋转动画过程中，产生了一个奇妙的想法：如果把 SmartRefreshLayout 旋转90度会怎样？
然后开始做试验：把 SmartRefreshLayout 旋转90度，再把 Content 旋转-90度。结果竟然真的可以用！！
这样就不用让我花大量时间精力去做横向刷新代码实现，SmartRefreshLayout 原有的所有功能多可以直接使用，
之前设计的十多个 Header 和 Footer 也可以不用任何修改直接使用！这让我产生了开发横向刷新库的动力。
于是基于这个试验想法就诞生了这个 SmartRefreshHorizontal 库 ！

明白了 SmartRefreshHorizontal 与 SmartRefreshLayout 的关系，那么大家在使用本库的使用同时也要依赖
SmartRefreshLayout，并且版本要大于 1.1.0-beta-1 ，否则会出现找不到类的错误。

## 传送门

 - [属性文档](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_property.md)
 - [常见问题](https://github.com/scwang90/SmartRefreshLayout/blob/master/art/md_faq.md)
 - [更新日志](https://github.com/scwang90/SmartRefreshHorizontal/blob/master/art/md_update.md)
 - [博客文章](https://segmentfault.com/a/1190000020038792)
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
    //2.x
    implementation 'com.scwang.smartrefresh:SmartRefreshHorizontal:2.0.0'
    implementation 'com.scwang.smartrefresh:SmartRefreshLayout:2.0.1'//必须依赖
    //1.x
    implementation 'com.scwang.smartrefresh:SmartRefreshHorizontal:1.1.1'
    implementation 'com.scwang.smartrefresh:SmartRefreshLayout:1.1.3'//必须依赖
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
