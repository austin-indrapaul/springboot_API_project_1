package demo.springboot_API_project_1.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.springboot_API_project_1.entities.Customer;
import demo.springboot_API_project_1.repositories.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository repository;

	@GetMapping("/customers")
	public ResponseEntity<?> getCustomers() {
		List<Customer> customers = repository.findAll();
		if (customers != null)
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.ACCEPTED);
		else {
			return new ResponseEntity<String>("no records found", HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
		try {
			return new ResponseEntity<String>("User successfully registered", HttpStatus.CREATED);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("invalid key or value..\n" + ex.getMessage());
		}
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> get_user(@PathVariable int id) {
		Optional<Customer> customer = repository.findById(id);
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		}
		return ResponseEntity.badRequest().body("Customer not found");
	}

	@PutMapping("/customer/{id}")
	public Customer update_user(@PathVariable int id, @RequestBody Customer customer) {
		Optional<Customer> _customer = repository.findById(id);
		if (_customer.isPresent()) {
			Customer customer_db = _customer.get();
			customer_db.update_customer(customer);
			return repository.save(customer_db);
		}
		return null;
	}

	@DeleteMapping("/customer/{id}")
	public String delete_user(@PathVariable int id) {
		repository.deleteById(id);
		return "User deleted!!";
	}

}
