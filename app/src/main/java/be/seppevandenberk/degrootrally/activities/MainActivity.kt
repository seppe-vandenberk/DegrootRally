package be.seppevandenberk.degrootrally.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import be.seppevandenberk.degrootrally.R
import be.seppevandenberk.degrootrally.databinding.ActivityMainBinding
import be.seppevandenberk.degrootrally.fragments.HoofdMenuFragment
import be.seppevandenberk.degrootrally.fragments.KalenderEnResultatenFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private val hoofdMenuFragment = HoofdMenuFragment()
    private val kalenderEnResultatenFragment = KalenderEnResultatenFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.drawerLayout
        navController = findNavController(R.id.fragmentLayoutMain)

        setupActionBarWithNavController(navController, drawerLayout)

        binding.navView.setupWithNavController(navController)
        //displayFragment(hoofdMenuFragment)

        //setupActionBarDrawer()

        //setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentLayoutMain)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun setupActionBarDrawer() {
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)


        actionBarDrawerToggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.i("Navigation", "Item selected: ${item.itemId}")
        // we need to do this to respond correctly to clicks on menu items, otherwise it won't be caught
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.i("Navigation", "Item selected: ${item.itemId}")
        when (item.itemId) {
            R.id.nav_home -> {
                displayFragment(hoofdMenuFragment)
            }

            R.id.nav_kalender_en_resultaten -> {
                displayFragment(kalenderEnResultatenFragment)
            }

            R.id.nav_account -> {
                navigateToAccount()
            }

            R.id.nav_picture -> {
                navigateToPicture()
            }
            // Handle other menu items here
        }
        // Close the navigation drawer if necessary
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun navigateToPicture() {
        TODO("Not yet implemented")
    }

    private fun navigateToAccount() {
        TODO("Not yet implemented")
    }


    private fun displayFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentLayoutMain, fragment)
            addToBackStack(null)
            commit()
        }
    }


}