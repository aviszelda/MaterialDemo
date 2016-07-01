package com.mobtion.materialdemo.com.mobtion.materialdemo.menu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mobtion.materialdemo.MainAbsFragmentActivity;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.product.ProductFragment;
import com.mobtion.materialdemo.com.mobtion.materialdemo.report.ReportFragment;
import com.mobtion.materialdemo.com.mobtion.materialdemo.search.SearchFragment;

public class MainMenuActivity extends MainAbsFragmentActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private DrawerLayout drawer = null;
    private NavigationView navigationView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();

        navigationView.getMenu().getItem(0).setChecked(true);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        if(item.isChecked()) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

            this.startNewFragment(new ProductFragment());

        } else if(id == R.id.nav_gallery) {

            this.startNewFragment(new SearchFragment());

        } else if(id == R.id.nav_slideshow) {

            this.startNewFragment(new ReportFragment());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}