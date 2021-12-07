package com.ORS.CustomerApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ORS.CustomerApplication.entity.TypeOfAddress;
import com.ORS.CustomerApplication.repository.TypeOfAddressRepository;

@Service
public class TypeOfAddressService {
	@Autowired
	private TypeOfAddressRepository typeAddressRepo;
	
	public TypeOfAddress saveTypeOfAddress(TypeOfAddress typeOfAddress) {
		return this.typeAddressRepo.save(typeOfAddress);
	}
	
}
