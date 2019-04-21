package com.github.rafasantos.oauth2authorizationserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Value("${spring.datasource.initialization-mode}")
    private String dataSourceInitializationMode;

    private AuthenticationManager authenticationManager;
    private DataSource dataSource;

    public AuthorizationServerConfiguration(
        AuthenticationConfiguration authenticationConfiguration,
        DataSource dataSource) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
        this.dataSource = dataSource;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        if (!dataSourceInitializationMode.equals("none")) {
            clients
                .jdbc(dataSource)
                .build();
            clients
                .jdbc(dataSource)
                .withClient("myClientId")
                .authorizedGrantTypes("password")
                .secret("{noop}myClientSecret")
                .scopes("all");
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager);
    }
}
