package com.mobtion.materialdemo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.mobtion.materialdemo.com.mobtion.materialdemo.login.LoginActivity;

import java.io.File;

/**
 * Created by amatamoros on 6/29/16.
 */
public class MainAbsFragmentActivity extends AppCompatActivity {


    /** Check if fragment can respond on back button press */
    protected int backStack = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Call for refresh menu options
        invalidateOptionsMenu();

    }

    @Override
    /**
     * Call when activity paused
     */
    public void onPause() {
        super.onPause();
    }

    /**
     * Fires after the OnStop() state
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        trimCache(this);
    }

    @Override
    /**
     * Called before placing the activity in such a background state
     */
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    /**
     * Called after onStart() when the activity is being re-initialized from a previously saved state
     */
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * Add new fragment without backStack
     * @param fragment
     */
    public void startNewFragment(MainAbsFragment fragment){
        this.hideKeyboard();

        // Check if fragment not doing any BE calling
        if (this.isCanChangeFragment()) {
            // Initialize fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace with fragment content
            ft.replace(R.id.simple_fragment, fragment).commit();
            // Reset stack
            this.backStack = 0;
        }
    }

    /**
     * Add new fragment to stack.
     * @param fragment
     */
    public void addFragmentToStack(MainAbsFragment fragment) {
        this.hideKeyboard();

        // Check if fragment not doing any BE calling
        if (this.isCanChangeFragment()) {

            // Initialize fragment transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace with fragment content
            ft.replace(R.id.simple_fragment, fragment);
            // Animation on change
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            // Clear stack (back button memory)
            ft.addToBackStack(null);
            ft.commitAllowingStateLoss();
            // Can do back action
            this.backStack++;

        }
    }

    /**
     * Check Internet connection
     * @return boolean
     */
    public boolean isNetworkAvailable() {
        // Connection status
        boolean isConnected = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        // true if any connection is available
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    @Override
    /**
     * Handle on back button pressed
     */
    public void onBackPressed() {
        this.hideKeyboard();

        // Check if fragment not doing any BE calling
        if (this.isCanChangeFragment()) {

            if (this.backStack > 0){
                super.onBackPressed();
                this.backStack--;

            } else {
                this.finish();
            }

        }
    }

    @Override
    /**
     * Create option menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    /**
     * Hide keyboard on tab change
     */
    public void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) MainAbsFragmentActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = MainAbsFragmentActivity.this.getCurrentFocus();

        if (view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    /**
     *
     * @param context
     */
    public void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param dir
     * @return
     */
    private boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();

            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));

                if (!success) {
                    return false;
                }
            }
        }
        // The directory is now empty so delete it
        return dir.delete();
    }

    public void starSplashActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
    }


    /** Validate back press button action */
    protected boolean change_fragment = true;
    public boolean isCanChangeFragment() { return this.change_fragment; }
    public void setCanChangeFragment(boolean value) { this.change_fragment = value; }

}
