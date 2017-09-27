package com.jimetevenard.snoopix.validation.step;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.jimetevenard.snoopix.validation.ValidationError;
import com.jimetevenard.snoopix.validation.ValidationError.ValidationErrorLevel;
import com.jimetevenard.snoopix.validation.ValidationErrorList;
import com.jimetevenard.snoopix.validation.ValidationStep;

public class ChooseStep implements ValidationStep {


	private List<ValidationStep> childValidations;

	private static final ValidationError SKIP = new ValidationError(ValidationErrorLevel.INFO,
			"Choose : A step just returned valid, so we skip the others");

	public ChooseStep() {
		super();
		this.childValidations = new ArrayList<>();
	}

	@Override
	public ValidationErrorList process(File file) {
		
		ValidationErrorList chooseErrors = new ValidationErrorList();
		
		for (ValidationStep validationStep : childValidations) {
			ValidationErrorList stepResult = validationStep.process(file);
			chooseErrors.addAll(stepResult);
			if(stepResult.isValid()){
				chooseErrors.add(SKIP);
				break;
			}
		}
		
		return chooseErrors;

	}

	public List<ValidationStep> getChildValidations() {
		return childValidations;
	}

	public boolean addChildStep(ValidationStep valitionStep) {
		return this.childValidations.add(valitionStep);
	}

	@Override
	public String toString() {
		return "ChooseStep [childValidations=" + childValidations + "]";
	}

}
