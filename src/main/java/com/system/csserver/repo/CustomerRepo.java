package com.system.csserver.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.csserver.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);

}
