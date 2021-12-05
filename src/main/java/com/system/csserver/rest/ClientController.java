package com.system.csserver.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class ClientController {

	public abstract ResponseEntity<?> login(String email, String password);

}
