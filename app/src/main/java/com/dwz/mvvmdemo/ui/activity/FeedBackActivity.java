package com.dwz.mvvmdemo.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dwz.mvvmdemo.R;
import com.dwz.mvvmdemo.vm.FeedBackVModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import library.App.AppConstants;
import library.baseView.BaseActivity;
import library.common.eventbus.model.EventModel;
import library.commonModel.TitleOptions;
import library.photoselect.PhotoConfigHelper;
import library.utils.ToastUtil;
import library.utils.permission.MPermissionUtils;

/**
 * 意见反馈
 */
public class FeedBackActivity extends BaseActivity<FeedBackVModel> {

    @Override
    public TitleOptions title() {
        return new TitleOptions("意见反馈");
    }

    @Override
    public Class<FeedBackVModel> getVMClass() {
        return FeedBackVModel.class;
    }

    @Override
    public int LayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    public void initViews() {
        vm.bind.recycleview.setLayoutManager(new GridLayoutManager(mContext, 4));
        vm.bind.recycleview.setAdapter(vm.getAdapter());
        vm.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        vm.bind.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedStr = vm.bind.editText.getText().toString();
                if (TextUtils.isEmpty(feedStr)) {
                    ToastUtil.showShort("请输入您的意见或建议");
                    return;
                }
                pCloseActivity();
            }
        });
    }

    /**
     * 权限请求
     */
    public void requestPermission() {
        MPermissionUtils.requestPermissionsResult(this, 1, new String[]{
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA}, new MPermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted() {
                PhotoConfigHelper.getInstance(FeedBackActivity.this)
                        .multiAlbum(AppConstants.REQUEST_CODE_CHOOSE, vm.maxSelectSize, vm.path);
            }

            @Override
            public void onPermissionDenied() {
            }
        });
    }

    /**
     * 权限请求
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == AppConstants.REQUEST_CODE_CHOOSE) {
                List<LocalMedia> images = PictureSelector.obtainMultipleResult(data);
                vm.getImagePath(images);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (vm.compositeDisposable != null) {
            vm.compositeDisposable.dispose();
        }
    }
}
