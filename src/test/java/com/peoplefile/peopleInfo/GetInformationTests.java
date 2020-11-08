package com.peoplefile.peopleInfo;

import com.peoplefile.json.PeopleInformationRequest;
import com.peoplefile.json.PeopleInformationResponse;
import com.peoplefile.services.GetPeopleInfoService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


public class GetInformationTests 
{
    @InjectMocks
    private GetPeopleInfoService service = new GetPeopleInfoService();
    
    @Test
    public void getPeopleInformationTest()
    {
        PeopleInformationRequest request = new PeopleInformationRequest();
        request.setName("Jesus Alberto Castellanos Santiago");
        request.setDateOfBirth("08-11-1991");
        System.out.println("El request tiene: " + request.toString());
        PeopleInformationResponse response = service.getInfoPeople(request);
        System.out.println("El response tiene: " + response.toString());
        assertNotNull(response);
        
    }
    
    @Test
    public void getPoemsFromApi()
    {
        String peom = service.getPoem();
        assertNotNull(peom);
    }
}
