package library.core;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * 董维周 加载本地资源图片
 */

public class TitleImageLoad {
    @BindingAdapter("imageTitle")
    public static void setTitleImage(ImageView view, int imageRes){
        if(imageRes == -1) return;
        else  view.setBackgroundResource(imageRes);
    }
}
