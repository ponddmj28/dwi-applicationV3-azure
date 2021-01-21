package th.co.acc.dwi.model;

import java.sql.Date;

public class UpdatedOrderScreen {
	private String jobId;
	private String circuitNo;
	private Boolean isItInAreaTmp;
	private String isitInArea;
	private String duedateInstallTmp;
	private Date duedateInstall;
	private String status;
	private String updateBy;
	private Date updateDate;
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getCircuitNo() {
		return circuitNo;
	}
	public void setCircuitNo(String circuitNo) {
		this.circuitNo = circuitNo;
	}
	public Boolean getIsItInAreaTmp() {
		return isItInAreaTmp;
	}
	public void setIsItInAreaTmp(Boolean isItInAreaTmp) {
		this.isItInAreaTmp = isItInAreaTmp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getIsitInArea() {
		return isitInArea;
	}
	public void setIsitInArea(String isitInArea) {
		this.isitInArea = isitInArea;
	}
	public String getDuedateInstallTmp() {
		return duedateInstallTmp;
	}
	public void setDuedateInstallTmp(String duedateInstallTmp) {
		this.duedateInstallTmp = duedateInstallTmp;
	}
	public void setDuedateInstall(Date duedateInstall) {
		this.duedateInstall = duedateInstall;
	}
	public Date getDuedateInstall() {
		return duedateInstall;
	}
	
	
}
