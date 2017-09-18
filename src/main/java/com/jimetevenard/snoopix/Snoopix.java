package com.jimetevenard.snoopix;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jimetevenard.snoopix.explorer.ExplorationException;
import com.jimetevenard.snoopix.explorer.Explorer;
import com.jimetevenard.snoopix.explorer.Source;
import com.jimetevenard.snoopix.explorer.Strategy;
import com.jimetevenard.snoopix.util.CommandLineArgs;
import com.jimetevenard.snoopix.util.CommandLineArgs.CommandLineException;

public class Snoopix {
	
	private static Strategy strategy;
	public static final Logger logger = LogManager.getLogger(Snoopix.class);
	public static final String NAMESPACE_URI = "http://jimetevenard.com/ns/snoopix/rules";

	public static void main(String[] args) {

		try {
			CommandLineArgs arguments = new CommandLineArgs(args);
			logger.trace("Snoopix starts with arguments : " + arguments);

			strategy = Strategy.fromCommandArgs(arguments);
			logger.trace(strategy);
			
			File sourceDir = Source.getSourceDir(arguments);

			Explorer explorer = new Explorer(sourceDir, strategy);
			explorer.startExploring();

		} catch (CommandLineException | ExplorationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

}
