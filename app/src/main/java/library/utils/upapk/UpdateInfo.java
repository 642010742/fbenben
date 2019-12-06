package library.utils.upapk;

import library.commonModel.BaseModel;

public class UpdateInfo extends BaseModel {


	/**
	 * versionId : 2
	 * versionNo : 1.1
	 * packageUrl : null
	 * isImposeUpdate : null
	 * isUpdate : null
	 * remark : null
	 * createTime : 2019-07-09 15:56:12
	 * updateTime : 2019-07-09 15:56:12
	 * isDeleted : null
	 */

	private String versionId;
	private String versionNo;
	private String packageUrl;
	private int isImposeUpdate;//0-是 1-否
	private int isUpdate;//0-是 1-否
	private String remark;
	private String createTime;
	private String updateTime;

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getPackageUrl() {
		return packageUrl;
	}

	public void setPackageUrl(String packageUrl) {
		this.packageUrl = packageUrl;
	}

	public int getIsImposeUpdate() {
		return isImposeUpdate;
	}

	public void setIsImposeUpdate(int isImposeUpdate) {
		this.isImposeUpdate = isImposeUpdate;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

}
