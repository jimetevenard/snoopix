package com.jimetevenard.snoopix.explorer;

import java.io.File;
import java.util.Map;

import com.jimetevenard.snoopix.util.CommandLineArgs;
import com.jimetevenard.snoopix.util.CommandLineArgs.CommandLineException;

public class Strategy {
	
	// FIRST WAY TO SPECIFY RULES :
	// single rules file supplied by the user
	private File uniqueRulesFile;
	
	// SECOND WAY TO SPECIFY RULES :
	// A filename to look for in the source directory
	private String rulesFileName;
	
	//look for a (configFileName matching) rules file in each subdirectory explored
	private boolean fetchRulesRecursively;
	
	// build-in default rul management
	private DefaultRuleManagement defaultRuleManagement;
	
	// depth of recursive exploring of dirs  
	private int recusiveDepth;
	
	private static  Strategy defaultStrategy;
	
	private Strategy(){
		// Use followings methods to instanciate
	}

	
	public static Strategy getDefaultStrategy(){
		if(defaultStrategy == null){
			defaultStrategy = new Strategy();
			defaultStrategy.setRulesFileName("validation.xml");
			defaultStrategy.setFetchRulesRecursively(true);
			defaultStrategy.setDefaultRuleManagement(DefaultRuleManagement.APPLY_IF_NO_CONFIG);
			defaultStrategy.setRecusiveDepth(Integer.MAX_VALUE);
		}
		return defaultStrategy;
	}
	
	public static Strategy fromCommandArgs(CommandLineArgs args){
		
		// TODO : Implement Me !
		
		// Today we just return the default;
		return getDefaultStrategy();			
	}
	
	
	
	
	
	
	
	
	// ####### Getters / Setters ########
	
	public String getRulesFileName() {
		return rulesFileName;
	}

	public void setRulesFileName(String configFileName) {
		this.rulesFileName = configFileName;
	}

	public boolean isFetchRulesRecursively() {
		return fetchRulesRecursively;
	}

	public void setFetchRulesRecursively(boolean fetchConfigRecursively) {
		this.fetchRulesRecursively = fetchConfigRecursively;
	}

	public DefaultRuleManagement getDefaultRuleManagement() {
		return defaultRuleManagement;
	}

	public void setDefaultRuleManagement(DefaultRuleManagement defaultRuleManagement) {
		this.defaultRuleManagement = defaultRuleManagement;
	}

	public int getRecusiveDepth() {
		return recusiveDepth;
	}

	public void setRecusiveDepth(int recusiveDepth) {
		this.recusiveDepth = recusiveDepth;
	}


	public File getUniqueRulesFile() {
		return uniqueRulesFile;
	}


	public void setUniqueRulesFile(File uniqueConfigFile) {
		this.uniqueRulesFile = uniqueConfigFile;
	}


	@Override
	public String toString() {
		return "Strategy [uniqueConfigFile=" + uniqueRulesFile + ", configFileName=" + rulesFileName
				+ ", fetchConfigRecursively=" + fetchRulesRecursively + ", defaultRuleManagement="
				+ defaultRuleManagement + ", recusiveDepth=" + recusiveDepth + "]";
	}
	
	

	
	

}
