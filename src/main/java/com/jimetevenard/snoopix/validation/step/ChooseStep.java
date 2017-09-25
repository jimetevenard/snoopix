package com.jimetevenard.snoopix.validation.step;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.jimetevenard.snoopix.validation.ValidationError;
import com.jimetevenard.snoopix.validation.ValidationError.ValidationErrorLevel;
import com.jimetevenard.snoopix.validation.ValidationStep;

public class ChooseStep implements ValidationStep {


	private List<ValidationStep> childValidations;

	public ChooseStep() {
		super();
		this.childValidations = new ArrayList<>();
	}

	@Override
	public Collection<ValidationError> process(File file) {
		// TODO implement validation
		return Arrays.asList(new ValidationError[] {
				new ValidationError(ValidationErrorLevel.WARNING, "ChooseStep not yet implemented") });
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
