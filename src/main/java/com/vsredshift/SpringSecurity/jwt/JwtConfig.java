package com.vsredshift.SpringSecurity.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

@ConfigurationProperties(prefix = "application.jwt")
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;


    public String getAuthorizationHeader() {
        return AUTHORIZATION;
    }
}
