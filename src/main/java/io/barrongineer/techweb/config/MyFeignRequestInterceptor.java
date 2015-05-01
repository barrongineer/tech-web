package io.barrongineer.techweb.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sbarron on 4/30/2015.
 */
@Component
public class MyFeignRequestInterceptor implements RequestInterceptor {

    private final HttpServletRequest request;

    @Autowired
    public MyFeignRequestInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void apply(RequestTemplate template) {
        String header = request.getHeader("authorization");

        template.header("Authorization", header);
    }
}
