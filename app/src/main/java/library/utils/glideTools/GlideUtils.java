package library.utils.glideTools;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.dwz.mvvmdemo.R;

import library.utils.PixelUtil;


/**
 * Created by Administrator on 2018/4/30.
 */

public class GlideUtils {

    /**
     * 加载网络图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadImage(Context mContext, String path,
                                 ImageView imageview) {
        Glide.with(mContext).load(path).centerCrop().placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
    }

    /**
     * 加载带尺寸的图片
     *
     * @param mContext
     * @param path
     * @param Width
     * @param Height
     * @param imageview
     */
    public static void LoadImageWithSize(Context mContext, String path,
                                         int Width, int Height, ImageView imageview) {
        Glide.with(mContext).load(path).override(Width, Height)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
    }


    /**
     * 加载本地图片
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadImageWithLocation(Context mContext, Integer path,
                                             ImageView imageview) {
        Glide.with(mContext).load(path).diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageview);
    }


    /**
     * 圆形加载
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadCircleImage(Context mContext, String path,
                                       ImageView imageview) {
        if (mContext != null)
            Glide.with(mContext).load(path).centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .transform(new GlideCircleTransform(mContext))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
    }

    /**
     * 加载圆角图片
     * 默认圆角4px
     *
     * @param mContext
     * @param path
     * @param imageview
     */
    public static void LoadRoundImage(Context mContext, String path,
                                      ImageView imageview) {
        if (mContext != null)
            Glide.with(mContext).load(path).centerCrop().placeholder(R.mipmap.gallery_pick_photo)
                    .transform(new GlideRoundTransform(mContext))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
    }


    /**
     * 加载圆角图片
     *
     * @param mContext
     * @param path
     * @param imageview
     * @param px
     */
    public static void LoadRoundImage(Context mContext, String path,
                                      ImageView imageview, int px) {
        if (mContext != null)
            Glide.with(mContext).load(path).centerCrop().placeholder(R.mipmap.gallery_pick_photo)
                    .transform(new GlideRoundTransform(mContext, PixelUtil.px2dp(px)))
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageview);
    }

    /**
     * @param context
     * @param url
     * @param imgView
     */
    public static void loadImage(Context context, String url, ImageView imgView) {
        loadImage(context, url, imgView, 0, null);
    }

    /**
     * @param context
     * @param url
     * @param imgView
     * @param defultImg
     */
    public static void loadImage(Context context, String url, ImageView imgView, int defultImg) {
        loadImage(context, url, imgView, defultImg, null);
    }

    /**
     * @param context
     * @param url
     * @param imgView
     * @param defultImg 自定义圆角图片
     */
    public static void loadImage(Context context, String url, ImageView imgView, int defultImg,
                                 BitmapTransformation transcoder) {
        if (transcoder == null) {

            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defultImg).dontAnimate()
                    .into(imgView);
        } else {

            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(defultImg).dontAnimate()
                    .transform(transcoder).into(imgView);
        }
    }


    /**
     * @param context
     * @param url
     * @param imgView
     * @param defultImg GIF
     */
    public static void loadGifImage(Context context, String url, ImageView imgView, int defultImg) {
        Glide.with(context).load(url).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(defultImg).crossFade().dontAnimate()
                .into(imgView);
    }


}
