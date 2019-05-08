package library.Retrofit_Http.HttpTools;


import android.text.TextUtils;

import java.lang.reflect.Field;

import library.Retrofit_Http.RequBean.RequestBean;
import library.Retrofit_Http.RequBean.baseBean.BaseRequestBean;

public class Regix<T> {

	/**
	 *
	 * @param requestBean
	 * @return
     */
	public String getUrlParams(RequestBean requestBean) {
		String filedName = null;
		Object obj = null;
		BaseRequestBean bsrqBean = requestBean.getBsrqBean();
		StringBuilder params = new StringBuilder();
		if (bsrqBean != null) {
			try {
				// 遍历属性
				Field[] f = bsrqBean.getClass().getDeclaredFields();
				for (Field fd : f) {
					filedName = fd.getName();// 属性的名字
					if (!TextUtils.isEmpty(filedName)
							&& (!filedName.equals("requestType")
									|| !filedName.equals("bsrqBean") || !filedName
										.equals("url"))) {
						fd.setAccessible(true);
						// 这个对象字段类属性值
						obj = fd.get(bsrqBean);
						if (obj != null) {
							params.append("&").append(filedName + "=")
									.append(obj.toString().trim());
						}
					}
					if (filedName != null) {
						filedName = null;
					}
					if (obj != null) {
						obj = null;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return params.toString();
	}
}
