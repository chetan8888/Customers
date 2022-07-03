package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormController {
	@Autowired
	CustomerRepo repo;
	
	@RequestMapping("/")
	public String details() {	
		return "edureka"; 
	}
	
	@RequestMapping("/details")
	public String details(Customers customers) {	
		repo.save(customers);
		return "edureka"; 
	}
	
	@RequestMapping("/getdetails")
	public String getdetails() {	
		return "ViewCustomers"; 
	}

	
	@PostMapping("/getdetails")
	public ModelAndView viewdetails(@RequestParam int cid) {
		
		ModelAndView mv = new ModelAndView("Retrieve");
		Customers customer = repo.findById(cid).orElse(null);
		System.out.println(customer);
		mv.addObject(customer.toString());
		return mv;
	}
	
	@RequestMapping("/customers/{cid}")
	@ResponseBody
	public String getCustomers(@PathVariable int cid) {
		return repo.findById(cid).toString();
	}
	
}
