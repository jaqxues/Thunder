package com.jaqxues.thunder

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.jaqxues.thunder.fragments.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    UserInformationFragment.OnFragmentInteractionListener, HealthCheckFragment.OnFragmentInteractionListener,
    CoroutineScope by MainScope() {

    private val userInfoFragment = UserInformationFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_main, userInfoFragment)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, SettingsFragment())
                    .addToBackStack(null)
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_personal -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, userInfoFragment)
                    .commit()
            }
            R.id.nav_calendar -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, CalendarFragment())
                    .commit()
            }
            R.id.nav_sos -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, SosFragment())
                    .commit()
            }
            R.id.nav_fin -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, TransactionFragment())
                    .commit()
            }
            R.id.nav_settings -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, SettingsFragment())
                    .commit()
            }
            R.id.nav_logout -> {
                launch {
                    Toast.makeText(this@MainActivity, "Logging out...", Toast.LENGTH_LONG).show()
                    delay(1500)
                    finish()
                }
            }
            R.id.nav_lab -> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, LabResultsItemFragment())
                    .commit()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun openMedicineQRs() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_main, QRCodeFragment())
            .addToBackStack(UserInformationFragment.TAG)
            .commit()
    }

    override fun openTransactions() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_main, TransactionFragment())
            .addToBackStack(UserInformationFragment.TAG)
            .commit()
    }

    override fun openHealthCheck() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_main, HealthCheckFragment())
            .addToBackStack(UserInformationFragment.TAG)
            .commit()
    }

    override fun uploadedItem() {
        supportFragmentManager
            .popBackStackImmediate()

        userInfoFragment.sentHealthCheck()
    }
}
