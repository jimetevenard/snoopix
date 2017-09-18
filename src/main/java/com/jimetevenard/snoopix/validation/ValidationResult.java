package com.jimetevenard.snoopix.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jimetevenard.snoopix.rule.Rule;

public class ValidationResult {

	private Rule ruleApplied;
	private File fileProcessed;
	private List<ValidationError> errors;

	public ValidationResult(Rule ruleApplied, File fileProcessed) {
		super();
		this.ruleApplied = ruleApplied;
		this.fileProcessed = fileProcessed;
	}

	public boolean isSucces() {
		return errors == null || errors.isEmpty();
	}

	public Rule getRuleApplied() {
		return ruleApplied;
	}

	public File getFileProcessed() {
		return fileProcessed;
	}

	public List<ValidationError> getErrors() {
		return errors != null ? errors : new ArrayList<>();
	}

	public void addError(ValidationError error) {
		if (errors == null) {
			// for performance purpose, we instanciate the list only if there is
			// actually errors
			errors = new ArrayList<>();
		}
		errors.add(error);
	}

	public static ValidationResult noneMatching(File fileProcessed) {
		// TODO Auto-generated method stub
		return new ValidationResult(null, fileProcessed);
	}

	@Override
	public String toString() {
		return "ValidationResult [ruleApplied=" + ruleApplied + ", fileProcessed=" + fileProcessed + ", errors="
				+ errors + "]";
	}

}
