package com.jimetevenard.snoopix.validation.step;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

import com.jimetevenard.snoopix.validation.ValidationError;
import com.jimetevenard.snoopix.validation.ValidationError.ValidationErrorLevel;
import com.jimetevenard.snoopix.validation.ValidationErrorList;
import com.jimetevenard.snoopix.validation.ValidationStep;

public class SingleValidationStep implements ValidationStep {

	private String href;
	private static final ValidationError NOT_YET_IMPLEMENTED = new ValidationError(ValidationErrorLevel.WARNING,
			"XML SingleValidation Step not yet implemented");

	public SingleValidationStep(String href) {
		super();
		this.href = href;
	}


	@Override
	public ValidationErrorList process(File file) {
		ValidationErrorList list = new ValidationErrorList(1);
		switch (href.substring(href.length() - 3).toLowerCase()) {
		case "dtd":
			list.add(new ValidationError(ValidationErrorLevel.WARNING, "DTD processing not yet implemented"));
			break;
		case "rnc":
			list.add(new ValidationError(ValidationErrorLevel.WARNING,
					"Conpact RelaxNG processing not yet implemented"));
			break;

		default:
			list.add(NOT_YET_IMPLEMENTED);
			break;
		}

		return list;
	}

	@Override
	public String toString() {
		return "SingleValidationStep href=" + href + "]";
	}

}
