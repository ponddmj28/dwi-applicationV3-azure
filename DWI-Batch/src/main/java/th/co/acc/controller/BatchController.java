package th.co.acc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.acc.dwi.model.Message;
import th.co.acc.dwi.remote.conf.RemotePropConfiguration;
import th.co.acc.dwi.service.WorkOrderService;

@RestController
public class BatchController {

	@Autowired
	private RemotePropConfiguration remotePropConfiguration;
	
	@Autowired
	private WorkOrderService workOrderService;
	
	@RequestMapping(value = "/isAlive" ,method=RequestMethod.GET)
	public @ResponseBody String isAlive() {
		return "{healthy:true}";
	}
	
	@RequestMapping(value = "/" ,method=RequestMethod.GET)
	public @ResponseBody String init() {
		return isAlive();
	}
	@RequestMapping(value = "/healthz",method=RequestMethod.GET)
	public @ResponseBody String isHealthz() {
		return isAlive();
	}
	
	@RequestMapping(value = "/env",method=RequestMethod.GET)
	public @ResponseBody RemotePropConfiguration isWhatEnv() {
		return remotePropConfiguration;
	}
	
	@RequestMapping(value = "/failureWithOutHystrix",method=RequestMethod.GET)
	public Message failureWithOutHystrix() throws Exception {
		return workOrderService.failureWithOutHystrix();
	}
}
