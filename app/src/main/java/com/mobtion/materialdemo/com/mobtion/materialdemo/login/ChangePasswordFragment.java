package com.mobtion.materialdemo.com.mobtion.materialdemo.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobtion.materialdemo.MainAbsFragment;
import com.mobtion.materialdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends MainAbsFragment {


    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.change_password_fragment, container, false);
        return view;
    }

}
