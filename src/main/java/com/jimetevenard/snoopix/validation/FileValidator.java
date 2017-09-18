package com.jimetevenard.snoopix.validation;

import java.io.File;

import com.jimetevenard.snoopix.process.ValidationProcessor;
import com.jimetevenard.snoopix.rule.Rule;
import com.jimetevenard.snoopix.rule.RuleSet;

public class FileValidator {

	public static ValidationResult processFile(File file, RuleSet rules) {
		for (Rule rule : rules) {
			if (rule.match(file)) {
				return ValidationProcessor.applyRule(file, rule);
			}
		}

		return ValidationResult.noneMatching(file);
	}

}
