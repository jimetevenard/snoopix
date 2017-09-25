package com.jimetevenard.snoopix.rule;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.ValidationException;

import com.jimetevenard.snoopix.explorer.Explorer;
import com.jimetevenard.snoopix.explorer.Strategy;
import com.jimetevenard.snoopix.ruleParserImp.DomRuleParser;

public class RuleSource {

	private Strategy strategy;
	private RuleSet uniqueRuleSet;
	private Map<File, RuleSet> allRuleSets;
	private File rootDir;
	private RuleParser ruleParser;

	public RuleSource(Strategy strategy, File rootDir) {
		super();
		this.strategy = strategy;
		this.rootDir = rootDir;
		this.allRuleSets = new HashMap<>();
		this.ruleParser = new DomRuleParser();

		if (strategy.getUniqueRulesFile() != null) {
			uniqueRuleSet = ruleParser.parseRulesFile(strategy.getUniqueRulesFile());
			for (Rule rule : uniqueRuleSet) {
				rule.setDirectory(rootDir);
				rule.computePriority(Explorer.ROOT_DEPHT);
			}
		} else {
			RuleSet rootRuleSet = ruleParser.parseRulesFile(findRuleFile(rootDir));
			for (Rule rule : rootRuleSet) {
				rule.setDirectory(rootDir);
				rule.computePriority(Explorer.ROOT_DEPHT);
			}
			allRuleSets.put(rootDir, rootRuleSet);
		}
	}

	private File findRuleFile(File dir) {
		return new File(dir.getAbsolutePath() + '/' + strategy.getRulesFileName());
	}

	public RuleSet ruleSet(File directory, int depht) {

		if (allRuleSets.get(directory) == null) {
			RuleSet localRules = ruleParser.parseRulesFile(findRuleFile(directory));
			for (Rule rule : localRules) {
				rule.setDirectory(directory);
				rule.computePriority(depht);
			}
			allRuleSets.put(directory, localRules);
		}

		return getApplicableRules(directory);

	}

	private RuleSet getApplicableRules(File directory) {
		RuleSet theRules = new RuleSet();
		theRules.addAll(allRuleSets.get(directory));
		if (!directory.equals(rootDir)) {
			theRules.addAll(getApplicableRules(directory.getParentFile()));
		}
		return theRules;
	}

	public static void validateRuleFile(File ruleFile) throws ValidationException {
		// TODO Validate the file !
		if (false) {
			throw new ValidationException("Invalid rules file : " + ruleFile.getAbsolutePath());
		}
	}

}
