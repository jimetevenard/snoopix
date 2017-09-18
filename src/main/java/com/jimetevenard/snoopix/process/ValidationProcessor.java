package com.jimetevenard.snoopix.process;

import java.io.File;

import com.jimetevenard.snoopix.rule.Rule;
import com.jimetevenard.snoopix.validation.ValidationResult;

public class ValidationProcessor {

	public static ValidationResult applyRule(File file, Rule rule) {
		// TODO APPLY
		return new ValidationResult(rule, file);
		
	}

}
