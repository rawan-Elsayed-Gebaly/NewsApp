package com.example.newsapp_v2.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.newsapp_v2.R
import com.example.newsapp_v2.databinding.ActivityHomeBinding
import com.example.newsapp_v2.ui.home.fragments.CategoriesFragment
import com.example.newsapp_v2.ui.home.fragments.SettingsFragment

class HomeActivity: AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        showCategories()
        showDrawer()
        startSearchActivity()

    }

    private fun startSearchActivity() {
        viewBinding.searchIcon.setOnClickListener {
            val intent = Intent(this , SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showDrawer() {
        viewBinding.drawerListIcon.setOnClickListener {
            viewBinding.drawerLayout.openDrawer(GravityCompat.START)
        }
        viewBinding.navView.setNavigationItemSelectedListener { itemMenue->
            when(itemMenue.itemId){
                R.id.nav_settings ->{
                    replaceFragment(SettingsFragment())
                    viewBinding.drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.nav_categories -> {
                    replaceFragment(CategoriesFragment())
                    viewBinding.drawerLayout.closeDrawer(GravityCompat.START)

                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun showCategories() {
        replaceFragment(CategoriesFragment())
    }

    fun replaceFragment(fragment: Fragment) {
         supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
             .addToBackStack(null) // Optional, to allow back navigation
            .commit()
    }

}