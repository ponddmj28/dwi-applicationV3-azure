package th.co.acc.dwi.model;

public class OrderList {
	private String startDate;
	private String endDate;
	private String status;
	private String sales;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSales() {
		return sales;
	}
	public void setSales(String sales) {
		this.sales = sales;
	}
	@Override
	public String toString() {
		return "OrderList [startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", sales=" + sales
				+ "]";
	}
	
}
