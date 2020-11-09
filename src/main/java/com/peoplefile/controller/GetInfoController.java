
package com.peoplefile.controller;

import com.peoplefile.json.PeopleInformationRequest;
import com.peoplefile.json.PeopleInformationResponse;
import com.peoplefile.services.GetPeopleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GetInfoController 
{
   @Autowired
   private GetPeopleInfoService service;
   @PostMapping("/information")
   public ResponseEntity<PeopleInformationResponse> getPeopleInfo(@RequestBody PeopleInformationRequest request)
   {
        System.out.println("Ingreso al metodo correctametne");
        System.out.println("Request recibido: " + request.toString());
        try
        {
             PeopleInformationResponse response = service.getInfoPeople(request);
             ResponseEntity<PeopleInformationResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
             return responseEntity; 
        }
        catch(Exception e)
        {
             ResponseEntity<PeopleInformationResponse> responseEntity = new ResponseEntity<>(new PeopleInformationResponse(), HttpStatus.BAD_REQUEST);
             return responseEntity;
        }

   }
}
