package com.jimetevenard.snoopix.ruleParserImp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import com.jimetevenard.snoopix.Snoopix;
import com.jimetevenard.snoopix.rule.Rule;
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

			NodeList rules = document.getElementsByTagName("rule");

			for (int i = 0; i < rules.getLength(); i++) {

				Snoopix.logger.trace("Just found a rule in " + rulesFile.getAbsolutePath());
				Rule r = new Rule();
				NamedNodeMap atts = rules.item(i).getAttributes();
				NodeList vals = rules.item(i).getChildNodes();

				r.setFileNamePattern(atts.getNamedItem("match").getTextContent());

				r.setPriority(i); // TODO refacto... la priorité calculée sera
									// ajoutée à cet entier

				// TODO Validations

				ruleSet.add(r);

			}

		} catch (Exception e) {
			Snoopix.logger.debug(e.getClass().getSimpleName() + ' ' + e.getMessage());
		}

		return ruleSet;
	}

}
