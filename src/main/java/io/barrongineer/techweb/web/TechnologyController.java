package io.barrongineer.techweb.web;

import com.google.gson.Gson;
import io.barrongineer.techweb.domain.Technology;
import io.barrongineer.techweb.properties.UrlProperties;
import io.barrongineer.techweb.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shaunn on 3/29/2015.
 */
@RestController
public class TechnologyController {

    private static final String ENDPOINT = "/technology";
    private static final Logger logger = LoggerFactory.getLogger(TechnologyController.class);

    @Autowired
    private UrlProperties urlProperties;
    @Autowired
    private RestService restService;
    @Autowired
    private Gson gson;

    @RequestMapping(value = "/technology", method = RequestMethod.GET)
    public List<Technology> findAll() {
        List<Technology> response = new ArrayList<>();

        try {
            String json = restService.get(buildUrl(ENDPOINT));
            response = Arrays.asList(gson.fromJson(json, Technology[].class));
        } catch (Exception e) {
            logger.error("There was an error.", e);
        }
        return response;
    }

    @RequestMapping(value = "/technology/search/{displayName}", method = RequestMethod.GET)
    public List<Technology> search(@PathVariable("displayName") String displayName) {
        List<Technology> response = new ArrayList<>();

        try {
            String json = restService.get(buildUrl(ENDPOINT, "/displayName/", displayName));
            response = Arrays.asList(gson.fromJson(json, Technology[].class));
        } catch (Exception e) {
            logger.error("There was an error.", e);
        }

        return response;
    }

    private String buildUrl(String... args) {
        StringBuilder stringBuilder = new StringBuilder(urlProperties.getTechService());

        for (String s : args) {
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }
}
