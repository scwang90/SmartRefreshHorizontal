package com.scwang.smart.refresh.horizontal.demo.fragment


import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.graphics.ColorUtils
import android.support.v4.view.ViewPager
import android.support.v4.widget.NestedScrollView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.horizontal.demo.R
import com.scwang.smart.refresh.horizontal.demo.util.StatusBarUtil
import kotlinx.android.synthetic.main.fragment_practice_goods.*
import kotlin.math.min

/**
 * A simple [Fragment] subclass.
 *
 */
class PracticeGoodsFragment : Fragment() {

    var pageIndex = 0
    var darkMode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practice_goods, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onScrollChange(0)
        tabLayout.setViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                pageIndex = position
                onScrollChange(if (position == 0) scrollView.scrollY else banner.height)
            }
        })
        scrollView.setOnScrollChangeListener { _: NestedScrollView?, _: Int, scrollY: Int, _: Int, oldScrollY: Int ->
            Log.d("NestedScrollView", String.format("scrollY=%d oldScrollY=%d ", scrollY, oldScrollY))
            onScrollChange(scrollY)
        }

        refreshHorizontal.setEnableOverScrollBounce(false)
        refreshHorizontal.setOnLoadMoreListener {
            viewPager.currentItem = 1
        }
        refreshLayout.setOnLoadMoreListener {
            viewPager.currentItem = 1
        }

        toolbar_btn_go_back.setOnClickListener {
            activity?.finish()
        }

        StatusBarUtil.immersive(activity)
        StatusBarUtil.setHeight(activity, toolbar)
        StatusBarUtil.setPaddingSmart(activity, toolbar_content)
        StatusBarUtil.setPaddingSmart(activity, pager1)
        StatusBarUtil.setPaddingSmart(activity, pager2)
    }

    private fun onScrollChange(scrollY: Int) {
        val percent = min(1f * scrollY / (banner.height+1), 1f)
        tabLayout.alpha = percent
        tabBackground.alpha = percent
        Log.d("NestedScrollView", String.format("alpha=%f", percent))

        val dark = percent > 0.4
        if (dark != darkMode) {
            darkMode = dark
            StatusBarUtil.darkMode(activity, dark)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val colorFront:Int = 0xff666666.toInt()
            val colorBackground = 0xffffffff.toInt()
            val colorAlpha = ColorUtils.setAlphaComponent(colorFront, (percent * 255).toInt())
            val color = ColorUtils.compositeColors(colorAlpha, colorBackground)
            toolbar_btn_more.drawable.setTint(color)
            toolbar_btn_go_back.drawable.setTint(color)
            toolbar_btn_trolley.drawable.setTint(color)

            toolbar_btn_more.background.alpha = ((1-percent) * 255).toInt()
            toolbar_btn_go_back.background.alpha = ((1-percent) * 255).toInt()
            toolbar_btn_trolley.background.alpha = ((1-percent) * 255).toInt()
        }
    }

}

