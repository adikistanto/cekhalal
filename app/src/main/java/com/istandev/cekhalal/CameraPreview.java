package com.istandev.cekhalal;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;

/**
 * Created by ADIK on 01/03/2016.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback
{
    SurfaceHolder holder;
    Camera camera;
    private List<Camera.Size> sizes;

    public CameraPreview(Context context, Camera camera)
    {
        super(context);
        this.camera = camera;
        holder = this.getHolder();

        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);

    }

    private Camera.Size getBestPreviewSize(int width, int height)
    {
        Camera.Size result=null;
        Camera.Parameters p = camera.getParameters();
        for (Camera.Size size : p.getSupportedPreviewSizes()) {
            if (size.width<=width && size.height<=height) {
                if (result==null) {
                    result=size;
                } else {
                    int resultArea=result.width*result.height;
                    int newArea=size.width*size.height;

                    if (newArea>resultArea) {
                        result=size;
                    }
                }
            }
        }
        return result;

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        if (holder.getSurface() == null) return;

        Camera.Parameters parameters = camera.getParameters();

        sizes = parameters.getSupportedPreviewSizes();
        Camera.Size optimalSize = getBestPreviewSize(width, height);
        try{
            parameters.setPreviewSize(optimalSize.width,optimalSize.height);
            camera.setParameters(parameters);

        }
        catch (NullPointerException a)
        {

        }
        camera.startPreview();


    }


    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

        try
        {
            camera.setPreviewDisplay(holder);
            camera.setDisplayOrientation(90);
        }
        catch (IOException e)
        {
            camera.release();
            camera=null;
            Log.d("FrontCam", "Error creating surface: " + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        if (camera != null) {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
        }


    }
}
