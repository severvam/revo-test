package com.revo.core;

import lombok.Value;

@Value
public class OperationResult {

	private final Status status;
	private final String message;

	public enum Status {
		OK, FAILED
	}

	public static OperationResult create(boolean ok, String message) {
		return new OperationResult(ok ? Status.OK : Status.FAILED, message);
	}

}
