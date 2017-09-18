package com.jimetevenard.snoopix.rule;

import java.util.Comparator;
import java.util.TreeSet;

public class RuleSet extends TreeSet<Rule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static RuleSet defaultRuleSet;



	public RuleSet() {
		super(new DescRuleComparator());
	}

	public RuleSet defaultRuleSet() {
		if (defaultRuleSet == null) {
			defaultRuleSet = new RuleSet();
			// TODO implement default rules
		}
		return defaultRuleSet;
	}



	private static class DescRuleComparator implements Comparator<Rule> {

		@Override
		public int compare(Rule o1, Rule o2) {
			// We stock Rules in decreasing order
			int prio = o2.getPriority() - o1.getPriority();
			return prio == 0 ? -1 : prio;
		}

	}


}
