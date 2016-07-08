package com.mobtion.materialdemo.com.mobtion.materialdemo.product;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.mobtion.materialdemo.MainAbsFragment;
import com.mobtion.materialdemo.MainAbsFragmentActivity;
import com.mobtion.materialdemo.R;
import com.mobtion.materialdemo.com.mobtion.materialdemo.report.ReportFragment;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.Constants;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.ImageItem;
import com.mobtion.materialdemo.com.mobtion.materialdemo.resources.SessionInfo;

import java.text.DateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends MainAbsFragment {

    private Button cameraButton;
    private Bitmap mImageBitmap;
    private String mTitle;
    private ImageView mImageView;
    protected SessionInfo session = null;
    private String currentDateTimeString = null;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.product_fragment, container, false);

        session = SessionInfo.getInstance();

        cameraButton = (Button) view.findViewById(R.id.cameraButton);
        mImageView = (ImageView) view.findViewById(R.id.photoImage);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        return view;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, Constants.REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_IMAGE_CAPTURE && resultCode == Constants.RESULT_OK) {
            Bundle extras = data.getExtras();
            mImageBitmap = (Bitmap) extras.get("data");

            currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

            ImageItem imageItem = new ImageItem(mImageBitmap, currentDateTimeString);
            session.getImageItem().add(imageItem);

            ((MainAbsFragmentActivity)getActivity()).startNewFragment(new ReportFragment());
        }
    }
}
