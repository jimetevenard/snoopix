package com.jimetevenard.snoopix.rule;

import java.io.File;

import com.jimetevenard.snoopix.explorer.Strategy;
import com.jimetevenard.snoopix.ruleParserImp.DomRuleParser;

public class RuleSource {

	private Strategy strategy;
	private RuleSet uniqueRuleSet;
	private RuleSet rootRuleSet;
	private File rootDir;
	private RuleParser ruleParser;

	RuleSource(Strategy strategy, File rootDir) {
		super();
		this.strategy = strategy;
		this.rootDir = rootDir;
		this.ruleParser = new DomRuleParser();

		if(strategy.getUniqueRulesFile() != null){
			uniqueRuleSet = ruleParser.parseRulesFile(strategy.getUniqueRulesFile());
		} else {
			rootRuleSet = ruleParser.parseRulesFile(findRuleFile(rootDir));
		}
	}

	private File findRuleFile(File dir) {
		return new File(dir.getAbsolutePath() + '/' + strategy.getRulesFileName());
	}

	public RuleSet ruleSet(File directory) {
		
		if(strategy.getUniqueRulesFile() != null){
			return uniqueRuleSet;
		} else {
			RuleSet ruleSet = rootRuleSet;
			if (strategy.isFetchRulesRecursively() && !rootDir.equals(directory)) {
				ruleSet.addAll(ruleParser.parseRulesFile(findRuleFile(directory)));
			}
			return ruleSet;
		}

	}

}
