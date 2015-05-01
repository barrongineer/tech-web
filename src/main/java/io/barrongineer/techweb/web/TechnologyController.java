package io.barrongineer.techweb.web;

import io.barrongineer.techweb.client.TechServiceClient;
import io.barrongineer.techweb.domain.Technology;
import io.barrongineer.techweb.service.TechnologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sbarron on 4/30/2015.
 */
@RestController
public class TechnologyController {

    private static final Logger logger = LoggerFactory.getLogger(TechnologyController.class);

    @Autowired
    private TechServiceClient techServiceClient;
    @Autowired
    private TechnologyService technologyService;

    @RequestMapping(value = "/technology", method = RequestMethod.GET)
    public List<Technology> getTechnologies() {
        return techServiceClient.getTechnologies();
    }

    @RequestMapping(value = "/technology", method = RequestMethod.POST)
    public Technology save(@RequestBody Technology technology) {
        logger.info("TECHNOLOGY: {}", technology.toString());

        return techServiceClient.save(technology);
    }

    @RequestMapping(value = "/hystrix")
    public List<Technology> testHystrix() {
        return technologyService.getTechnologies();
    }
}
