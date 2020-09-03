package library.core;

import androidx.databinding.BindingAdapter;
import androidx.annotation.ColorRes;
import android.view.View;

import com.dwz.mvvmdemo.R;

/**
 * @author dongweizhou
 * @createTime 2019/3/28
 * @describe
 * @DWZ
 */
public class TitleBackgroudColorLoad {
    @BindingAdapter("titleColor")
    public static void setTitleBackgroudColor(View view,@ColorRes int color){
        if(color !=0){
            view.setBackgroundColor(view.getContext().getResources().getColor(color));
        }else{
            view.setBackgroundColor(view.getContext().getResources().getColor(R.color.f3f3f3));
        }
    }
}
