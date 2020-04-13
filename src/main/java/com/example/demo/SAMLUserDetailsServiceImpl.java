package com.example.demo;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Service;
@Service
public class SAMLUserDetailsServiceImpl implements SAMLUserDetailsService {

	@Override
	public Object loadUserBySAML(SAMLCredential credential) throws UsernameNotFoundException {
		String userName = credential.getNameID().getValue();
        String email     = credential.getAttributeAsString("email");
        String firstName = credential.getAttributeAsString("FirstName");
        String lastName  = credential.getAttributeAsString("lastName");
        String city = credential.getAttributeAsString("city");
        System.out.println("attributes-->"+userName+" "+firstName+" "+lastName+" "+city+" "+email);
		return null;
	}

}
