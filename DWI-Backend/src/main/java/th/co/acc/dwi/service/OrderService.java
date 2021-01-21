package th.co.acc.dwi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.OrderPackage;
import th.co.acc.dwi.model.StatusOrder;
import th.co.acc.dwi.model.SubmitOrderScreen;
import th.co.acc.dwi.model.UpdatedOrderScreen;
import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.model.WorkOrder;

public interface OrderService {
	public SubmitOrderScreen applyOrder(SubmitOrderScreen submitOrderScreen) throws Exception;
	public SubmitOrderScreen getOrderJobByJobId(String jobId) throws Exception;
	public List<WorkOrder> getOrderListForScreen(OrderList orderlist) throws Exception;
	public List<OrderPackage> getAllPackages() throws Exception;
	public List<StatusOrder> getAllStatus() throws Exception;
	public List<UserInfo> getUserInfoInListOrder() throws Exception;
	public void updateOrderByJobId(UpdatedOrderScreen updatedOrderScreen) throws Exception;
	public void uploadingImg(MultipartFile multipartFile, String idCard) throws Exception;
}
