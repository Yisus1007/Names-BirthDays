
package com.peoplefile.controller;

import com.peoplefile.json.PeopleInformationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GetInfoController 
{
   @PostMapping("/information")
   public ResponseEntity<PeopleInformationResponse> getPeopleInfo()
   {
       PeopleInformationResponse response = new PeopleInformationResponse();
       ResponseEntity<PeopleInformationResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
       return responseEntity;

   }
}
