package com.example.test;

import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.test.utils.CameraHelper;
import com.example.test.utils.CameraHelper.CameraInfo2;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageWhiteBalanceFilter;

public class CameraActivity extends AppCompatActivity  {
    private GPUImage mGPUImage;
    private CameraHelper mCameraHelper;
    private CameraLoader mCamera;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        mGPUImage = new GPUImage(this);
        mGPUImage.setGLSurfaceView((GLSurfaceView) findViewById(R.id.surfaceView));
        mCameraHelper = new CameraHelper(this);
        mCamera = new CameraLoader();
        mGPUImage.setFilter(new GPUImageWhiteBalanceFilter());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCamera.onResume();
    }

    @Override
    protected void onPause() {
        mCamera.onPause();
        super.onPause();
    }

    private class CameraLoader {

        private int mCurrentCameraId = 0;
        private Camera mCameraInstance;

        public void onResume() {
            setUpCamera(mCurrentCameraId);
        }

        public void onPause() {
            releaseCamera();
        }

        public void switchCamera() {
            releaseCamera();
            mCurrentCameraId = (mCurrentCameraId + 1) % mCameraHelper.getNumberOfCameras();
            setUpCamera(mCurrentCameraId);
        }

        private void setUpCamera(final int id) {
            mCameraInstance = getCameraInstance(id);
            Parameters parameters = mCameraInstance.getParameters();
            // TODO adjust by getting supportedPreviewSizes and then choosing
            // the best one for screen size (best fill screen)
            if (parameters.getSupportedFocusModes().contains(
                    Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                parameters.setFocusMode(Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            }
            mCameraInstance.setParameters(parameters);

            int orientation = mCameraHelper.getCameraDisplayOrientation(
                    CameraActivity.this, mCurrentCameraId);
            CameraInfo2 cameraInfo = new CameraInfo2();
            mCameraHelper.getCameraInfo(mCurrentCameraId, cameraInfo);
            boolean flipHorizontal = cameraInfo.facing == CameraInfo.CAMERA_FACING_FRONT;
            mGPUImage.setUpCamera(mCameraInstance, orientation, flipHorizontal, false);
        }

        /** A safe way to get an instance of the Camera object. */
        private Camera getCameraInstance(final int id) {
            Camera c = null;
            try {
                c = mCameraHelper.openCamera(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;
        }

        private void releaseCamera() {
            mCameraInstance.setPreviewCallback(null);
            mCameraInstance.release();
            mCameraInstance = null;
        }
    }
}
