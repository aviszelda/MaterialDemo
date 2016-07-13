package com.mobtion.materialdemo.com.mobtion.materialdemo.report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.mobtion.materialdemo.MainAbsFragment;
import com.mobtion.materialdemo.MainAbsFragmentActivity;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.adapters.GridViewAdapter;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.ImageItem;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.SessionInfo;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends MainAbsFragment {

    private GridView gridView;
    protected SessionInfo session = null;

    public ReportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.report_fragment, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);

        session = SessionInfo.getInstance();
        ArrayList<ImageItem> arrayOfItems = session.getImageItem();
        GridViewAdapter gridAdapter = new GridViewAdapter(getActivity(), arrayOfItems);

        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    session.selected_photo = position;
                    ((MainAbsFragmentActivity) getActivity()).addFragmentToStack(new ReportDetailFragment());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
