package io.github.alphahelixdev.ottolanguage.v2;

import io.github.alphahelixdev.helius.Helius;
import io.github.alphahelixdev.ottolanguage.independency.IOttoLanguage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class OttoLanguage implements IOttoLanguage {
	
	
	
	@Override
	public OttoLanguage start() {
		System.out.println("Please enter your path to your .otto file:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String path = null;
		try {
			path = br.readLine();
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		assert path != null;
		
		return this.start(path);
	}
	
	@Override
	public OttoLanguage start(String filePath) {
		this.interpret( Helius.read(Helius.createFile(new File(filePath))));
		return this;
	}
	
	@Override
	public void interpret(String fileInput) {
	
	}
}
