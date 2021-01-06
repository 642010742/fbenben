package library.photoselect;

import library.commonModel.BaseModel;

/**
 * @author Administrator
 * @Create 2019/3/26
 * @Description TODO
 * @zmf
 */
public class PhotoModel extends BaseModel {
    private boolean isDefault;
    private String imagePath;
    private String path;
    private String pictureType;
    private long duration;

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
