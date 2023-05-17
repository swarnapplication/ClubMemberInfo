package com.club.memberinfo.utility;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.club.memberinfo.exception.CustomFeignClientConfiguration;
import com.club.memberinfo.model.MemberFeesAmountModel;
import com.club.memberinfo.model.MemberFeesEntityResponseModel;
import com.club.memberinfo.model.MemberFeesResponseModel;




@FeignClient(value = "MemberFeesService", url = "http://localhost:8551/club/memberfees/", fallback=MemberFeesFeignClientFallBack.class)
public interface MemberFeesFeignClient {

	@GetMapping("/getMemberFees/{memberId}")
	public MemberFeesResponseModel getMemberFees(@PathVariable("memberId") String memberId);

	@GetMapping("/getMemberFeesByMemberIdAndYear/{memberId}/{year}")
	public MemberFeesEntityResponseModel getMemberFeesByMemberIdAndYear(@PathVariable("memberId") String memberId,
			@PathVariable("year") Integer year);

	@PostMapping("/saveMemberFeesMultipleYear/")
	public String saveMemberFeesMultipleYear(@RequestBody MemberFeesAmountModel memberFees);

}
