package com.ablanco.zoomy;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.PixelCopy;
import android.view.View;
import android.view.Window;

/**
 * Created by Álvaro Blanco Cabrero on 11/02/2017.
 * Zoomy.
 */
class ViewUtils {
    static void getBitmapFromView(Window window, View view, final BitmapCallback callback) {
        final Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int[] location = new int[2];
            view.getLocationInWindow(location);
            Rect rect = new Rect(location[0], location[1], location[0] + view.getWidth(), location[1] + view.getHeight());
            PixelCopy.OnPixelCopyFinishedListener listener = new PixelCopy.OnPixelCopyFinishedListener() {
                @Override
                public void onPixelCopyFinished(int copyResult) {
                    if (copyResult == PixelCopy.SUCCESS) {
                        callback.onBitmapReady(returnedBitmap);
                    } else {
                        callback.onBitmapReady(null);
                    }
                }
            };
            PixelCopy.request(window, rect, returnedBitmap, listener, new Handler(Looper.getMainLooper()));
        } else {
            Canvas canvas = new Canvas(returnedBitmap);
            view.draw(canvas);
            canvas.setBitmap(null);

            callback.onBitmapReady(returnedBitmap);
        }
    }

    static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    interface BitmapCallback {
        void onBitmapReady(Bitmap bitmap);
    }

    static Point getViewAbsoluteCords(View v) {
        int[] location = new int[2];
        v.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];

        return new Point(x, y);
    }

    static void viewMidPoint(PointF point, View v) {
        float x = v.getWidth();
        float y = v.getHeight();
        point.set(x / 2, y / 2);
    }
}
