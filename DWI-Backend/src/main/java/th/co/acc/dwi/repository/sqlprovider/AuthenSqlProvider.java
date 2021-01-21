package th.co.acc.dwi.repository.sqlprovider;

import java.util.Map;

public class AuthenSqlProvider {
	
	public String sqlUserInfobyUsername (Map<String,Object> params) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * ");
		sqlBuilder.append(" FROM TBL_MEMBER ");
		sqlBuilder.append(" WHERE username = #{username} ");
		return sqlBuilder.toString();
	}
	
	public String sqlAllUserInfo (Map<String,Object> params) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * ");
		sqlBuilder.append(" FROM TBL_MEMBER ");
		sqlBuilder.append(" WHERE ROLE_CODE IN ('SALES','ADMIN') ");
		sqlBuilder.append(" AND STATUS = '1'");
		return sqlBuilder.toString();
	}
	
}
