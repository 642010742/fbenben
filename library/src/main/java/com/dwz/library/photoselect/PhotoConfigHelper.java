package com.dwz.library.photoselect;

import android.app.Activity;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;


public class PhotoConfigHelper {

    private static PhotoConfigHelper photoConfigHelper = null;
    private static Activity mActivity = null;

    public static PhotoConfigHelper getInstance(Activity activity) {
        mActivity = activity;
        if (photoConfigHelper == null) {
            photoConfigHelper = new PhotoConfigHelper();
        }
        return photoConfigHelper;
    }

    /**
     * 相机
     */
    public void camera() {
        PictureSelector.create(mActivity)
                .openCamera(PictureMimeType.ofImage())
                .enableCrop(true)// 是否裁剪
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 相册  单选  不显示相机
     * 默认四列
     * 默认显示相机
     * 默认 不剪裁
     */
    public void singleAlbum() {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .enableCrop(true)// 是否裁剪
                .isCamera(false)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * 相册 多选  显示相机
     *
     * @param requestCode
     * @param maxSelectSize
     * @param selectList
     */
    public void multiAlbum(int requestCode, int maxSelectSize, List<LocalMedia> selectList) {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(maxSelectSize)
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .selectionMedia(selectList)// 是否传入已选图片
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                .forResult(requestCode);
    }

    /**
     * 多选 相册/视频  显示相机
     * @param requestCode
     * @param maxSelectSize
     * @param selectList
     */
    public void multiAlbumWithVideo(int requestCode,int maxSelectSize, List<LocalMedia> selectList) {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofAll())
                .maxSelectNum(maxSelectSize)
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .selectionMedia(selectList)// 是否传入已选图片
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                .forResult(requestCode);
    }

}