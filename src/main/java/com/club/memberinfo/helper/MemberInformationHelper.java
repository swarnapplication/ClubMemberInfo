package com.club.memberinfo.helper;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.club.memberinfo.entity.MemberInformation;
import com.club.memberinfo.service.MemberInformationService;


@Component
public class MemberInformationHelper {

	@Autowired
	private MemberInformationService memberInformationService;
	
	@Autowired
	private CSVUploader cSVUploader;
	
	public MemberInformation createOrUpdateMemberInformation(MemberInformation m)
	{
		return memberInformationService.createOrUpdateMemberInformation(m);
	}
	
	public List<MemberInformation> fetchmemberByContactNoOREmail(String contactNo,String email)
	{
		return memberInformationService.fetchmemberByContactNoOREmail(contactNo, email);
	}
	
	public void saveMembersFromFile(MultipartFile file) {
	    try {
	      List<MemberInformation> memberList = cSVUploader.csvToListData(file.getInputStream());
	     
	      System.out.println("######## File Size: "+memberList.size());
	      memberInformationService.createOrUpdateMemberInformationAsList(memberList);
	      
	    } catch (IOException e) {
	      throw new RuntimeException("fail to store csv data: " + e.getMessage());
	    }
	  }
}
