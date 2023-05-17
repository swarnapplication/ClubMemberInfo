package com.club.memberinfo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.club.memberinfo.entity.MemberInformation;

@Repository
public interface MemberInformationRepository extends JpaRepository<MemberInformation, Integer>{

	
	//In Native SQL Query we have to select all the columns, else have to use Object[] instead of class name
	@Query(value="SELECT MEMBERROWID,MEMBERID,FIRST_NAME,LAST_NAME,EMAIL,CONTACT_NO,ADDRESS,CREATED_DATE,MODIFIED_DATE FROM club_memberinfo "
			+ "WHERE CONTACT_NO=?1 OR EMAIL=?2",nativeQuery=true)
	public List<MemberInformation> fetchmemberByContactNoOREmail(String contactNo,String email);
	
	
	@Query(value="SELECT MEMBERROWID,MEMBERID,FIRST_NAME,LAST_NAME,EMAIL,CONTACT_NO,ADDRESS,CREATED_DATE,MODIFIED_DATE FROM club_memberinfo "
			+ "WHERE ADDRESS=?1",nativeQuery=true)
	public Optional<List<MemberInformation>> fetchMemberListByAddress(String address);
	
	
	@Query(value="SELECT MEMBERID FROM club_memberinfo "
			+ "WHERE MEMBERROWID=(SELECT MAX(MEMBERROWID) FROM club_memberinfo)",nativeQuery=true)
	public Optional<String> fetchLastRowMemberId();
}
