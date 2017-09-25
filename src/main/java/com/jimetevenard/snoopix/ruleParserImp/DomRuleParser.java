package com.jimetevenard.snoopix.ruleParserImp;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.jimetevenard.snoopix.Snoopix;
import com.jimetevenard.snoopix.rule.Rule;
import com.jimetevenard.snoopix.rule.RuleParser;
import com.jimetevenard.snoopix.rule.RuleSet;
import com.jimetevenard.snoopix.rule.RuleSource;
import com.jimetevenard.snoopix.validation.ValidationStep;
import com.jimetevenard.snoopix.validation.step.AutoValidation;
import com.jimetevenard.snoopix.validation.step.ChooseStep;
import com.jimetevenard.snoopix.validation.step.SingleValidationStep;

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
		// The XML rules file must comply width src/main/resources/rules.xsd
		// (ie Validation rules definition - version 1.0)

		RuleSet ruleSet = new RuleSet();
		try {
			RuleSource.validateRuleFile(rulesFile);


			Document document = builder.parse(rulesFile);

			NodeList rules = document.getElementsByTagName("rule");

			for (int i = 0; i < rules.getLength(); i++) {

				Snoopix.logger.trace("Just found a rule in " + rulesFile.getAbsolutePath());
				Rule r = new Rule();
				NamedNodeMap attributes = rules.item(i).getAttributes();
				NodeList stepNodes = rules.item(i).getChildNodes();

				r.setFileNamePattern(attributes.getNamedItem("match").getTextContent());

				r.setPriority(i); // TODO refacto... la priorité calculée sera
									// ajoutée à cet entier

				for (int j = 0; j < stepNodes.getLength(); j++) {
					parseValidationStep(stepNodes.item(j), r);
				}

				ruleSet.add(r);

			}

		} catch (Exception e) {
			Snoopix.logger.debug(e.getClass().getSimpleName() + ' ' + e.getMessage());
		}

		return ruleSet;
	}

	private void parseValidationStep(Node validationStepNode, Rule rule) {
		// The XML rules file must comply width src/main/resources/rules.xsd
		// (ie Validation rules definition - version 1.0)

		if (isElem(validationStepNode, "validate")) {

			rule.getValidationSteps().add(parseSimpleStep(validationStepNode));

		} else if (isElem(validationStepNode, "choose")) {

			rule.getValidationSteps().add(parseChooseStep(validationStepNode));

		}

	}

	private ValidationStep parseSimpleStep(Node validateNode) {

		if (((Element) validateNode).hasAttribute("href")) {
			return new SingleValidationStep(((Element) validateNode).getAttribute("href"));
		} else {
			return new AutoValidation();
		}

	}

	private ValidationStep parseChooseStep(Node validateNode) {

		ChooseStep choose = new ChooseStep();

		NodeList childStepNodes = validateNode.getChildNodes();
		for (int i = 0; i < childStepNodes.getLength(); i++) {
			if (isElem(childStepNodes.item(i), "validate")) {
				choose.addChildStep(parseSimpleStep(childStepNodes.item(i)));
			}
		}

		return choose;
	}

	private boolean isElem(Node node, String elemName) {
		return node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(elemName);
	}


}
