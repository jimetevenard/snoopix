package com.jimetevenard.snoopix.validation;

public class ValidationError {
	public static enum ValidationErrorLevel {
		INFO, WARNING, ERROR;
	}

	private ValidationErrorLevel level;
	private String message;

	ValidationError(ValidationErrorLevel level, String message) {
		super();
		this.level = level;
		this.message = message;
	}

	public ValidationErrorLevel getLevel() {
		return level;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "ValidationError [level=" + level + ", message=" + message + "]";
	}

}
