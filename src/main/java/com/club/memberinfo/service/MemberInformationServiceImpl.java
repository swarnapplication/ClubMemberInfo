package com.club.memberinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.memberinfo.entity.MemberInformation;
import com.club.memberinfo.mapper.ClubMemberInfoMapper;
import com.club.memberinfo.model.MemberInformationResponseModel;
import com.club.memberinfo.repository.MemberInformationRepository;

@Service
public class MemberInformationServiceImpl implements MemberInformationService{

	
	@Autowired
	private MemberInformationRepository memberInformationRepository;
	
	@Autowired
	private ClubMemberInfoMapper clubMemberInfoMapper;
	
	public MemberInformation createOrUpdateMemberInformation(MemberInformation memberInfo) {
		// TODO Auto-generated method stub
		return memberInformationRepository.save(memberInfo);
	}

	@Override
	public void createOrUpdateMemberInformationAsList(List<MemberInformation> memberInfoList) {
		// TODO Auto-generated method stub
		memberInformationRepository.saveAll(memberInfoList);
	}

	@Override
	public List<MemberInformation> fetchmemberByContactNoOREmail(String contactNo, String email) {
		// TODO Auto-generated method stub
		return memberInformationRepository.fetchmemberByContactNoOREmail(contactNo, email);
	}

	@Override
	public List<MemberInformationResponseModel> fetchMemberListByAddress(String address) {
		// TODO Auto-generated method stub
		
		List<MemberInformationResponseModel> memberList = new ArrayList<>();
		
		Optional<List<MemberInformation>> memberListOptional = memberInformationRepository.fetchMemberListByAddress(address);
		if (memberListOptional.isPresent()) {
			memberList = memberListOptional.get().stream()
					.map(mem -> clubMemberInfoMapper.getMemberInformationResponseModel(mem))
					.collect(Collectors.toList());
		}
		return memberList;
	}

	@Override
	public String fetchLastRowMemberId() {
		// TODO Auto-generated method stub
		
		Optional<String> lastRowMemberIdOptional = memberInformationRepository.fetchLastRowMemberId();
		String nextMemberId = "KCA-1001";
		if(lastRowMemberIdOptional.isPresent())
		{
			String lastMemberId = lastRowMemberIdOptional.get();
			String lastPart_afterHiphen = lastMemberId.split("-")[1];
			Integer nextNum = Integer.parseInt(lastPart_afterHiphen)+1;
			nextMemberId = "KCA-"+nextNum;
		}
		
		
		return nextMemberId;
	}

	@Override
	public MemberInformation fetchMemberInfoByMemberId(String memberId) {
		// TODO Auto-generated method stub

		Optional<MemberInformation> memberInfoOptional = memberInformationRepository
				.fetchMemberInfoByMemberId(memberId);
		return memberInfoOptional.isPresent() ? memberInfoOptional.get() : new MemberInformation();

	}

}
