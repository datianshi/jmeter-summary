package com.shaozhending.performance.summary.jmeter.output;

/**
 * Create the Output Object based on the input 
 * 
 * @author Shaozhen Ding
 *
 */
public class OutPutFactory{
	
	public Output createOutput(OutPutType type){
		
		if(type == OutPutType.CONSOLE){
			return new ConsoleOutput();
		}
		else if(type == OutPutType.CSV){
			return new CsvOutput();
		}
		else{
			return null;
		}
	}
	
	public static enum OutPutType{
		CONSOLE, 
		CSV
	}

}
