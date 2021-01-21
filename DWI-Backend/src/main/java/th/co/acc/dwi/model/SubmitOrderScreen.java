package th.co.acc.dwi.model;

import org.springframework.web.multipart.MultipartFile;

public class SubmitOrderScreen extends WorkOrder {
	
	private int idInsertKey;
	private static final long serialVersionUID = 818913666841483948L;
	private String installDate;
	private Boolean isItInArea;
	private MultipartFile upload1;
	private MultipartFile upload2;
	private MultipartFile upload3;
	public static final String PENDING = "PENDING";
	public static final String PREVIEW = "PREVIEW";
	public static final String SUMMARY = "SUMMARY";
	public static final String UPDATED = "UPDATED";
	public static final String CLOSED = "CLOSED";
	public static final String ORDER = "ORDER";
	public static final String PAGE = "PAGE";
	public static final String YES = "Y";
	public static final String NO = "N";
	public static final String PARAM_BASE_URL = "baseURL";
	
	public int getIdInsertKey() {
		return idInsertKey;
	}
	public void setIdInsertKey(int idInsertKey) {
		this.idInsertKey = idInsertKey;
	}
	public String getInstallDate() {
		return installDate;
	}
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}
	public MultipartFile getUpload1() {
		return upload1;
	}
	public void setUpload1(MultipartFile upload1) {
		this.upload1 = upload1;
	}
	public MultipartFile getUpload2() {
		return upload2;
	}
	public void setUpload2(MultipartFile upload2) {
		this.upload2 = upload2;
	}
	public MultipartFile getUpload3() {
		return upload3;
	}
	public void setUpload3(MultipartFile upload3) {
		this.upload3 = upload3;
	}
	public Boolean getIsItInArea() {
		return isItInArea;
	}
	public void setIsItInArea(Boolean isItInArea) {
		this.isItInArea = isItInArea;
	}
	
}
