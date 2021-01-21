package th.co.acc.dwi.repository;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.StatementType;

import th.co.acc.dwi.model.OrderList;
import th.co.acc.dwi.model.OrderPackage;
import th.co.acc.dwi.model.StatusOrder;
import th.co.acc.dwi.model.SubmitOrderScreen;
import th.co.acc.dwi.model.UpdatedOrderScreen;
import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.repository.sqlprovider.OrderSqlProvider;


@Mapper
public interface OrderRepository {
	
	@InsertProvider(type=OrderSqlProvider.class,method="insertWorkOrder")
	@Options(useGeneratedKeys = true, keyProperty = "idInsertKey", keyColumn = "REQ_NO")
	public int insertWorkOrder(@Param("submitOrderScreen") SubmitOrderScreen submitOrderScreen) throws Exception;
	
	@SelectProvider(type = OrderSqlProvider.class, method = "getOrderJobByJobId")
	@Options(statementType = StatementType.CALLABLE,  useCache = false)
	@Results({ @Result(property = "id", column = "REQ_NO" ,javaType=String.class)
	,@Result(property = "jobId", column = "JOB_ID" ,javaType=String.class)
	,@Result(property = "circuitNo", column = "CIRCIT_NO" ,javaType=String.class)
	,@Result(property = "firstName", column = "FIRSTNAME" ,javaType=String.class)
	,@Result(property = "lastName", column = "LASTNAME" ,javaType=String.class)
	,@Result(property = "idCard", column = "ID_CARD" ,javaType=String.class)
	,@Result(property = "customerAddress", column = "ADDRESS" ,javaType=String.class)
	,@Result(property = "pathImg1", column = "PATH_IMG1" ,javaType=String.class)
	,@Result(property = "pathImg2", column = "PATH_IMG2" ,javaType=String.class)
	,@Result(property = "pathImg3", column = "PATH_IMG3" ,javaType=String.class)
	,@Result(property = "phoneNo1", column = "PHONE_NO_1" ,javaType=String.class)
	,@Result(property = "phoneNo2", column = "PHONE_NO_2" ,javaType=String.class)
	,@Result(property = "targetPackage", column = "PACKAGE_CD" ,javaType=String.class)
	,@Result(property = "targetPackageDesc", column = "PACKAGE_DESC" ,javaType=String.class)
	,@Result(property = "isInArea", column = "ISINAREA" ,javaType=String.class)
	,@Result(property = "statusCd", column = "STATUS_CD" ,javaType=String.class)
	,@Result(property = "nmLine", column = "LINENAME" ,javaType=String.class)
	,@Result(property = "remark", column = "REMARK" ,javaType=String.class)
	,@Result(property = "dueDateInstall", column = "INSTALL_DUEDATE" ,javaType=java.sql.Date.class)
	,@Result(property = "closedBy", column = "CLOSEDBY" ,javaType=String.class)
	,@Result(property = "closedDate", column = "CLOSEDDATE" ,javaType=java.sql.Date.class)
	,@Result(property = "createdBy", column = "CREATE_BY" ,javaType=String.class)
	,@Result(property = "createdDate", column = "CREATE_DATE" ,javaType=java.sql.Date.class)
	,@Result(property = "updateCircuitNoBy", column = "UPD_CIRCITNO_BY" ,javaType=String.class)
	,@Result(property = "updateCircuitDate", column = "UPD_CIRCITNO_DATE" ,javaType=java.sql.Date.class)
	,@Result(property = "updateBy", column = "UPDATE_BY" ,javaType=String.class)
	,@Result(property = "updateDate", column = "UPDATE_DATE" ,javaType=java.sql.Date.class)
	})
	public SubmitOrderScreen getOrderJobByJobId(@Param("jobId")String jobId) throws Exception;
	
	@SelectProvider(type = OrderSqlProvider.class, method = "getOrderListForScreen")
	@Options(statementType = StatementType.CALLABLE,  useCache = false)
	@Results({ @Result(property = "id", column = "REQ_NO" ,javaType=String.class)
	,@Result(property = "jobId", column = "JOB_ID" ,javaType=String.class)
	,@Result(property = "circuitNo", column = "CIRCIT_NO" ,javaType=String.class)
	,@Result(property = "firstName", column = "FIRSTNAME" ,javaType=String.class)
	,@Result(property = "lastName", column = "LASTNAME" ,javaType=String.class)
	,@Result(property = "idCard", column = "ID_CARD" ,javaType=String.class)
	,@Result(property = "customerAddress", column = "ADDRESS" ,javaType=String.class)
	,@Result(property = "pathImg1", column = "PATH_IMG1" ,javaType=String.class)
	,@Result(property = "pathImg2", column = "PATH_IMG2" ,javaType=String.class)
	,@Result(property = "pathImg3", column = "PATH_IMG3" ,javaType=String.class)
	,@Result(property = "phoneNo1", column = "PHONE_NO_1" ,javaType=String.class)
	,@Result(property = "phoneNo2", column = "PHONE_NO_2" ,javaType=String.class)
	,@Result(property = "targetPackage", column = "PACKAGE_CD" ,javaType=String.class)
	,@Result(property = "targetPackageDesc", column = "PACKAGE_DESC" ,javaType=String.class)
	,@Result(property = "isInArea", column = "ISINAREA" ,javaType=String.class)
	,@Result(property = "statusCd", column = "STATUS_CD" ,javaType=String.class)
	,@Result(property = "nmLine", column = "LINENAME" ,javaType=String.class)
	,@Result(property = "remark", column = "REMARK" ,javaType=String.class)
	,@Result(property = "dueDateInstall", column = "INSTALL_DUEDATE" ,javaType=java.sql.Date.class)
	,@Result(property = "closedBy", column = "CLOSEDBY" ,javaType=String.class)
	,@Result(property = "closedDate", column = "CLOSEDDATE" ,javaType=java.sql.Date.class)
	,@Result(property = "createdBy", column = "CREATE_BY" ,javaType=String.class)
	,@Result(property = "createdDate", column = "CREATE_DATE" ,javaType=java.sql.Date.class)
	,@Result(property = "updateCircuitNoBy", column = "UPD_CIRCITNO_BY" ,javaType=String.class)
	,@Result(property = "updateCircuitDate", column = "UPD_CIRCITNO_DATE" ,javaType=java.sql.Date.class)
	,@Result(property = "updateBy", column = "UPDATE_BY" ,javaType=String.class)
	,@Result(property = "updateDate", column = "UPDATE_DATE" ,javaType=java.sql.Date.class)
	})
	public List<WorkOrder> getOrderListForScreen(@Param("orders") OrderList orderList) throws Exception;

	@SelectProvider(type = OrderSqlProvider.class, method = "getAllPackages")
	@Options(statementType = StatementType.CALLABLE,  useCache = false)
	@Results({ @Result(property = "id", column = "ID_PKG" ,javaType=String.class)
	,@Result(property = "packageCd", column = "PKG_CD" ,javaType=String.class)
	,@Result(property = "packageDesc", column = "PKG_DESC" ,javaType=String.class)
	,@Result(property = "status", column = "STATUS" ,javaType=String.class)
	})
	public List<OrderPackage> getAllPackages();
	
	@SelectProvider(type = OrderSqlProvider.class, method = "getAllStatus")
	@Options(statementType = StatementType.CALLABLE,  useCache = false)
	@Results({ @Result(property = "id", column = "ID_STS" ,javaType=String.class)
	,@Result(property = "statusCd", column = "STS_CD" ,javaType=String.class)
	,@Result(property = "statusDesc", column = "STS_DESC" ,javaType=String.class)
	,@Result(property = "status", column = "STATUS" ,javaType=String.class)
	})
	public List<StatusOrder> getAllStatus();

	@UpdateProvider(type=OrderSqlProvider.class,method ="updateOrderByJobId")
	public void updateOrderByJobId(@Param("updatedObj") UpdatedOrderScreen updatedOrderScreen);
}
