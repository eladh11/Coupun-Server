package com.system.bcs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.system.bcs.beans.Category;
import com.system.bcs.beans.Coupon;
import com.system.bcs.beans.Customer;
import com.system.bcs.exceptions.ThereIsException;

import lombok.Setter;

@Service
@Setter
@Scope("prototype")
public class CustomerService extends ClientService {

	private int customerID;

	@Override
	public boolean login(String email, String password) {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getPassword().equals(password) && customer.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Customer login Successfully!");
				return true;
			}
		}
		System.out.println("Wrong Details...");
		return false;
	}

	public void purchaseCoupon(int customerID, int couponID) throws ThereIsException {

		// cannot buy the same coupon more than once
		List<Coupon> coupons = customerDBDAO.getOneCustomer(customerID).get().getCoupons();
		if (coupons != null) {
			for (Coupon coup : coupons) {
				if (coup.getId() == couponID) {
					throw new ThereIsException("cannot buy tha same coupon more than once...");
				}
			}
		}
		// cannot buy coupon that is amount=0
		Optional<Coupon> c = couponDBDAO.getOneCoupon(couponID);
		if (c.get().getAmount() == 0) {
			throw new ThereIsException("cannot buy the coupon: amount = 0");
		}
		// cannot buy coupon if date expired
		if (c.get().getEndDate().before(new Date())) {
			throw new ThereIsException("the date of the coupon expired...");
		}
		// amount - 1
		System.out.println("coupon: " + c.get().getTitle() + " is available.");
		c.get().setAmount(c.get().getAmount() - 1);
		couponDBDAO.updateCoupon(c.get());
		couponDBDAO.addCouponPurchase(customerID, couponID);
		System.out.println("Enjoy your coupon :->");

	}

	public List<Coupon> getCustomerCoupons(int id) {
		List<Coupon> coupons = customerDBDAO.getOneCustomer(id).get().getCoupons();
		if (coupons == null) {
			System.out.println("Coupons not found...");
			return null;
		}
		return coupons;
	}

	public List<Coupon> getCustoemrCoupons(Category category) {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = customerDBDAO.getOneCustomer(this.customerID).get().getCoupons();
		for (Coupon coupon : coupons) {
			if (coupons != null) {
				if (coupon.getCategory() == category) {
					idx.add(coupon);
				}
			}
		}
		return idx;
	}

	public List<Coupon> getCustomerCoupons(double maxPrice) {
		List<Coupon> idx = new ArrayList<Coupon>();
		List<Coupon> coupons = customerDBDAO.getOneCustomer(this.customerID).get().getCoupons();
		for (Coupon coupon : coupons) {
			if (coupons != null) {
				if (coupon.getPrice() <= maxPrice) {
					idx.add(coupon);
				}
			}
		}
		return idx;
	}

	public Customer getCustomerDetails() {
		Optional<Customer> customer = customerDBDAO.getOneCustomer(this.customerID);
		if (customer.get().getCoupons() == null) {
			System.out.println("Customer:" + customer.get().getFirst() + " does not have Coupons");
			return customer.get();
		}
		return customer.get();
	}

	public List<Customer> getAllCustomers() {
		return customerDBDAO.getAllCustomers();
	}

	public int getCustomerID(String email) {
		return customerDBDAO.getCustomerID(email);
	}

	public Customer getOneCustomerByEmail(String email) {
		return customerDBDAO.getOneCustomerByEmail(email);
	}
}