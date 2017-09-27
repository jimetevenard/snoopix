package com.jimetevenard.snoopix.validation.step;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import com.jimetevenard.snoopix.validation.ValidationError;
import com.jimetevenard.snoopix.validation.ValidationError.ValidationErrorLevel;
import com.jimetevenard.snoopix.validation.ValidationErrorList;
import com.jimetevenard.snoopix.validation.ValidationStep;

public class AutoValidation implements ValidationStep {

	private static final ValidationError NOT_YET_IMPLEMENTED = new ValidationError(ValidationErrorLevel.WARNING,
			"AutoValition Step not yet implemented");

	public AutoValidation() {
		super();

	}

	@Override
	public ValidationErrorList process(File file) {
		// TODO implement validation
		ValidationErrorList list = new ValidationErrorList(1);
		list.add(NOT_YET_IMPLEMENTED);
		return list;

	}

	@Override
	public String toString() {
		return "AutoValidation []";
	}

}
