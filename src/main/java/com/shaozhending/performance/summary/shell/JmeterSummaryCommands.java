package com.shaozhending.performance.summary.shell;

import java.io.File;

import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.util.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

import com.shaozhending.performance.summary.jmeter.SummaryResultWrapper;
import com.thoughtworks.xstream.XStream;

@Component
public class JmeterSummaryCommands implements CommandMarker {
	
	private boolean simpleCommandExecuted = false;
	
	@Autowired
	XStream xstream;
	
//	@CliAvailabilityIndicator({"hw simple"})
//	public boolean isSimpleAvailable() {
//		//always available
//		return false;
//	}
	
	@CliCommand(value= "summary")
	public String simple(@CliOption(key = {"input"}, mandatory = true, help= "The input file") final String input){
		File file = new File(input);
		SummaryResultWrapper result = (SummaryResultWrapper) xstream.fromXML(file);
		StringBuilder st = new StringBuilder();
		st.append("Label,     ").append("Max Response Time").append("\n");
		for(String label : result.getLabels()){
			Calculator c = result.getRow(label);
			st.append(label).append("  " + c.getMax()).append("ms\n");
		}
		return st.toString();
	}
	
//	@CliAvailabilityIndicator({"hw complex", "hw enum"})
//	public boolean isComplexAvailable() {
//		if (simpleCommandExecuted) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//		
//	@CliCommand(value = "hw simple", help = "Print a simple hello world message")
//	public String simple(
//		@CliOption(key = { "message" }, mandatory = true, help = "The hello world message") final String message,
//		@CliOption(key = { "location" }, mandatory = false, help = "Where you are saying hello", specifiedDefaultValue="At work") final String location) {		
//		simpleCommandExecuted = true;
//		return "Message = [" + message + "] Location = [" + location + "]";
//	}
//	
//	@CliCommand(value = "hw complex", help = "Print a complex hello world message")
//	public String hello(
//		@CliOption(key = { "message" }, mandatory = true, help = "The hello world message") final String message,
//		@CliOption(key = { "name1"}, mandatory = true, help = "Say hello to the first name") final String name1,
//		@CliOption(key = { "name2" }, mandatory = true, help = "Say hello to a second name") final String name2,
//		@CliOption(key = { "time" }, mandatory = false, specifiedDefaultValue="now", help = "When you are saying hello") final String time,
//		@CliOption(key = { "location" }, mandatory = false, help = "Where you are saying hello") final String location) {		
//		return "Hello " + name1 + " and " + name2 + ". Your special message is "  + message + ". time=[" + time + "] location=[" + location + "]";
//	}
//	
//	@CliCommand(value = "hw enum", help = "Print a simple hello world message from an enumerated value")
//	public String eenum(
//		@CliOption(key = { "message" }, mandatory = true, help = "The hello world message") final MessageType message){		
//		return "Hello.  Your special enumerated message is " + message;
//	}
//	
//	enum MessageType {		
//		Type1("type1"),
//		Type2("type2"),
//		Type3("type3");
//		
//		private String type;
//		
//		private MessageType(String type){
//			this.type = type;
//		}
//		
//		public String getType(){
//			return type;
//		}
//	}
}