package com.jimetevenard.snoopix.rule;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.jimetevenard.snoopix.validation.ValidationStep;

public class Rule implements Comparable<Rule> {

	// the directory within the rule where defined
	private File directory;

	// the native priority of the rule
	private int priority;

	// the pattern for wich the name will match or not
	private String fileNamePattern;

	// validations to process
	private List<ValidationStep> validationSteps;

	public Rule() {
		validationSteps = new ArrayList<>();
	}

	public void computePriority(int depht) {
		int prio = depht * 100;
		if (!fileNamePattern.contains("*")) {
			prio = prio + 50;
		}

		// TODO chemins de reps...

		this.priority = this.priority + prio;
	}

	public boolean match(File file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(Rule o) {
		// TODO Auto-generated method stub
		return this.getPriority() - o.getPriority();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((directory == null) ? 0 : directory.hashCode());
		result = prime * result + ((fileNamePattern == null) ? 0 : fileNamePattern.hashCode());
		result = prime * result + priority;
		result = prime * result + ((validationSteps == null) ? 0 : validationSteps.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (directory == null) {
			if (other.directory != null)
				return false;
		} else if (!directory.equals(other.directory))
			return false;
		if (fileNamePattern == null) {
			if (other.fileNamePattern != null)
				return false;
		} else if (!fileNamePattern.equals(other.fileNamePattern))
			return false;
		if (priority != other.priority)
			return false;
		if (validationSteps == null) {
			if (other.validationSteps != null)
				return false;
		} else if (!validationSteps.equals(other.validationSteps))
			return false;
		return true;
	}

	public File getDirectory() {
		return directory;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getFileNamePattern() {
		return fileNamePattern;
	}

	public List<ValidationStep> getValidationSteps() {
		return validationSteps;
	}

	public void setDirectory(File directory) {
		this.directory = directory;
	}

	public void setFileNamePattern(String fileNamePattern) {
		this.fileNamePattern = fileNamePattern;
	}

	@Override
	public String toString() {
		return "Rule [directory=" + directory + ", priority=" + priority + ", fileNamePattern=" + fileNamePattern
				+ ", validationSteps=" + validationSteps + "]";
	}


}
