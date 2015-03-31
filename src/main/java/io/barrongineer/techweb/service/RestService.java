package io.barrongineer.techweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by shaunn on 3/30/2015.
 */
@Service
public class RestService {

    public String get(String url) throws Exception {
        return getRestTemplate().getForEntity(url, String.class).getBody();
    }

    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
