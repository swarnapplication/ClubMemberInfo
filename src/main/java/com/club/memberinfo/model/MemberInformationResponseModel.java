package com.club.memberinfo.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberInformationResponseModel implements Serializable{

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer memberrowId;
	
    private String memberId;
	
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String email;
	
	
	private String contactNo;
	
	
	private String address;
	
	
	private Date createdDate;
	
	
	
	private Date modifiedDate;

    private Double feesAmount;

	


	public Double getFeesAmount() {
		return feesAmount;
	}



	public void setFeesAmount(Double feesAmount) {
		this.feesAmount = feesAmount;
	}



	public Integer getMemberrowId() {
		return memberrowId;
	}



	public void setMemberrowId(Integer memberrowId) {
		this.memberrowId = memberrowId;
	}



	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getContactNo() {
		return contactNo;
	}



	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getModifiedDate() {
		return modifiedDate;
	}



	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	
	
}
