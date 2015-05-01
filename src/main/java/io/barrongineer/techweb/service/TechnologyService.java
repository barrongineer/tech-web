package io.barrongineer.techweb.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.barrongineer.techweb.domain.Technology;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by sbarron on 4/30/2015.
 */
@Service
public class TechnologyService {

    @HystrixCommand(fallbackMethod = "defaultTechnologies")
    public List<Technology> getTechnologies() {
        Technology technology = new Technology();
        technology.setId(1);
        technology.setDisplayName("Go");
        technology.setSummary("Im really fast and really small.");

        throw new RuntimeException("Triggering hystrix.");

//        return Arrays.asList(technology);
    }

    public List<Technology> defaultTechnologies() {
        Technology technology = new Technology();
        technology.setId(1);
        technology.setDisplayName("Java");
        technology.setSummary("Im kind of fast and not so small.");

        return Arrays.asList(technology);
    }
}
