package th.co.acc.dwi.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import th.co.acc.dwi.feign.conf.HeaderInterceptor;
import th.co.acc.dwi.feign.fallback.WorkOrderServiceFallBack;
import th.co.acc.dwi.model.Message;
import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.WorkOrder;

//@FeignClient(name = "order-service", url = "${DWI_WS_URL:http://localhost:8100/dwi-ws}")
@RibbonClient(name="dwi-ws")
//@FeignClient(name="dwi-ws",contextId="order-service", configuration = HeaderInterceptor.class)
@FeignClient(name="dwi-gtwy",contextId="order-service", configuration = HeaderInterceptor.class,path="/dwi-ws", fallback=WorkOrderServiceFallBack.class)
public interface WorkOrderService {
	
	@RequestMapping(method = RequestMethod.POST, value ="/listOrders" )
	public List<WorkOrder> getOrderListForScreen(OrderList orderlist) throws Exception;
	
	@RequestMapping(method = RequestMethod.GET, value ="/failureWithOutHystrix" )
	public Message failureWithOutHystrix() throws Exception;
}
