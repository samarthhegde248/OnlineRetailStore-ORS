package com.ORS.CustomerApplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ORS.CustomerApplication.entity.Customers;

@Repository
public interface CustomersRepository extends JpaRepository<Customers, Long> {
	List<Customers> findByEmail(String email);
	Optional<Customers> findByMobile(String mobile);
	boolean existsByMobile(String mobile);
	boolean existsByEmail(String email);
	List<Customers> findByEmailOrMobile(String email, String mobile);
}
