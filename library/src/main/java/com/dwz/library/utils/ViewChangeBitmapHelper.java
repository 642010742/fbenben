package com.dwz.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwz.library.App.AppContexts;
import com.dwz.library.R;
import com.dwz.library.commonModel.ChangeViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import androidx.annotation.ColorRes;
import androidx.annotation.IdRes;

/**
 * @author dongweizhou
 * @createTime 2019/5/5
 * @describe   布局生成图片
 * @DWZ
 */
public class ViewChangeBitmapHelper {

    public static final String TAG = ViewChangeBitmapHelper.class.getSimpleName();

    private SparseArray<View> views;

    public static ViewChangeBitmapHelper helper;

    public static ViewChangeBitmapHelper getInstance(){
        if (helper ==null) helper = new ViewChangeBitmapHelper();
        return helper;
    }

    public ViewChangeBitmapHelper() {
        views = new SparseArray<>();
    }


    public  Bitmap getBitmap(Context context, int layoutId, @IdRes int withTheLayoutId, @IdRes int[] textViewIds) {
        Bitmap bitmap = null;
        LayoutInflater factorys = LayoutInflater.from(context);
        final View textEntryView = factorys.inflate(layoutId, null);
        View ll = textEntryView.findViewById(withTheLayoutId);
        if(textViewIds != null){
            for (int i = 0; i < textViewIds.length; i++) {
                try {
                    TextView textView = textEntryView.findViewById(textViewIds[i]);
                    textView.setTextColor(context.getResources().getColor(R.color.white));
                }catch (Exception e){
                    LogUtils.loge(TAG,"====传入的布局id ===textViewId==不是textView");
                   return null;
                }
            }
        }
        ll.setDrawingCacheEnabled(true);
        ll.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        ll.layout(0, 0, ll.getMeasuredWidth(), ll.getMeasuredHeight());
        bitmap = Bitmap.createBitmap(ll.getDrawingCache());
        ll.setDrawingCacheEnabled(false);
        return bitmap;
    }


    /**
     * 生成bitmap
     * @param context
     * @param layoutId
     * @param withTheLayoutId
     * @param backgroundColor  bitmap 背景色设置
     * @return
     */
    public  Bitmap getBitmap(Context context, int layoutId, @IdRes int withTheLayoutId, @ColorRes int backgroundColor,int width,int height,@IdRes int[] textViewIds, String[] contents) {
        Bitmap bitmap = null;
        LayoutInflater factorys = LayoutInflater.from(context);
        final View textEntryView = factorys.inflate(layoutId, null);
        View ll = textEntryView.findViewById(withTheLayoutId);

        if(textViewIds != null){
            for (int i = 0; i < textViewIds.length; i++) {
                try {
                    TextView textView = textEntryView.findViewById(textViewIds[i]);
                    textView.setText(contents[i]);
                }catch (Exception e){
                    LogUtils.loge(TAG,"====传入的布局id ===textViewId==不是textView");
                    return null;
                }
            }
        }

        ll.setDrawingCacheEnabled(true);
        ll.measure(View.MeasureSpec.makeMeasureSpec(width,width<=0?View.MeasureSpec.UNSPECIFIED:View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, height<=0?View.MeasureSpec.UNSPECIFIED:View.MeasureSpec.EXACTLY));
        ll.layout(0, 0, ll.getMeasuredWidth(), ll.getMeasuredHeight());
        bitmap = Bitmap.createBitmap(ll.getDrawingCache());
        ll.setDrawingCacheEnabled(false);
        if(bitmap != null){
            Bitmap bitmap1 = drawBg4Bitmap(context.getResources().getColor(backgroundColor), bitmap);
            return bitmap1;
        }
        return bitmap;
    }


    /**
     * 生成bitmap
     * @return
     */
    public  Bitmap getBitmap(Context context, ChangeViewModel changeViewModel) {
        Bitmap bitmap = null;
        LayoutInflater factorys = LayoutInflater.from(context);
        final View textEntryView = factorys.inflate(changeViewModel.getRootIdView(), null);
        View ll = textEntryView.findViewById(changeViewModel.getRootId());

        if(changeViewModel.getTextModelList() != null){
            for (int i = 0; i < changeViewModel.getTextModelList().size(); i++) {
                try {
                    TextView textView = textEntryView.findViewById(changeViewModel.getTextModelList().get(i).getId());
                    textView.setText(changeViewModel.getTextModelList().get(i).getContent());
                }catch (Exception e){
                    LogUtils.loge(TAG,"====传入的布局id ===textViewId==不是textView");
                    return null;
                }
            }
        }


        if (changeViewModel.getImageModelList() != null) {
            for (int i = 0; i < changeViewModel.getImageModelList().size(); i++) {
                try {
                        ImageView imageView = textEntryView.findViewById(changeViewModel.getImageModelList().get(i).getId());
                        imageView.setImageBitmap(changeViewModel.getImageModelList().get(i).getBitmap());
                }catch (Exception e){

                }
            }
        }

        ll.setDrawingCacheEnabled(true);
        ll.measure(View.MeasureSpec.makeMeasureSpec(changeViewModel.getWidth(),changeViewModel.getWidth()<=0?View.MeasureSpec.UNSPECIFIED:View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(changeViewModel.getHeight(), changeViewModel.getHeight()<=0?View.MeasureSpec.UNSPECIFIED:View.MeasureSpec.EXACTLY));
        ll.layout(0, 0, ll.getMeasuredWidth(), ll.getMeasuredHeight());
        bitmap = Bitmap.createBitmap(ll.getDrawingCache());
        ll.setDrawingCacheEnabled(false);
        if(bitmap != null){
            Bitmap bitmap1 = drawBg4Bitmap(context.getResources().getColor(changeViewModel.getBackgroundColor()), bitmap);
            return bitmap1;
        }
        return bitmap;
    }


    /**
     *  更换背景色
     * @param color
     * @param orginBitmap
     * @return
     */
    public static Bitmap drawBg4Bitmap(int color, Bitmap orginBitmap) {
        Paint paint = new Paint();
        paint.setColor(color);
        Bitmap bitmap = Bitmap.createBitmap(orginBitmap.getWidth(),
                orginBitmap.getHeight(), orginBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0, 0, orginBitmap.getWidth(), orginBitmap.getHeight(), paint);
        canvas.drawBitmap(orginBitmap, 0, 0, paint);
        return bitmap;
    }


    public void saveBitmapFile(Context context, int layoutId, @IdRes int withTheLayoutId, @IdRes int[] textViewIds){
        saveBitmapFile(getBitmap(context, layoutId,withTheLayoutId, textViewIds));
    }


    public void saveBitmapFile(Context context, int layoutId, @IdRes int withTheLayoutId,@ColorRes int color){
        saveBitmapFile(getBitmap(context, layoutId,withTheLayoutId,color,0,0,null,null));
    }


    public String saveBitmapFile(Bitmap bitmap){
        return saveBitmapFile("",bitmap);
    }

    /**
     * 保存本地文件
     * @param fileName
     * @param bitmap
     * @return
     */
    public String saveBitmapFile(String fileName,Bitmap bitmap)  {
        if(bitmap == null){
            return fileName;
        }else{

            if(StringUtils.isNotBlank(fileName)){
                if((!FileUtils.getFileType(fileName).equals("jpg") ||
                        !FileUtils.getFileType(fileName).equals("png"))){
                    fileName = FileUtils.randomAppImageName();
                }else{
                    String baseFileName = FileUtils.getBaseFileName(fileName);
                    File file = new File(baseFileName);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File childFile = new File(fileName);
                    try {
                        childFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                        LogUtils.loge(TAG,"=====文件找不到======"+e.getMessage());
                    }
                }
            }else{
                fileName = FileUtils.randomAppImageName();
            }

            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(fileName);
                bitmap.compress(FileUtils.getFileType(fileName).equals("png")? Bitmap.CompressFormat.PNG:Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                return fileName;
            }
        }

        // 更新到系统图库里
        FileUtils.insertSystemImageStore(AppContexts.App(),new File(fileName));
        return fileName;
    }


    public   <T extends View> T getView(int viewId, View viewGroup){
        View view = views.get(viewId);
        if (view == null ){
            view = viewGroup.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T) view;
    }
}
