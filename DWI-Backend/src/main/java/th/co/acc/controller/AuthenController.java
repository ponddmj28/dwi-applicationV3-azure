package th.co.acc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import th.co.acc.DWIRestfulApplication;
import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.repository.AuthenRepository;

@RestController
public class AuthenController {

	@Autowired
	private AuthenRepository authenRepository;
	
	@RequestMapping(value = "/getUserInfobyUsername",method=RequestMethod.POST)
	public UserInfo getUserInfobyUsername(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth,@RequestBody String username) throws Exception {
		return authenRepository.getUserInfobyUsername(username);
	}
	
	@RequestMapping(value = "/getUserInfoInListOrder",method=RequestMethod.POST)
	public List<UserInfo> getUserInfoInListOrder(@RequestHeader(DWIRestfulApplication.AUTH_TOKEN) String auth) throws Exception {
		return authenRepository.getUserInfoInListOrder();
	}
	
}
