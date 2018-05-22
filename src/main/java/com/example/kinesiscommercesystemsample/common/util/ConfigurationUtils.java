package com.example.kinesiscommercesystemsample.common.util;

import com.amazonaws.ClientConfiguration;

public class ConfigurationUtils {

	public static ClientConfiguration getClientConfigWithUserAgent(String applicationName) {
		final ClientConfiguration config = new ClientConfiguration();
		final StringBuilder userAgent = new StringBuilder(ClientConfiguration.DEFAULT_USER_AGENT);

		// Separate fields of the user agent with a space
		userAgent.append(" ");
		// Append the application name followed by version number of the sample
		userAgent.append(applicationName);
		userAgent.append("/");
		userAgent.append("1");

		config.setUserAgentPrefix(userAgent.toString());
		config.setUserAgentSuffix(null);

		return config;
	}

}
