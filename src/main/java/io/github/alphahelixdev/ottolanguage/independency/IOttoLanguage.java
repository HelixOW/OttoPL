package io.github.alphahelixdev.ottolanguage.independency;

public interface IOttoLanguage {
	
	default IOttoLanguage start() {
		return start("");
	}
	
	IOttoLanguage start(String filePath);
	
	void interpret(String fileInput);
	
}
