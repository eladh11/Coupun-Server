package com.system.bcs.clr;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.system.bcs.beans.Category;
import com.system.bcs.beans.Company;
import com.system.bcs.beans.Coupon;
import com.system.bcs.beans.Customer;
import com.system.bcs.exceptions.ThereIsException;
import com.system.bcs.security.ClientType;
import com.system.bcs.security.LoginManager;
import com.system.bcs.service.AdminService;
import com.system.bcs.service.CompanyService;
import com.system.bcs.service.CustomerService;

@Component
@Order(2)
public class Test2 implements CommandLineRunner {

	@Autowired
	private LoginManager loginManager;

//	public RestTemplate restTemplate = new RestTemplate();
//	private static final String BASE_URL = "http://localhost:8081/admin/";
	@Override
	public void run(String... args) throws Exception {

//			LoginResponse loginResponse = restTemplate.postForObject(BASE_URL+"login?email=admin@admin.com&password=admin", null, LoginResponse.class);
//			System.out.println(loginResponse);

		testAll();

	}

	public void testAll() throws ThereIsException {
		space();
		space();
		AdminService admin = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);

		Company company = Company.builder().name("stam Company").email("stam@gmail.com").password("stam1234").build();
		admin.addCompany(company);
		System.out.println(admin.getOneCompany(company.getId()));
		space();
		company.setName("blabla");
		System.out.println(admin.getAllCompanies());
		space();
		admin.deleteCompany(company);
		System.out.println(admin.getAllCompanies());
		space();

		admin.addCompany(company);

		CompanyService wiskey = (CompanyService) loginManager.login("Wiskey@gmail.com", "Wiskey1234",
				ClientType.Company);

		Coupon coupon = Coupon.builder().id(11).companyID(5).category(Category.Vacation).title("stam Coupon")
				.description("bla bla").startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(100).price(9.99).image("url").build();

		company.setCoupons(Arrays.asList(coupon));
		admin.updateCompany(company);

		System.out.println(wiskey.getCompanyCoupons(2));
		space();
		coupon.setTitle("bla bla");
		wiskey.updateCoupon(coupon);
		System.out.println(wiskey.getCompanyCoupons(Category.Vacation));
		space();
		wiskey.deleteCoupon(11);
		System.out.println(wiskey.getCompanyCoupons(30));
		space();

		Customer customer = Customer.builder().first("e").last("h").email("e@gmail.com").password("e1234").build();
		admin.addCustomer(customer);
		space();
		CustomerService elad = (CustomerService) loginManager.login("e@gmail.com", "e1234", ClientType.Customer);
		elad.purchaseCoupon(22, 2);
		space();
		elad.purchaseCoupon(22, 3);
		space();
		elad.purchaseCoupon(22, 4);
		space();
		elad.purchaseCoupon(22, 5);
		space();
	}

	public void space() {
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
	}

	// Change from java.util.Date - to - java.sql.Date
	@SuppressWarnings("deprecation")
	public java.sql.Date convertUtilDateToSQL(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay() + 1);
	}

}
