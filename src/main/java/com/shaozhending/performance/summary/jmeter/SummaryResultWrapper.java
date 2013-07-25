package com.shaozhending.performance.summary.jmeter;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.apache.jmeter.save.TestResultWrapper;
import org.apache.jmeter.util.Calculator;

public class SummaryResultWrapper extends TestResultWrapper{
	
	private final Map<String, Calculator> rows = new TreeMap<String, Calculator>();
	
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
