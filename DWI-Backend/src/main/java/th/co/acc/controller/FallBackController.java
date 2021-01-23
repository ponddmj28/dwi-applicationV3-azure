package th.co.acc.controller;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import io.vavr.control.Try;
import reactor.core.publisher.Mono;
import th.co.acc.dwi.model.Message;

@RestController
public class FallBackController {
	
	private Logger logger = LoggerFactory.getLogger(FallBackController.class);
	@GetMapping(path="/failure")
//	@HystrixCommand(fallbackMethod="getDefaultResponse",
//	commandProperties = {
//			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "40"),
//	          @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000"),
//	          @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
//	          @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
//			
//	})
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
	
	@GetMapping(path="/failureWithOutHystrix")
	public Message failureWithOutHystrix() {
		Random random = new Random();
		int dummy = random.nextInt(10 - 0 + 1) + 0;
		if(dummy > 5) {
			throw new NullPointerException("Failure");
		}
		return new Message("Success");
	}
	
	@GetMapping(path="/retry-resilience")
	@Retry(name="dwiws",fallbackMethod="getDefaultResponseRetry")
	public Message retryResilience() {
//		Random random = new Random();
//		int dummy = random.nextInt(10 - 0 + 1) + 0;
//		if(dummy > 5) {
//			throw new NullPointerException("Failure");
//		}
//		return new Message("Success");
		logger.info("retryResilience()");
		throw new NullPointerException("Failure");
	}
	
	public Message getDefaultResponseRetry(Throwable throwable) {
		return new Message("Handle By Retry");
	}
	
	@GetMapping(path="/cir-excp-resilience")
	@CircuitBreaker(name="dwiws",fallbackMethod="getDefaultResponseCircuitException")
	public Message circuitExceptionResilience() {
		Random random = new Random();
		int dummy = random.nextInt(10 - 0 + 1) + 0;
		if(dummy > 5) {
			throw new NullPointerException("Failure");
		}
		return new Message("Success");
	}
	
	public Message getDefaultResponseCircuitException(Throwable throwable) {
		if(!(throwable instanceof NullPointerException)) {
			throwable.printStackTrace();
		}
		
		return new Message("Handle By Circuit Open with encounter a exception "+throwable.getMessage());
	}
	
//	@GetMapping(path="/cir-timeout-resilience")
//	@CircuitBreaker(name="dwiws",fallbackMethod="getDefaultResponseCircuitTimeOut")
//	public Message circuitTimeOutResilience() {
//		new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                logger.info("circuitTimeOutResilience {}",Thread.currentThread().getName());
//            }
//        }, 5000);
//		return new Message("Success");
//	}
	
//	public Message getDefaultResponseCircuitTimeOut(Throwable throwable) {
//		return new Message("Handle By Circuit Open cause TimeOut");
//	}
	
	@GetMapping(path="/bulkhead-thread-resilience")
	@Bulkhead(name="dwiws",fallbackMethod="getDefaultResponseBulkhead",type=Type.THREADPOOL)
	public CompletableFuture<Message> bulkheadThreadResilience() {
		return CompletableFuture.completedFuture(new Message("Success"));
	}
	
	@GetMapping(path="/bulkhead-semaphore-resilience")
	@Bulkhead(name="dwiws",fallbackMethod="getDefaultResponseSemaphoreBulkhead",type=Type.SEMAPHORE)
	public CompletableFuture<Message> bulkheadSemaphoreResilience() {
		return CompletableFuture.completedFuture(new Message("Success"));
	}
	
	public CompletableFuture<Message> getDefaultResponseBulkhead(Throwable throwable) {
		if(!(throwable instanceof NullPointerException)) {
			throwable.printStackTrace();
		}
		return CompletableFuture.completedFuture(new Message("Handle By Circuit Open with encounter heavy loads"+Thread.currentThread().getName()));
	}
	
	public CompletableFuture<Message> getDefaultResponseSemaphoreBulkhead(Throwable throwable) {
		return CompletableFuture.completedFuture(new Message("Handle By Circuit Open with encounter heavy loads"+Thread.currentThread().getName()));
	}
	
	@GetMapping(path="/ratelimit-resilience")
	@RateLimiter(name="dwiws",fallbackMethod="getDefaultResponseRateLimit")
	public Message rateLimiterResilience() {
		return new Message("Success");
	}
	
	public Message getDefaultResponseRateLimit(Throwable throwable) {
		if(!(throwable instanceof NullPointerException)) {
			throwable.printStackTrace();
		}
		return new Message("Handle By Circuit Open with too much audiences");
	}
	

	@GetMapping(path="/cir-timeout-resilience")
	@TimeLimiter(name="dwiws",fallbackMethod="getDefaultResponseCircuitTimeOut")
	public CompletableFuture<Message> circuitTimeOutResilience() {
		Try.run(() -> Thread.sleep(5000));
		return CompletableFuture.completedFuture(new Message("Success")); 
	}
	
	public CompletableFuture<Message> getDefaultResponseCircuitTimeOut(Throwable throwable) {
		if(!(throwable instanceof NullPointerException)) {
			throwable.printStackTrace();
		}
		return CompletableFuture.completedFuture(new Message("Handle By timeout"));  
	}
	
}
