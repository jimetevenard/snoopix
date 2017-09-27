package com.jimetevenard.snoopix;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jimetevenard.snoopix.explorer.ExplorationException;
import com.jimetevenard.snoopix.explorer.Explorer;
import com.jimetevenard.snoopix.explorer.Source;
import com.jimetevenard.snoopix.explorer.Strategy;
import com.jimetevenard.snoopix.util.CommandLineArgs;
import com.jimetevenard.snoopix.util.CommandLineArgs.CommandLineException;
import com.jimetevenard.snoopix.validation.ValidationResult;

public class Snoopix {
	
	private static Strategy strategy;
	public static final Logger logger = LogManager.getLogger(Snoopix.class);
	public static final String NAMESPACE_URI = "http://jimetevenard.com/ns/validation-rules-definition";

	public static void main(String[] args) {


		try {

			Implementations.init();

			CommandLineArgs arguments = new CommandLineArgs(args);
			logger.trace("Snoopix starts with arguments : " + arguments);

			strategy = Strategy.fromCommandArgs(arguments);
			logger.trace(strategy);
			
			File sourceDir = Source.getSourceDir(arguments);

			Explorer explorer = new Explorer(sourceDir, strategy);
			explorer.startExploring();


			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++

			List<ValidationResult> testResults = explorer.getResult();
			testDisplayResult(testResults);

		} catch (CommandLineException | ExplorationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

	/**
	 * This method is for testing purpose. It just output the results in console
	 * 
	 * @param results
	 */
	private static void testDisplayResult(Collection<ValidationResult> results) {
		System.out.println("\n======== RESULTS =========");
		
		for (ValidationResult v : results) {
			System.out.println("\nFILE : " + v.getFileProcessed() + "\n\t- "
					+ (v.isValid() ? "YES, VALID DOCUMENT\n\t- " : "OUCH, INVALID DOCUMENT ! \n\t- ")
					+ (v.getRuleApplied() == null ? "No matching rule" : v.getRuleApplied())
					+ "\n\t- Errors : " + v.getErrors());
		}
	}

}
