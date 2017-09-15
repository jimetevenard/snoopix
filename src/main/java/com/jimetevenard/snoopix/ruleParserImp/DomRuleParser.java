package com.jimetevenard.snoopix.ruleParserImp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.jimetevenard.snoopix.Snoopix;
import com.jimetevenard.snoopix.rule.RuleParser;
import com.jimetevenard.snoopix.rule.RuleSet;

public class DomRuleParser extends RuleParser {

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;

	public DomRuleParser() {
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			builder = null;
			Snoopix.logger.debug(e.getStackTrace());
		}
	}

	@Override
	public RuleSet parseRulesFile(File rulesFile) {
		RuleSet ruleSet = new RuleSet();
		try {
			Document document = builder.parse(rulesFile);
		} catch (Exception e) {
			Snoopix.logger.debug(e.getStackTrace());
		}

		return ruleSet;
	}

}
