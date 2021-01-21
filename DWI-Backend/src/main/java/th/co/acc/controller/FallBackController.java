package th.co.acc.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class FallBackController {
	
	@GetMapping(path="/failure")
	@HystrixCommand(fallbackMethod="getDefaultResponse",
	commandProperties = {
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "40"),
	          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000"),
	          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
	          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
			
	})
	public Message getFailure() {
		Random random = new Random();
		int dummy = random.nextInt(10 - 0 + 1) + 0;
		if(dummy > 5) {
			throw new NullPointerException("Failure");
		}
		return new Message("Success");
	}
	
	public Message getDefaultResponse(Throwable throwable) {
		return new Message("Handle By WS Fallback Hystrix");
	}
	public class Message {
		private String message;
		
		public Message(String message) {
			super();
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
	}
	
	@GetMapping(path="/failureWithOutHystrix")
	public Message failureWithOutHystrix() {
		Random random = new Random();
		int dummy = random.nextInt(10 - 0 + 1) + 0;
		if(dummy > 5) {
			throw new NullPointerException("Failure");
		}
		return new Message("Success");
	}
}
