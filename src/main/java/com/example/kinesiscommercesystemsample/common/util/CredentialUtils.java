package com.example.kinesiscommercesystemsample.common.util;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;

public class CredentialUtils {

	public static AWSCredentialsProvider getCredentialsProvider() throws Exception {

		AWSCredentialsProvider credentialsProvider = new EnvironmentVariableCredentialsProvider();

		return credentialsProvider;
	}
}
