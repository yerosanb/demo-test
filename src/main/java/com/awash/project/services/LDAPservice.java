package com.awash.project.services;

import java.util.List;

import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Service;

@Service
public class LDAPservice {
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.base}")
    private String ldapBase;

  	
 
public List<Attributes> getADUserDetails(String username, String password) {
	
    ldapTemplate.setContextSource(contextSource(username, password));

    // Define search controls
    SearchControls searchControls = new SearchControls();
    searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

    List<Attributes> results = ldapTemplate.search("", "(samaccountname=" + username + ")",
            searchControls, (AttributesMapper<Attributes>) attrs -> attrs);

    return results;
}

 private LdapContextSource contextSource(String username, String password) {
        LdapContextSource contextSource = new LdapContextSource();
        //for production
        contextSource.setUrl(ldapUrl);
        contextSource.setBase(ldapBase);
        contextSource.setUserDn(username+"@awash.local"); 
        //for test 
       /* contextSource.setUrl("ldap://awashtest.local");
        contextSource.setBase("dc=awashtest,dc=local");
        contextSource.setUserDn(username+"@awashtest.local");*/
        contextSource.setPassword(password);
        contextSource.setReferral("follow"); // Enable referral following
        contextSource.afterPropertiesSet(); // required
       
        return contextSource;
    }
}
