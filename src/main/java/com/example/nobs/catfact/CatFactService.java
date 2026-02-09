package com.example.nobs.catfact;

import com.example.nobs.cqrs.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


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

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<CatFactResponse> response = restTemplate
                    .exchange(uri, HttpMethod.GET, entity, CatFactResponse.class);

            return new CatFactDTO(response.getBody().getFact());
        } catch(Exception e) {
            throw new RuntimeException("Cat Fact API is down");
        }
    }
}
