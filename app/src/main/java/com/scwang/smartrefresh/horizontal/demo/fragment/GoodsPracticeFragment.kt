package com.scwang.smartrefresh.horizontal.demo.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
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
        tabLayout.setViewPager(viewPager)
    }

}
