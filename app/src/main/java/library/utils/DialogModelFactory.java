package library.utils;

import android.content.res.Resources;


import java.util.Arrays;
import java.util.List;

import library.App.AppContexts;
import library.commonModel.DialogModel;


public class DialogModelFactory {

    private static Resources resources;

    public static Resources init(){
         resources = AppContexts.App().getResources();
         return resources;
    }

    public static DialogModel creatModel(int arrays){
        return model(arrays);

    }


    public static DialogModel model(int arrays){
        if(resources == null) init();
        return new DialogModel(list(arrays));
    }


    public static List<String> list(int arryas){
        if(resources == null) init();
        List<String> list =  Arrays.asList(resources.getStringArray(arryas));
        return list;
    }


    public  enum ShareType{

        // 超过领取限制
        LIMIT(0),

        //订单未算
        ORDER_NO_PAY_MONEY(1),

        //靠谱信用分不够
         KAOPU_CRDEIT_NO_ENOUGH(2),

        //确定ICCID
         SURE_ICCID(3),

        //分享领卡
       SHARE_OBTAIN_CARD (4),

        //没有实名认证信息
        NO_REAL_NAME_(5),

        //未做身份验证
        NO_IDENTITY_VERIFY(6),

        //拔出卡提示
       NO_PHONE_CARD (7),

        //默认
        DEFAULT(-1);



        private int flag;


        public int getFlag() {
            return flag;
        }

        ShareType(int flag) {
            this.flag = flag;
        }


        /* 根据value返回枚举类型 */
        public static ShareType getByType(int type) {
            for(ShareType businessType : ShareType.values()) {
                if(businessType.getFlag() == type) {
                    return businessType;
                }
            }
            return DEFAULT;
        }
    }

}
