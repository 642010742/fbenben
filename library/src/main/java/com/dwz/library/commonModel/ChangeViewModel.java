package com.dwz.library.commonModel;

import android.graphics.Bitmap;

import java.util.List;

import androidx.annotation.ColorRes;
import androidx.annotation.Dimension;
import androidx.annotation.IdRes;

/**
 * @author dongweizhou
 * @createTime 2019/5/14
 * @describe
 * @DWZ
 */
public class ChangeViewModel extends BaseModel {



    private List<TextModel> textModelList;

    private List<ImageModel> imageModelList;


    public List<TextModel> getTextModelList() {
        return textModelList;
    }

    public void setTextModelList(List<TextModel> textModelList) {
        this.textModelList = textModelList;
    }

    public List<ImageModel> getImageModelList() {
        return imageModelList;
    }

    public void setImageModelList(List<ImageModel> imageModelList) {
        this.imageModelList = imageModelList;
    }

    @IdRes
    private int rootId;

    @IdRes
    private int rootIdView;


    public int getRootId() {
        return rootId;
    }

    public void setRootId(int rootId) {
        this.rootId = rootId;
    }

    @ColorRes
    private int backgroundColor;


    private int width=0;
    private int height=0;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }



    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }



    public int getRootIdView() {
        return rootIdView;
    }

    public void setRootIdView(int rootIdView) {
        this.rootIdView = rootIdView;
    }

    public ChangeViewModel(List<TextModel> textModelList, List<ImageModel> imageModelList, int rootId, int rootIdView, int backgroundColor, int width, int height) {
        this.textModelList = textModelList;
        this.imageModelList = imageModelList;
        this.rootId = rootId;
        this.rootIdView = rootIdView;
        this.backgroundColor = backgroundColor;
        this.width = width;
        this.height = height;
    }

    public ChangeViewModel() {
    }


    public static class TextModel{
        @IdRes
        private int id;
        private String content;
        @ColorRes
        private int color;
        @Dimension
        private float size;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public float getSize() {
            return size;
        }

        public void setSize(float size) {
            this.size = size;
        }

        public TextModel(int id, String content) {
            this.id = id;
            this.content = content;
        }

        public TextModel() {
        }
    }

    public static class ImageModel{
        @IdRes
        private int id;
        private Bitmap bitmap;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Bitmap getBitmap() {
            return bitmap;
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public ImageModel(int id, Bitmap bitmap) {
            this.id = id;
            this.bitmap = bitmap;
        }

        public ImageModel() {
        }
    }
}
