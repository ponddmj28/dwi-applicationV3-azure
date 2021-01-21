package th.co.acc.dwi.service;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import th.co.acc.dwi.feign.conf.HeaderInterceptor;
import th.co.acc.dwi.model.UserInfo;

//@FeignClient(name = "authen-service", url = "${DWI_WS_URL:http://localhost:8100/dwi-ws}")
@RibbonClient(name="dwi-ws")
//@FeignClient(name="dwi-ws",contextId="authen-service", configuration = HeaderInterceptor.class)
@FeignClient(name="dwi-gtwy",contextId="authen-service", configuration = HeaderInterceptor.class,path="/dwi-ws")
public interface AuthenticationService  {
	
	@RequestMapping(method = RequestMethod.POST, value ="/getUserInfobyUsername")
	public UserInfo getUserInfobyUsername(String username) throws Exception;
	
	@RequestMapping(method = RequestMethod.POST, value ="/getUserInfoInListOrder")
	public List<UserInfo> getUserInfoInListOrder() throws Exception;
}
