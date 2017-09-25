package com.jimetevenard.snoopix.validation.step;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import com.jimetevenard.snoopix.validation.ValidationError;
import com.jimetevenard.snoopix.validation.ValidationError.ValidationErrorLevel;
import com.jimetevenard.snoopix.validation.ValidationStep;

public class SingleValidationStep implements ValidationStep {

	private String href;

	public SingleValidationStep(String href) {
		super();
		this.href = href;
	}


	@Override
	public Collection<ValidationError> process(File file) {
		// TODO implement validation
		return Arrays.asList(new ValidationError[] {
				new ValidationError(ValidationErrorLevel.WARNING, "Single Validation Step not yet implemented") });
	}

	@Override
	public String toString() {
		return "SingleValidationStep href=" + href + "]";
	}

}
