package com.shaozhending.performance.summary.shell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultHistoryFileNameProvider;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Shaozhen Ding
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyHistoryFileNameProvider extends DefaultHistoryFileNameProvider {

	public String getHistoryFileName() {
		return "my.log";
	}

	@Override
	public String name() {
		return "My history file name provider";
	}
	
}
