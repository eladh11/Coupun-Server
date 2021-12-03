package com.system.bcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.bcs.dbdao.CompanyDBDAO;
import com.system.bcs.dbdao.CouponDBDAO;
import com.system.bcs.dbdao.CustomerDBDAO;

@Service
public abstract class ClientService {
	@Autowired
	protected CompanyDBDAO companyDBDAO;
	@Autowired
	protected CouponDBDAO couponDBDAO;
	@Autowired
	protected CustomerDBDAO customerDBDAO;

	public abstract boolean login(String email, String password);
}
