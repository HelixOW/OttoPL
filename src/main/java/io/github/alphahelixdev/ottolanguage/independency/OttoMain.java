package io.github.alphahelixdev.ottolanguage.independency;

import io.github.alphahelixdev.helius.utils.StringUtil;
import io.github.alphahelixdev.ottolanguage.v1.OttoLanguage;

public class OttoMain {
	
	private static IOttoLanguage ottoLanguage;
	
	public static void main(String[] args) {
		if(args.length == 0)
			ottoLanguage = new OttoLanguage().start();
		else if(args.length == 1) {
			String version = args[0];
			if(StringUtil.matches(version, "1.0", "1", "one", "first", "original"))
				ottoLanguage = new OttoLanguage().start();
			else
				ottoLanguage = new io.github.alphahelixdev.ottolanguage.v2.OttoLanguage().start();
		} else if(args.length == 2) {
			String version = args[0];
			String path = args[1];
			if(StringUtil.matches(version, "1.0", "1", "one", "first", "original"))
				ottoLanguage = new OttoLanguage().start(path);
			else
				ottoLanguage = new io.github.alphahelixdev.ottolanguage.v2.OttoLanguage().start(path);
		}
	}
	
	public static IOttoLanguage getOttoLanguage() {
		return OttoMain.ottoLanguage;
	}
}
