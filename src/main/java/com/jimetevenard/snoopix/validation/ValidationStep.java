package com.jimetevenard.snoopix.validation;

import java.io.File;
import java.util.Collection;

public interface ValidationStep {

	public ValidationErrorList process(File file);

}
