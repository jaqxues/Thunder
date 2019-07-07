package com.jaqxues.thunder

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.jaqxues.thunder.fragments.HealthCheckFragment
import com.jaqxues.thunder.fragments.QRCodeFragment
import com.jaqxues.thunder.fragments.TransactionFragment
import com.jaqxues.thunder.fragments.UserInformationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_user_information.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    UserInformationFragment.OnFragmentInteractionListener, HealthCheckFragment.OnFragmentInteractionListener {

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
            super.onBackPressed()
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
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

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

        userInfoFragment.senTHealthCheck()
    }
}
