package com.mobtion.materialdemo.com.mobtion.materialdemo.product;


import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.format.Time;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends MainAbsFragment {

    private Button cameraButton;
    private Bitmap mImageBitmap;
    protected SessionInfo session = null;
    private String currentDateTimeString = null;
    private OutputStream outFile = null;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.product_fragment, container, false);

        session = SessionInfo.getInstance();

        cameraButton = (Button) view.findViewById(R.id.cameraButton);

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

            if (requestCode == 1) {
                try {

                    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Crediexpress/";

                    File newDir = new File(path);

                    if(!newDir.exists()){
                        newDir.mkdirs();
                    }

                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                    try {
                        outFile = new FileOutputStream(file);

                        mImageBitmap = addWatermark(getResources(), mImageBitmap);

                        mImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outFile);

                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Bitmap addWatermark(Resources res, Bitmap source) {
        int w, h;
        Canvas c;
        Paint paint;
        Bitmap bmp, watermark;

        Matrix matrix;
        float scale;
        RectF r;

        w = source.getWidth();
        h = source.getHeight();

        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG |Paint.FILTER_BITMAP_FLAG);

        c = new Canvas(bmp);
        c.drawBitmap(source, 0, 0, paint);

        watermark = BitmapFactory.decodeResource(res, R.drawable.watermark_example);
        scale = (float) (((float) h * 0.10) / (float) watermark.getHeight());

        matrix = new Matrix();
        matrix.postScale(scale, scale);
        r = new RectF(0, 0, watermark.getWidth(), watermark.getHeight());
        matrix.mapRect(r);
        matrix.postTranslate(w - r.width(), h - r.height());

        c.drawBitmap(watermark, matrix, paint);
        watermark.recycle();

        return bmp;
    }
}
