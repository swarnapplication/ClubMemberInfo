package com.club.memberinfo.exception;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder{


	
	
	@Override
	public Exception decode(String methodKey, Response response) {
		// TODO Auto-generated method stub
		//START DECODING ORIGINAL ERROR MESSAGE
        String erroMessage = null;
        Reader reader = null;
        

        //capturing error message from response body.
        try {
            reader = response.body().asReader();
            String result = IOUtils.toString(reader);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            FeignExceptionMessage exceptionMessage = mapper.readValue(result,
                FeignExceptionMessage.class);

            erroMessage = exceptionMessage.getMessage();

        } catch (IOException e) {
            System.out.println("IO Exception on reading exception message feign client" + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            	System.out.println("IO Exception on reading exception message feign client" + e);
            }
	}

        
      //END DECODING ORIGINAL ERROR MESSAGE
	
        switch (response.status()) {
        case 400:
        	System.out.println("Error in request went through feign client 400 {}: "+ erroMessage);
            //handle exception
            return new MemberInfoCustomException("Bad Request Through Feign");
        case 401:
        	System.out.println("Error in request went through feign client 401 {}: "+ erroMessage);
            //handle exception
            return new MemberInfoCustomException("Unauthorized Request Through Feign");
        case 404:
        	System.out.println("Error in request went through feign client 404{}: "+ erroMessage);
            //handle exception
        	return new MemberInfoCustomException(erroMessage,HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND);
           // return new Exception("Unidentified Request Through Feign");
        default:
        	System.out.println("Error in request went through feign client default {}: "+ erroMessage);
            //handle exception
            return new Exception("Common Feign Exception");
        }//end of switch case
}
	
	
	
	
}
