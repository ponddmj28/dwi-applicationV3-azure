package th.co.acc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import th.co.acc.DWIRestfulApplication;
import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.OrderPackage;
import th.co.acc.dwi.model.StatusOrder;
import th.co.acc.dwi.model.SubmitOrderScreen;
import th.co.acc.dwi.model.UpdatedOrderScreen;
import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.remote.conf.RemotePropConfiguration;
import th.co.acc.dwi.service.OrderService;

@RestController
public class OrderJobController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RemotePropConfiguration remotePropConfiguration;
	
	
	@RequestMapping(value = "/" ,method=RequestMethod.GET)
	public @ResponseBody String init(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth) {
		return isAlive(auth);
	}
	@RequestMapping(value = "/healthz",method=RequestMethod.GET)
	public @ResponseBody String isHealthz(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth) {
		return isAlive(auth);
	}
	@RequestMapping(value = "/isAlive" ,method=RequestMethod.GET)
	public @ResponseBody String isAlive(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth) {
		return "{healthy:true}";
	}
	
	@RequestMapping(value = "/getAllpackages",method=RequestMethod.POST)
	public List<OrderPackage> getAllPackages(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth) throws Exception {
		return orderService.getAllPackages();
	}
	
	@RequestMapping(value = "/submitOrder",method=RequestMethod.POST)
	public SubmitOrderScreen  submitOrderScreen(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth, HttpServletRequest request,@RequestBody SubmitOrderScreen submitOrderScreen) throws Exception {
		return orderService.applyOrder(submitOrderScreen);
	}
	
	@RequestMapping(value = "/submitOrder/uploadImg/{filename}",method=RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void  uploadingImg(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth,HttpServletRequest request,@PathVariable("filename") String filename) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		multipartRequest.getFileMap().entrySet().forEach(entry-> {
			try {
				orderService.uploadingImg(entry.getValue(),filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
	}
	
	@RequestMapping(value = "/orderDetail/{jobId}",method=RequestMethod.POST)
	public SubmitOrderScreen orderDetailScreen(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth,@PathVariable("jobId") String jobId) throws Exception {
		return orderService.getOrderJobByJobId(jobId);
	}
	
	@RequestMapping(value = "/listOrders",method=RequestMethod.POST)
	public List<WorkOrder> getOrderListForScreen(
			@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth,
			@RequestBody OrderList orderlist
			) throws Exception {
		return orderService.getOrderListForScreen(orderlist);
	}
	
	@RequestMapping(value = "/getAllstatus",method=RequestMethod.POST)
	public List<StatusOrder> getAllStatus(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth) throws Exception {
		return orderService.getAllStatus();
	}
	
	@RequestMapping(value = "/updateOrderByJobId",method=RequestMethod.POST)
	public void rejectOrClosed(
			@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth,
			@RequestBody UpdatedOrderScreen updatedOrderScreen) throws Exception {
		orderService.updateOrderByJobId(updatedOrderScreen);
	}
	
	@RequestMapping(value = "/env",method=RequestMethod.GET)
	public @ResponseBody RemotePropConfiguration isWhatEnv() {
		return remotePropConfiguration;
	}
}
