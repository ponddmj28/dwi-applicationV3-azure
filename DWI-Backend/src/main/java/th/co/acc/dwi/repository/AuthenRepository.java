package th.co.acc.dwi.repository;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.repository.sqlprovider.AuthenSqlProvider;

@Repository
public interface AuthenRepository {
	
	@SelectProvider(type = AuthenSqlProvider.class, method = "sqlUserInfobyUsername")
	@Options(statementType = StatementType.CALLABLE,  useCache = false)
	@Results({ @Result(property = "idmember", column = "ID_MEMBER" ,javaType=String.class)
		,@Result(property = "username", column = "USERNAME" ,javaType=String.class)
		,@Result(property = "password", column = "PASSWORD" ,javaType=String.class)
		,@Result(property = "companyCode", column = "COMPANY_CODE" ,javaType=String.class)
		,@Result(property = "firstName", column = "FIRSTNAME" ,javaType=String.class)
		,@Result(property = "lastName", column = "LASTNAME" ,javaType=String.class)
		,@Result(property = "email", column = "EMAIL" ,javaType=String.class)
		,@Result(property = "phoneNo", column = "PHONENO" ,javaType=String.class)
		,@Result(property = "roleCode", column = "ROLE_CODE" ,javaType=String.class)
		
	})
	public UserInfo getUserInfobyUsername(@Param("username") String username);
	
	
	@SelectProvider(type = AuthenSqlProvider.class, method = "sqlAllUserInfo")
	@Options(statementType = StatementType.CALLABLE,  useCache = false)
	@Results({ @Result(property = "idmember", column = "ID_MEMBER" ,javaType=String.class)
		,@Result(property = "username", column = "USERNAME" ,javaType=String.class)
		,@Result(property = "password", column = "PASSWORD" ,javaType=String.class)
		,@Result(property = "companyCode", column = "COMPANY_CODE" ,javaType=String.class)
		,@Result(property = "firstName", column = "FIRSTNAME" ,javaType=String.class)
		,@Result(property = "lastName", column = "LASTNAME" ,javaType=String.class)
		,@Result(property = "email", column = "EMAIL" ,javaType=String.class)
		,@Result(property = "phoneNo", column = "PHONENO" ,javaType=String.class)
		,@Result(property = "roleCode", column = "ROLE_CODE" ,javaType=String.class)
		,@Result(property = "status", column = "status" ,javaType=String.class)
	})
	public List<UserInfo> getUserInfoInListOrder();
}
