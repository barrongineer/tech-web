package io.barrongineer.techweb.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shaunn on 4/29/2015.
 */
public class MyUserInfoTokenServices implements ResourceServerTokenServices {
    protected final Log logger = LogFactory.getLog(getClass());

    private String userInfoEndpointUrl;

    private String clientId;

    private OAuth2RestOperations restTemplate;

    public MyUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
        this.userInfoEndpointUrl = userInfoEndpointUrl;
        this.clientId = clientId;
    }

    public void setRestTemplate(OAuth2RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken)
            throws AuthenticationException, InvalidTokenException {

        Map<String, Object> map = getMap(userInfoEndpointUrl, accessToken);

        if (map.containsKey("error")) {
            logger.debug("userinfo returned error: " + map.get("error"));
            throw new InvalidTokenException(accessToken);
        }

        return extractAuthentication(map);
    }

    private OAuth2Authentication extractAuthentication(Map<String, Object> map) {
        List<Map<String, String>> authorities = (List<Map<String, String>>) map.get("authorities");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Map<String, String> auth : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(auth.get("authority")));
        }

        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(getPrincipal(map), "N/A", grantedAuthorities);
        user.setDetails(map);

        OAuth2Request request = new OAuth2Request(null, clientId, null, true, null, null, null, null, null);
        return new OAuth2Authentication(request, user);
    }

    private Object getPrincipal(Map<String, Object> map) {
        String[] keys = new String[]{"user", "username", "userid", "user_id", "login", "id", "name"};
        for (String key : keys) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return "unknown";
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    private Map<String, Object> getMap(String path, String accessToken) {
        logger.info("Getting user info from: " + path);
        OAuth2RestOperations restTemplate = this.restTemplate;
        if (restTemplate == null) {
            BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
            resource.setClientId(clientId);
            restTemplate = new OAuth2RestTemplate(resource);
        }
        restTemplate.getOAuth2ClientContext().setAccessToken(
                new DefaultOAuth2AccessToken(accessToken));
        @SuppressWarnings("rawtypes")
        Map map = restTemplate.getForEntity(path, Map.class).getBody();
        @SuppressWarnings("unchecked")
        Map<String, Object> result = map;
        return result;
    }
}
