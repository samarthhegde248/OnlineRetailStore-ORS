package com.ORS.CustomerApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ORS.CustomerApplication.entity.TypeOfAddress;

@Repository
public interface TypeOfAddressRepository extends JpaRepository<TypeOfAddress, Integer>{

}
