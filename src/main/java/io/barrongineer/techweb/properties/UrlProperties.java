package io.barrongineer.techweb.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by shaunn on 3/30/2015.
 */
@Component
@ConfigurationProperties(prefix = "url")
public class UrlProperties {

    private String techService;

    public String getTechService() {
        return techService;
    }

    public void setTechService(String techService) {
        this.techService = techService;
    }
}
