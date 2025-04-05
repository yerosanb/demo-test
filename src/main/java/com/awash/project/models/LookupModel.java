package com.awash.project.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LookupModel {

	private Long id;
	private String gradeBasedOn;
	private String customerType_criteria;
	private String riskLevel;
	private Long riskScore;
	private String scenario;
	private Long value;
	private String maker;
	private String savedDate;
	private String changedDate;
	private String approver;
	private String approvedDate;
	private String deletedBy;
	private String deletedDate;
	
	
	public String getDeletedBy() {
		return deletedBy;
	}
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}
	public String getDeletedDate() {
		return deletedDate;
	}
	public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}
	private Long status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGradeBasedOn() {
		return gradeBasedOn;
	}
	public void setGradeBasedOn(String gradeBasedOn) {
		this.gradeBasedOn = gradeBasedOn;
	}
	public String getCustomerType_criteria() {
		return customerType_criteria;
	}
	public void setCustomerType_criteria(String customerType_criteria) {
		this.customerType_criteria = customerType_criteria;
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
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getSavedDate() {
		return savedDate;
	}
	public void setSavedDate(String savedDate) {
		this.savedDate = savedDate;
	}
	public String getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(String changedDate) {
		this.changedDate = changedDate;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}


	public LookupModel(Long id, String gradeBasedOn, String customerType_criteria, String riskLevel, Long riskScore,
			String scenario, Long value, String maker, String savedDate, String changedDate, String approver,
			String approvedDate, String deletedBy, String deletedDate, Long status) {
		super();
		this.id = id;
		this.gradeBasedOn = gradeBasedOn;
		this.customerType_criteria = customerType_criteria;
		this.riskLevel = riskLevel;
		this.riskScore = riskScore;
		this.scenario = scenario;
		this.value = value;
		this.maker = maker;
		this.savedDate = savedDate;
		this.changedDate = changedDate;
		this.approver = approver;
		this.approvedDate = approvedDate;
		this.deletedBy = deletedBy;
		this.deletedDate = deletedDate;
		this.status = status;
	}
	
	
}
