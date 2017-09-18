package com.jimetevenard.snoopix.validation;

import java.util.List;
import java.util.Map;

public class ResultsReport {

	private Map<ValidationError.ValidationErrorLevel, List<ValidationResult>> errors;
	private List<ValidationResult> success;
	private List<ValidationResult> skipped;
	private boolean computed;
	private int errorsLength;

	public void compteReport() {
		// TODO implement : place results in appropriate collection

		// then
		// TODO computed = true
	}

	public String quickReport(){
		if (!computed) {
			throw new IllegalStateException("Report is not yet computed !");
		}
		
		return "REPORT :: Errors : " + errorsLength + " Succes " + success.size() + " Skipped files " + skipped.size();
	}

	public Map<ValidationError.ValidationErrorLevel, List<ValidationResult>> getErrors() {
		if (!computed) {
			throw new IllegalStateException("Report is not yet computed !");
		}
		return errors;
	}

	public List<ValidationResult> getSuccess() {
		if (!computed) {
			throw new IllegalStateException("Report is not yet computed !");
		}
		return success;
	}

	public List<ValidationResult> getSkipped() {
		if (!computed) {
			throw new IllegalStateException("Report is not yet computed !");
		}
		return skipped;
	}

	public boolean isComputed() {
		return computed;
	}

	@Override
	public String toString() {
		return "ResultsReport [errors=" + errors + ", success=" + success + ", skipped=" + skipped + ", computed="
				+ computed + "]";
	}

}
