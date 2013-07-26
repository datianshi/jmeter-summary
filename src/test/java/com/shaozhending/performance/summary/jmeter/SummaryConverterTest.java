package com.shaozhending.performance.summary.jmeter;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

public class SummaryConverterTest {
	
	private final URL testFile = this.getClass().getResource("/test.jtl");
	
	@Test
	public void check(){
		System.out.println(testFile.getFile());
		File file = new File(testFile.getFile());
		assertThat(file.exists(), is(true));
		
		
	}
	

	@Test
	public void test() throws Exception {
		XStream xstream = new XStreamWrapper(new PureJavaReflectionProvider());
		
		SummaryResultWrapper result = (SummaryResultWrapper) xstream.fromXML(testFile);
		
		assertThat(result.getSampleResults().size(), is(2));
		assertThat(result.getRow("HTTP Request").getMax(), is(304l));
		assertThat(result.getRow("Total").getMax(), is(304l));
		
	
	}

}
