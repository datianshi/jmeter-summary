package com.shaozhending.performance.summary.shell;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.plugin.support.DefaultBannerProvider;
import org.springframework.shell.support.util.OsUtils;
import org.springframework.stereotype.Component;

/**
 * @author Shaozhen Ding
 *
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MyBannerProvider extends DefaultBannerProvider 
				implements CommandMarker {

	@CliCommand(value = { "version" }, help = "Displays current CLI version")
	public String getBanner() {
		StringBuffer buf = new StringBuffer();
		buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
		buf.append("*                                     *"+ OsUtils.LINE_SEPARATOR);
		buf.append("*            Jmeter Summary           *" +OsUtils.LINE_SEPARATOR);
		buf.append("*                                     *"+ OsUtils.LINE_SEPARATOR);
		buf.append("=======================================" + OsUtils.LINE_SEPARATOR);
		buf.append("Version:" + this.getVersion());
		return buf.toString();

	}

	public String getVersion() {
		return "0.0.1";
	}

	public String getWelcomeMessage() {
		return "Welcome to Jmeter Summary CLI";
	}
	
	@Override
	public String name() {
		return "Jmeter Summary Banner";
	}
}