package com.awash.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awash.project.models.TransactionFrequencyModel;
import com.awash.project.models.UserModel;
import com.awash.project.services.RiskGradingService;

@RestController
@RequestMapping("/risk/")
public class RiskGradingController {
	
	@Autowired
	private RiskGradingService serv;
	
	 @GetMapping("transactionfrequency_all") 
	    public ResponseEntity<List<TransactionFrequencyModel>> transactionfrequency_all() {
	        try {
	            List<TransactionFrequencyModel> freq_all = serv.transactionfrequency_all(); 
	            return ResponseEntity.ok(freq_all); 
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(null); // Handle the error accordingly
	        }
	    }
	
	 @GetMapping("transactionfrequency") 
	    public ResponseEntity<List<TransactionFrequencyModel>> getFrequency(@RequestParam String customerType) {
	        try {
	            List<TransactionFrequencyModel> freq = serv.getFrequency(customerType); 
	            return ResponseEntity.ok(freq); 
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(null); // Handle the error accordingly
	        }
	    }
	 
 	//Pageable pageable = PageRequest.of(page - 1, size); 

	 @GetMapping("transactionfrequencyBypage")
	    public ResponseEntity<Page<TransactionFrequencyModel>> transactionfrequencyBypage(
	    	@RequestParam("customerType") String customerType,
	        @RequestParam("page") int page,
	        @RequestParam("size") int size) {
	        
	        try {
	            Pageable pageable = PageRequest.of(page - 1, size); // Adjust for zero-indexing
	            Page<TransactionFrequencyModel> freq = serv.transactionfrequencyBypage(customerType,pageable);
	            return ResponseEntity.ok(freq);  
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	 
	 @GetMapping("customerProfile") 
	    public ResponseEntity<List<TransactionFrequencyModel>> customer_profile(@RequestParam String searchKey) {
	        try {
	            List<TransactionFrequencyModel> profile = serv.customer_profile(searchKey); 
	            return ResponseEntity.ok(profile); 
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(null); // Handle the error accordingly
	        }
	 }
}
