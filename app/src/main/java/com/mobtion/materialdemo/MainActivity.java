package com.mobtion.materialdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Bundle;

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
