package com.shaozhending.performance.summary.jmeter.output;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedHashMap;

import org.apache.jmeter.util.Calculator;
import org.junit.Test;

import com.shaozhending.performance.summary.jmeter.SummaryResultWrapper;

public class ConsoleOutputTest {
	@Test
	public void test(){
		ConsoleOutput output = new ConsoleOutput();
		LinkedHashMap<String, Calculator> colls = new LinkedHashMap<String, Calculator>();
		SummaryResultWrapper result = new SummaryResultWrapper(colls);
		
		Calculator c = mock(Calculator.class);
		when(c.getLabel()).thenReturn("HTTP Request");
		when(c.getCount()).thenReturn(10);
		when(c.getMax()).thenReturn(10000l);
		when(c.getMin()).thenReturn(500l);
		when(c.getErrorPercentage()).thenReturn(10.2);
		when(c.getRate()).thenReturn(new Double("32.33333333"));
		when(c.getKBPerSecond()).thenReturn(new Double("12.344444"));
		when(c.getMean()).thenReturn(new Double("345.78907"));
		when(c.getAvgPageBytes()).thenReturn(new Double("12.5555555"));
		when(c.getBytesPerSecond()).thenReturn(new Double("12.7555"));
		when(c.getStandardDeviation()).thenReturn(new Double("90.77777777"));
		
		colls.put("HTTP Request", c);
		
		String expected = "Label                          #Samples   Average    Min        Max        Std.Dev    Error%     Throughput/sec  KB/sec     Avg.Bytes \nHTTP Request                   10         345.79     500        10000      90.78      10.20      32.33           12.34      12.56     \n";
		
		assertThat(output.output(result), is(equalTo(expected)));
	}
}
