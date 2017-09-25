package com.jimetevenard.snoopix.validation;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.jimetevenard.snoopix.rule.Rule;
import com.jimetevenard.snoopix.validation.ValidationError.ValidationErrorLevel;

public class ValidationResult {

	private Rule ruleApplied;
	private File fileProcessed;
	private List<ValidationError> errors;

	public ValidationResult(Rule ruleApplied, File fileProcessed) {
		super();
		this.ruleApplied = ruleApplied;
		this.fileProcessed = fileProcessed;
	}

	/**
	 * 
	 * @return <b>false</b> if there at least one error whith the level
	 *         ValidationErrorLevel.ERROR, <b>true</b> otherwhise.
	 * 
	 * @see ValidationErrorLevel
	 * 
	 */
	public boolean isValid() {
		return isValid(ValidationErrorLevel.ERROR);
	}

	/**
	 * 
	 * @return false if there at least one error witch level is stronger that
	 *         the level provided in parameter.
	 * 
	 * @param level
	 *            : max error level accepted to be valid
	 * 
	 * @see ValidationErrorLevel
	 */
	public boolean isValid(ValidationErrorLevel level) {
		if (errors == null || errors.isEmpty()) {
			return true;
		}
		for (ValidationError er : errors) {
			if (er.getLevel().compareTo(level) >= 0) {
				return false;
			}
		}

		return true; // but there is some warnings...
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

	public void addErrors(Collection<ValidationError> errors) {
		if (this.errors == null) {
			// for performance purpose, we instanciate the list only if there is
			// actually errors
			this.errors = new ArrayList<>();
		}
		this.errors.addAll(errors);
	}

	public void addErrors(ValidationError... errors) {
		if (this.errors == null) {
			// for performance purpose, we instanciate the list only if there is
			// actually errors
			this.errors = new ArrayList<>();
		}
		this.errors.addAll(Arrays.asList(errors));
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
