package com.system.bcs.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.bcs.utils.Env;
import com.system.bcs.beans.Category;
import com.system.bcs.beans.Coupon;
import com.system.bcs.beans.Customer;
import com.system.bcs.exceptions.ThereIsException;
import com.system.bcs.rest.ClientController;
import com.system.bcs.security.ClientType;
import com.system.bcs.security.LoginManager;
import com.system.bcs.service.CustomerService;

@RestController
@RequestMapping("customer")
@CrossOrigin(origins = Env.URL, allowedHeaders = "*")
public class CustomerController extends ClientController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoginManager loginManager;

	public CustomerController() {
		super();
	}

	@PostMapping("login")
	@Override
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
//		String token = loginManager.loginToken(email, password, ClientType.Customer);
		List<Customer> customers = customerService.getAllCustomers();
		for (Customer customer : customers) {
			if (customer.getPassword().equals(password) && customer.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Customer login Successfully!");
				return new ResponseEntity<Customer>(HttpStatus.CREATED);
			}
		}
		System.out.println("the details are incorrect...");
		return new ResponseEntity<String>("the details are incorrect...", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("purchase-coupon/{customerID}")
	public ResponseEntity<?> purchaseCoupon(@PathVariable(name = "customerID") int customerID,
			@RequestBody Coupon coupon) throws ThereIsException {
		customerService.purchaseCoupon(customerID, coupon.getId());
		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	@GetMapping("get-all-customer-coupons/{id}")
	public ResponseEntity<?> getAllCustomerCoupons(@PathVariable(name = "id") int id) {
		return new ResponseEntity<ArrayList<Coupon>>((ArrayList<Coupon>) customerService.getCustomerCoupons(id),
				HttpStatus.OK);
	}

	@GetMapping("get-all-customer-coupons-category/{category}")
	public ResponseEntity<?> getAllCustomerCoupons(@PathVariable(name = "category") Category category) {
		return new ResponseEntity<ArrayList<Coupon>>((ArrayList<Coupon>) customerService.getCustoemrCoupons(category),
				HttpStatus.OK);
	}

	@GetMapping("get-all-customer-coupons-maxprice/{maxprice}")
	public ResponseEntity<?> getAllCustomerCoupons(@PathVariable(name = "maxprice") Double maxPrice) {
		return new ResponseEntity<ArrayList<Coupon>>((ArrayList<Coupon>) customerService.getCustomerCoupons(maxPrice),
				HttpStatus.OK);
	}

	@GetMapping("get-all-customer-details")
	public ResponseEntity<?> getCustomerDetails() {
		return new ResponseEntity<Customer>(customerService.getCustomerDetails(), HttpStatus.OK);
	}

}