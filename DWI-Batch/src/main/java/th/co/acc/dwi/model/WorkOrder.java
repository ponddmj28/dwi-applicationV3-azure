package th.co.acc.dwi.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="WorkOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkOrder {
	
	private String id;
	private String jobId;
	private String firstName;
	private String lastName;
	private String idCard;
	private String phoneNo1;
	private String phoneNo2;
	private String targetPackage;
	private String targetPackageDesc;
	private String remark;
	private String isInArea;
	private String customerAddress;
	private String pathImg1;
	private String pathImg2;
	private String pathImg3;
	private String statusCd;
	private String circuitNo;
	private String closedBy;
	private String nmLine;
	private Date dueDateInstall;
	private Date closedDate;
	private String createdBy;
	private Date createdDate;
	private String updateCircuitNoBy;
	private Date updateCircuitDate;
	private String updateBy;
	private Date updateDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhoneNo1() {
		return phoneNo1;
	}
	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}
	public String getPhoneNo2() {
		return phoneNo2;
	}
	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}
	public String getTargetPackage() {
		return targetPackage;
	}
	public void setTargetPackage(String targetPackage) {
		this.targetPackage = targetPackage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsInArea() {
		return isInArea;
	}
	public void setIsInArea(String isInArea) {
		this.isInArea = isInArea;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getPathImg1() {
		return pathImg1;
	}
	public void setPathImg1(String pathImg1) {
		this.pathImg1 = pathImg1;
	}
	public String getPathImg2() {
		return pathImg2;
	}
	public void setPathImg2(String pathImg2) {
		this.pathImg2 = pathImg2;
	}
	public String getPathImg3() {
		return pathImg3;
	}
	public void setPathImg3(String pathImg3) {
		this.pathImg3 = pathImg3;
	}
	public String getCircuitNo() {
		return circuitNo;
	}
	public void setCircuitNo(String circuitNo) {
		this.circuitNo = circuitNo;
	}
	public String getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	public String getTargetPackageDesc() {
		return targetPackageDesc;
	}
	public void setTargetPackageDesc(String targetPackageDesc) {
		this.targetPackageDesc = targetPackageDesc;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public Date getDueDateInstall() {
		return dueDateInstall;
	}
	public void setDueDateInstall(Date dueDateInstall) {
		this.dueDateInstall = dueDateInstall;
	}
	public String getNmLine() {
		return nmLine;
	}
	public void setNmLine(String nmLine) {
		this.nmLine = nmLine;
	}
	public String getUpdateCircuitNoBy() {
		return updateCircuitNoBy;
	}
	public void setUpdateCircuitNoBy(String updateCircuitNoBy) {
		this.updateCircuitNoBy = updateCircuitNoBy;
	}
	public Date getUpdateCircuitDate() {
		return updateCircuitDate;
	}
	public void setUpdateCircuitDate(Date updateCircuitDate) {
		this.updateCircuitDate = updateCircuitDate;
	}
	@Override
	public String toString() {
		return "WorkOrder [id=" + id + ", jobId=" + jobId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", idCard=" + idCard + ", phoneNo1=" + phoneNo1 + ", phoneNo2=" + phoneNo2 + ", targetPackage="
				+ targetPackage + ", targetPackageDesc=" + targetPackageDesc + ", remark=" + remark + ", isInArea="
				+ isInArea + ", customerAddress=" + customerAddress + ", pathImg1=" + pathImg1 + ", pathImg2="
				+ pathImg2 + ", pathImg3=" + pathImg3 + ", statusCd=" + statusCd + ", circuitNo=" + circuitNo
				+ ", closedBy=" + closedBy + ", nmLine=" + nmLine + ", dueDateInstall=" + dueDateInstall
				+ ", closedDate=" + closedDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updateCircuitNoBy=" + updateCircuitNoBy + ", updateCircuitDate=" + updateCircuitDate
				+ ", updateBy=" + updateBy + ", updateDate=" + updateDate + "]";
	}
	
}
