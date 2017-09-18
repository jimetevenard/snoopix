package com.jimetevenard.snoopix.rule;

import java.io.File;

import com.jimetevenard.snoopix.explorer.Explorer;
import com.jimetevenard.snoopix.explorer.Strategy;
import com.jimetevenard.snoopix.ruleParserImp.DomRuleParser;

public class RuleSource {

	private Strategy strategy;
	private RuleSet uniqueRuleSet;
	private RuleSet rootRuleSet;
	private File rootDir;
	private RuleParser ruleParser;

	public RuleSource(Strategy strategy, File rootDir) {
		super();
		this.strategy = strategy;
		this.rootDir = rootDir;
		this.ruleParser = new DomRuleParser();

		if(strategy.getUniqueRulesFile() != null){
			uniqueRuleSet = ruleParser.parseRulesFile(strategy.getUniqueRulesFile());
			for (Rule rule : uniqueRuleSet) {
				rule.setDirectory(rootDir);
				rule.computePriority(Explorer.ROOT_DEPHT);
			}
		} else {
			rootRuleSet = ruleParser.parseRulesFile(findRuleFile(rootDir));
			for (Rule rule : rootRuleSet) {
				rule.setDirectory(rootDir);
				rule.computePriority(Explorer.ROOT_DEPHT);
			}
		}
	}

	private File findRuleFile(File dir) {
		return new File(dir.getAbsolutePath() + '/' + strategy.getRulesFileName());
	}

	public RuleSet ruleSet(File directory, int depht) {
		
		if(strategy.getUniqueRulesFile() != null){
			return uniqueRuleSet;
		} else {
			RuleSet ruleSet = rootRuleSet;
			if (strategy.isFetchRulesRecursively() && !rootDir.equals(directory)) {
				RuleSet localRules = ruleParser.parseRulesFile(findRuleFile(directory));
				for (Rule rule : localRules) {
					rule.setDirectory(directory);
					rule.computePriority(depht);
				}
				ruleSet.addAll(localRules);

			}
			return ruleSet;
		}

	}

}
