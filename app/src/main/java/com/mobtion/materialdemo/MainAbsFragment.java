package com.mobtion.materialdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.mobtion.materialdemo.com.mobtion.materialdemo.menu.MainMenuActivity;

/**
 * Created by amatamoros on 6/29/16.
 */
public class MainAbsFragment extends Fragment {


    @Override
    /**
     * Create Fragment
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fragment most show menu option
        setHasOptionsMenu(true);

    }

    @Override
    /**
     * Call on fragment start
     */
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDestroy() {
        // Check fragment attack to activity for clear cache memory
        if (getActivity() != null) {
            ((MainAbsFragmentActivity)getActivity()).trimCache(getActivity());
        }

        super.onDestroy();
    }

    public void OpenMainContent() {
        Intent intent = new Intent(getActivity(), MainMenuActivity.class);
        startActivity(intent);
    }

}
