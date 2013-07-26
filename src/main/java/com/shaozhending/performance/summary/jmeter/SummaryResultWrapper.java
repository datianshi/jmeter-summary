package com.shaozhending.performance.summary.jmeter;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.apache.jmeter.save.TestResultWrapper;
import org.apache.jmeter.util.Calculator;

/**
 * A wrapper of xstream result to hold all the calculation results
 * 
 * @author Shaozhen Ding
 *
 */
public class SummaryResultWrapper extends TestResultWrapper{
	
	private final LinkedHashMap<String, Calculator> rows;
	
	public SummaryResultWrapper(LinkedHashMap<String, Calculator> rows) {
		super();
		this.rows = rows;
	}
	
	public SummaryResultWrapper(){
		this.rows = new LinkedHashMap<String, Calculator>();
	}

	public void putRow(String label, Calculator row){
		rows.put(label, row);
	}
	
	public Calculator getRow(String label){
		return rows.get(label);
	}
	
	public Collection<String> getLabels(){
		return rows.keySet();
	}
	
}
