package com.argmnt.turkish_identity_checker;

import com.argmnt.turkish_identity_checker.identity_domain.TCIdentityNumberValidationClient;
import com.argmnt.turkish_identity_checker.identity_domain.TCKimlikNoDogrula;
import com.argmnt.turkish_identity_checker.identity_domain.TCKimlikNoDogrulaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TurkishIdentityCheckerApplication {

	private static final Logger log = LoggerFactory.getLogger(TurkishIdentityCheckerApplication.class);

	private final String name;

	private final String surname;

	private final int birthYear;

	private final long identityNumber;

	public TurkishIdentityCheckerApplication(@Value("${app.name}") String name, @Value("${app.surname}") String surname,
											 @Value("${app.birthYear}") int birthYear, @Value("${app.identityNumber}") long identityNumber) {
		this.name = name;
		this.surname = surname;
		this.birthYear = birthYear;
		this.identityNumber = identityNumber;
	}

	public static void main(String[] args) {
		SpringApplication.run(TurkishIdentityCheckerApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(TCIdentityNumberValidationClient client) {
		return args -> {
			TCKimlikNoDogrula tcKimlikNoDogrula = new TCKimlikNoDogrula();
			tcKimlikNoDogrula.setAd(name);
			tcKimlikNoDogrula.setSoyad(surname);
			tcKimlikNoDogrula.setDogumYili(birthYear);
			tcKimlikNoDogrula.setTCKimlikNo(identityNumber);

			TCKimlikNoDogrulaResponse response = client.isTCNoValid(tcKimlikNoDogrula);
			log.info(String.valueOf("Identity checking result: " + response.isTCKimlikNoDogrulaResult()));
		};
	}

}
