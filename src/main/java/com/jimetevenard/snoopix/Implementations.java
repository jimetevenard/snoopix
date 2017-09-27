package com.jimetevenard.snoopix;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class Implementations {

	public static void init() throws IOException {
		Properties implems = new Properties();

		implems.loadFromXML(Implementations.class.getClassLoader().getResourceAsStream("implementations.xml"));

		for (Entry<Object, Object> e : implems.entrySet()) {
			System.setProperty("javax.xml.validation.SchemaFactory:" + e.getKey(), e.getValue().toString());
		}
	}

}
