package com.xoriant.bankingapplication.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xoriant.bankingapplication.command.LoginCommand;
import com.xoriant.bankingapplication.enums.Role;
import com.xoriant.bankingapplication.service.LoginLogoutService;

@Controller
public class LoginLogoutController {
	@Autowired
	private LoginLogoutService service;

	@RequestMapping(value = { "/", "/index" })
	public String index(Model m) {
		m.addAttribute("command", new LoginCommand());
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model m, HttpSession session) {
		try {
			if (service.getAdmin(cmd.getUserId(), cmd.getPassword())) {
				session.setAttribute("userId", cmd.getUserId());
				session.setAttribute("role", Role.MANAGER.toString());
				return "redirect:admin/dashboard";
			} else if (service.login(cmd.getUserId(), cmd.getPassword())) {
				session.setAttribute("userId", cmd.getUserId());
				session.setAttribute("role", Role.CUSTOMER.toString());
				return "redirect:user/dashboard";
			} else {
				m.addAttribute("err", "Invalid User ROLE");
				return "index";
			}
		} catch (Exception ex) {
			m.addAttribute("err", ex.getMessage());
			System.out.println(ex);
			return "index";
		}
	}

	@RequestMapping("/user/dashboard")
	public String userDashboard() {
		return "dashboard_user";
	}

	@RequestMapping("/admin/dashboard")
	public String adminDashboard() {
		return "dashboard_admin";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index?act=lo";
	}

	@RequestMapping(value = {"/aboutLink" })
	public String about(Model m) {
		return "about";
	}
	
	public LoginLogoutService getService() {
		return service;
	}

	public void setService(LoginLogoutService service) {
		this.service = service;
	}
}
