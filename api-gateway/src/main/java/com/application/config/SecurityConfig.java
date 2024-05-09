package com.application.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



public class SecurityConfig {
/*
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity)
    {
        serverHttpSecurity.csrf()
                .disable()
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec.pathMatchers("/eureka/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)  ;

       return serverHttpSecurity.build() ;

    }

 */
}
