package com.example.demo;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;

import com.github.ulisesbocchio.spring.boot.security.saml.user.SAMLUserDetails;

import org.opensaml.saml2.core.Attribute;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    
    
   /* @Bean
    public SAMLUserDetailsService userDetailsService() {
        return new SAMLUserDetailsService() {
            @Override
            public Object loadUserBySAML(SAMLCredential samlCredential) throws UsernameNotFoundException {
                return new SAMLUserDetails(samlCredential) {
                    @Override
                    public Map<String, String> getAttributes() {
                    	Map<String,String> map = samlCredential.getAttributes().stream()
                                .collect(Collectors.toMap(Attribute::getName, this::getValue));
                    	System.out.println("getAttributes----->"+map);
                        return map;
                    }

                    private String getValue(Attribute attribute) {
                        return Optional.ofNullable(getAttribute(attribute.getName())).orElse("");
                    }
                };
            }
        };
    }*/
}
