package com.jimetevenard.snoopix.explorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.message.SimpleMessage;

import com.jimetevenard.snoopix.Snoopix;
import com.jimetevenard.snoopix.rule.RuleSet;
import com.jimetevenard.snoopix.rule.RuleSource;
import com.jimetevenard.snoopix.validation.FileValidator;
import com.jimetevenard.snoopix.validation.ValidationResult;

public class Explorer {

	private File sourceDir;
	private Strategy strategy;
	private RuleSource ruleSource;
	private List<ValidationResult> result = new ArrayList<>();
	private int depht;
	public static final int ROOT_DEPHT = 0;

	private boolean trace = true;

	public Explorer(File sourceDir, Strategy strategy) {
		super();
		this.sourceDir = sourceDir;
		this.strategy = strategy;
	}

	public void startExploring() throws ExplorationException {

		if (!sourceDir.isDirectory()) {
			throw new ExplorationException("source must exist and be a directory [" + sourceDir + ']');
		}

		this.depht = 0;
		this.ruleSource = new RuleSource(strategy, sourceDir);
		this.exploreDirectory(sourceDir);


	}

	private void exploreDirectory(File dir) {
		trace(dir); // trace file tree

		File[] dirContent = dir.listFiles();
		RuleSet rules = this.ruleSource.ruleSet(dir, this.depht);
		Snoopix.logger.debug(() -> {
			return new SimpleMessage("Exploring directory " + dir.getPath() + " whith followings rules " + rules);
		});
		// 

		// first iteration for processing files
		this.processFiles(dirContent, rules);

		// second iteration for exploring sub-dirs
		if (this.depht < strategy.getRecusiveDepth()) {
			this.exploreSubDirs(dirContent);
		}
	}

	private void processFiles(File[] dirContent, RuleSet rules) {
		for (File file : dirContent) {
			if (file.isFile()) {
				trace(file);
				result.add(FileValidator.processFile(file, rules));
			}
		}
	}

	private void exploreSubDirs(File[] dirContent) {
		for (File file : dirContent) {
			if (file.isDirectory()) {
				this.depht++;
				this.exploreDirectory(file);
				this.depht--;
			}
		}
	}

	private static String ident(int level) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < level; i++) {
			b.append("|   ");
		}
		return b.toString();
	}


	private void trace(File file) {
		if (trace) {
			System.out.println(ident(depht) + (file.isFile() ? "|   " : "") + file);
		}
	}

}
