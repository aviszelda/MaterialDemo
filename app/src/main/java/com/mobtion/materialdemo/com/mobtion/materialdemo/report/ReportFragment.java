package com.mobtion.materialdemo.com.mobtion.materialdemo.report;


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
public class ReportFragment extends MainAbsFragment {


    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.report_fragment, container, false);
        return view;
    }

}
