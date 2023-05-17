package com.club.memberinfo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.club.memberinfo.entity.MemberInformation;
import com.club.memberinfo.helper.CSVUploader;
import com.club.memberinfo.helper.MemberInformationHelper;
import com.club.memberinfo.mapper.ClubMemberInfoMapper;
import com.club.memberinfo.model.MemberFeesAmountModel;
import com.club.memberinfo.model.MemberFeesEntityResponseModel;
import com.club.memberinfo.model.MemberFeesResponseModel;
import com.club.memberinfo.model.MemberInformationResponseModel;
import com.club.memberinfo.service.MemberInformationService;
import com.club.memberinfo.utility.MemberInformationUtil;




@RestController
@RequestMapping("/club/memberinfo")
public class MemberInformationController {

	
	@Autowired
	private MemberInformationHelper memberInformationHelper;
	
	@Autowired
	private MemberInformationUtil memberInformationUtil;
	
	@Autowired
	private MemberInformationService memberInformationService;
	
	@Autowired
	private ClubMemberInfoMapper clubMemberInfoMapper;
	
	@PostMapping(value = "/addOrUpdateMember/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberInformationResponseModel> addOrUpdateMember(
			@RequestBody MemberInformationResponseModel memberInformation

	) {

		MemberInformationResponseModel mresp = new MemberInformationResponseModel();

		MemberInformation memberInformationEntity = clubMemberInfoMapper.getMemberInformation(memberInformation);

		String generatedMemberId = memberInformationService.fetchLastRowMemberId();
		memberInformationEntity.setMemberId(generatedMemberId);
		memberInformationEntity.setCreatedDate(new Date());

		MemberInformation createdMember = memberInformationHelper
				.createOrUpdateMemberInformation(memberInformationEntity);
		if (null != createdMember && null != createdMember.getMemberId()) {
			System.out.println("MemberID: " + createdMember.getMemberId());
			mresp.setFirstName(createdMember.getFirstName());
			mresp.setMemberId(createdMember.getMemberId());

			if (null != memberInformation.getFeesAmount() && memberInformation.getFeesAmount() > 0) {
				String resp = memberInformationUtil.saveMemberFeesMultipleYear(createdMember.getMemberId(),
						memberInformation.getFeesAmount());
				System.out.println(":::Response from MemberFeesService: " + resp);
			}
		}

		return new ResponseEntity<>(mresp, null, HttpStatus.OK);

	}
	
	
	@GetMapping(value = "/fetchMember/{contactNo}/{email}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<MemberInformation>> fetchMember(
			@PathVariable("contactNo") String contactNo,@PathVariable("email") String email)
	{
		List<MemberInformation> memberList = new ArrayList<>();
		
		memberList=memberInformationHelper.fetchmemberByContactNoOREmail(contactNo, email);
		
		return new ResponseEntity<>(memberList, null, HttpStatus.OK);
		
	}
	
	
	@GetMapping(value = "/fetchMemberByAddress/{address}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<MemberInformationResponseModel>> fetchMemberByAddress(
			@PathVariable("address") String address)
	{
		List<MemberInformationResponseModel> memberList = new ArrayList<>();
		
		memberList=memberInformationUtil.fetchMemberListByAddress(address);
		
		return new ResponseEntity<>(memberList, null, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/uploadMembers")
	  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (CSVUploader.hasCSVFormat(file)) {
	      try {
	    	  System.out.println("%%%%%%%%%%%%%%%%%%%%%  Inside: uploadFile ");
	    	  
	    	  memberInformationHelper.saveMembersFromFile(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        
	      }
	    }

	    return new ResponseEntity<>(message, null, HttpStatus.OK);
	  }
	
	@GetMapping(value="/fetchMemberFees/{memberid}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberFeesResponseModel> fetchMemberFees(
			@PathVariable("memberid") String memberid)
	{
		
		
		return new ResponseEntity<>(memberInformationUtil.getMemberFeesDetails(memberid), null, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/fetchMemberFeesByYear/{memberid}/{year}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<MemberFeesEntityResponseModel> fetchMemberFees(
			@PathVariable("memberid") String memberid,@PathVariable("year") Integer year)
	{
		
		
		return new ResponseEntity<>(memberInformationUtil.getmemberFeesByYear(memberid,year), null, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/saveMemberFeesMultipleYear/{memberId}/{feesamount}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> saveMemberFeesMultipleYearGET(@PathVariable("memberId") String memberId,
			@PathVariable("feesamount") Double feesamount) {
		String resp = memberInformationUtil.saveMemberFeesMultipleYear(memberId, feesamount);

		return new ResponseEntity<>(resp, null, HttpStatus.OK);
	}
	
}
