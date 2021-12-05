package com.system.csserver.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.system.csserver.service.AdminService;
import com.system.csserver.service.ClientService;
import com.system.csserver.service.CompanyService;
import com.system.csserver.service.CustomerService;

@Service
@Lazy
public class LoginManager {

	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	public ClientService login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case Administrator:
			if (adminService.login(email, password) == true) {
				return adminService;
			}
			System.out.println("Wrong Details...");
			return null;
		case Company:
			if (companyService.login(email, password)) {
				return companyService;
			}
			System.out.println("Wrong Details...");
			return null;
		case Customer:
			if (customerService.login(email, password)) {
				return customerService;
			}
			System.out.println("Wrong Details...");
			return null;
		default:
			System.out.println("the Client type has not found...");
			break;
		}
		return null;
	}

	public String loginToken(String email, String password, ClientType type) {
		return UUID.randomUUID().toString();
	}
}
