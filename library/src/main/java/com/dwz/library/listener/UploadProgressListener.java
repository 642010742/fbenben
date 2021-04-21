package com.dwz.library.listener;

/**
 * @author dongweizhou
 * @createTime 2019/4/12
 * @describe 图片上传进度 回调
 * @DWZ
 */
public interface UploadProgressListener {
    /**
     * 上传进度
     * @param currentBytesCount
     * @param totalBytesCount
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}
