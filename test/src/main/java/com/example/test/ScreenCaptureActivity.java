package com.example.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;

public class ScreenCaptureActivity extends Activity {

    private static final int REQUEST_MEDIA_PROJECTION = 1;
    private MediaProjectionManager projectionManager;
    boolean screenCapture = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_capture);
    }

    public void onclick(View view) {
        new AlertDialog.Builder(this)
                .setPositiveButton("截屏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        screenCapture = true;
                        takeScreenshot2();
                    }
                }).create().show();
    }

    public void takeScreenshot2() {
        try {
            projectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            startActivityForResult(
                    projectionManager.createScreenCaptureIntent(),
                    REQUEST_MEDIA_PROJECTION);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MEDIA_PROJECTION && screenCapture) {
            try {
                final int mWidth = getWindowManager().getDefaultDisplay().getWidth();
                final int mHeight = getWindowManager().getDefaultDisplay().getHeight();
                final ImageReader mImageReader = ImageReader.newInstance(mWidth, mHeight, PixelFormat.RGBA_8888, 2);
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int mScreenDensity = metrics.densityDpi;

                final MediaProjection mProjection = projectionManager.getMediaProjection(-1, data);
                final VirtualDisplay virtualDisplay = mProjection.createVirtualDisplay("screen-mirror",
                        mWidth, mHeight, mScreenDensity, DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
                        mImageReader.getSurface(), null, null);
                mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader reader) {
                        try {
                            mProjection.stop();
                            Image image = mImageReader.acquireLatestImage();
                            final Image.Plane[] planes = image.getPlanes();
                            final ByteBuffer buffer = planes[0].getBuffer();
                            int offset = 0;
                            int pixelStride = planes[0].getPixelStride();
                            int rowStride = planes[0].getRowStride();
                            int rowPadding = rowStride - pixelStride * mWidth;
                            Bitmap bitmap = Bitmap.createBitmap(mWidth + rowPadding / pixelStride, mHeight, Bitmap.Config.ARGB_8888);
                            bitmap.copyPixelsFromBuffer(buffer);
                            image.close();

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
                            String strDate = dateFormat.format(new java.util.Date());
                            String pathImage = Environment.getExternalStorageDirectory().getPath() + "/Pictures/";
                            String nameImage = pathImage + strDate + ".png";
                            if (bitmap != null) {
                                try {
                                    File fileImage = new File(nameImage);
                                    if (!fileImage.exists()) {
                                        fileImage.createNewFile();
                                    }
                                    FileOutputStream out = new FileOutputStream(fileImage);
                                    if (out != null) {
                                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                                        out.flush();
                                        out.close();
                                        Toast.makeText(ScreenCaptureActivity.this, "get phone's screen succeed", Toast.LENGTH_SHORT).show();
                                        Intent media = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                                        Uri contentUri = Uri.fromFile(fileImage);
                                        media.setData(contentUri);
                                        getApplicationContext().sendBroadcast(media);
                                        screenCapture = false;
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Toast.makeText(ScreenCaptureActivity.this, "cannot get phone's screen", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}