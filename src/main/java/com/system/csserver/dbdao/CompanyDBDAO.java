package com.system.csserver.dbdao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.csserver.beans.Company;
import com.system.csserver.repo.CompanyRepo;

@Repository
public class CompanyDBDAO {

	@Autowired
	private CompanyRepo companyRepo;

	public boolean isCompanyExist(int companyID) {
		return companyRepo.existsById(companyID);
	}

	public void addCompany(Company company) {
		companyRepo.save(company);
	}

	public void updateCompany(Company company) {
		companyRepo.saveAndFlush(company);
	}

	public void deleteCompany(Company company) {
		companyRepo.delete(company);
	}

	public void deleteCompanyById(int id) {
		companyRepo.deleteById(id);
	}

	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
	}

	public Optional<Company> getOneCompany(int companyID) {
		return companyRepo.findById(companyID);
	}

	public int getCompanyID(String email) {
		return companyRepo.findByEmail(email).getId();
	}

	public Company getOneCompanyByEmail(String email) {
		return companyRepo.findByEmail(email);
	}

}
