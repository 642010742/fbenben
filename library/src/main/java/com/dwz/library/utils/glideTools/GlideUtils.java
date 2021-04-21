package com.dwz.library.utils.glideTools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dwz.library.utils.PixelUtil;

import androidx.annotation.DrawableRes;


/**
 * Created by Administrator on 2018/4/30.
 */

public class GlideUtils {

    /**
     * 加载网络图片
     */
    public static void LoadImage(Context mContext, String path, ImageView imageview) {
        LoadImage(mContext, path, null, imageview);
    }

    /**
     * 加载网络图片
     */
    @SuppressLint("CheckResult")
    public static void LoadImage(Context mContext, String path, @DrawableRes Integer defaultImage, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        if (defaultImage != null) {
            options.placeholder(defaultImage)
                    .error(defaultImage);
        }

        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    /**
     * 加载带尺寸的图片
     */
    public static void LoadImageWithSize(Context mContext, String path, int Width, int Height, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.override(Width, Height).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    /**
     * 加载圆形图片
     */
    public static void LoadCircleImage(Context mContext, String path, ImageView imageview) {
        LoadCircleImage(mContext, path, null, imageview);
    }

    /**
     * 加载圆形图片
     */
    @SuppressLint("CheckResult")
    public static void LoadCircleImage(Context mContext, String path, @DrawableRes Integer defaultImage, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .transform(new GlideCircleTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        if (defaultImage != null) {
            options.placeholder(defaultImage)
                    .error(defaultImage);
        }
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }

    /**
     * 加载圆角图片
     * 默认圆角4px 无默认图片
     */
    public static void LoadRoundImage(Context mContext, String path, ImageView imageview) {
        LoadRoundImage(mContext, path, null, 4, imageview);
    }

    /**
     * 加载圆角图片
     * 默认圆角4px
     */
    public static void LoadRoundImage(Context mContext, String path, @DrawableRes Integer defaultImage,
                                      ImageView imageview) {
        LoadRoundImage(mContext, path, defaultImage, 4, imageview);
    }


    /**
     * 加载圆角图片
     */
    @SuppressLint("CheckResult")
    public static void LoadRoundImage(Context mContext, String path, @DrawableRes Integer defaultImage,
                                      int px, ImageView imageview) {
        RequestOptions options = new RequestOptions();
        options.centerCrop()
                .transform(new GlideRoundTransform(mContext, PixelUtil.px2dp(px)))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        if (defaultImage != null) {
            options.placeholder(defaultImage)
                    .error(defaultImage);
        }
        Glide.with(mContext).load(path).apply(options).into(imageview);
    }


    /**
     * 加载gif图
     */
    @SuppressLint("CheckResult")
    public static void loadGifImage(Context context, String url, @DrawableRes Integer defaultImag,
                                    ImageView imgView) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(defaultImag)
                .error(defaultImag)
                .dontAnimate();

        Glide.with(context).asGif().load(url).apply(options)
                .into(imgView);
    }


}
