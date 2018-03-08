package com.exercise.temi.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by adi.matzliah on 07/03/18.
 */
public class ImageUtils {

    public ImageUtils() {
    }

    public static BitmapImageViewTarget getCircleImageView(final Context c, final ImageView imageView) {
        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(c.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        };
    }

    public static void setImageViewDrawable(Context c, ImageView iv, int resourceId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            iv.setImageDrawable(ResourcesCompat.getDrawable(c.getResources(), resourceId, null));
        } else {
            iv.setImageDrawable(c.getResources().getDrawable(resourceId));
        }

    }
}
