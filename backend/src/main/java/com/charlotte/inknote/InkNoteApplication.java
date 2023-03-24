package com.charlotte.inknote;

import com.charlotte.inknote.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class InkNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(InkNoteApplication.class, args);
	}

}
