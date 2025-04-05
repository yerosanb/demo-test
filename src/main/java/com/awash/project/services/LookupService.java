package com.awash.project.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.awash.project.mappers.LookupMapper;
import com.awash.project.models.LookupModel;
import com.awash.project.models.TransactionFrequencyModel;

@Service
public class LookupService {

	@Autowired
	private LookupMapper map;
	
    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public void saveLookup(LookupModel lookup) {
		Date d=new Date();
		if(lookup.getId()==null) {
		 map.saveLookup(lookup);
		}
		else {
			lookup.setChangedDate(dateformat.format(d));
			 map.updateLookup(lookup);	
		}
	}

	public List<LookupModel> getAllLookup() {
		
		return map.getAllLookup();
	}

	public Page<LookupModel> getLookupBypage(Pageable pageable) {
		int limit = pageable.getPageSize();
		int offset = pageable.getPageNumber() * pageable.getPageSize();
		
		List<LookupModel> lookup = map.getLookupBypage(limit, offset);
		
		int cnt = map.COUNT_Lookup();
		
		return new PageImpl<>(lookup, pageable, cnt);
		
	}

	public LookupModel getLookupById(Long id) {
		
		return map.getLookupById(id);
	}

	public void approveLookup(LookupModel lookup) {
		Date dt=new Date();
		lookup.setApprovedDate(dateformat.format(dt));
		map.approveLookup(lookup);
	}

	public void deleteLookup(LookupModel lookup) {
		Date dt=new Date();
		lookup.setDeletedDate(dateformat.format(dt));
		map.deleteLookup(lookup);
	}

}
