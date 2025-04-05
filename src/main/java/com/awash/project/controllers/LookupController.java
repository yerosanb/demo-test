package com.awash.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.awash.project.models.LookupModel;
import com.awash.project.services.LookupService;

@RestController
@RequestMapping("/lookup/")
public class LookupController {
	
	@Autowired
	private LookupService serv;
	
	@PostMapping("saveLookup")
    public ResponseEntity<String> saveLookup(@RequestBody LookupModel lookup) {
		
	
		 try {
			
			
		serv.saveLookup(lookup);
			
        return ResponseEntity.ok("registered successfully!");
		 } 
		 catch (DataIntegrityViolationException e) {
	          
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                                 .body("Record already exists.");
	        }
		 catch (Exception e) {
		        
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                             .body("Error in registration: " + e.getMessage());
		    }
    }
	
	@PostMapping("approveLookup")
    public ResponseEntity<String> approveLookup(@RequestBody LookupModel lookup) {
		
	
		 try {
			
			
		serv.approveLookup(lookup);
			
        return ResponseEntity.ok("registered successfully!");
		 } 
		 catch (DataIntegrityViolationException e) {
	          
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                                 .body("Record already exists.");
	        }
		 catch (Exception e) {
		        
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                             .body("Error in registration: " + e.getMessage());
		    }
    }
	
	@PostMapping("deleteLookup")
    public ResponseEntity<String> deleteLookup(@RequestBody LookupModel lookup) {
		
	
		 try {
			
			
		serv.deleteLookup(lookup);
			
        return ResponseEntity.ok("registered successfully!");
		 } 
		 catch (DataIntegrityViolationException e) {
	          
	            return ResponseEntity.status(HttpStatus.CONFLICT)
	                                 .body("Record already exists.");
	        }
		 catch (Exception e) {
		        
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
		                             .body("Error in registration: " + e.getMessage());
		    }
    }
	
	@GetMapping("getLookupById/{id}") 
    public ResponseEntity<LookupModel> getLookupById(@PathVariable("id") Long id) {
        try {
            LookupModel look = serv.getLookupById(id); 
            return ResponseEntity.ok(look); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null); // Handle the error accordingly
        }
    }
	
	 @GetMapping("getLookupBypage")
	    public ResponseEntity<Page<LookupModel>> getLookupBypage(
	    	@RequestParam("page") int page,
	        @RequestParam("size") int size) {
	        
	        try {
	            Pageable pageable = PageRequest.of(page - 1, size); // Adjust for zero-indexing
	            Page<LookupModel> lookup = serv.getLookupBypage(pageable);
	            return ResponseEntity.ok(lookup);  
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	
	 @GetMapping("getAllLookup") 
	    public ResponseEntity<List<LookupModel>> getAllLookup() {
	        try {
	            List<LookupModel> look = serv.getAllLookup(); 
	            return ResponseEntity.ok(look); 
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body(null); // Handle the error accordingly
	        }
	    }
	
	
}
