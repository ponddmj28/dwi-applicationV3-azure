package th.co.acc.dwi.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SpringSecuerityController {
	
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model,Authentication authentication,HttpSession session) {
      model.addAttribute("name", name);
      return "login";
  }
  
  @RequestMapping(value = "/login", params = "error")
	public String loginFailed(HttpServletRequest request, Locale locale, Model model, HttpSession session) throws Exception {
		
			model.addAttribute("error", true);		
			model.addAttribute("errorMessage", "Internal or External Error");
			
		return "login";
	}
	
	//error403
	@RequestMapping(value="/error/403")
	public String redirectError403(){
//		logger.info("User don't has permission to Website");
		return "redirect:login";
	}
	
	@RequestMapping(value="/session/{error}")
	public String sessionError(@PathVariable String error, HttpServletResponse response){
//		logger.info("Session {}", error);
		response.setStatus(410);
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login";
	}
}
