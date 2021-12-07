package com.ORS.CustomerApplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ORS.CustomerApplication.entity.Address;
import com.ORS.CustomerApplication.entity.Customers;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	List<Address> findByCustomer(Customers customer);
}
