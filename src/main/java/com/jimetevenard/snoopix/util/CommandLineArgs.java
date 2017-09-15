package com.jimetevenard.snoopix.util;

import java.util.HashMap;
import java.util.Map;

public class CommandLineArgs {
	
	private Map<String, String> arguments;
	
	public CommandLineArgs(String[] args) throws CommandLineException{
		arguments = parseArgs(args);
	}
	
	public String get(String key){
		return arguments.get(key);
	}
	
	public boolean getBoolean(String key){
		return arguments.containsKey(key) && !arguments.get(key).equals("false");
	}
	public boolean has(String key){
		return arguments.containsKey(key);
	}
	
	
	public static Map<String, String> parseArgs(String[] args) throws CommandLineException{
		Map<String, String> arguments = new HashMap<String, String>();
		boolean isKey;
		String key = null;
		String val;
		for (String arg : args) {
			isKey = arg.startsWith("--");
			if(isKey){
				if (key != null) {
					arguments.put(key, "true");
					key = null;
				}
				key = key(arg);
				continue;
			} else {
				if (key == null) {
					throw new CommandLineException();
				}
				val = arg;
				arguments.put(key, val);
				key = null;
			}		
		}
		return arguments;
	}
	
	
	private static String key(String arg) {
			return arg.substring(2);
	}
	
	public static class CommandLineException extends Exception {
		private static final long serialVersionUID = 1L;

		public CommandLineException(){
			super("Syntax error in command line arguments.\nUse the form '--param value' or '--trueBooleanParam'");
		}
	}

	@Override
	public String toString() {
		return "CommandLineArgs [arguments=" + arguments + "]";
	}
	
	

}
