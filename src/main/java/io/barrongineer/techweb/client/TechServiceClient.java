package io.barrongineer.techweb.client;

import io.barrongineer.techweb.domain.Technology;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by sbarron on 4/30/2015.
 */
@FeignClient("tech-service")
public interface TechServiceClient {

    @RequestMapping(value = "/technology", method = RequestMethod.GET)
    List<Technology> getTechnologies();

    @RequestMapping(value = "/technology", method = RequestMethod.POST, consumes = "application/json")
    Technology save(Technology technology);
}
