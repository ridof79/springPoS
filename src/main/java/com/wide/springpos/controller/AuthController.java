package com.wide.springpos.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import com.wide.springpos.dto.CashierDto;
import com.wide.springpos.service.AuthService;

@Controller
public class AuthController {
	
	private AuthService authService;
    private SecurityContextLogoutHandler logoutHandler;
	
	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
		logoutHandler = new SecurityContextLogoutHandler();
	}

    @RequestMapping("/auth/login")
    public String loginForm(@RequestParam(value = "error", defaultValue = "false") boolean loginError, Model model) {
    	if(loginError) {
    		model.addAttribute("status", "error");
    		return "login";
    	}
        return "login";
    }
    
	@RequestMapping("/auth/register")
	public String showRegistrationForm(WebRequest request, Model model) {
	    CashierDto cashierDto = new CashierDto();
	    model.addAttribute("cashier", cashierDto);
	    return "register";
	}
	
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public String registerCashier(CashierDto cashierDto, Model model) {
    	try {
        	authService.register(cashierDto);
		} catch (Exception e) {
			model.addAttribute("createStatus", "failed");
			return "register";
		}
        return "redirect:/auth/login";
    }
    

	@RequestMapping("/auth/logout")
    public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        this.logoutHandler.logout(request, response, authentication);
        return "redirect:/home";
    }

}