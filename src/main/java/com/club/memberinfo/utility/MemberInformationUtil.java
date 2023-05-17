package com.club.memberinfo.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.club.memberinfo.exception.FeignCustomErrorDecoder;

import com.club.memberinfo.exception.FeignExceptionMessage;
import com.club.memberinfo.model.MemberFeesAmountModel;
import com.club.memberinfo.model.MemberFeesEntityResponseModel;
import com.club.memberinfo.model.MemberFeesResponseModel;
import com.club.memberinfo.model.MemberInformationResponseModel;
import com.club.memberinfo.service.MemberInformationService;

import feign.FeignException;

@Component
public class MemberInformationUtil {

	
	@Autowired
	private MemberInformationService memberInformationService;
	
	@Autowired
	private MemberFeesFeignClient memberFeesFeignClient;
	
	
	
	
	public List<MemberInformationResponseModel> fetchMemberListByAddress(String address)
	{
		return memberInformationService.fetchMemberListByAddress(address);
	}
	
	public MemberFeesResponseModel getMemberFeesDetails(String memberId)
	{
		return memberFeesFeignClient.getMemberFees(memberId);
		
		
	}
	
	public MemberFeesEntityResponseModel getmemberFeesByYear(String memberId,Integer year)
	{
		return memberFeesFeignClient.getMemberFeesByMemberIdAndYear(memberId, year);
	}
	
	public String saveMemberFeesMultipleYear(String memberId,Double feesAmount)
	{
		MemberFeesAmountModel memberFees = new MemberFeesAmountModel();
		memberFees.setMemberId(memberId);
		memberFees.setMemberFeesAmount(feesAmount);
		
		return memberFeesFeignClient.saveMemberFeesMultipleYear(memberFees);
	}
	
}
