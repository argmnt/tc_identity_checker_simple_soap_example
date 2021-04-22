package com.argmnt.turkish_identity_checker.config;

import com.argmnt.turkish_identity_checker.identity_domain.TCIdentityNumberValidationClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class Config {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.argmnt.turkish_identity_checker.identity_domain");
        return marshaller;
    }

    @Bean
    public TCIdentityNumberValidationClient countryClient(Jaxb2Marshaller marshaller) {
        TCIdentityNumberValidationClient client = new TCIdentityNumberValidationClient();
        client.setDefaultUri("http://tckimlik.nvi.gov.tr/WS");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
