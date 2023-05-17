package com.club.memberinfo.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.club.memberinfo.entity.MemberInformation;



@Service
public class CSVUploader {

	public static String TYPE = "text/csv";
	static String[] HEADERs = { "MEMBERID", "FIRST_NAME", "LAST_NAME", "EMAIL","CONTACT_NO","ADDRESS" };
	
	public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }
	
	public List<MemberInformation> csvToListData(InputStream is) {
		System.out.println("$$$$$$$$$$$$$$$$$ Inside csvToListData(...) ");
		List<MemberInformation> members = new ArrayList<MemberInformation>();
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	    	System.out.println("$$$$$$$$$$$$$$$$$ Inside try(...) ");
	      //List<EmployeeInfoEntity> employees = new ArrayList<EmployeeInfoEntity>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      System.out.println("##### Checkpoint.... "+csvRecords);

	      for (CSVRecord csvRecord : csvRecords) {
	    	 // System.out.println("Data 1: "+csvRecord.get("EMPID")+" / "+csvRecord.get("EMPNAME")+" / "+csvRecord.get("EMPADDRESS")+" / "+csvRecord.get("EMP_PHONE"));

	    	  /*
	    	  EmployeeInfoEntity tutorial = new EmployeeInfoEntity(
	              Integer.parseInt(csvRecord.get("EMPID")),
	              csvRecord.get("EMPNAME"),
	              csvRecord.get("EMPADDRESS"),
	             csvRecord.get("EMP_PHONE")
	            );
	            */
	    	  
	    	  MemberInformation mem = new MemberInformation();
	    	  mem.setMemberrowId(Integer.parseInt(csvRecord.get("MEMBERID")));
	    	  mem.setFirstName(csvRecord.get("FIRST_NAME"));
	    	  mem.setLastName(csvRecord.get("LAST_NAME"));
	    	  mem.setEmail(csvRecord.get("EMAIL"));
	    	  mem.setContactNo(csvRecord.get("CONTACT_NO"));
	    	  mem.setAddress(csvRecord.get("ADDRESS"));
	    	  mem.setCreatedDate(new Date());
	    	  

	    	  System.out.println("Data 2: "+csvRecord.get("FIRST_NAME")+" / "+csvRecord.get("CONTACT_NO"));
	    	  members.add(mem);
	      }

	     // return employees;
	    } catch (IOException e) {
	    	e.printStackTrace();
	     // throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	    
	    return members;
	  }
	
	
	
	
}
