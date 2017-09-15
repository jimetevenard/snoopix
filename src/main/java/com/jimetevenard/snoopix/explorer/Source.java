package com.jimetevenard.snoopix.explorer;

import java.io.File;

import com.jimetevenard.snoopix.util.CommandLineArgs;

public class Source {
	
	public static File getSourceDir(){
		return new File(System.getProperty("user.dir"));
	}

	public static File getSourceDir(CommandLineArgs args){
		if(args.has("source")){
			return new File(args.get("source"));
		}
		return new File(System.getProperty("user.dir"));
	}

}
