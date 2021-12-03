package com.system.bcs.rest;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
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

import com.system.bcs.utils.Env;
import com.system.bcs.beans.Company;
import com.system.bcs.beans.Customer;
import com.system.bcs.exceptions.ThereIsException;
import com.system.bcs.rest.ClientController;
import com.system.bcs.security.ClientType;
import com.system.bcs.security.LoginManager;
import com.system.bcs.service.AdminService;
import com.system.bcs.service.CompanyService;
import com.system.bcs.service.CustomerService;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = Env.URL, allowedHeaders = "*")
public class AdminController extends ClientController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private LoginManager loginManager;

	public AdminController() {
		super();
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {

//		String token = loginManager.loginToken(email, password, ClientType.Administrator);
//		&& token != null
		if (password.equals("admin") && email.equalsIgnoreCase("admin@admin.com")) {
			System.out.println("Admin login Successfully!");
			return new ResponseEntity<Admin>(HttpStatus.CREATED);

		}
		System.out.println("the details are incorrect...");
		return new ResponseEntity<String>("the details are incorrect...", HttpStatus.UNAUTHORIZED);

	}

	@PostMapping("add-company")
	public ResponseEntity<?> addCompany(@RequestBody Company company) throws ThereIsException {
		adminService.addCompany(company);
		return new ResponseEntity<Company>(HttpStatus.CREATED);
	}

	@PutMapping("update-company")
	public ResponseEntity<?> updateCompany(@RequestBody Company company) throws ThereIsException {
		adminService.updateCompany(company);
		return ResponseEntity.ok(company);
	}

	@DeleteMapping("delete-company/{companyID}")
	public ResponseEntity<?> deleteCompany(@PathVariable(name = "companyID") int companyID) {
		adminService.deleteCompany(companyID);
		return new ResponseEntity<Company>(HttpStatus.OK);
	}

	@GetMapping("/get-all-companies")
	public ResponseEntity<?> getAllCompanies() {
		return new ResponseEntity<ArrayList<Company>>((ArrayList<Company>) adminService.getAllCompanies(),
				HttpStatus.OK);

	}

	@GetMapping("get-one-company/{companyID}")
	public ResponseEntity<?> getOneCompany(@PathVariable(name = "companyID") int companyID) {
		return new ResponseEntity<Company>(adminService.getOneCompany(companyID).get(), HttpStatus.OK);
	}

	@PostMapping("add-customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws ThereIsException {
		adminService.addCustomer(customer);
		return new ResponseEntity<Customer>(HttpStatus.CREATED);
	}

	@PutMapping("update-customer")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer customer) throws ThereIsException {
		adminService.updateCustomer(customer);
		return ResponseEntity.ok(customer);
	}

	@DeleteMapping("delete-customer/{customerID}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(name = "customerID") int customerID) {
		adminService.deleteCustomer(customerID);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}

	@GetMapping("/get-all-customers")
	public ResponseEntity<?> getAllCustomers() {
		return new ResponseEntity<ArrayList<Customer>>((ArrayList<Customer>) adminService.getAllCustomers(),
				HttpStatus.OK);
	}

	@GetMapping("get-one-customer/{customerID}")
	public ResponseEntity<?> getOneCustomer(@PathVariable int customerID) {
		Optional<Customer> c = adminService.getOneCustomer(customerID);
		return new ResponseEntity<Customer>(c.get(), HttpStatus.OK);
	}

	@GetMapping("get-one-company-email/{email}")
	public ResponseEntity<?> getOneCompanyByEmail(@PathVariable(name = "email") String email) {
		return new ResponseEntity<Company>(companyService.getOneCompanyByEmail(email), HttpStatus.OK);
	}

	@GetMapping("get-one-customer-email/{email}")
	public ResponseEntity<?> getOneCustomerByEmail(@PathVariable(name = "email") String email) {
		return new ResponseEntity<Customer>(customerService.getOneCustomerByEmail(email), HttpStatus.OK);
	}
}
