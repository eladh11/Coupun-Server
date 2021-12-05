package com.system.csserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.system.csserver.beans.Company;
import com.system.csserver.beans.Customer;
import com.system.csserver.exceptions.ThereIsException;

import lombok.Setter;

@Service
@Setter
public class AdminService extends ClientService {
	@Override
	public boolean login(String email, String password) {
		if (email.equals("admin@admin.com") && password.equals("admin")) {
			System.out.println("admin login Successfully!");
			return true;
		}
		System.out.println("the details are incorrect...");
		return false;
	}

	public void addCompany(Company company) throws ThereIsException {
		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company comp : companies) {
			if (comp.getName().equals(company.getName())) {
				throw new ThereIsException("Company: " + company.getName() + " Already Exit.");
			}
			if (comp.getEmail().equalsIgnoreCase(company.getEmail())) {
				throw new ThereIsException("Email: " + company.getEmail() + " Already Exit.");
			}
		}
		companyDBDAO.addCompany(company);
	}

	public void updateCompany(Company company) throws ThereIsException {
		List<Company> companies = companyDBDAO.getAllCompanies();
		for (Company comp : companies) {
			if (comp.getName() == company.getName()) {
				throw new ThereIsException("NAME - cannot change the Company `NAME`");
			}
		}
		companyDBDAO.updateCompany(company);
	}

	public void deleteCompany(Company company) {
		companyDBDAO.deleteCompany(company);
		System.out.println("Delete the Company Successfully.");
	}

	public void deleteCompany(int companyID) {
		companyDBDAO.deleteCompanyById(companyID);
		System.out.println("Delete the Company Successfully.");
	}

	public List<Company> getAllCompanies() {
		return companyDBDAO.getAllCompanies();
	}

	public Optional<Company> getOneCompany(int companyID) {
		return companyDBDAO.getOneCompany(companyID);
	}

	public void addCustomer(Customer customer) throws ThereIsException {
		List<Customer> customers = customerDBDAO.getAllCustomers();
		for (Customer cust : customers) {
			if (cust.getEmail().equalsIgnoreCase(customer.getEmail())) {
				throw new ThereIsException("Email:" + customer.getEmail() + " Already Exit.");
			}
		}
		customerDBDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws ThereIsException {
		customerDBDAO.updateCustomer(customer);
	}

	public void deleteCustomer(Customer customer) throws ThereIsException {
		customerDBDAO.deleteCustomer(customer);
		System.out.println("Delete the Customer Successfully.");
	}

	public void deleteCustomer(int customerID) {
		customerDBDAO.deleteCustomerById(customerID);
		System.out.println("Delete the Customer Successfully.");

	}

	public List<Customer> getAllCustomers() {
		return customerDBDAO.getAllCustomers();
	}

	public Optional<Customer> getOneCustomer(int customerID) {
		return customerDBDAO.getOneCustomer(customerID);

	}
}
