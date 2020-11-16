package com.example.sarang.ui.main

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sarang.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val navController: NavController
        get() = findNavController(R.id.nav_fragment)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val navController:NavController = findNavController(R.id.nav_fragment)

        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.menu_home,
            R.id.menu_bookmark,
            R.id.menu_new_feed1,
            R.id.menu_news,
            R.id.menu_mypage,
            R.id.mypage2Fragment
        ).build()

        bottom_navigation.setupWithNavController(navController)

        navController.getViewModelStoreOwner(R.id.nav_graph)

        setSupportActionBar(main_toolBar)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setupActionBarWithNavController(this, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->

            val logo: Drawable? = when (destination.id) {
                R.id.menu_home, R.id.bottomSheet -> {
                    resources.getDrawable(R.drawable.group, null)
                }
                else -> null
            }

            val btnRes: Drawable? = when (destination.id) {
                R.id.menu_new_feed1 -> resources.getDrawable(
                    R.drawable.next,
                    null
                )
                R.id.newFeed2Fragment -> resources.getDrawable(
                    R.drawable.ic_check,
                    null
                )
                R.id.menu_mypage -> null
                R.id.mypage2Fragment -> resources.getDrawable(R.drawable.ic_cancel, null)
                else -> resources.getDrawable(R.drawable.ic_search, null)
            }

            main_toolBar.logo = logo

            toolBar_button.setImageDrawable(btnRes)
        }

    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()

}
