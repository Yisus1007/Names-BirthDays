package com.peoplefile.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.applicationinsights.core.dependencies.http.HttpEntity;
import com.microsoft.applicationinsights.core.dependencies.http.HttpResponse;
import com.microsoft.applicationinsights.core.dependencies.http.client.ClientProtocolException;
import com.microsoft.applicationinsights.core.dependencies.http.client.ResponseHandler;
import com.microsoft.applicationinsights.core.dependencies.http.client.methods.HttpGet;
import com.microsoft.applicationinsights.core.dependencies.http.impl.client.CloseableHttpClient;
import com.microsoft.applicationinsights.core.dependencies.http.impl.client.HttpClients;
import com.microsoft.applicationinsights.core.dependencies.http.util.EntityUtils;
import com.peoplefile.json.PeopleInformationResponse;
import com.peoplefile.json.PeopleInformationRequest;
import com.peoplefile.json.PoemsResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class GetPeopleInfoService {

    public PeopleInformationResponse getInfoPeople(PeopleInformationRequest request) 
    {
        System.out.println("Llego al servicio con: " + request.toString());
        PeopleInformationResponse response = new PeopleInformationResponse();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try 
        {   
            LocalDate today = LocalDate.now();
            LocalDate birthdayReq = LocalDate.parse(request.getDateOfBirth(),formatter);
            System.out.println("birthdayReq: " + birthdayReq);
            LocalDate nextBirth = birthdayReq.withYear(today.getYear());
            System.out.println("nextBirth: " + nextBirth);
            String[] completeName = request.getName().split(" ");
            System.out.println("Primer nombre: " + completeName[0]);
            System.out.println("Primer apellido: " + completeName[2]);
            response.setName(completeName[0] +" " + completeName[2]);
            if(nextBirth.isBefore(today) || nextBirth.equals(today))
            {
                nextBirth = nextBirth.plusYears(1);
            }
            long totalDays = ChronoUnit.DAYS.between(today, nextBirth);
            response.setDateToBirth(String.valueOf(totalDays));
            if(totalDays == 365)
            {
                System.out.println("Cumpleanos");
                Integer age = today.getYear() - birthdayReq.getYear();
                response.setAge(age.toString());
                response.setPoem(this.getPoem());
            }
            else
            {   
                Integer age = 0;
                if(today.getMonthValue() > birthdayReq.getMonthValue())
                {
                     age = (today.getYear()) - birthdayReq.getYear();
                }
                else
                {
                    age = (today.getYear()-1) - birthdayReq.getYear();
                }
                
                response.setAge(age.toString());
            }

        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return response;
    }
    public String getPoem()
    {
        String poem = null;
        String urlStr = "https://www.poemist.com/api/v1/randompoems";
        System.out.println("URL: " + urlStr);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try 
        {
            HttpGet httpget = new HttpGet(urlStr);
            ResponseHandler<String> responseHandler = (final HttpResponse response) -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = (HttpEntity) response.getEntity();
                    return entity != null ? EntityUtils.toString((HttpEntity) entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            ObjectMapper mapper = new ObjectMapper();
            poem = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(poem);
            System.out.println("----------------------------------------");
            PoemsResponse[] responsePoem = mapper.readValue(poem, PoemsResponse[].class);
            System.out.println("Tiene: " + responsePoem.length + " registros");
            List<PoemsResponse> lsitPoems = Arrays.asList(responsePoem);
            Random random = new Random();
            String poemToReturn = null;
            for(int i = 0; i < lsitPoems.size(); i++)
            {
                int randomNumber = random.nextInt(lsitPoems.size());
                poemToReturn = lsitPoems.get(randomNumber).getContent();
            
            }
            System.out.println("Poema selecciionado: " + poemToReturn);
            return poemToReturn;
        }
        catch(Exception ex ) 
        {
            System.out.println("Error URL: " + ex.getMessage());
            return poem;
        } 
    }
}
