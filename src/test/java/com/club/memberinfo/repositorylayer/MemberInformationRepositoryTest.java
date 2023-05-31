package com.club.memberinfo.repositorylayer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.club.memberinfo.entity.MemberInformation;
import com.club.memberinfo.repository.MemberInformationRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback(false)
public class MemberInformationRepositoryTest {

	@Autowired
	private MemberInformationRepository memberInformationRepository;
	
	private MemberInformation memberInformation;
	
	@Before
    public void setup(){
		memberInformation = new MemberInformation();
		memberInformation.setMemberId("KCATEST-4001");
		memberInformation.setFirstName("Rahim");
		memberInformation.setLastName("Sinha");
		memberInformation.setEmail("rahim@test.com");
		memberInformation.setContactNo("9878980000");
		memberInformation.setAddress("Kolkata");
		memberInformation.setCreatedDate(new Date());
		
	}
	
	@Test
	public void testMemberSave()
	{
		
		
		MemberInformation memberInformationObj = new MemberInformation();
		memberInformationObj.setMemberId("KCATEST-8001");
		memberInformationObj.setFirstName("Sachin");
		memberInformationObj.setLastName("Sinha");
		memberInformationObj.setEmail("sachin@test.com");
		memberInformationObj.setContactNo("9878980088");
		memberInformationObj.setAddress("Mumbai");
		memberInformationObj.setCreatedDate(new Date());
		
		//when action of the behaviour that we are going to test
		MemberInformation savedMember = memberInformationRepository.save(memberInformationObj);
		
		System.out.println("Generated RowID in h2 Database: "+savedMember.getMemberrowId());
		
		//then verify the output
		
		assertThat(savedMember).isNotNull();
		
		assertThat(savedMember.getMemberrowId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testfetchMemberInfoByMemberId()
	{
		/*
		MemberInformation memberInformationObj = new MemberInformation();
		memberInformationObj.setMemberId("KCATEST-8002");
		memberInformationObj.setFirstName("Saurav");
		memberInformationObj.setLastName("Ganguly");
		memberInformationObj.setEmail("saurav@test.com");
		memberInformationObj.setContactNo("9876567890");
		memberInformationObj.setAddress("Kolkata");
		memberInformationObj.setCreatedDate(new Date());
		
		
		memberInformationRepository.save(memberInformationObj);
		*/
		
		
		MemberInformation memberInfoData  = new MemberInformation();
		Optional<MemberInformation> memberInfoOptional = memberInformationRepository.fetchMemberInfoByMemberId("KCATEST-8001");
		
		if(memberInfoOptional.isPresent())
		{
			memberInfoData = memberInfoOptional.get();
			System.out.println(":::Data : "+memberInfoData.getFirstName());
		}
		
		assertEquals("Sachin", memberInfoData.getFirstName());
	}
	
	@Test
	public void testfetchmemberByContactNoOREmail()
	{
		List<MemberInformation> memberInfoList = memberInformationRepository.fetchmemberByContactNoOREmail("9878980088", "sachin@test.com");
		
		assertThat(memberInfoList.size()).isEqualTo(1);
	}
	
	@Test
	public void testFetchAll()
	{
		MemberInformation memberInformationObj = new MemberInformation();
		memberInformationObj.setMemberId("KCATEST-8002");
		memberInformationObj.setFirstName("Saurav");
		memberInformationObj.setLastName("Ganguly");
		memberInformationObj.setEmail("saurav@test.com");
		memberInformationObj.setContactNo("9876567890");
		memberInformationObj.setAddress("Kolkata");
		memberInformationObj.setCreatedDate(new Date());
		
		
		memberInformationRepository.save(memberInformationObj);
		
		List<MemberInformation> memberInfoList = memberInformationRepository.findAll();
		assertThat(memberInfoList.size()).isEqualTo(2);
	}
}
