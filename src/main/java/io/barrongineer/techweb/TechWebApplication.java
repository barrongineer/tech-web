package io.barrongineer.techweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;

/**
 * Created by shaunn on 3/29/2015.
 */
@EnableZuulProxy
@EnableOAuth2Sso
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@SpringBootApplication
public class TechWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechWebApplication.class, args);
    }
}
