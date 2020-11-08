package com.peoplefile.services;

import com.peoplefile.json.PeopleInformationResponse;
import com.peoplefile.json.PeopleInformationRequest;
import java.text.DecimalFormat;
import java.util.Calendar;
import org.springframework.stereotype.Service;

@Service
public class GetPeopleInfoService {

    public PeopleInformationResponse getInfoPeople(PeopleInformationRequest request) 
    {
        System.out.println("Llego al servicio con: " + request.toString());
        PeopleInformationResponse response = new PeopleInformationResponse();
        Calendar calendar = Calendar.getInstance();
        DecimalFormat mFormat = new DecimalFormat("00");
        try 
        {
            String[] birthRequest = request.getDateOfBirth().split("-");
            String dayOfBirth = birthRequest[0];
            System.out.println("Dia de nacimiento: " + dayOfBirth);
            String monthOfBirth = birthRequest[1];
            System.out.println("mes de nacimiento: " + monthOfBirth);
            String yearOfBirth = birthRequest[2];
            System.out.println("año de nacimiento: " + yearOfBirth);
            String[] getName = request.getName().split(" ");
            System.out.println("El primer nombre: " + getName[0] + " primer apellido: " + getName[2]);
            String todayDay = mFormat.format(Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            System.out.println("Dia de hoy: " + todayDay);
            String todayMonth = mFormat.format(Double.valueOf(calendar.get(Calendar.MONTH)));
            System.out.println("Mes de hoy: " + todayMonth);
            String todayYear = String.valueOf(calendar.get(Calendar.YEAR));
            System.out.println("Year de hoy: " + todayYear);
            response.setName(getName[0] + " " + getName[2]);
            response.setAge("29");
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

        return response;
    }
}
