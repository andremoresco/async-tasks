package com.axcient.gsuitesafe.gmailbackup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class GmailBackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(GmailBackupApplication.class, args);
	}

}
