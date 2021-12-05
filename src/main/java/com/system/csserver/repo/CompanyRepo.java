package com.system.csserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.csserver.beans.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {

	Company findByEmail(String email);
}
