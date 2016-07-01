package com.mobtion.materialdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mobtion.materialdemo.com.mobtion.materialdemo.login.LoginActivity;
import com.mobtion.materialdemo.com.mobtion.materialdemo.login.LoginFragment;

public class MainActivity extends MainAbsFragmentActivity {

    /** Handler Instance for load splash */
    private Handler handler = null;
    //how long until we go to the next activity
    protected int SPLASH_TIME = 1500;
    private Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        this.handler = new Handler();

        this.handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (this != null)
                    //startNewFragment(new LoginFragment());
                    finish();
                    starSplashActivity(context);

            }
        }, SPLASH_TIME);
    }
}
