package com.ORS.CustomerApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ORS.CustomerApplication.entity.Address;
import com.ORS.CustomerApplication.entity.Customers;
import com.ORS.CustomerApplication.repository.AddressRepository;

@Service
public class AddressService {
	@Autowired
	private AddressRepository addressRepo;
	
	public Address saveAddress(Address address) {
		return this.addressRepo.save(address);
	}
	
	public List<Address> getCustomerAddresses(Customers customer){
		return this.addressRepo.findByCustomer(customer);
	}
	
	public Address getAddressById(long id) {
		return this.addressRepo.findById(id).get();
	}
	
	public boolean checkIfAddressExistsById(long id) {
		return this.addressRepo.existsById(id);
	}
	
	public void deleteAddressById(long id) {
		this.addressRepo.deleteById(id);
	}
}
