package com.shaozhending.performance.summary.shell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.shaozhending.performance.summary.jmeter.output.OutPutFactory;
import com.shaozhending.performance.summary.jmeter.output.OutPutFactory.OutPutType;

public class JmeterSummaryCommandsTest {

	@Test
	public void testGetCorrectType() {
		JmeterSummaryCommands command = new JmeterSummaryCommands();
		assertThat(command.getType("something.csv"), is(OutPutType.CSV));
		assertThat(command.getType(null), is(OutPutType.CONSOLE));
		assertThat(command.getType("asdf"), is(OutPutType.CONSOLE));
		assertThat(command.getType(""), is(OutPutType.CONSOLE));
	}

}
