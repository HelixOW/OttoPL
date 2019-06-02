package io.github.alphahelixdev.ottolanguage.v1;

import io.github.alphahelixdev.helius.Helius;
import io.github.alphahelixdev.ottolanguage.independency.IOttoLanguage;
import io.github.alphahelixdev.ottolanguage.independency.KeyWords;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class OttoLanguage implements IOttoLanguage {
	
	private static final Map<String, Integer> METHODS = new HashMap<>();
	
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
		fileInput = fileInput.replace(System.lineSeparator(), "")
				.replaceAll(OttoKeyWord.COMMENT.getOttoLanguageWordEscaped() + ".*" + OttoKeyWord.COMMENT.getOttoLanguageWordEscaped(), "");
		
		String[] lines = fileInput.split(KeyWords.SEPARATOR);
		
		int i = 0;
		
		while(i < lines.length) {
			String line = lines[i].replaceFirst(KeyWords.SEPARATOR, "");
			line = OttoKeyWord.removeTabs(line);
			
			if(line.startsWith(KeyWords.KEY))
				METHODS.put(OttoKeyWord.KEY_SIGN.getKeyWordAction().run(line), i+1);
			
			if(line.startsWith(KeyWords.OUT))
				OttoKeyWord.OUT.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.TEXT))
				OttoKeyWord.TEXT.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.BOOLEAN))
				OttoKeyWord.BOOLEAN.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.NUMBER))
				OttoKeyWord.NUMBER.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.ADD))
				OttoKeyWord.ADD.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.SUB))
				OttoKeyWord.SUB.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.MUL))
				OttoKeyWord.MUL.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.DIV))
				OttoKeyWord.DIV.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.MODULO))
				OttoKeyWord.MODULO.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.GET))
				OttoKeyWord.GET.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.EVEN))
				OttoKeyWord.EVEN.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.ODD))
				OttoKeyWord.ODD.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.LOW))
				OttoKeyWord.LOW.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.BIG))
				OttoKeyWord.BIG.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.EQUAL))
				OttoKeyWord.EQUAL.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.UNEQUAL))
				OttoKeyWord.UNEQUAL.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.LOWEQUAL))
				OttoKeyWord.LOW_EQUAL.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.BIGEQUAL))
				OttoKeyWord.BIG_EQUAL.getKeyWordAction().run(line);
			
			if(line.startsWith(KeyWords.END)) {
				String str = OttoKeyWord.END.getKeyWordAction().run(line);
				if(str == null)
					break;
			}
			
			if(line.startsWith(KeyWords.JMP)) {
				String str = OttoKeyWord.JMP.getKeyWordAction().run(line);
				
				if(str.startsWith("oJUMP:"))
					i = METHODS.get(str.replace("oJUMP:", ""));
				else
					i++;
			} else
				i++;
		}
	}
}
