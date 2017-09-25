package com.jimetevenard.snoopix.validation;

import java.io.File;
import java.util.Collection;

public interface ValidationStep {

	public Collection<ValidationError> process(File file);

}
