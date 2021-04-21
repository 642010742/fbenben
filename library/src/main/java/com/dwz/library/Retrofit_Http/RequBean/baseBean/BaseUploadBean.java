package com.dwz.library.Retrofit_Http.RequBean.baseBean;

import java.util.List;

/***
 * 请求数据实体
 * 
 * @author Administrator
 *
 */
public class BaseUploadBean extends BaseRequestBean {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	List<BaseUploadItem> pathList;

	public List<BaseUploadItem> getPathList() {
		return pathList;
	}

	public void setPathList(List<BaseUploadItem> pathList) {
		this.pathList = pathList;
	}
}
