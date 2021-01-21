package th.co.acc.dwi.feign.conf;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class HeaderInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate requestTemplate) {
		requestTemplate.header("Auth", "Pond");
	}

}
