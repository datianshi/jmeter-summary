package com.shaozhending.performance.summary.shell;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import com.shaozhending.performance.summary.jmeter.SummaryResultWrapper;
import com.shaozhending.performance.summary.jmeter.output.OutPutFactory;
import com.shaozhending.performance.summary.jmeter.output.OutPutFactory.OutPutType;
import com.thoughtworks.xstream.XStream;
/**
 * Spring shell of jmeter "summary" command
 * 
 * jmeter-summary>summary --input fileName 
 *  
 * @author Shaozhen Ding
 *
 */

@Component
public class JmeterSummaryCommands implements CommandMarker {
	
	private boolean simpleCommandExecuted = false;
	
	@Autowired
	OutPutFactory factory;
	
	@Autowired
	XStream xstream;
	
	@CliCommand(value= "summary")
	public String simple(@CliOption(key = {"input"}, mandatory = true, help= "The input file") final String input,
			@CliOption(key = {"output"}, mandatory = false, help= "The output file, by default output to console") final String output){
		OutPutType type = getType(output); 
		
		File file = new File(input);
		SummaryResultWrapper result = (SummaryResultWrapper) xstream.fromXML(file);
		return factory.createOutput(type).output(result);
	}

	protected OutPutType getType(final String output) {
		/*
		 * Tell from the file extension
		 */
		OutPutType type = null;
		if(output != null){
			try{
				type = OutPutType.valueOf(output.substring(output.lastIndexOf(".") + 1).toUpperCase());
				
			}
			catch(Exception e){
				// eat the exception
			}
		}
		type = type == null ? OutPutType.CONSOLE : type;
		return type;
	}
}