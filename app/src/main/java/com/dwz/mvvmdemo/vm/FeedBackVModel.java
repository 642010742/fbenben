package com.dwz.mvvmdemo.vm;

import android.text.TextUtils;
import android.view.View;

import com.dwz.library.BaseAdapter.fadapter.baseAdapter.recyclerbasic.CommnBindRecycleAdapter;
import com.dwz.library.baseVModel.BaseVModel;
import com.dwz.library.interfaces.IPBaseCallBack;
import com.dwz.library.photoselect.PhotoModel;
import com.dwz.library.photoselect.PhotoSelectAdapter;
import com.dwz.library.utils.LogUtils;
import com.dwz.library.utils.ToastUtil;
import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.databinding.ActivityFeedBackBinding;
import com.dwz.mvvmdemo.ui.activity.FeedBackActivity;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
/**
 * @author Administrator
 * @Create 2019/7/23
 * @Description TODO
 * @zmf 意见反馈
 */
public class FeedBackVModel extends BaseVModel<ActivityFeedBackBinding> implements IPBaseCallBack {
    //图片最大选择数
    public int maxSelectSize = 6;
    public List<PhotoModel> photoModels = new ArrayList<>();
    //图片路径
    public List<LocalMedia> path = new ArrayList<>();
    public PhotoSelectAdapter adapter;
    //选择的图片地址
    public String pathStr = "";
    public CompositeDisposable compositeDisposable;

    @Override
    public void initPresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    public void submit() {
        /**
         * 上传图片
         * 意见反馈提交
         */
        String feedStr = bind.editText.getText().toString();
        if (TextUtils.isEmpty(feedStr)) {
            ToastUtil.showShort("请输入您的意见或建议");
            return;
        }

        /**
         * 选择图像
         * 上传图片
         * 调取意见反馈接口
         * 未选择图片
         * 调取意见返回接口
         */
//        if (path.size() > 0) {
//            pUpLoadFiles.LoadImage(mContext, path, 0, true);
//        } else {
//            pFeedback.feedBack(mContext, feedStr, pathStr, 1, true);
//        }
    }

    /**
     * 获取适配器
     * 适配数据
     *
     * @return
     */
    public PhotoSelectAdapter getAdapter() {
        if (adapter == null) {
            adapter = new PhotoSelectAdapter(mContext, R.layout.item_photo_select, photoModels);
        }
        adapter.setOnClickListener(new CommnBindRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, String type) {
                if (type.equals("deleteImg")) {
                    //删除图片
                    path.remove(position);
                    photoModels.remove(position);
                    if (!photoModels.get(photoModels.size() - 1).isDefault()) {
                        initData();
                    }
                    adapter.notifyDataSetChanged();
                } else if (type.equals("defaultImg")) {
                    ((FeedBackActivity) mContext).requestPermission();
                }
            }
        });
        return adapter;
    }

    /**
     * 初始化图片选择布局
     * 设置数据源
     * 通知适配器
     */
    public void initData() {
        PhotoModel model = new PhotoModel();
        model.setDefault(true);
        photoModels.add(model);
        adapter.notifyDataSetChanged();
    }

    public void getImagePath(List<LocalMedia> images) {
        path.clear();
        path.addAll(images);
        photoModels.clear();
        for (int i = 0; i < path.size(); i++) {
            LocalMedia localMedia = images.get(i);
            PhotoModel model = new PhotoModel();
            model.setImagePath(!TextUtils.isEmpty(localMedia.getCompressPath()) ?
                    localMedia.getCompressPath() : localMedia.getPath());
            model.setPictureType(localMedia.getPictureType());
            model.setDuration(localMedia.getDuration());
            model.setDefault(false);
            photoModels.add(i, model);
        }
        initData();
        if (photoModels.size() > maxSelectSize) {
            photoModels.remove(photoModels.size() - 1);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 接口请求成功
     *
     * @param type
     * @param oj
     */
    @Override
    public void onSuccess(int type, Object oj) {
        if (0 == type) {
            /**
             * 上传图片
             */
            List<String> imagePath = (List<String>) oj;
            if (imagePath != null && imagePath.size() > 0) {
                pathStr = imagePath.toString();
                String feedStr = bind.editText.getText().toString();
                pathStr.substring(1, pathStr.length());
                LogUtils.logd("===zmf===pathStr" + pathStr);
//                LogUtils.logd("===zmf===pathStr" + pathStr.substring(1, pathStr.length() - 1));
//                pFeedback.feedBack(mContext, feedStr, pathStr.substring(1, pathStr.length() - 1), 1, true);
            }
        } else if (1 == type) {
            /**
             * 意见反馈
             */
            ToastUtil.showShort("提交成功,感谢您的反馈");
            mView.pCloseActivity();
        }
    }

    /**
     * 接口请求失败
     *
     * @param code
     * @param msg
     */
    @Override
    public void onError(int type, int code, String msg) {
        ToastUtil.showShort(msg);
    }

}
