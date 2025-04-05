package com.awash.project.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.awash.project.mappers.RiskGradingMapper;
import com.awash.project.models.TransactionFrequencyModel;
import com.awash.project.models.UserModel;

@Service
public class RiskGradingService {

	@Autowired
	private RiskGradingMapper map;
	
	public List<TransactionFrequencyModel> transactionfrequency_all() {
		
		return map.transactionfrequency_all();
	}
	
	public List<TransactionFrequencyModel> getFrequency(String customerType) {
		
		return map.getFrequency(customerType);
	}

	public Page<TransactionFrequencyModel> transactionfrequencyBypage(@Param("customerType") String customerType, @Param("pageable") Pageable pageable) {
		int limit = pageable.getPageSize();
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		
		List<TransactionFrequencyModel> freq = map.transactionfrequencyBypage(customerType,limit, offset);
		
		int totalFreq = map.COUNT_transactionFrequencyBasedOn_CustomerType(customerType);
		
		return new PageImpl<>(freq, pageable, totalFreq);
		
		
	}

	public List<TransactionFrequencyModel> customer_profile(String searchKey) {
		
		return map.customer_profile(searchKey);
	}

	

}
