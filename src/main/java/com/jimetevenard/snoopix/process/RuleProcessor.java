package com.jimetevenard.snoopix.process;

import java.io.File;

import com.jimetevenard.snoopix.rule.Rule;
import com.jimetevenard.snoopix.validation.ValidationResult;
import com.jimetevenard.snoopix.validation.ValidationStep;

public class RuleProcessor {

	public static ValidationResult applyRule(File file, Rule rule) {

		ValidationResult result = new ValidationResult(rule, file);

		for (ValidationStep step : rule.getValidationSteps()) {
			result.addErrors(step.process(file));
		}

		return result;
		
	}

}
