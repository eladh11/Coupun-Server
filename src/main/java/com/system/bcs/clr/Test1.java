package com.system.bcs.clr;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.system.bcs.beans.Category;
import com.system.bcs.beans.Company;
import com.system.bcs.beans.Coupon;
import com.system.bcs.beans.Customer;
import com.system.bcs.dbdao.CompanyDBDAO;
import com.system.bcs.dbdao.CustomerDBDAO;
import com.system.bcs.exceptions.ThereIsException;

@Component
@Order(1)
public class Test1 implements CommandLineRunner {

	@Autowired
	private CompanyDBDAO companyDBDAO;
	@Autowired
	private CustomerDBDAO customerDBDAO;
	public final static int AMOUNT = 100;

	@Override
	public void run(String... args) throws Exception {
		// Create All Companies And Coupons();
		createCompaniesAndCoupons();
		// Create All Customers
		createCusromers();
	}

	// Create Customers
	public void createCusromers() throws ThereIsException {
		Customer customer = Customer.builder().first("elad").last("hakmon").email("elad@gmail.com").password("elad1234")
				.build();
		customerDBDAO.addCustomer(customer);
		for (int i = 0; i < 20; i++) {
			customerDBDAO.addCustomer(generateOneCustomer());
			System.out.println("new Customer as added :-)");
		}
		System.out.println("all Customers as added Successfully!");
		space();
	}

	// Generate one Customer:
	public Customer generateOneCustomer() {
		Customer customer = Customer.builder().first(generateFirstName()).last(generateLastName()).build();
		customer.setEmail(generateEmail(customer));
		customer.setPassword(generatePassword(customer));
		return customer;
	}

	// Generate Random Customers - First Name:
	public String generateFirstName() {
		int rand = (int) (Math.random() * 25);
		switch (rand) {
		case 0:
			return "elad";
		case 1:
			return "kobi";
		case 2:
			return "dan";
		case 3:
			return "haki";
		case 4:
			return "heli";
		case 5:
			return "natali";
		case 6:
			return "omer";
		case 7:
			return "karin";
		case 8:
			return "danit";
		case 9:
			return "yossi";
		case 10:
			return "hen";
		case 11:
			return "ben";
		case 12:
			return "yoni";
		case 13:
			return "kfir";
		case 14:
			return "sharon";
		case 15:
			return "yaeli";
		case 16:
			return "dana";
		case 17:
			return "sapir";
		case 18:
			return "moshe";
		case 19:
			return "rotem";
		case 20:
			return "eden";
		case 21:
			return "israel";
		case 22:
			return "noha";
		case 23:
			return "malachi";
		case 24:
			return "tchahi";

		default:
			return null;
		}
	}

	// Generate Random Customers - Last Name:
	public String generateLastName() {
		String string = " ";
		Random random = new Random();
		int r = (int) (Math.random() * 8) + 2;
		String abc = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < r; i++) {
			string += abc.charAt(random.nextInt(abc.length()));
		}
		return string;
	}

	// Generate Random Customers - Email:
	public String generateEmail(Customer customer) {
		return customer.getFirst() + (int) (Math.random() * 1000 + 100) + "@gmail.com";
	}

	// Generate Random Customers - Password:
	public String generatePassword(Customer customer) {
		return customer.getLast() + "12345";
	}

	// Change from java.util.Date - to - java.sql.Date
	@SuppressWarnings("deprecation")
	public java.sql.Date convertUtilDateToSQL(java.util.Date date) {
		return new java.sql.Date(date.getYear() - 1900, date.getMonth() - 1, date.getDay() + 1);
	}

	// Create Companies:
	public void createCompaniesAndCoupons() throws ThereIsException {

		Company c1 = Company.builder().name("Electricity").email("Electricity@gmail.com").password("electricity1234")
				.build();

		Coupon coupon1 = Coupon.builder().companyID(1).category(Category.Electricity).title("Electricity")
				.description("get 50%").startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(11).image("url").build();

		c1.setCoupons(generateCouponsForCompany(coupon1));

		Company c2 = Company.builder().name("Coca-cola").email("cola@gmail.com").password("cola1234").build();

		Coupon coupon2 = Coupon.builder().companyID(2).category(Category.Food).title("1+1").description("buy 1 get 2")
				.startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(5.5).image("url").build();

		c2.setCoupons(generateCouponsForCompany(coupon2));

		Company c3 = Company.builder().name("Wiskey-bar").email("Wiskey@gmail.com").password("Wiskey1234").build();

		Coupon coupon3 = Coupon.builder().companyID(3).category(Category.Food).title("Wiskey Bar")
				.description("Free Dessert").startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(39.99).image("url").build();

		c3.setCoupons(generateCouponsForCompany(coupon3));

		Company c4 = Company.builder().name("Spa-Hotel").email("spa@gmail.com").password("spa1234").build();

		Coupon coupon4 = Coupon.builder().companyID(4).category(Category.Vacation).title("Spa")
				.description("receive an hour and a half")
				.startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(150).image("url").build();

		c4.setCoupons(generateCouponsForCompany(coupon4));

		Company c5 = Company.builder().name("Trivago").email("Trivago@gmail.com").password("trivago1234").build();

		Coupon coupon5 = Coupon.builder().companyID(5).category(Category.Vacation).title("Trivago")
				.description("chip upgrade").startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(200).image("url").build();

		c5.setCoupons(generateCouponsForCompany(coupon5));
		
		Company c6 = Company.builder().name("Candy's").email("candy@gmail.com").password("candy1234")
				.build();

		Coupon coupon6 = Coupon.builder().companyID(6).category(Category.Food).title("Candy's")
				.description("10% off").startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(49.99).image("url").build();

		c6.setCoupons(generateCouponsForCompany(coupon6));

		Company c7 = Company.builder().name("Pepsi").email("pepsi@gmail.com").password("pepsi1234").build();

		Coupon coupon7 = Coupon.builder().companyID(7).category(Category.Food).title("Pepsi Coupun").description("buy 2 get 1")
				.startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(0/99).image("url").build();

		c7.setCoupons(generateCouponsForCompany(coupon7));

		Company c8 = Company.builder().name("DBS Hotels").email("dbshotels@gmail.com").password("dbshotel1234").build();

		Coupon coupon8 = Coupon.builder().companyID(8).category(Category.Vacation).title("DBS Hotels")
				.description("Vaction Discount!").startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(100.99).image("url").build();

		c8.setCoupons(generateCouponsForCompany(coupon8));

		Company c9 = Company.builder().name("KFC").email("KFC@gmail.com").password("KFC1234").build();

		Coupon coupon9 = Coupon.builder().companyID(9).category(Category.Food).title("KFC")
				.description("KFC free Meal")
				.startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(2.99).image("url").build();

		c9.setCoupons(generateCouponsForCompany(coupon9));

		Company c10 = Company.builder().name("BBB Burger").email("bbb@gmail.com").password("bbb1234").build();

		Coupon coupon10 = Coupon.builder().companyID(10).category(Category.Food).title("BBB Burger")
				.description("Buy Burger get Free Chips!").startDate(convertUtilDateToSQL(new Date(2021, 01, 01)))
				.endDate(convertUtilDateToSQL(new Date(2023, 01, 01))).amount(AMOUNT).price(4.99).image("url").build();

		c10.setCoupons(generateCouponsForCompany(coupon10));

		companyDBDAO.addCompany(c1);
		checkCompanyMethod(c1.getName());
		companyDBDAO.addCompany(c2);
		checkCompanyMethod(c2.getName());
		companyDBDAO.addCompany(c3);
		checkCompanyMethod(c3.getName());
		companyDBDAO.addCompany(c4);
		checkCompanyMethod(c4.getName());
		companyDBDAO.addCompany(c5);
		checkCompanyMethod(c5.getName());
		companyDBDAO.addCompany(c6);
		checkCompanyMethod(c6.getName());
		companyDBDAO.addCompany(c7);
		checkCompanyMethod(c7.getName());
		companyDBDAO.addCompany(c8);
		checkCompanyMethod(c8.getName());
		companyDBDAO.addCompany(c9);
		checkCompanyMethod(c9.getName());
		companyDBDAO.addCompany(c10);
		checkCompanyMethod(c10.getName());
		System.out.println("all Companies as added Successfully!");
		space();
	}

	public List<Coupon> generateCouponsForCompany(Coupon coupon) {
		List<Coupon> coupons = new ArrayList<Coupon>();
		coupons.add(coupon);
		return coupons;

	}

	public void checkCompanyMethod(String name) {
		System.out.println("the Company:" + name + " as added Successfully!");
	}

	public void space() {
		System.out.println();
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println();
	}

}