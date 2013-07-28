package com.shaozhending.performance.summary;

import java.io.IOException;
import java.util.Properties;

import org.apache.jorphan.logging.LoggingManager;
import org.springframework.shell.Bootstrap;

public class Main {
	
	public static void main(String[] args) throws IOException{
		
		/**
		 * Add custom properties to suppress jmeter libraries logging
		 */
		Properties p = new Properties();

		p.load(Main.class.getClassLoader()
		        .getResourceAsStream("META-INF/logging.properties"));
		LoggingManager.initializeLogging(p);
		
		Bootstrap.main(args);
	}
}
