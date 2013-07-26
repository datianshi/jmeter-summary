package com.shaozhending.performance.summary.jmeter.output;

import java.util.Formatter;
import java.util.Locale;

import org.apache.jmeter.util.Calculator;

import com.shaozhending.performance.summary.jmeter.SummaryResultWrapper;
/**
 * This is the console output to output a formatted string.
 * 
 * @author Shaozhen Ding
 *
 */
public class ConsoleOutput implements Output{
	
	private final static String CONSOLE_FORMATTER = "%1$-30s %2$-10d %3$-10.2f %4$-10s %5$-10s %6$-10.2f %7$-10.2f %8$-15.2f %9$-10.2f %10$-10.2f%n";

	@Override
	public String output(SummaryResultWrapper result) {
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("%1$-30s %2$-10s %3$-10s %4$-10s %5$-10s %6$-10s %7$-10s %8$-15s %9$-10s %10$-10s%n", "Label", "#Samples", "Average", "Min", "Max", "Std.Dev", "Error%", "Throughput/sec", "KB/sec", "Avg.Bytes");
		for(String label : result.getLabels()){
			Calculator row = result.getRow(label);
			formatter.format(CONSOLE_FORMATTER, label, row.getCount(), row.getMean(), row.getMin(), row.getMax(), row.getStandardDeviation(), row.getErrorPercentage(), row.getRate(), row.getKBPerSecond(), row.getAvgPageBytes());
		}
		return sb.toString();
	}

}
