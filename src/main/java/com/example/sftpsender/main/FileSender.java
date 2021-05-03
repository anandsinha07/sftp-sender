package com.example.sftpsender.main;

import com.example.sftpsender.services.SftpOutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;



@Component
@Slf4j
//@ConditionalOnNotWebApplication
public class FileSender implements CommandLineRunner {

	@Autowired
	private SftpOutboundService sftpOutBoundService;
	
	@Override
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");
		main(args);
	}

	public void main(String[] args) throws Exception {

		log.info("STARTING THE APPLICATION");
		sftpOutBoundService.uploadToSftp("src/main/resources/test.txt");

		System.out.println("\nSuccess! \n");

		log.info("APPLICATION FINISHED");


	}
}
