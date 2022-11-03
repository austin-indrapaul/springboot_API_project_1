package demo.springboot_API_project_1.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping(value = { "", "/", "home" })
	public String home() {
		return "Welcome - springboot_API_Project1";
	}

	@GetMapping("/dashboard")
	public Map<String, String> getDashboard() {
		Map<String, String> urls = new LinkedHashMap();

		urls.put("Homepage", "http://localhost:8080/home");
		urls.put("Dashboard", "http://localhost:8080/dashboard");
		urls.put("List of all customers", "http://localhost:8080/customers");

		return urls;
	}

}
