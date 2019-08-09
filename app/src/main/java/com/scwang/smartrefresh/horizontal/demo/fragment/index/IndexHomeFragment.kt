package com.scwang.smartrefresh.horizontal.demo.fragment.index

import android.R.layout.simple_list_item_2
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scwang.smartrefresh.horizontal.demo.R
import com.scwang.smartrefresh.horizontal.demo.activity.FragmentActivity
import com.scwang.smartrefresh.horizontal.demo.adapter.BaseRecyclerAdapter
import com.scwang.smartrefresh.horizontal.demo.adapter.SmartViewHolder
import com.scwang.smartrefresh.horizontal.demo.fragment.ExampleBasicFragment
import com.scwang.smartrefresh.horizontal.demo.fragment.PracticeGoodsFragment
import com.scwang.smartrefresh.horizontal.demo.fragment.PracticePagerFragment
import kotlinx.android.synthetic.main.fragment_index_home.*
import kotlin.reflect.KClass

class IndexHomeFragment : Fragment() {

    enum class HomeItem(@StringRes val titleId: Int, @StringRes val descriptionId: Int, val fragment: KClass<out Fragment>) {
        Goods(R.string.practice_goods_fragment_title, R.string.practice_goods_fragment_description, PracticeGoodsFragment::class),
        Pager(R.string.practice_pager_fragment_title,R.string.practice_pager_fragment_description, PracticePagerFragment::class),
        More(R.string.example_basic_fragment_title,R.string.example_basic_fragment_description, ExampleBasicFragment::class)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_index_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = object : BaseRecyclerAdapter<HomeItem>(HomeItem.values().asList(), simple_list_item_2) {
            override fun onBindViewHolder(holder: SmartViewHolder, model: HomeItem, position: Int) {
                holder.text(android.R.id.text1, model.titleId)
                holder.text(android.R.id.text2, model.descriptionId)
            }
        }
        adapter.setOnItemClickListener { _, _, i, _ ->
            adapter.get(i)?.also { item->
                FragmentActivity.start(activity, item.fragment.java)
            }
        }
        recyclerView.adapter = adapter
    }
}

