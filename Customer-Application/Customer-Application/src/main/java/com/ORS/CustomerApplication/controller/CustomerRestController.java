package com.ORS.CustomerApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ORS.CustomerApplication.common.InternalServerErrorException;
import com.ORS.CustomerApplication.common.NotFoundException;
import com.ORS.CustomerApplication.entity.Address;
import com.ORS.CustomerApplication.entity.Customers;
import com.ORS.CustomerApplication.service.AddressService;
import com.ORS.CustomerApplication.service.CustomersService;
import com.ORS.CustomerApplication.service.TypeOfAddressService;


@RestController
@RequestMapping(value = "/customers")
public class CustomerRestController {
	@Value("${message}")
	private String welcomeMsg;
	
	@Autowired
	private CustomersService customersService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private TypeOfAddressService typeOfAddressService;
	
	@GetMapping(value="/welcome")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String showWelcomeMsg() {
		return this.welcomeMsg;
	}
	
	@PostMapping(value="/addCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Customers saveCustomer(@RequestBody Customers customer) {
		if(this.customersService.checkIfCustomerExistsByEmail(customer.getEmail())) {
			throw new InternalServerErrorException(customer.getEmail() + " this email is already in use.");
		}else if(this.customersService.checkIfCustomerExistsByMobile(customer.getMobile())) {
			throw new InternalServerErrorException(customer.getMobile() + " this mobile is already in use.");
		}else {
			return this.customersService.saveCustomer(customer);
		}
	}
	
	@GetMapping(value="/", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<Customers> getAllCustomers() {
		return this.customersService.getAllCustomers();
	}
	
	@GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Customers getCustomerById(@PathVariable long id) {
		if(!this.customersService.checkIfCustomerExistsById(id)) {
			throw new NotFoundException("Customer with id " + Long.toString(id) + " is Not Found." );
		}
		return this.customersService.getCustomerById(id);
	}
	
	@GetMapping(value="/email", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Customers getCustomerByEmail(@RequestParam String email) {
		if(!this.customersService.checkIfCustomerExistsByEmail(email)) {
			throw new NotFoundException("Customer with email " + email + " is Not Found." );
		}
		return this.customersService.getCustomerByEmail(email);
	}
	
	@GetMapping(value="/mobile", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Customers getCustomerByMobile(@RequestParam String mobile) {
		if(!this.customersService.checkIfCustomerExistsByMobile(mobile)) {
			throw new NotFoundException("Customer with mobile " + mobile + " is Not Found." );
		}
		return this.customersService.getCustomerByMobile(mobile);
	}
	
	@DeleteMapping(value="/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String deleteCustomerById(@PathVariable long id) {
		if(this.customersService.checkIfCustomerExistsById(id)) {
			this.customersService.deleteCustomerById(id);
			return "Customer with id " + id + " has been deleted successfully";
		}else {
			throw new NotFoundException("Customer with id " + Long.toString(id) + " is Not Found. Deletion Failed!" );
		}	
	}
	
	@PutMapping(value="/{id}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Customers updateCustomerById(@PathVariable long id, @RequestBody Customers customer) {
		if(this.customersService.checkIfCustomerExistsById(customer.getId())) {
			Customers updatingCustomer = this.customersService.getCustomerById(id);
			List<Customers> filteredEmailMobileCustomers = this.customersService.getAllCustomersByEmailOrMobile(customer.getEmail(), customer.getMobile());
			if(filteredEmailMobileCustomers.size()>1 && updatingCustomer.getId()!=filteredEmailMobileCustomers.get(0).getId()) {
				throw new InternalServerErrorException("Either email or mobile is already in use by other customer.");
			}
			updatingCustomer.setEmail(customer.getEmail());
			updatingCustomer.setMobile(customer.getMobile());
			updatingCustomer.setFirstName(customer.getFirstName());
			updatingCustomer.setLastName(customer.getLastName());
			updatingCustomer.setDateOfBirth(customer.getDateOfBirth());
			return this.customersService.saveCustomer(updatingCustomer);
		}else {
			throw new NotFoundException("Customer with id " + Long.toString(id) + " is Not Found.");
		}
	}
	
	@GetMapping(value="/{id}/address")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<Address> getCustomerAddresses(@PathVariable long id) {
		if(this.customersService.checkIfCustomerExistsById(id)) {
			return this.addressService.getCustomerAddresses(this.customersService.getCustomerById(id));
		}else {
			throw new NotFoundException("Customer with id " + Long.toString(id) + " is Not Found." );
		}
	}
	
	@GetMapping(value="/{id}/address/{adrId}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Address getCustomerAddressesById(@PathVariable long id, @PathVariable long adrId) {
		if(this.customersService.checkIfCustomerExistsById(id)) {
			if(this.addressService.checkIfAddressExistsById(id)) {	
				Address address = this.addressService.getAddressById(adrId);
				if(address.getCustomer().getId() == id) {
					return address;
				}else {
					throw new InternalServerErrorException("Customer id " + Long.toString(id) + " is not authorized to fetch address id " + Long.toString(adrId));
				}
			}else {
				throw new NotFoundException("Address with id " + Long.toString(adrId) + "of customer " + Long.toString(id) + " is Not Found." );
			}
		}else {
			throw new NotFoundException("Customer with id " + Long.toString(id) + " is Not Found." );
		}
	}
	
	@PostMapping(value="/{id}/address", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Address saveCustomerAddress(@PathVariable long id, @RequestBody Address address) {
		if(address.getType().getType().equalsIgnoreCase("permanent") || address.getType().getType().equalsIgnoreCase("delivery") || address.getType().getType().equalsIgnoreCase("shipped")) {
			address.setType(this.typeOfAddressService.saveTypeOfAddress(address.getType()));
		}else {
			throw new NotFoundException("Address type not found.");
		}
		if(this.customersService.checkIfCustomerExistsByEmail(address.getEmail()) && this.customersService.checkIfCustomerExistsById(id)) {
			Customers customer = this.customersService.getCustomerById(id);
			if(customer.getEmail().equalsIgnoreCase(address.getEmail())) {
				address.setCustomer(this.customersService.getCustomerByEmail(address.getEmail()));
			}else {
				throw new InternalServerErrorException("Customer id " + Long.toString(id) + " and " + address.getEmail() + " doesnt match!");
			}
		}
		return this.addressService.saveAddress(address);
	}
	
	@PutMapping(value="/{id}/address/{adrId}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public Address updateCustomerAddressesById(@PathVariable long id, @PathVariable long adrId, @RequestBody Address address) {
		if(this.customersService.checkIfCustomerExistsById(id)) {
			if(this.addressService.checkIfAddressExistsById(adrId)) {
				Address updateAddress = this.addressService.getAddressById(adrId);
				if(address.getType().getType().equalsIgnoreCase("permanent") || address.getType().getType().equalsIgnoreCase("delivery") || address.getType().getType().equalsIgnoreCase("shipped")) {
					address.setType(this.typeOfAddressService.saveTypeOfAddress(address.getType()));
				}else {
					throw new NotFoundException("Address type not found.");
				}
				updateAddress.setAddressDetail(address.getAddressDetail());
				updateAddress.setType(address.getType());
				return this.addressService.saveAddress(updateAddress);
			}else {
				throw new NotFoundException("Address with id " + Long.toString(adrId) + "of customer " + Long.toString(id) + " is Not Found." );
			}
		}else {
			throw new NotFoundException("Customer with id " + Long.toString(id) + " is Not Found." );
		}
	}
	
	@DeleteMapping(value = "/{id}/address/{adrId}")
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String deleteCustomerAddressesById(@PathVariable long id, @PathVariable long adrId) {
		if(this.customersService.checkIfCustomerExistsById(id)) {
			if(this.addressService.checkIfAddressExistsById(adrId)) {
				this.addressService.deleteAddressById(adrId);
				return "Address with id " + Long.toString(adrId) + "of customer " + Long.toString(id) + " has been deleted successfully.";
			}else {
				throw new NotFoundException("Address with id " + Long.toString(adrId) + "of customer " + Long.toString(id) + " is Not Found." );
			}
		}else {
			throw new NotFoundException("Customer with id " + Long.toString(id) + " is Not Found." );
		}
	}
}
