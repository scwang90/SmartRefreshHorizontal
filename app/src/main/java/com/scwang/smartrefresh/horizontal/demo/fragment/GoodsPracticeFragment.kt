package com.scwang.smartrefresh.horizontal.demo.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.scwang.smartrefresh.horizontal.demo.R
import kotlinx.android.synthetic.main.fragment_practice_goods.*

/**
 * A simple [Fragment] subclass.
 *
 */
class GoodsPracticeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_practice_goods, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val adapter = FragmentPagerItemAdapter(
//            childFragmentManager, FragmentPagerItems.with(context)
//                .add(R.string.practice_goods_rad_goods, PageFragment::class.java)
//                .add(R.string.practice_goods_rad_detail, PageFragment::class.java)
//                .add(R.string.practice_goods_rad_comment, PageFragment::class.java)
//                .create()
//        )
//
//        viewPager.adapter = adapter
        tabLayout.setViewPager(viewPager)
    }

    class PageFragment: Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//            return inflater.inflate(R.layout.fragment_practice_goods, container, false)
            return FrameLayout(inflater.context).apply {
                setBackgroundColor(java.util.Random().nextInt())
            }
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
        }
    }
}
