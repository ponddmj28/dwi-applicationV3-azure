package th.co.acc.dwi.service.adapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.service.WorkOrderService;
import th.co.acc.dwi.service.util.BeanUtils;

public class WorkOrderServiceAdapter implements InitializingBean {

	private WorkOrderService workOrderService;
	
	private List<WorkOrder> works;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		OrderList order = new OrderList();
		LocalDate date = LocalDate.now();
		String today = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		order.setStartDate(today);
		order.setEndDate(today);
		this.works = workOrderService.getOrderListForScreen(order);
	}
	
	public WorkOrder nextWork() {
		if(BeanUtils.isNotEmpty(works)) {
			return works.remove(0);
		}else {
			return null;
		}
		
	}
	public List<WorkOrder> getWorks() {
		return works;
	}

	public void setWorks(List<WorkOrder> works) {
		this.works = works;
	}

}
