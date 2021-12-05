package com.system.csserver.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.csserver.beans.Category;
import com.system.csserver.beans.Company;
import com.system.csserver.beans.Coupon;
import com.system.csserver.exceptions.ThereIsException;
import com.system.csserver.rest.ClientController;
import com.system.csserver.security.ClientType;
import com.system.csserver.security.LoginManager;
import com.system.csserver.service.CompanyService;
import com.system.csserver.utils.Env;

@RestController
@RequestMapping("company")
@CrossOrigin(origins = Env.URL, allowedHeaders = "*")

public class CompanyController extends ClientController {
	@Autowired
	private CompanyService companyService;
	@Autowired
	private LoginManager loginManager;

	public CompanyController() {
		super();
	}

	@PostMapping("login")
	@Override
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
//		String token = loginManager.loginToken(email, password, ClientType.Company);
		List<Company> companies = companyService.getAllCompanies();
		for (Company company : companies) {
			if (company.getPassword().equals(password) && company.getEmail().equalsIgnoreCase(email)) {
				System.out.println("Company login Successfully!");
				return new ResponseEntity<Company>(HttpStatus.CREATED);

			}
		}
		System.out.println("the details are incorrect...");
		return new ResponseEntity<String>("the details are incorrect...", HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("add-coupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon) throws ThereIsException {
		companyService.addCoupon(coupon);
		return new ResponseEntity<Coupon>(HttpStatus.CREATED);
	}

	@PutMapping("update-coupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon) throws ThereIsException {
		companyService.updateCoupon(coupon);
		return ResponseEntity.ok(coupon);
	}

	@DeleteMapping("delete-coupon/{couponID}")
	public ResponseEntity<?> deleteCoupon(@PathVariable(name = "couponID") int couponID) {
		companyService.deleteCoupon(couponID);
		return new ResponseEntity<Coupon>(HttpStatus.OK);
	}

	@GetMapping("get-all-company-coupons/{id}")
	public ResponseEntity<?> getAllCompanyCoupons(@PathVariable(name = "id") int id) {
		return new ResponseEntity<ArrayList<Coupon>>((ArrayList<Coupon>) companyService.getCompanyCoupons(id),
				HttpStatus.OK);
	}

	@GetMapping("get-all-coupons")
	public ResponseEntity<?> getAllCoupons() {
		return new ResponseEntity<ArrayList<Coupon>>((ArrayList<Coupon>) companyService.getAllCoupons(), HttpStatus.OK);
	}

	@GetMapping("get-all-company-coupons-category/{category}")
	public ResponseEntity<?> getAllCompanyCoupons(@PathVariable(name = "category") Category category) {
		return new ResponseEntity<ArrayList<Coupon>>((ArrayList<Coupon>) companyService.getCompanyCoupons(category),
				HttpStatus.OK);
	}

	@GetMapping("get-all-company-coupons-maxprice/{maxprice}")
	public ResponseEntity<?> getAllCompanyCoupons(@PathVariable(name = "maxprice") Double maxPrice) {
		return new ResponseEntity<ArrayList<Coupon>>((ArrayList<Coupon>) companyService.getCompanyCoupons(maxPrice),
				HttpStatus.OK);
	}

	@GetMapping("get-all-company-details")
	public ResponseEntity<?> getCompanyDetails() {
		return new ResponseEntity<Company>(companyService.getCompanyDetails(), HttpStatus.OK);
	}

}
