package com.club.memberinfo.utility;

import org.springframework.stereotype.Component;

import com.club.memberinfo.model.MemberFeesAmountModel;
import com.club.memberinfo.model.MemberFeesEntityResponseModel;
import com.club.memberinfo.model.MemberFeesResponseModel;

@Component
public class MemberFeesFeignClientFallBack implements MemberFeesFeignClient{
	
	//Ref Url: https://www.munonye.com/microservices/hystrix-circuit-breaker-step-by-step-configuration-with-feign-client-part-1/

	public MemberFeesResponseModel getMemberFees(String memberId) {
		// TODO Auto-generated method stub
		MemberFeesResponseModel memberFeesResp = new MemberFeesResponseModel();
		memberFeesResp.setStatusCode("404");
		memberFeesResp.setStatusMessage("From FallBack: ServiceNotFound....Please try after some time");
		
		return memberFeesResp;
	}

	public MemberFeesEntityResponseModel getMemberFeesByMemberIdAndYear(String memberId, Integer year) {
		// TODO Auto-generated method stub
		MemberFeesEntityResponseModel resp = new MemberFeesEntityResponseModel();
		resp.setStatusCode("404");
		resp.setStatusMessage("From FallBack: ServiceNotFound....Please try after some time");
		return resp;
	}

	@Override
	public String saveMemberFeesMultipleYear(MemberFeesAmountModel memberFees) {
		// TODO Auto-generated method stub
		
		String message = "From FallBack: ServiceNotFound....Please try after some time";
		return message;
	}

}
