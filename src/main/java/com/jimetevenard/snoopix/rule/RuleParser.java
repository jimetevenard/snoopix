package com.jimetevenard.snoopix.rule;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public abstract class RuleParser {

	public abstract RuleSet parseRulesFile(File rulesFile);

}
