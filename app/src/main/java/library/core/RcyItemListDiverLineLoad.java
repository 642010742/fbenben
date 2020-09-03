package library.core;

import androidx.databinding.BindingAdapter;
import android.view.View;

import library.utils.StringUtils;

/**
 * @author dongweizhou
 * @createTime 2019/3/25
 * @describe recyclerView item的分割线
 * @DWZ
 */
public class RcyItemListDiverLineLoad {

    @BindingAdapter("ItemDiver")
    public static void recyclerViewListDiver(View view,String type){
        if(StringUtils.isNotBlank(type)){
            if(type.equals("init")){
                view.setVisibility(View.VISIBLE);
            }else{
                view.setVisibility(View.GONE);
            }
        }else{
            view.setVisibility(View.VISIBLE);
        }
    }
}
