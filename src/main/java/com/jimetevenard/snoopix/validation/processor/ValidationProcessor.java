package com.jimetevenard.snoopix.validation.processor;

import java.io.File;

import com.jimetevenard.snoopix.validation.ValidationErrorList;

public abstract class ValidationProcessor {

	public abstract ValidationErrorList validate(File file);


}
