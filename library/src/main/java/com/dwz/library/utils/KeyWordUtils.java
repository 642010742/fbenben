package com.dwz.library.utils;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyWordUtils {
    /**
     * 搜索关键字标红
     * @param title
     * @param keyword
     * @return
     */
    public static String matcherSearchTitle(String title,String keyword){
        String content = title;
        String wordReg = "(?i)"+keyword;//用(?i)来忽略大小写
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile(wordReg).matcher(content);
        while(matcher.find()){
            //这样保证了原文的大小写没有发生变化
            matcher.appendReplacement(sb, "<font color=\"#ff0014\">"+matcher.group()+"</font>");
        }
        matcher.appendTail(sb);
        content = sb.toString();
        Log.i("Utils", content);
        //如果匹配和替换都忽略大小写,则可以用以下方法
        //content = content.replaceAll(wordReg,"<font color=\"#ff0014\">"+keyword+"</font>");
        Log.i("Utils", content);
        return content;
    }
}
