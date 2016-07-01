package com.mobtion.materialdemo.com.mobtion.materialdemo.login;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.mobtion.materialdemo.MainAbsFragmentActivity;
import com.mobtion.materialdemo.R;

public class LoginActivity extends MainAbsFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_fragment);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        startNewFragment(new LoginFragment());

    }
}
