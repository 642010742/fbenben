package library.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.App.AppContexts;

/**
 * Created by AA on 2017/3/24.
 */
public class FileUtils {
    public static long UserId = 1L;
    public static final String ROOT_PATH = AppContexts.App().getFilesDir() + File.separator;
    public static final String PICTURE_PATH = AppContexts.App().getFilesDir().getPath() + "/safe-vault/" + UserId + "/picture";
    public static final String VOICE_PATH = AppContexts.App().getFilesDir().getPath() + "/safe-vault/" + UserId + "/voice";
    public static final String PATH = AppContexts.App().getFilesDir().getPath();


    /**
     * 根据文件路径获取文件名称
     *
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

    /**
     * 根据文件路径根目录
     *
     * @param filePath
     * @return
     */
    public static String getBaseFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return "";
        }
        return filePath.substring(0, filePath.lastIndexOf(File.separator));
    }


    public static String getClassPageName(String classPageName) {
        if (TextUtils.isEmpty(classPageName)) {
            return "";
        }
        return classPageName.substring(0, classPageName.lastIndexOf("."));
    }

    /**
     * 生成本地文件路径
     *
     * @param filePath
     * @return
     */
    public static File gerateLocalFile(String filePath) {
        String fileNmae = getFileName(filePath);
        File dirFile = new File(ROOT_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return new File(dirFile, fileNmae);
    }

    public static String getImageFileName() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + ".jpg";
        return imageFileName;
    }

    public static String getImageGifFileName() {
        //String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String imageFileName = "IMG_kaopu_vault_friend_verify.gif";
        return imageFileName;
    }

    public static String getRecordFileName() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
        String imageFileName = "Video_" + timeStamp + ".mp3";
        return imageFileName;
    }

    public static String getFileType(String fileName) {
        String[] strArray = fileName.split("\\.");
        int suffixIndex = strArray.length - 1;
        return strArray[suffixIndex];
    }


    public static File getLocalFile(String fileName) {
        File dirFile = new File(ROOT_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return new File(dirFile, fileName);
    }

    /**
     * 转换文件大小
     *
     * @param fileSize
     * @return
     */
    public static String FormetFileSize(long fileSize) {
        if (fileSize <= 0) {
            return "0KB";
        }

        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileSize < 1024) {
            fileSizeString = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            fileSizeString = df.format((double) fileSize / 1024) + "K";
        } else if (fileSize < 1073741824) {
            fileSizeString = df.format((double) fileSize / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 取得文件大小
     *
     * @param f
     * @return
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static long getFileSizes(File f) throws Exception {
        long size = 0;
        if (f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            size = fis.available();
        } else {
            f.createNewFile();
        }
        return size;
    }

//    public static void saveFile(FileUpload fileUpload) throws FileNotFoundException {
//        String filePath = PATH+fileUpload.getFilePath();
//        byte[] bytes = fileUpload.getBytes();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//        File file  = new File(filePath);
//        File file1 = new File(PICTURE_PATH);
//        File flle2 = new File(VOICE_PATH);
//        if(!file1.exists())file1.mkdirs();
//        if(!flle2.exists())flle2.mkdirs();
//        if(file.exists()) {
//            file.delete();
//        }else {
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
//        if(fileUpload.getFileName().contains("jpg")){
//            bitmap.compress(Bitmap.CompressFormat.JPEG,100,bufferedOutputStream);
//        }else if (fileUpload.getFileName().contains("png")){
//            bitmap.compress(Bitmap.CompressFormat.PNG,100,bufferedOutputStream);
//        }
//    }
//
//    public static String getSaveVideoFilePath(){
//        String mediaFileUri = DialogUtils.getInstance().getMediaFileUri();
//        String recordFileName = getRecordFileName();
//        return mediaFileUri+recordFileName;
//    }
//
//    public static String getSaveImageFilePath(){
//        String mediaFileUri = DialogUtils.getInstance().getMediaFileUri();
//        String recordFileName = getImageFileName();
//        return mediaFileUri+recordFileName;
//    }


    public static void svaeVideoFile(final String path, final String saveFilePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //  if(!FileUtils.getFileType(path).equals("mp3")){
                //changeToMp3(path,saveFilePath);
                //File file1 = new File(path);
//                       if (file1.isFile()) {
//                           if (file1.exists()) {
//                               if (file1.length()>0) {
//                                   try{
//
//                                   }catch (Exception e){
//                                       file1.delete();
//                                   }
//                               }
//                           }
//                       }
                // }else {
                try {
                    FileInputStream fileInputStream = new FileInputStream(path);
                    File file = new File(saveFilePath);
                    if (!file.exists()) file.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] temp = new byte[1024];

                    int len = 0;
                    //读取文件内容:
                    while ((len = fileInputStream.read(temp)) > 0) {
                        fileOutputStream.write(temp, 0, temp.length);
                    }
                    fileInputStream.close();
                    fileOutputStream.close();
                    File file1 = new File(path);
                    if (file1.isFile()) {
                        if (file1.exists()) {
                            if (file1.length() > 0) {
                                try {
                                    file1.delete();
                                } catch (Exception e) {

                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //  }
            }
        }).start();
    }


    public static void MP3(String path1, String path2) {
        String command = "sudo ffmpeg -i " + path1
                + " -f mp3 " + path2;
        runCmd(command);
    }


    public static void runCmd(String command) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(command);
            Thread.sleep(1000);
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            System.out.println("");
            while ((line = br.readLine()) != null)
                System.out.println(line);
            System.out.println("");
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }


    public static void changeToMp3(String url, String audiopath, String target) {
        try {

            //windows下面的是ffmpeg.exe   linux如下
            Process process = Runtime.getRuntime().exec(url + File.separator + "ffmpeg -i " + audiopath + " " + target);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            while ((line = input.readLine()) != null)
                System.out.println(line);
            int exitVal = process.waitFor();
            System.out.println("Process exitValue: " + exitVal);

        } catch (Exception e) {
            System.err.println("IOException " + e.getMessage());
        }


    }


    public static void saveImageToGallery(Bitmap bmp, String imagePath, File file) {
        // 首先保存图片
        // String mediaFileUri = DialogUtils.getInstance().getMediaFileUri()+FileUtils.getImageFileName();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imagePath);
            boolean compress = bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Log.e("saveImageToGallery", "------------------是否成功" + compress);
            fos.flush();
//            if (file.exists()) {
//                file.delete();
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 其次把文件插入到系统图库
//               try {
//                   MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                           currentFile.getAbsolutePath(), fileName, null);
//              } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//               }
//
//                // 最后通知图库更新
//                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                                Uri.fromFile(new File(currentFile.getPath()))));

    }


    public static String getFPUriToPath(Context context, Uri uri) {
        try {
            List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
            if (packs != null) {
                String fileProviderClassName = FileProvider.class.getName();
                for (PackageInfo pack : packs) {
                    ProviderInfo[] providers = pack.providers;
                    if (providers != null) {
                        for (ProviderInfo provider : providers) {
                            if (uri.getAuthority().equals(provider.authority)) {
                                if (provider.name.equalsIgnoreCase(fileProviderClassName)) {
                                    Class<FileProvider> fileProviderClass = FileProvider.class;
                                    try {
                                        Method getPathStrategy = fileProviderClass.getDeclaredMethod("getPathStrategy", Context.class, String.class);
                                        getPathStrategy.setAccessible(true);
                                        Object invoke = getPathStrategy.invoke(null, context, uri.getAuthority());
                                        if (invoke != null) {
                                            String PathStrategyStringClass = FileProvider.class.getName() + "$PathStrategy";
                                            Class<?> PathStrategy = Class.forName(PathStrategyStringClass);
                                            Method getFileForUri = PathStrategy.getDeclaredMethod("getFileForUri", Uri.class);
                                            getFileForUri.setAccessible(true);
                                            Object invoke1 = getFileForUri.invoke(invoke, uri);
                                            if (invoke1 instanceof File) {
                                                String filePath = ((File) invoke1).getAbsolutePath();
                                                return filePath;
                                            }
                                        }
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Map<String, String> infos = new HashMap<String, String>();

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    public String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            SimpleDateFormat sDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String time = sDateFormat.format(new Date());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = "/sdcard/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e("TAG", "an error occured while writing file...", e);
        }
        return null;
    }


    private static final String separator = "/";

    private static final String fenbiImage = "fenbi_image";


    /**
     * 随机获取图片名称
     */

    public static String randomAppImageName() {

        File childFile = new File(saveFileBase(), "IMG_DF_" + getTime(System.currentTimeMillis()) + ".jpg");
        if (!childFile.exists()) {
            try {
                childFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return childFile.getAbsolutePath();
    }

    /**
     * 随机获取图片名称
     */
// Glide.with(mContext).asBitmap().load(objects.get(0).getPicUrl()).into(new SimpleTarget<Bitmap>() {
//        @Override
//        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//            FileOutputStream fileOutputStream = null;
//            try {
//                fileOutputStream = new FileOutputStream(FileUtils.randomAppImageName(AppConstants.startImage));
//                resource.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    });
    public static String randomAppImageName(String name) {

        File childFile = new File(saveFileBase(), name);
        if (childFile.exists()) {
            try {
                childFile.delete();
                childFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                childFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return childFile.getAbsolutePath();
    }

    /**
     * 随机获取图片名称
     *FileUtils.getAppImageName(AppConstants.startImage)
     * @param name
     * @return
     */
    public static String getAppImageName(String name) {

        File childFile = new File(saveFileBase(), name);
        try {
            childFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return childFile.getAbsolutePath();
    }


    /**
     * 存储文件的文件夹
     *
     * @return
     */

    public static String saveFileBase() {
        String absolutePath = AppContexts.App().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath, fenbiImage);
        if (!file.exists()) {
            file.mkdirs();
        }
        String basePath = file.getAbsolutePath();
        return basePath;
    }


    public static String getTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
        return simpleDateFormat.format(time);
    }


    /**
     * 更新系统图库
     *
     * @param context
     * @param file
     */
    public static void insertSystemImageStore(Context context, File file) {
        if (file != null) {
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        file.getAbsolutePath(), file.getName(), null);
                // 最后通知图库更新
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                        Uri.fromFile(new File(file.getPath()))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                LogUtils.loge("==========更新系统图库====" + e.getMessage() + "==" + e.getCause());
            }
        }
        // context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" +  file.getAbsolutePath())));
    }
}
