package com.scwang.smartrefresh.horizontal.demo.fragment.index

import android.R.layout.simple_list_item_2
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.horizontal.demo.R
import com.scwang.smartrefresh.horizontal.demo.activity.FragmentActivity
import com.scwang.smartrefresh.horizontal.demo.adapter.BaseRecyclerAdapter
import com.scwang.smartrefresh.horizontal.demo.adapter.SmartViewHolder
import com.scwang.smartrefresh.horizontal.demo.fragment.GoodsPracticeFragment
import kotlinx.android.synthetic.main.fragment_index_home.*
import kotlin.reflect.KClass

class IndexHomeFragment : Fragment() {

    enum class HomeItem(@StringRes val titleId: Int, @StringRes val descriptionId: Int, val fragment: KClass<out Fragment>) {
        Goods(R.string.practice_goods_fragment_title, R.string.practice_goods_fragment_description, GoodsPracticeFragment::class)
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
        recyclerView.adapter = adapter;

//        val adapter = QuickAdapterAdapter(HomeItem.values())
//        adapter.setOnItemChildClickListener { _, _, position ->
//            adapter.getItem(position)?.also { item->
//                FragmentActivity.start(activity, item.fragment.java)
//            }
//        }
//        recyclerView.adapter = adapter
//        recyclerView.addOnItemTouchListener(object : SimpleClickListener() {
//            override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//            }
//            override fun onItemLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//            }
//            override fun onItemChildLongClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
//            }
//            override fun onItemClick(a: BaseQuickAdapter<*, *>?, b: View?, position: Int) {
//                adapter.getItem(position)?.also { item->
//                    FragmentActivity.start(activity, item.fragment.java)
//                }
//            }
//        })
    }

    inner class QuickAdapterAdapter(items: Array<HomeItem>) :
        BaseQuickAdapter<HomeItem, BaseViewHolder>(simple_list_item_2, items.asList()) {

        override fun convert(holder: BaseViewHolder, item: HomeItem) {
            holder.setText(android.R.id.text1, item.titleId)
            holder.setText(android.R.id.text2, item.descriptionId)
        }

    }
}

