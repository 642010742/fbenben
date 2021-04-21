package com.dwz.library.photoselect;

import android.content.Context;
import android.view.View;

import com.dwz.library.BaseAdapter.fadapter.baseAdapter.recyclerbasic.CommnBindRecycleAdapter;
import com.dwz.library.databinding.ItemPhotoSelectBinding;
import com.dwz.library.utils.glideTools.GlideUtils;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.tools.DateUtils;
import java.util.List;

/**
 * @author Administrator
 * @Create 2019/3/26
 * @Description TODO
 * @zmf 选择图片适配器
 */
public class PhotoSelectAdapter extends CommnBindRecycleAdapter<PhotoModel, ItemPhotoSelectBinding> {

    private int curPos;
    private int type = 0;

    public PhotoSelectAdapter(Context context, int layoutResId, List<PhotoModel> data) {
        super(context, layoutResId, data);
    }

    public PhotoSelectAdapter(Context context, int layoutResId, List<PhotoModel> data, int curPos) {
        super(context, layoutResId, data);
        this.curPos = curPos;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected void convert(ItemPhotoSelectBinding bind, final CommnBindRecycleAdapter.ItemViewHolder holder, PhotoModel item, int layoutResId) {
        /**
         * 如果是默认图片
         * 是-显示默认图片
         * 否显示已经选中的图片
         */
        bind.selectImgLayout.setVisibility(item.isDefault() ? View.GONE : View.VISIBLE);
        bind.defaultImg.setVisibility(item.isDefault() ? View.VISIBLE : View.GONE);
        /**
         * 记载图片
         */
        GlideUtils.LoadRoundImage(mContext, item.getImagePath(), bind.selectImage);
        if (!item.isDefault()) {
            /**
             * 判断是否是默认图片
             * 默认图片只能新增图片
             * 不是默认图片
             * 则做的操作是删除图片
             */
            bind.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view, holder.getLayoutPosition(), "deleteImg");
                    }
                }
            });
            int pictureType = PictureMimeType.isPictureType(item.getPictureType());
            bind.duration.setVisibility(pictureType == PictureMimeType.ofVideo() ? View.VISIBLE : View.GONE);
            bind.duration.setText(DateUtils.timeParse(item.getDuration()));

        } else {
            bind.defaultImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view, holder.getLayoutPosition(), "defaultImg");
                    }
                }
            });
        }
    }
}
