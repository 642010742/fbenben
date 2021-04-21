package com.dwz.library.Retrofit_Http.RequBean.baseBean;

/***
 * 请求数据实体
 * 
 * @author Administrator
 *
 */
public class BaseUploadItem extends BaseRequestBean {

	String path;
	String fileName;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
