package th.co.acc.dwi.controller;

import java.io.File;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.RequestContextUtils;

import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.OrderPackage;
import th.co.acc.dwi.model.StatusOrder;
import th.co.acc.dwi.model.SubmitOrderScreen;
import th.co.acc.dwi.model.UpdatedOrderScreen;
import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.remote.conf.RemotePropConfiguration;
import th.co.acc.dwi.service.OrderService;
import th.co.acc.dwi.utils.BeanUtils;

@Controller
public class OrderJobController {

	@Autowired
	private OrderService orderService;
	
	@Value("${path.storage.img}")
	private String pathStorageImg;
	
	
	@Autowired
	private RemotePropConfiguration remotePropConfiguration;
	
	@RequestMapping(value = "/env",method=RequestMethod.GET)
	public @ResponseBody RemotePropConfiguration isWhatEnv() {
		return remotePropConfiguration;
	}
	
	@RequestMapping(value = "/healthz",method=RequestMethod.GET)
	public @ResponseBody String isHealthz() {
		return isAlive();
	}
	
	@RequestMapping(value = "/isAlive",method=RequestMethod.GET)
	public @ResponseBody String isAlive() {
		return "{healthy:true}";
	}
	
	@RequestMapping(value = "/applyOrder",method=RequestMethod.GET)
	public String applyOrderScreen(
			Authentication authentication,
			HttpServletRequest request,  Model model, HttpSession session) throws Exception {
		UserInfo userInfo = null;
		if(BeanUtils.isNull(authentication)) {
			return "redirect:login";
		}
		userInfo = (UserInfo) authentication.getPrincipal();
		if(BeanUtils.isNull(userInfo)) {
			return "redirect:login";
		}
		
		
		
		if(UserInfo.ROLE_ADMIN_SALES.equals(userInfo.getRoleCode())) {
			return "redirect:listOrders";
		}
		
		model.addAttribute("order", new SubmitOrderScreen());
		List<OrderPackage> pkgs = orderService.getAllPackages();
		model.addAttribute("pkgs", pkgs);
		model.addAttribute(SubmitOrderScreen.PAGE, SubmitOrderScreen.ORDER);
		return "registerOrder";
	}
	
	@RequestMapping(value = "/applyOrder",method=RequestMethod.POST)
	public String applyOrder(
			Authentication authentication
			,HttpServletRequest request,  Model model, HttpSession session,@ModelAttribute SubmitOrderScreen submitOrderScreen ) throws Exception {
		UserInfo userInfo = null;
		if(BeanUtils.isNull(authentication)) {
			return "redirect:login";
		}
		userInfo = (UserInfo) authentication.getPrincipal();
		if(BeanUtils.isNull(userInfo)) {
			return "redirect:login";
		}
		
		if(UserInfo.ROLE_ADMIN_SALES.equals(userInfo.getRoleCode())) {
			return "redirect:listOrders";
		}
		
		model.addAttribute("order", submitOrderScreen == null ? new SubmitOrderScreen() : submitOrderScreen);
		model.addAttribute(SubmitOrderScreen.PAGE, SubmitOrderScreen.PREVIEW);
		return "registerOrder";
	}
	
	@RequestMapping(value = "/submitOrder",method=RequestMethod.POST)
	public String  submitOrderScreen(
			HttpServletRequest request,
			Authentication authentication,
			  Model model, HttpSession session,@ModelAttribute SubmitOrderScreen submitOrderScreen ) throws Exception {
		UserInfo userinfo = ((UserInfo) authentication.getPrincipal());
		java.util.Date dateinstall = null;
		if(BeanUtils.isNotEmpty(submitOrderScreen.getInstallDate())) {
			dateinstall =new SimpleDateFormat("MM/dd/yyyy").parse(submitOrderScreen.getInstallDate()); 
		}
		MultipartFile upload1 = submitOrderScreen.getUpload1();
		MultipartFile upload2 = submitOrderScreen.getUpload2();
		MultipartFile upload3 = submitOrderScreen.getUpload3();
		List<MultipartFile> lsmpf = Arrays.asList(upload1,upload2,upload3);
		submitOrderScreen.setPathImg1(!upload1.isEmpty() ? submitOrderScreen.getIdCard().concat("-").concat(String.valueOf(1)).concat(".").concat(FilenameUtils.getExtension(upload1.getOriginalFilename())) : null);
		submitOrderScreen.setPathImg2(!upload2.isEmpty() ? submitOrderScreen.getIdCard().concat("-").concat(String.valueOf(2)).concat(".").concat(FilenameUtils.getExtension(upload2.getOriginalFilename())) : null);
		submitOrderScreen.setPathImg3(!upload3.isEmpty() ? submitOrderScreen.getIdCard().concat("-").concat(String.valueOf(3)).concat(".").concat(FilenameUtils.getExtension(upload3.getOriginalFilename())) : null);
		submitOrderScreen.setStatusCd(SubmitOrderScreen.PENDING);
		submitOrderScreen.setIsInArea(submitOrderScreen.getIsItInArea() != null && submitOrderScreen.getIsItInArea() ? SubmitOrderScreen.YES : SubmitOrderScreen.NO );
		submitOrderScreen.setDueDateInstall(BeanUtils.isNotEmpty(dateinstall) ? new Date(dateinstall.getTime()) : null);
		submitOrderScreen.setCreatedBy(userinfo.getUsername());
		submitOrderScreen.setCreatedDate(new Date(new java.util.Date().getTime()));
		int i = 0;
		for(MultipartFile mpf : lsmpf) {
			if(BeanUtils.isNull(mpf)){
				
			}else if(i==0) {
				File file = new File(pathStorageImg, submitOrderScreen.getPathImg1());
				FileUtils.writeByteArrayToFile(file, mpf.getBytes());
			}else if(i==1) {
				File file = new File(pathStorageImg, submitOrderScreen.getPathImg2());
				FileUtils.writeByteArrayToFile(file, mpf.getBytes());
			}else if(i==2) {
				File file = new File(pathStorageImg, submitOrderScreen.getPathImg3());
				FileUtils.writeByteArrayToFile(file, mpf.getBytes());
			}
			i++;
		}
		//first 4 digits of username combine with YYYYMMDDmmhhss 
		DateTimeFormatter FOMATTER = DateTimeFormatter.ofPattern("MMddyyyyhhmmss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String dateFM = FOMATTER.format(localDateTime);
		String userKey = userinfo.getUsername().substring(0,4);
		submitOrderScreen.setJobId(userKey.concat(dateFM));
		SubmitOrderScreen submitOrderScreenNew = orderService.applyOrder(submitOrderScreen);
		submitOrderScreenNew.setInstallDate(submitOrderScreen.getInstallDate());
		submitOrderScreenNew.setIsItInArea(submitOrderScreen.getIsItInArea());
		submitOrderScreenNew.setRemark(submitOrderScreen.getRemark());
		
	    Map<String,SubmitOrderScreen> mapOrders = new HashMap<String,SubmitOrderScreen>();
	    mapOrders.put("submitForm", submitOrderScreenNew);
	    request.setAttribute(DispatcherServlet.INPUT_FLASH_MAP_ATTRIBUTE, mapOrders);
		return "redirect:/orderDetail/".concat(submitOrderScreenNew.getJobId());
	}
	
	@RequestMapping(value = "/orderDetail/{jobId}",method=RequestMethod.GET)
	public String orderDetailScreen(
			Authentication authentication
			,@PathVariable("jobId") String jobId,HttpServletRequest request,  Model model, HttpSession session) throws Exception {
		UserInfo userInfo = null;
		if(BeanUtils.isNull(authentication)) {
			return "redirect:login";
		}
		userInfo = (UserInfo) authentication.getPrincipal();
		if(BeanUtils.isNull(userInfo)) {
			return "redirect:login";
		}
		
		String roleCd = userInfo.getRoleCode() ;
		String userName = userInfo.getUsername();
		
		SubmitOrderScreen submitOrderScreenNew = null;
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		if (inputFlashMap != null) {
	        model.addAttribute("order", inputFlashMap.get("submitOrderScreen"));
	    }else {
	    	submitOrderScreenNew = orderService.getOrderJobByJobId(jobId);
	    	if(BeanUtils.isNull(submitOrderScreenNew)) {
				return "redirect:/listOrders";
			}
	    	String pattern = "MM/dd/yyyy";
	    	DateFormat df = new SimpleDateFormat(pattern);
	    	submitOrderScreenNew.setInstallDate(df.format(submitOrderScreenNew.getDueDateInstall()));
			submitOrderScreenNew.setIsItInArea(SubmitOrderScreen.YES.equals(submitOrderScreenNew.getIsInArea()) ? true : false);
			model.addAttribute("order", submitOrderScreenNew);
	    }
		model.addAttribute(SubmitOrderScreen.PAGE, SubmitOrderScreen.SUMMARY);
		
		if(UserInfo.ROLE_SALES.equals(roleCd) && !userName.equals(submitOrderScreenNew.getCreatedBy())) {
			return "redirect:/listOrders";
		}
		
		
		return "registerOrder";
	}
	
	@RequestMapping(value = "/listOrders",method=RequestMethod.GET)
	public String listOrders(
			Authentication authentication,
			HttpServletRequest request,  Model model, HttpSession session
			,@ModelAttribute OrderList orderlist
			) throws Exception {
			
			String userName = orderlist.getSales();
			String roleCd = null;
			
			UserInfo userInfo = null;
			if(authentication != null) {
				userInfo = (UserInfo) authentication.getPrincipal();
				roleCd = (String) userInfo.getRoleCode() ;
			}
			final String userInfoUserName = userInfo.getUsername();
			
			orderlist.setSales(BeanUtils.isNotEmpty(userName) ? userName : userInfoUserName );
			List<WorkOrder> wks = orderService.getOrderListForScreen(orderlist);
			if(UserInfo.ROLE_SALES.equals(roleCd)) {
				 wks = wks.stream()               
		                .filter(e -> e.getCreatedBy().equals(userInfoUserName) ) 
		                .collect(Collectors.toList());
			}
			model.addAttribute("wks", wks);
			List<StatusOrder> ordsts = orderService.getAllStatus();
			model.addAttribute("sts", ordsts);
			List<UserInfo> usInfos = orderService.getUserInfoInListOrder();
			if(UserInfo.ROLE_SALES.equals(roleCd)) {
				usInfos = usInfos.stream()               
		                .filter(e -> e.getUsername().equals(userInfoUserName) ) 
		                .collect(Collectors.toList());
			}
			model.addAttribute("users", usInfos);
			
		return "listOrders";
	}
	
	@RequestMapping(value = "/updatedOrder/{jobId}",method=RequestMethod.GET)
	public String updatedOrder(
			Authentication authentication
			,@PathVariable("jobId") String jobId,HttpServletRequest request,  Model model, HttpSession session) throws Exception {
		UserInfo userInfo = null;
		if(BeanUtils.isNull(authentication)) {
			return "redirect:/login";
		}
		
		userInfo = (UserInfo) authentication.getPrincipal();
		if(BeanUtils.isNull(userInfo)) {
			return "redirect:/login";
		}
		
		String roleCd = userInfo.getRoleCode() ;
		if(UserInfo.ROLE_SALES.equals(roleCd)) {
			return "redirect:/listOrders";
		}
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		UpdatedOrderScreen updatedOrder = new UpdatedOrderScreen();
		String pattern = "MM/dd/yyyy";
    	DateFormat df = new SimpleDateFormat(pattern);
    	SubmitOrderScreen submitOrderScreenNew = null;
		if (inputFlashMap != null) {
			submitOrderScreenNew = (SubmitOrderScreen) inputFlashMap.get("submitOrderScreen");
	    }else {
	    	submitOrderScreenNew = orderService.getOrderJobByJobId(jobId);
	    	submitOrderScreenNew.setInstallDate(df.format(submitOrderScreenNew.getDueDateInstall()));
			submitOrderScreenNew.setIsItInArea(SubmitOrderScreen.YES.equals(submitOrderScreenNew.getIsInArea()) ? true : false);
	    }
		updatedOrder.setJobId(submitOrderScreenNew.getJobId());
		updatedOrder.setCircuitNo(submitOrderScreenNew.getCircuitNo());
		updatedOrder.setDuedateInstallTmp(df.format(submitOrderScreenNew.getDueDateInstall()));
		updatedOrder.setIsItInAreaTmp(SubmitOrderScreen.YES.equals(submitOrderScreenNew.getIsInArea()) ? true : false);
		
		model.addAttribute("updateObj", updatedOrder);
		model.addAttribute("order", submitOrderScreenNew);
		model.addAttribute(SubmitOrderScreen.PAGE, SubmitOrderScreen.UPDATED);
		return "registerOrder";
	}
	
	@RequestMapping(value = "/updatedOrder",method=RequestMethod.POST)
	public String updatedOrder(Authentication authentication
			,@ModelAttribute UpdatedOrderScreen updatedOrderScreen ,HttpServletRequest request,  Model model, HttpSession session) throws Exception {
		if(BeanUtils.isNotEmpty(updatedOrderScreen.getJobId())) {
			UserInfo userInfo = null;
			if(BeanUtils.isNull(authentication)) {
				return "redirect:/login";
			}
			userInfo = (UserInfo) authentication.getPrincipal();
			if(BeanUtils.isNull(userInfo)) {
				return "redirect:/login";
			}
			
			String roleCd = userInfo.getRoleCode() ;
			
			if(UserInfo.ROLE_SALES.equals(roleCd)) {
				return "redirect:/listOrders";
			}
			
			updatedOrderScreen.setStatus(SubmitOrderScreen.UPDATED);
			String pattern = "MM/dd/yyyy";
	    	DateFormat df = new SimpleDateFormat(pattern);
	    	java.util.Date dateinstall = null;
			if(BeanUtils.isNotEmpty(updatedOrderScreen.getDuedateInstallTmp())) {
				dateinstall =new SimpleDateFormat("MM/dd/yyyy").parse(updatedOrderScreen.getDuedateInstallTmp()); 
				
			}
			updatedOrderScreen.setDuedateInstall(BeanUtils.isNotEmpty(dateinstall) ? new Date(dateinstall.getTime()) : null);
			updatedOrderScreen.setIsitInArea(updatedOrderScreen.getIsItInAreaTmp() != null && updatedOrderScreen.getIsItInAreaTmp() ? SubmitOrderScreen.YES : SubmitOrderScreen.NO );
			updatedOrderScreen.setUpdateBy("tosaporn");
			orderService.updateOrderByJobId(updatedOrderScreen);
		}
		return "redirect:listOrders";
	}
	
	@RequestMapping(value = "/rejectOrClosed/{jobId}",method=RequestMethod.GET)
	public String rejectOrClosed(
			Authentication authentication,
			@PathVariable("jobId") String jobId,HttpServletRequest request,  Model model, HttpSession session) throws Exception {
		UserInfo userInfo = null;
		if(BeanUtils.isNull(authentication)) {
			return "redirect:/login";
		}
		userInfo = (UserInfo) authentication.getPrincipal();
		if(BeanUtils.isNull(userInfo)) {
			return "redirect:/login";
		}
		
		String roleCd = userInfo.getRoleCode() ;
		
		if(UserInfo.ROLE_SALES.equals(roleCd)) {
			return "redirect:/listOrders";
		}
		
		Map<String, ?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
		UpdatedOrderScreen updatedOrder = new UpdatedOrderScreen();
		String pattern = "MM/dd/yyyy";
    	DateFormat df = new SimpleDateFormat(pattern);
    	SubmitOrderScreen submitOrderScreenNew = null;
		if (inputFlashMap != null) {
			submitOrderScreenNew = (SubmitOrderScreen) inputFlashMap.get("submitOrderScreen");
	    }else {
	    	submitOrderScreenNew = orderService.getOrderJobByJobId(jobId);
	    	submitOrderScreenNew.setInstallDate(df.format(submitOrderScreenNew.getDueDateInstall()));
			submitOrderScreenNew.setIsItInArea(SubmitOrderScreen.YES.equals(submitOrderScreenNew.getIsInArea()) ? true : false);
	    }
		updatedOrder.setJobId(submitOrderScreenNew.getJobId());
		updatedOrder.setCircuitNo(submitOrderScreenNew.getCircuitNo());
		updatedOrder.setDuedateInstallTmp(df.format(submitOrderScreenNew.getDueDateInstall()));
		updatedOrder.setIsItInAreaTmp(SubmitOrderScreen.YES.equals(submitOrderScreenNew.getIsInArea()) ? true : false);
		updatedOrder.setStatus(submitOrderScreenNew.getStatusCd());
		List<StatusOrder> ordsts = orderService.getAllStatus();
		model.addAttribute("sts", ordsts);
		
		model.addAttribute("updateObj", updatedOrder);
		model.addAttribute("order", submitOrderScreenNew);
		model.addAttribute(SubmitOrderScreen.PAGE, SubmitOrderScreen.CLOSED);
		return "registerOrder";
	}
	
	@RequestMapping(value = "/rejectOrClosed",method=RequestMethod.POST)
	public String rejectOrClosed(
			Authentication authentication,
			@ModelAttribute UpdatedOrderScreen updatedOrderScreen ,HttpServletRequest request,  Model model, HttpSession session) throws Exception {
		UserInfo userInfo = null;
		if(BeanUtils.isNull(authentication)) {
			return "redirect:/login";
		}
		userInfo = (UserInfo) authentication.getPrincipal();
		if(BeanUtils.isNull(userInfo)) {
			return "redirect:/login";
		}
		
		String roleCd = userInfo.getRoleCode() ;
		if(UserInfo.ROLE_SALES.equals(roleCd)) {
			return "redirect:/listOrders";
		}
		
		if(BeanUtils.isNotEmpty(updatedOrderScreen.getJobId())) {
			if(SubmitOrderScreen.CLOSED.equals(updatedOrderScreen.getStatus())) {
				updatedOrderScreen.setStatus(SubmitOrderScreen.CLOSED);
			}else {
				updatedOrderScreen.setStatus(SubmitOrderScreen.REJECTED);
			}
			
			updatedOrderScreen.setUpdateBy(userInfo.getUsername());
			orderService.updateOrderByJobId(updatedOrderScreen);
		}
		return "redirect:listOrders";
	}
}
