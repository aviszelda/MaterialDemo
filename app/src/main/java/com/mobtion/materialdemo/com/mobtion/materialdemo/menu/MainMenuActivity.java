package com.mobtion.materialdemo.com.mobtion.materialdemo.menu;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;
import com.mobtion.materialdemo.MainAbsFragmentActivity;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.product.ProductFragment;
import com.mobtion.materialdemo.com.mobtion.materialdemo.report.ReportFragment;
import com.mobtion.materialdemo.com.mobtion.materialdemo.search.SearchFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenuActivity extends MainAbsFragmentActivity {

    private DrawerLayout mDrawerLayout;
    private int lastExpandedPosition = -1;
    ExpandableListAdapter mMenuAdapter;
    ExpandableListView expandableList;
    List<ExpandedMenuModel> listDataHeader;
    HashMap<ExpandedMenuModel, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpToolbar(true, R.drawable.ic_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        expandableList = (ExpandableListView) findViewById(R.id.navigationmenu);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        prepareListData();
        mMenuAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, expandableList);

        // setting list adapter
        expandableList.setAdapter(mMenuAdapter);

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Log.d("DEBUG", "submenu item clicked");
                Log.d("DEBUG", "submenu int i " + i);
                Log.d("DEBUG", "submenu int i1 " + i1);
                Log.d("DEBUG", "submenu long " + l);

                if (i == 0 && i1 == 0 && l == 0) {
                    startNewFragment(new ProductFragment());
                    mDrawerLayout.closeDrawers();
                }

                if (i == 1 && i1 == 0 && l == 0) {
                    startNewFragment(new ReportFragment());
                    mDrawerLayout.closeDrawers();
                }

                if (i == 1 && i1 == 1 && l == 1) {
                    startNewFragment(new SearchFragment());
                    mDrawerLayout.closeDrawers();
                }

                if (i == 1 && i1 == 2 && l == 2) {
                    Toast.makeText(MainMenuActivity.this, "no hay fragment para este", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                }

                if (i == 2 && i1 == 0 && l == 0) {
                    Toast.makeText(MainMenuActivity.this, "no hay fragment para este", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawers();
                }

                return false;
            }
        });
        expandableList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Log.d("DEBUG", "heading clicked");
                Log.d("DEBUG", "heading int i " + i);
                Log.d("DEBUG", "heading long " + l);
                return false;
            }
        });

        expandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableList.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        //navigationView.getMenu().getItem(0).setChecked(true);

    }

//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//
//
//        if(item.isChecked()) {
//            drawer.closeDrawer(GravityCompat.START);
//            return true;
//        }
//
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//
//            this.startNewFragment(new ProductFragment());
//
//        } else if(id == R.id.nav_gallery) {
//
//            this.startNewFragment(new SearchFragment());
//
//        } else if(id == R.id.nav_slideshow) {
//
//            this.startNewFragment(new ReportFragment());
//        }
//
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

    private void prepareListData() {
        listDataHeader = new ArrayList<ExpandedMenuModel>();
        listDataChild = new HashMap<ExpandedMenuModel, List<String>>();

        ExpandedMenuModel item1 = new ExpandedMenuModel();
        item1.setIconName("heading1");
        item1.setIconImg(android.R.drawable.ic_delete);
        // Adding data header
        listDataHeader.add(item1);

        ExpandedMenuModel item2 = new ExpandedMenuModel();
        item2.setIconName("heading2");
        item2.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item2);


        ExpandedMenuModel item3 = new ExpandedMenuModel();
        item3.setIconName("heading3");
        item3.setIconImg(android.R.drawable.ic_delete);
        listDataHeader.add(item3);


        // Adding child data
        List<String> heading1 = new ArrayList<String>();
        heading1.add("Submenu of item 1");

        List<String> heading2 = new ArrayList<String>();
        heading2.add("Submenu of item 2");
        heading2.add("Submenu of item 2");
        heading2.add("Submenu of item 2");

        List<String> heading3 = new ArrayList<String>();
        heading3.add("Submenu of item 3");

        listDataChild.put(listDataHeader.get(0), heading1);// Header, Child data
        listDataChild.put(listDataHeader.get(1), heading2);
        listDataChild.put(listDataHeader.get(2), heading3);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        //revision: this don't works, use setOnChildClickListener() and setOnGroupClickListener() above instead
        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);

                Toast.makeText(MainMenuActivity.this, "clicked " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).toString(), Toast.LENGTH_SHORT).show();
                mDrawerLayout.closeDrawers();

                return true;
            }
        });

    /*
    navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    mDrawerLayout.closeDrawers();
                    return true;
                }
            });
    */
    }

    public void setUpToolbar(boolean displayHome, int resource) {

        getSupportActionBar().setDisplayHomeAsUpEnabled(displayHome);
        getSupportActionBar().setHomeAsUpIndicator(resource);
    }

}