package com.system.bcs.dbdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.bcs.beans.Customer;
import com.system.bcs.repo.CustomerRepo;

@Repository
public class CustomerDBDAO {

	@Autowired
	private CustomerRepo customerRepo;

	public boolean isCustomerExist(int customerID) {
		return customerRepo.existsById(customerID);
	}

	public void addCustomer(Customer customer) {
		customerRepo.save(customer);
	}

	public void updateCustomer(Customer customer) {
		customerRepo.saveAndFlush(customer);
	}

	public void deleteCustomer(Customer customer) {
		customerRepo.delete(customer);
	}

	public void deleteCustomerById(int id) {
		customerRepo.deleteById(id);
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Optional<Customer> getOneCustomer(int customerID) {
		return customerRepo.findById(customerID);
	}

	public int getCustomerID(String email) {
		return customerRepo.findByEmail(email).getId();
	}

	public Customer getOneCustomerByEmail(String email) {
		return customerRepo.findByEmail(email);
	}
}
