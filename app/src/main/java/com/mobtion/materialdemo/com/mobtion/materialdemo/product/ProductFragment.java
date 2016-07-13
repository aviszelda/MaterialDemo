package com.mobtion.materialdemo.com.mobtion.materialdemo.product;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
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

    private ImageView imageView;
    private String mCurrentPhotoPath;
    private String newPhotoPath;

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.product_fragment, container, false);

        session = SessionInfo.getInstance();

        cameraButton = (Button) view.findViewById(R.id.cameraButton);
        imageView = (ImageView) view.findViewById(R.id.imageView);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dispatchTakePictureIntent();

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        Log.i(Constants.TAG, "IOException");
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        startActivityForResult(cameraIntent, Constants.REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });

        return view;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = "temp";
        String imageFileName = timeStamp + "_";
        String tempPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Temp/";

        File storageDir = new File(tempPath);

        if(!storageDir.exists()){
            storageDir.mkdirs();
        }

        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, Constants.REQUEST_IMAGE_CAPTURE);
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Constants.REQUEST_IMAGE_CAPTURE && resultCode == Constants.RESULT_OK) {

            try {

                mImageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(mCurrentPhotoPath));

                String tempPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Temp/";
                File dir = new File(tempPath);
                if (dir.isDirectory())
                {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++)
                    {
                        new File(dir, children[i]).delete();
                    }
                }

                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Crediexpress/";

                File newDir = new File(path);

                if(!newDir.exists()){
                    newDir.mkdirs();
                }

                File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");

                newPhotoPath = "file:" + String.valueOf(file);

                try {
                    outFile = new FileOutputStream(file);

                    mImageBitmap = addWatermark(getResources(), mImageBitmap);

                    String gText = "ejemplo";
                    //float scale = getResources().getDisplayMetrics().density;
                    Canvas canvas = new Canvas(mImageBitmap);
                    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                    paint.setColor(Color.YELLOW);
                    paint.setTextSize(300);
                    //paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);
                    // draw text to the Canvas center
                    Rect bounds = new Rect();
                    paint.getTextBounds(gText, 0, gText.length(), bounds);
                    int x = (mImageBitmap.getWidth() - bounds.width())/2 - bounds.width()+20;
                    int y = (mImageBitmap.getHeight() + bounds.height())/2;
                    canvas.drawText(gText, x, 150, paint);

                    mImageBitmap.compress(Bitmap.CompressFormat.JPEG, 50, outFile);

                    outFile.flush();
                    outFile.close();


                    currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

                    mImageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(newPhotoPath));

                    ImageItem imageItem = new ImageItem(mImageBitmap, currentDateTimeString);
                    session.getImageItem().add(imageItem);

                    ((MainAbsFragmentActivity)getActivity()).startNewFragment(new ReportFragment());


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        if (requestCode == Constants.REQUEST_IMAGE_CAPTURE && resultCode == Constants.RESULT_OK) {
//            Bundle extras = data.getExtras();
//            mImageBitmap = (Bitmap) extras.get("data");
//        }
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

        watermark = BitmapFactory.decodeResource(res, R.drawable.avira_logo);
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
