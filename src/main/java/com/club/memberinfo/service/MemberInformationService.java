package com.club.memberinfo.service;

import com.club.memberinfo.entity.MemberInformation;
import com.club.memberinfo.model.MemberInformationResponseModel;

import java.util.*;

public interface MemberInformationService {

	public MemberInformation createOrUpdateMemberInformation(MemberInformation memberInfo);
	public void createOrUpdateMemberInformationAsList(List<MemberInformation> memberInfoList);
	public List<MemberInformation> fetchmemberByContactNoOREmail(String contactNo,String email);
	public List<MemberInformationResponseModel> fetchMemberListByAddress(String address);
	public String fetchLastRowMemberId();
}
