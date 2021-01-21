package th.co.acc.dwi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.service.AuthenticationService;
import th.co.acc.dwi.utils.BeanUtils;

@Service	
public class AuthenticationServiceImpl  implements UserDetailsService{
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Override
	public UserInfo loadUserByUsername(String username) throws UsernameNotFoundException {
			UserInfo userInfo;
			try {
				userInfo = authenticationService.getUserInfobyUsername(username);
			} catch (Exception e) {
				e.printStackTrace();
				throw new UsernameNotFoundException("Authentication failure");
			}
			if(BeanUtils.isNull(userInfo)) {
				throw new UsernameNotFoundException("Username Not Found");
			}
	        return userInfo;
	}

}
