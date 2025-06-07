package net.engineeringdigest.service;

import net.engineeringdigest.apiResponse.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

@Component
public class WeatherService {
    @Autowired
 private RestTemplate restTemplate;

    private  String apiKey="5ab327d4f5be4476bc271051250506";
    private String apiURL="https://api.weatherapi.com/v1/current.json?q=IP_ADDRESS&key=API_KEY";

    public Weather greet(String ip){
        String finalApi= apiURL.replace("IP_ADDRESS",ip).replace("API_KEY",apiKey);
        HttpHeaders headers=new HttpHeaders();
        headers.setAccept(Collections.singletonList( MediaType.APPLICATION_JSON));
        headers.set("Content-Type","Application/json");
        headers.setBearerAuth("sdfsdfsdf");

        HttpEntity<String> httpHeader= new HttpEntity<>(headers);
        System.out.println("--->"+httpHeader.toString());

//      ResponseEntity<Weather> res=  restTemplate.getForEntity(finalApi, Weather.class);
//        System.out.println("--->"+res.getBody().location.getName());
//        return  res.getBody();
//        Weather res= restTemplate.getForObject(finalApi,Weather.class);
//        System.out.println(res);

      ResponseEntity<Weather> res= restTemplate.exchange(finalApi, HttpMethod.GET,httpHeader,Weather.class);
        System.out.println(res.getHeaders());

return res.getBody();
    }



}
