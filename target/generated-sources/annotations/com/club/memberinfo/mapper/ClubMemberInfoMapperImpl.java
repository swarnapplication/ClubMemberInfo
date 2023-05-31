package com.club.memberinfo.mapper;

import com.club.memberinfo.entity.MemberInformation;
import com.club.memberinfo.model.MemberInformationResponseModel;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T12:47:12+0530",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class ClubMemberInfoMapperImpl implements ClubMemberInfoMapper {

    @Override
    public MemberInformationResponseModel getMemberInformationResponseModel(MemberInformation memberInfo) {
        if ( memberInfo == null ) {
            return null;
        }

        MemberInformationResponseModel memberInformationResponseModel = new MemberInformationResponseModel();

        memberInformationResponseModel.setFirstName( memberInfo.getFirstName() );
        memberInformationResponseModel.setLastName( memberInfo.getLastName() );
        memberInformationResponseModel.setAddress( memberInfo.getAddress() );
        memberInformationResponseModel.setCreatedDate( memberInfo.getCreatedDate() );
        memberInformationResponseModel.setModifiedDate( memberInfo.getModifiedDate() );
        memberInformationResponseModel.setEmail( memberInfo.getEmail() );
        memberInformationResponseModel.setMemberId( memberInfo.getMemberId() );
        memberInformationResponseModel.setContactNo( memberInfo.getContactNo() );
        memberInformationResponseModel.setMemberrowId( memberInfo.getMemberrowId() );

        return memberInformationResponseModel;
    }

    @Override
    public MemberInformation getMemberInformation(MemberInformationResponseModel memberInfo) {
        if ( memberInfo == null ) {
            return null;
        }

        MemberInformation memberInformation = new MemberInformation();

        memberInformation.setFirstName( memberInfo.getFirstName() );
        memberInformation.setLastName( memberInfo.getLastName() );
        memberInformation.setMemberrowId( memberInfo.getMemberrowId() );
        memberInformation.setAddress( memberInfo.getAddress() );
        memberInformation.setCreatedDate( memberInfo.getCreatedDate() );
        memberInformation.setModifiedDate( memberInfo.getModifiedDate() );
        memberInformation.setEmail( memberInfo.getEmail() );
        memberInformation.setMemberId( memberInfo.getMemberId() );
        memberInformation.setContactNo( memberInfo.getContactNo() );

        return memberInformation;
    }
}
