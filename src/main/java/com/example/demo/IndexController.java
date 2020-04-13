package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.opensaml.saml2.core.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.security.saml.userdetails.SAMLUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@Autowired
    SAMLUserDetailsService userDetailsService;
	
	@Autowired
	UserRepository userRepository;
	
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @PostMapping("/formLogin")
    public String formLogin(HttpServletRequest req,Model model) {
    	String userName = req.getParameter("Username");
    	String password = req.getParameter("password");
    	User user= userRepository.findUser(userName, password);
    	if(user != null) {
        	model.addAttribute("userName", user.getUsername());
        	model.addAttribute("firstName", user.getFirstName());

        	model.addAttribute("lastName", user.getLastName());

            
    	}
    	
    	
    	
    	return "home";
    }
    @RequestMapping("/saml")
    public String landing(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	SAMLCredential credentials= (SAMLCredential)auth.getCredentials();
    	
    	List<Attribute> list  = credentials.getAttributes();
    	for(Attribute attr: list) {
    		System.out.println("Names -"+attr.getName());
    		model.addAttribute(attr.getName(), credentials.getAttributeAsString(attr.getName()));
    	}
      
        String login     = credentials.getAttributeAsString("userName");
        String firstName = credentials.getAttributeAsString("firstName");
        String lastName  = credentials.getAttributeAsString("lastName");
        
    	User user = new User();
    	user.setUsername(login);
    	user.setFirstName(firstName);
    	user.setLastName(lastName);
    	user.setImported(true);
    	
    	userRepository.save(user);
    	
        return "home";
    }
  
}