package io.barrongineer.techweb.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by shaunn on 3/29/2015.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        String view = "index";

        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user.equals("anonymousUser")) {
            view = "redirect:/login";
        }

        logger.info("Returning view: {}", view);

        return view;
    }
}
