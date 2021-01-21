package th.co.acc.dwi.repository.sqlprovider;

import java.util.Map;

import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.SubmitOrderScreen;
import th.co.acc.dwi.service.util.BeanUtils;


public class OrderSqlProvider {
	public String insertWorkOrder (Map<String, Object> params){
		SubmitOrderScreen submitOrderScreen = (SubmitOrderScreen)params.get("submitOrderScreen");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" Insert into TBL_ORDER                          ");
		stringBuilder.append(" (                                                  ");
		stringBuilder.append(" JOB_ID                                                  ");
		stringBuilder.append(" ,CIRCIT_NO                                               ");
		stringBuilder.append(" ,FIRSTNAME                                               ");
		stringBuilder.append(" ,LASTNAME                                                ");
		stringBuilder.append(" ,ID_CARD                                                 ");
		stringBuilder.append(" ,ADDRESS                                                 ");
		stringBuilder.append(" ,PATH_IMG1                                               ");
		stringBuilder.append(" ,PATH_IMG2                                               ");
		stringBuilder.append(" ,PATH_IMG3                                               ");
		stringBuilder.append(" ,PHONE_NO_1                                              ");
		stringBuilder.append(" ,PHONE_NO_2                                              ");
		stringBuilder.append(" ,PACKAGE_CD                                              ");
		stringBuilder.append(" ,PACKAGE_DESC                                            ");
		stringBuilder.append(" ,ISINAREA                                            ");
		stringBuilder.append(" ,STATUS_CD                                               ");
		stringBuilder.append(" ,LINENAME                                             ");
		stringBuilder.append(" ,REMARK                                             ");
		stringBuilder.append(" ,INSTALL_DUEDATE                                         ");
		stringBuilder.append(" ,CLOSEDBY                                                ");
		stringBuilder.append(" ,CLOSEDDATE                                              ");
		stringBuilder.append(" ,CREATE_BY                                               ");
		stringBuilder.append(" ,CREATE_DATE                                             ");
		stringBuilder.append(" ,UPDATE_BY                                               ");
		stringBuilder.append(" ,UPDATE_DATE                                             ");
		stringBuilder.append(" ,UPD_CIRCITNO_BY                                         ");
		stringBuilder.append(" ,UPD_CIRCITNO_DATE)                                      ");
		stringBuilder.append(" values                                                   ");
		stringBuilder.append(" (                                              ");
		stringBuilder.append(" #{submitOrderScreen.jobId, jdbcType=VARCHAR}        ");
		stringBuilder.append(" ,#{submitOrderScreen.circuitNo, jdbcType=VARCHAR}        ");
		stringBuilder.append(" ,#{submitOrderScreen.firstName, jdbcType=VARCHAR}        ");
		stringBuilder.append(" ,#{submitOrderScreen.lastName, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.idCard, jdbcType=VARCHAR}           ");
		stringBuilder.append(" ,#{submitOrderScreen.customerAddress, jdbcType=VARCHAR}  ");
		stringBuilder.append(" ,#{submitOrderScreen.pathImg1, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.pathImg2, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.pathImg3, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.phoneNo1, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.phoneNo2, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.targetPackage, jdbcType=VARCHAR}    ");
		stringBuilder.append(" ,#{submitOrderScreen.targetPackageDesc, jdbcType=VARCHAR} ");
		stringBuilder.append(" ,#{submitOrderScreen.isInArea, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.statusCd, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.nmLine, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.remark, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.dueDateInstall, jdbcType=TIMESTAMP} ");
		stringBuilder.append(" ,#{submitOrderScreen.closedBy, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.closedDate, jdbcType=TIMESTAMP}     ");
		stringBuilder.append(" ,#{submitOrderScreen.createdBy, jdbcType=VARCHAR}        ");
		stringBuilder.append(" ,#{submitOrderScreen.createdDate, jdbcType=TIMESTAMP}    ");
		stringBuilder.append(" ,#{submitOrderScreen.updateBy, jdbcType=VARCHAR}        ");
		stringBuilder.append(" ,#{submitOrderScreen.updateDate, jdbcType=TIMESTAMP}    ");
		stringBuilder.append(" ,#{submitOrderScreen.updateCircuitNoBy, jdbcType=VARCHAR}         ");
		stringBuilder.append(" ,#{submitOrderScreen.updateCircuitDate, jdbcType=TIMESTAMP} )    ");
		return stringBuilder.toString();
	}
	
	public String getOrderJobByJobId(Map<String, Object> params) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" SELECT *                          ");
		stringBuilder.append(" FROM TBL_ORDER                          ");
		stringBuilder.append(" WHERE JOB_ID = #{jobId, jdbcType=VARCHAR}                       ");
		return stringBuilder.toString();
	}
	
	public String getOrderListForScreen(Map<String, Object> params) {
		OrderList orders = (OrderList) params.get("orders");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" SELECT *                          ");
		stringBuilder.append(" FROM TBL_ORDER                          ");
		if(BeanUtils.isEmpty(orders.getSales())
				&& BeanUtils.isEmpty(orders.getStatus())
				&& BeanUtils.isEmpty(orders.getStartDate()) 
				&& BeanUtils.isEmpty(orders.getEndDate())
				) {
			stringBuilder.append(" WHERE 1=0                      ");
			return stringBuilder.toString();
		}
		
		stringBuilder.append(" WHERE 1=1                      ");
		if(BeanUtils.isNotEmpty(orders.getSales())) {
			stringBuilder.append(" AND CREATE_BY = #{orders.sales, jdbcType=VARCHAR}                       ");
		}
		if(BeanUtils.isNotEmpty(orders.getStatus())) {
			stringBuilder.append(" AND STATUS_CD = #{orders.status, jdbcType=VARCHAR}                      ");
		}
		if(BeanUtils.isNotEmpty(orders.getStartDate()) && BeanUtils.isNotEmpty(orders.getEndDate())) {
			stringBuilder.append(" AND DATE(CREATE_DATE) >= STR_TO_DATE(#{orders.startDate, jdbcType=VARCHAR}, '%m/%d/%Y') AND DATE(CREATE_DATE) <= STR_TO_DATE(#{orders.endDate, jdbcType=VARCHAR}, '%m/%d/%Y') ");
		}
		stringBuilder.append(" ORDER BY CREATE_BY ASC, JOB_ID DESC ");
		return stringBuilder.toString();
	}
	
	public String getAllPackages(Map<String, Object> params) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" SELECT *                          ");
		stringBuilder.append(" FROM TBL_PKGPRMTION                          ");
		stringBuilder.append(" WHERE STATUS = '1'                         ");
		return stringBuilder.toString();
	}
	
	public String getAllStatus(Map<String, Object> params) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" SELECT *                          ");
		stringBuilder.append(" FROM TBL_STATUS                          ");
		stringBuilder.append(" WHERE INDICATOR_FL = 'A'                         ");
		return stringBuilder.toString();
	}
	
	public String updateOrderByJobId(Map<String, Object> params) {
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" UPDATE  TBL_ORDER   SET CIRCIT_NO = IFNULL(#{updatedObj.circuitNo, jdbcType=VARCHAR},CIRCIT_NO)             ");
		stringBuilder.append(" ,  ISINAREA = IFNULL(#{updatedObj.isitInArea, jdbcType=VARCHAR},ISINAREA)                        ");
		stringBuilder.append(" ,  INSTALL_DUEDATE = IFNULL(#{updatedObj.duedateInstall},INSTALL_DUEDATE)                        ");
		stringBuilder.append(" ,  STATUS_CD = IFNULL(#{updatedObj.status},STATUS_CD)                        ");
		stringBuilder.append(" ,  UPD_CIRCITNO_BY = IFNULL(#{updatedObj.updateBy, jdbcType=VARCHAR},UPD_CIRCITNO_BY)                         ");
		stringBuilder.append(" ,  UPD_CIRCITNO_DATE = IFNULL(SYSDATE(),UPD_CIRCITNO_DATE) ");
		stringBuilder.append(" ,  UPDATE_BY      =     IFNULL(#{updatedObj.updateBy, jdbcType=VARCHAR},UPDATE_BY)              ");
		stringBuilder.append(" ,  UPDATE_DATE    = IFNULL(SYSDATE(),UPDATE_DATE)                     ");
		stringBuilder.append(" WHERE JOB_ID = #{updatedObj.jobId, jdbcType=VARCHAR}  AND   STATUS_CD IN ('PENDING','UPDATED')                    ");
		return stringBuilder.toString();
	}
	
}
