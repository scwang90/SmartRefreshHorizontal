package com.scwang.smartrefresh.horizontal.demo

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.scwang.smartrefresh.horizontal.demo.fragment.PracticeGoodsFragment
import com.scwang.smartrefresh.horizontal.demo.fragment.HorizontalExampleFragment
import com.scwang.smartrefresh.horizontal.demo.fragment.index.IndexHomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment: Fragment by lazy {
        IndexHomeFragment()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                supportFragmentManager.beginTransaction().replace(R.id.fragment, homeFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                supportFragmentManager.beginTransaction().replace(R.id.fragment, PracticeGoodsFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                supportFragmentManager.beginTransaction().replace(R.id.fragment, HorizontalExampleFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().replace(R.id.fragment, HorizontalExampleFragment()).commit()
    }
}
