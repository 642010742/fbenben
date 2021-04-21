package com.dwz.library.Retrofit_Http.HttpTools;

import android.util.Log;

import com.dwz.library.App.HttpConstants;

public class RxOkHttpLog {

	
	public static void d(String msg){
		
		if(HttpConstants.ISDEBUG){
			
			Log.d(HttpConstants.TAG, msg);
		}
	}
}
