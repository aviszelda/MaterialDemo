package com.mobtion.materialdemo.com.mobtion.materialdemo.report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobtion.materialdemo.MainAbsFragment;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.SessionInfo;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportDetailFragment extends MainAbsFragment {

    private TextView titleTextView;
    private ImageView imageView;
    protected SessionInfo session = null;

    public ReportDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.report_detail_fragment, container, false);
        session = SessionInfo.getInstance();

        titleTextView = (TextView) view.findViewById(R.id.detailTitle);
        imageView = (ImageView) view.findViewById(R.id.detailImage);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Call for refresh menu options
        titleTextView.setText(session.getImageItem().get(session.selected_photo).getTitle());
        //imageView.setImageBitmap(session.getImageItem().get(SessionInfo.selected_photo).getImage());
        Picasso.with(getActivity()).load("file:/storage/emulated/0/Pictures/Crediexpress/" +
                session.getImageItem().get(session.selected_photo).getName()).into(imageView);
    }
}
