package th.co.acc.dwi.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import th.co.acc.dwi.feign.conf.HeaderInterceptor;
import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.OrderPackage;
import th.co.acc.dwi.model.StatusOrder;
import th.co.acc.dwi.model.SubmitOrderScreen;
import th.co.acc.dwi.model.UpdatedOrderScreen;
import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.model.WorkOrder;

//@FeignClient(name = "order-service", url = "${DWI_WS_URL:http://localhost:8100/dwi-ws}")
@RibbonClient(name="dwi-ws")
//@FeignClient(name="dwi-ws",contextId="order-service", configuration = HeaderInterceptor.class)
@FeignClient(name="dwi-gtwy",contextId="order-service", configuration = HeaderInterceptor.class,path="/dwi-ws")
public interface OrderService  {
	
	@RequestMapping(method = RequestMethod.POST, value ="/submitOrder" )
	public SubmitOrderScreen applyOrder(SubmitOrderScreen submitOrderScreen) throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/submitOrder/uploadImg/{filename}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void uploadingImg(MultipartFile file,@PathVariable(value = "filename") String id) throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/orderDetail/{jobId}") 
	public SubmitOrderScreen getOrderJobByJobId(@PathVariable(value = "jobId") String jobId) throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/listOrders" )
	public List<WorkOrder> getOrderListForScreen(OrderList orderlist) throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/getAllpackages")
	public List<OrderPackage> getAllPackages() throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/getAllstatus")
	public List<StatusOrder> getAllStatus() throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/getUserInfoInListOrder")
	public List<UserInfo> getUserInfoInListOrder() throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/updateOrderByJobId")
	public void updateOrderByJobId(UpdatedOrderScreen updatedOrderScreen) throws Exception;
	
}
