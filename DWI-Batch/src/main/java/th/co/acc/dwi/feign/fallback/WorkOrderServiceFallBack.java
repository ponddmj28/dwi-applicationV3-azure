package th.co.acc.dwi.feign.fallback;

import java.util.List;

import org.springframework.stereotype.Component;

import th.co.acc.dwi.model.Message;
import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.service.WorkOrderService;

@Component
public class WorkOrderServiceFallBack  implements WorkOrderService {

	@Override
	public List<WorkOrder> getOrderListForScreen(OrderList orderlist) throws Exception {
		return null;
	}
	
	@Override
	public Message failureWithOutHystrix() throws Exception {
		return new Message("Handle By Feign Client");
	}
	
}
