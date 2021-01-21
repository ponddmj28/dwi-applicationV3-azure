package th.co.acc.dwi.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.OrderPackage;
import th.co.acc.dwi.model.StatusOrder;
import th.co.acc.dwi.model.SubmitOrderScreen;
import th.co.acc.dwi.model.UpdatedOrderScreen;
import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.repository.AuthenRepository;
import th.co.acc.dwi.repository.OrderRepository;
import th.co.acc.dwi.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private AuthenRepository authenRepository;
	
	@Override
	public SubmitOrderScreen applyOrder(SubmitOrderScreen submitOrderScreen) throws Exception {
		orderRepository.insertWorkOrder(submitOrderScreen);
		submitOrderScreen.setId(String.valueOf(submitOrderScreen.getIdInsertKey()));
		return submitOrderScreen;
	}

	@Override
	public void uploadingImg(MultipartFile multipartFile,String filename) throws Exception {
//			if(multipartFile != null && !multipartFile.isEmpty()) {
//				File file = new File(pathStorageImg, filename);
//				FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
//			}
	}
	
	@Override
	public SubmitOrderScreen getOrderJobByJobId (String jobId) throws Exception {
		return orderRepository.getOrderJobByJobId(jobId);
	}
	
	@Override
	public void updateOrderByJobId (UpdatedOrderScreen updatedOrderScreen) throws Exception {
		orderRepository.updateOrderByJobId(updatedOrderScreen);
	}

	@Override
	public List<WorkOrder> getOrderListForScreen (OrderList orderList) throws Exception {
		return orderRepository.getOrderListForScreen(orderList);
	}
	
	@Override
	public List<OrderPackage> getAllPackages () throws Exception {
		return orderRepository.getAllPackages();
	}
	
	@Override
	public List<StatusOrder> getAllStatus () throws Exception {
		return orderRepository.getAllStatus();
	}
	
	@Override
	public List<UserInfo> getUserInfoInListOrder() throws Exception {
		return authenRepository.getUserInfoInListOrder();
	}
}
