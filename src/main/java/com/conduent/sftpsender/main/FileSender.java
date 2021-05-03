package com.conduent.sftpsender.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@Slf4j
//@ConditionalOnNotWebApplication
public class FileSender implements CommandLineRunner {


	@Override
	public void run(String... args) throws Exception {
		log.info("EXECUTING : command line runner");
		main(args);
	}

	public void main(String[] args) throws Exception {

		log.info("STARTING THE APPLICATION");

		System.out.println("\nSuccess! \n");

		log.info("APPLICATION FINISHED");


	}
}
