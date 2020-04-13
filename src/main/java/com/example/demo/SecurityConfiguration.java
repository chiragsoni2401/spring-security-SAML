package com.example.demo;

import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.saml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    SAMLUserDetailsService userDetailsService;
	
    @Value("${security.saml2.metadata-url}")
    String metadataUrl;

    @Value("${server.ssl.key-alias}")
    String keyAlias;

    @Value("${server.ssl.key-store-password}")
    String password;

    @Value("${server.port}")
    String port;

    @Value("${server.ssl.key-store}")
    String keyStoreFilePath;
    
    
    
    //Uisng SAML2.0
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/formLogin").permitAll()
                .anyRequest().authenticated()
                .and()
            .apply(saml())
                .serviceProvider()
                    .keyStore()
                        .storeFilePath(this.keyStoreFilePath)
                        .password(this.password)
                        .keyname(this.keyAlias)
                        .keyPassword(this.password)
                        .and()
                    .protocol("https")
                    .hostname(String.format("%s:%s", "localhost", this.port))
                    .basePath("/")
                    .and().userDetailsService(userDetailsService)
                .identityProvider()
                .metadataFilePath(this.metadataUrl);
    }
    
    //Using OAuth2.0
   
}