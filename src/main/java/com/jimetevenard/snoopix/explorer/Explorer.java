package com.jimetevenard.snoopix.explorer;

import java.io.File;

public class Explorer {

	private File sourceDir;
	private Strategy strategy;

	private int depht;
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

		this.exploreDirectory(sourceDir);

	}

	private void exploreDirectory(File dir) {
		trace(dir);

		File[] dirContent = dir.listFiles();

		// first iteration for processing files
		this.processFiles(dirContent);

		// second iteration for exploring sub-dirs
		if (this.depht < strategy.getRecusiveDepth()) {
			this.exploreSubDirs(dirContent);
		}
	}

	private void processFiles(File[] dirContent) {
		for (File file : dirContent) {
			if (file.isFile()) {
				trace(file);
				// Do process
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
