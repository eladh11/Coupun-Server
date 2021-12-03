package com.system.bcs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.system.bcs.beans.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Customer findByEmail(String email);

}
