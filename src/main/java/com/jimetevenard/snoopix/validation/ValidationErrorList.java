package com.jimetevenard.snoopix.validation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.jimetevenard.snoopix.validation.ValidationError.ValidationErrorLevel;

public class ValidationErrorList extends ArrayList<ValidationError> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationErrorList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationErrorList(Collection<? extends ValidationError> c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	public ValidationErrorList(int initialCapacity) {
		super(initialCapacity);
		// TODO Auto-generated constructor stub
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
		if (this.isEmpty()) {
			return true;
		}
		for (ValidationError er : this) {
			if (er.getLevel().compareTo(level) >= 0) {
				return false;
			}
		}
		return true; // but there is some warnings...
	}



}
