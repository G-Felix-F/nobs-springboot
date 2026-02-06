package com.example.nobs.catfact;

import com.example.nobs.cqrs.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;


@Service
public class CatFactService implements Query<Integer, CatFactDTO> {

    private final RestTemplate restTemplate;
    private final String URL = "https://catfact.ninja/fact";
    private final String MAX_LENGTH = "max_length";

    public CatFactService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public CatFactDTO execute(Integer input) {
        URI uri = UriComponentsBuilder
                .fromUriString(URL)
                .queryParam(MAX_LENGTH, input)
                .build()
                .toUri();

//        HttpHeaders headers = new org.springframework.http.HttpHeaders();


        CatFactResponse catFactResponse = restTemplate.getForObject(
                "?max_length=" + input,
                CatFactResponse.class
        );
        return new CatFactDTO(catFactResponse.getFact());
    }
}
