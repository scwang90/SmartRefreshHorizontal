package com.scwang.smartrefresh.horizontal.demo

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.scwang.smartrefresh.horizontal.demo.fragment.PracticeGoodsFragment
import com.scwang.smartrefresh.horizontal.demo.fragment.ExampleBasicFragment
import com.scwang.smartrefresh.horizontal.demo.fragment.index.IndexHomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val homeFragment: androidx.fragment.app.Fragment by lazy {
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
                supportFragmentManager.beginTransaction().replace(R.id.fragment,
                    ExampleBasicFragment()
                ).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.visibility = View.GONE

        supportFragmentManager.beginTransaction().replace(R.id.fragment, IndexHomeFragment()).commit()
    }
}
