package com.shaozhending.performance.summary.jmeter.output;

import com.shaozhending.performance.summary.jmeter.SummaryResultWrapper;

/**
 * Interface of Output
 * 
 * @author Shaozhen Ding
 *
 */
public interface Output {
	
	public String output(SummaryResultWrapper result); 
}
