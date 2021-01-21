package th.co.acc.reader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.service.WorkOrderService;
import th.co.acc.dwi.service.util.BeanUtils;

public class WorkOrderReader implements ItemReader<WorkOrder> {
	
	private int nextOrderIndex;
	
	private List<WorkOrder> works;
	
	private WorkOrderService workOrderService;
	
	private boolean done = false;
	public WorkOrderReader(WorkOrderService workOrderService) {
		this.nextOrderIndex = 0;
		this.workOrderService = workOrderService;
	}

	@Override
	public WorkOrder read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (isNotInitialized()) {
			works = fetchWorkOrderDataFromAPI();
        }else if(done) {
        	works = fetchWorkOrderDataFromAPI();
        }else {
        	done = false;
        }
 
		WorkOrder nextWorkOrder = null;
		if(BeanUtils.isNotEmpty(works)) {
			nextWorkOrder = works.remove(nextOrderIndex);
		}else {
			done = true;
		}
		
        return nextWorkOrder;
	}

	private boolean isNotInitialized() {
        return this.works == null;
    }
 
    private List<WorkOrder> fetchWorkOrderDataFromAPI() throws Exception {
    	OrderList order = new OrderList();
		LocalDate date = LocalDate.now();
		String today = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		order.setStartDate(today);
		order.setEndDate(today);
		this.works = workOrderService.getOrderListForScreen(order);
    	return this.works;
    }
}
