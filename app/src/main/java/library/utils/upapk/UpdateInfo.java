package library.utils.upapk;

import library.commonModel.BaseModel;

public class UpdateInfo extends BaseModel {

	/**
	 * paramKey : APP_VERSION
	 * paramName : APP版本更新
	 * paramValue : 更新地址
	 * memo : 版本更新（service_type==2 为强制更新）
	 * isConfigurable : 1
	 * operatorType : null
	 * operatorId : null
	 * createTime : 2019-06-10 11:39:03
	 * updateTime : 2019-06-10 11:39:06
	 * isDeleted : 0
	 * serviceType : 2
	 */

	private String paramKey;
	private String paramName;
	private String paramValue;
	private String memo;
	private int isConfigurable;
	private Object operatorType;
	private Object operatorId;
	private String createTime;
	private String updateTime;
	private int isDeleted;
	private String upContent;
	private String newVersion;

	public String getUpContent() {
		return upContent;
	}

	public void setUpContent(String upContent) {
		this.upContent = upContent;
	}

	public String getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getIsConfigurable() {
		return isConfigurable;
	}

	public void setIsConfigurable(int isConfigurable) {
		this.isConfigurable = isConfigurable;
	}

	public Object getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Object operatorType) {
		this.operatorType = operatorType;
	}

	public Object getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Object operatorId) {
		this.operatorId = operatorId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

}
