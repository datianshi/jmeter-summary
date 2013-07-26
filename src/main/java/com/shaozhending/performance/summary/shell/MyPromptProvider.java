package com.shaozhending.performance.summary.shell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.plugin.support.DefaultPromptProvider;
import org.springframework.stereotype.Component;

/**
 * @author Shaozhen Ding
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyPromptProvider extends DefaultPromptProvider {

	@Override
	public String getPrompt() {
		return "jmeter-summary>";
	}

	
	@Override
	public String name() {
		return "My prompt provider";
	}

}
