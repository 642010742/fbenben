package library.core;

import androidx.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dwz.mvvmdemo.R;

import library.App.AppContexts;
import library.utils.LogUtils;
import library.utils.StringUtils;

/**
 * 董维周  加载网络图片
 */
public class NetImageUrlLoad {

    @BindingAdapter("NetImageLoad")
    public static void LoadImage(ImageView view,String url){
        LogUtils.loge("========url==="+url);
        if(StringUtils.isNotBlank(url)){
            Glide.with(AppContexts.App())
                    .load(url)
                    .fitCenter()
                    .error(R.mipmap.ic_launcher)
                    .into(view);
        }else{
           // view.setImageResource(R.mipmap.ic_launcher);
        }
    }

}
