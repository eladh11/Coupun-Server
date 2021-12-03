package com.system.bcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bcs.beans.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

	Company findByEmail(String email);
}
