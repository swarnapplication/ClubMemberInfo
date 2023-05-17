package com.club.memberinfo.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import com.club.memberinfo.entity.MemberInformation;
import com.club.memberinfo.model.MemberInformationResponseModel;

@Component
@Mapper(componentModel = "spring")
public interface ClubMemberInfoMapper {

	
@Mappings({
		
		@Mapping(target = "memberId",source = "memberInfo.memberId"),
		@Mapping(target = "firstName",source = "memberInfo.firstName"),
		@Mapping(target = "lastName",source = "memberInfo.lastName"),
		@Mapping(target = "email",source = "memberInfo.email"),
		@Mapping(target = "contactNo",source = "memberInfo.contactNo"),
		@Mapping(target = "address",source = "memberInfo.address"),
		@Mapping(target = "createdDate",source = "memberInfo.createdDate"),
		@Mapping(target = "modifiedDate",source = "memberInfo.modifiedDate"),
		
		})
@BeanMapping(qualifiedByName = "memberInfoRespModelMap")	
public MemberInformationResponseModel getMemberInformationResponseModel(MemberInformation memberInfo);


@Mappings({
	@Mapping(target = "memberrowId",source = "memberInfo.memberrowId"),
	@Mapping(target = "memberId",source = "memberInfo.memberId"),
	@Mapping(target = "firstName",source = "memberInfo.firstName"),
	@Mapping(target = "lastName",source = "memberInfo.lastName"),
	@Mapping(target = "email",source = "memberInfo.email"),
	@Mapping(target = "contactNo",source = "memberInfo.contactNo"),
	@Mapping(target = "address",source = "memberInfo.address"),
	@Mapping(target = "createdDate",source = "memberInfo.createdDate"),
	@Mapping(target = "modifiedDate",source = "memberInfo.modifiedDate"),
	
	})
@BeanMapping(qualifiedByName = "memberInformationMap")	
public MemberInformation getMemberInformation(MemberInformationResponseModel memberInfo);




}
