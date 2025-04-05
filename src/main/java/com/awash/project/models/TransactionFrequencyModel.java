package com.awash.project.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransactionFrequencyModel {
	private Long id;
	private String accountId;
	private String accountName;
	private String district;
	private String branchName;
	private Long frequency;
	private Double amount;
	private String riskLevel;
	private Long riskScore;
	private String gradeBasedOn;
	private String customerType;
	private String fromDate;
	private String toDate;
	private String pulledDate;
	private Long status;
	
	
	
	public TransactionFrequencyModel(Long id, String accountId, String accountName, String district, String branchName,
			Long frequency, Double amount, String riskLevel, Long riskScore, String gradeBasedOn, String customerType,
			String fromDate, String toDate, String pulledDate, Long status) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.district = district;
		this.branchName = branchName;
		this.frequency = frequency;
		this.amount = amount;
		this.riskLevel = riskLevel;
		this.riskScore = riskScore;
		this.gradeBasedOn = gradeBasedOn;
		this.customerType = customerType;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.pulledDate = pulledDate;
		this.status = status;
	}





	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getAccountId() {
		return accountId;
	}



	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}



	public String getAccountName() {
		return accountName;
	}



	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}



	public String getDistrict() {
		return district;
	}



	public void setDistrict(String district) {
		this.district = district;
	}



	public String getBranchName() {
		return branchName;
	}



	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}



	public Long getFrequency() {
		return frequency;
	}



	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}



	public Double getAmount() {
		return amount;
	}



	public void setAmount(Double amount) {
		this.amount = amount;
	}



	public String getRiskLevel() {
		return riskLevel;
	}



	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}



	public Long getRiskScore() {
		return riskScore;
	}



	public void setRiskScore(Long riskScore) {
		this.riskScore = riskScore;
	}



	public String getGradeBasedOn() {
		return gradeBasedOn;
	}



	public void setGradeBasedOn(String gradeBasedOn) {
		this.gradeBasedOn = gradeBasedOn;
	}



	public String getCustomerType() {
		return customerType;
	}



	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}



	public String getFromDate() {
		return fromDate;
	}



	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}



	public String getToDate() {
		return toDate;
	}



	public void setToDate(String toDate) {
		this.toDate = toDate;
	}



	public String getPulledDate() {
		return pulledDate;
	}



	public void setPulledDate(String pulledDate) {
		this.pulledDate = pulledDate;
	}



	public Long getStatus() {
		return status;
	}



	public void setStatus(Long status) {
		this.status = status;
	}



	@Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString(); // Fallback to default toString() if JSON fails
        }
    }

} 
