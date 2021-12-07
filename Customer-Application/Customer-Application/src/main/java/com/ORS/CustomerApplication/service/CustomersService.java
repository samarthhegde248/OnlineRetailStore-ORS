package com.ORS.CustomerApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ORS.CustomerApplication.entity.Customers;
import com.ORS.CustomerApplication.repository.CustomersRepository;

@Service
public class CustomersService {
	@Autowired
	private CustomersRepository customerRepo;
	
	public Customers saveCustomer(Customers customer) {
		return this.customerRepo.save(customer);
	}
	
	public Customers getCustomerByEmail(String email) {
		return this.customerRepo.findByEmail(email).get(0);
	}
	
	public List<Customers> getAllCustomers(){
		return this.customerRepo.findAll();
	}
	
	public Customers getCustomerById(long id) {
		return this.customerRepo.findById(id).get();
	}
	
	public Customers getCustomerByMobile(String mobile) {
		return this.customerRepo.findByMobile(mobile).get();
	}
	
	public List<Customers> getAllCustomersByEmailOrMobile(String email, String mobile) {
		return this.customerRepo.findByEmailOrMobile(email, mobile);
	}
	
	public boolean checkIfCustomerExistsById(long id) {
		return this.customerRepo.existsById(id);
	}
	
	public boolean checkIfCustomerExistsByMobile(String mobile) {
		return this.customerRepo.existsByMobile(mobile);
	}
	
	public boolean checkIfCustomerExistsByEmail(String email) {
		return this.customerRepo.existsByEmail(email);
	}
	
	public void deleteCustomerById(long id) {
		this.customerRepo.deleteById(id);
	}
}
