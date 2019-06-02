package io.github.alphahelixdev.ottolanguage.v1;

import io.github.alphahelixdev.ottolanguage.independency.KeyWords;

import java.util.HashMap;
import java.util.Map;

public enum OttoKeyWord {
	
	COMMENT(KeyWords.COMMENT, new OttoKeyWordAction()),
	VAR_SIGN(KeyWords.VARIABLE, new OttoKeyWordAction()),
	KEY_SIGN(KeyWords.KEY, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			return input.replaceFirst(KeyWords.KEY, "");
		}
	}),
	TEXT(KeyWords.TEXT, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.TEXT, "");
			String[] kVS = out.split(" ");
			
			if(out.startsWith(KeyWords.VARIABLE)) {
				String key = kVS[0];
				String value = replaceVars(joinAfter(kVS, 1));
				
				VARIABLES.put(key, removeTabs(value));
			}
			
			return out;
		}
	}),
	BOOLEAN(KeyWords.BOOLEAN, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.BOOLEAN, "");
			String[] kVS = out.split(" ");
			
			if(out.startsWith(KeyWords.VARIABLE)) {
				String varName = kVS[0];
				String value = joinAfter(kVS, 1).replaceFirst(" ", "");
				
				value = replaceVars(value);
				
				if(value.startsWith(KeyWords.EVEN))
					value = EVEN.getKeyWordAction().run(value);
				else if(value.startsWith(KeyWords.ODD))
					value = ODD.getKeyWordAction().run(value);
				else if(value.startsWith(KeyWords.BIG))
					value = BIG.getKeyWordAction().run(value);
				else if(value.startsWith(KeyWords.LOW))
					value = LOW.getKeyWordAction().run(value);
				
				VARIABLES.put(varName, removeTabs(value));
			}
			
			return out;
		}
	}),
	NUMBER(KeyWords.NUMBER, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.NUMBER, "");
			String[] kVS = out.split(" ");
			
			if(out.startsWith(KeyWords.VARIABLE)) {
				String varName = kVS[0];
				String value = joinAfter(kVS, 1).replaceFirst(" ", "");
				
				if(value.startsWith(KeyWords.ADD))
					value = ADD.getKeyWordAction().run(value);
				else if(value.startsWith(KeyWords.SUB))
					value = SUB.getKeyWordAction().run(value);
				else if(value.startsWith(KeyWords.MUL))
					value = MUL.getKeyWordAction().run(value);
				else if(value.startsWith(KeyWords.DIV))
					value = DIV.getKeyWordAction().run(value);
				
				value = replaceVars(value);
				
				VARIABLES.put(varName, removeTabs(value));
			}
			
			return out;
		}
	}),
	ADD(KeyWords.ADD, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.ADD, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Double.toString(a + b);
		}
	}),
	SUB(KeyWords.SUB, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.ADD, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Double.toString(a - b);
		}
	}),
	MUL(KeyWords.MUL, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.ADD, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Double.toString(a * b);
		}
	}),
	DIV(KeyWords.DIV, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.ADD, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Double.toString(a / b);
		}
	}),
	MODULO(KeyWords.MODULO, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.MODULO, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Double.toString(a % b);
		}
	}),
	ODD(KeyWords.ODD, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.ODD, "");
			String[] parameters = out.split(" ");
			
			return Boolean.toString(getDouble(parameters[0]) % 2 != 0);
		}
	}),
	EVEN(KeyWords.EVEN, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.EVEN, "");
			String[] parameters = out.split(" ");
			
			return Boolean.toString(getDouble(parameters[0]) % 2 == 0);
		}
	}),
	BIG(KeyWords.BIG, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.BIG, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Boolean.toString(a > b);
		}
	}),
	LOW(KeyWords.LOW, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.LOW, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Boolean.toString(a < b);
		}
	}),
	EQUAL(KeyWords.EQUAL, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.EQUAL, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Boolean.toString(a == b);
		}
	}),
	UNEQUAL(KeyWords.UNEQUAL,  new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.EQUAL, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Boolean.toString(a != b);
		}
	}),
	LOW_EQUAL(KeyWords.LOWEQUAL,  new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.EQUAL, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Boolean.toString(a <= b);
		}
	}),
	BIG_EQUAL(KeyWords.BIGEQUAL, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.EQUAL, "");
			String[] parameters = out.split(" ");
			
			double a = getDouble(parameters[0]);
			double b = getDouble(parameters[1]);
			
			return Boolean.toString(a >= b);
		}
	}),
	GET(KeyWords.GET, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			return replaceVars(input.replaceFirst(KeyWords.GET, ""));
		}
	}),
	OUT(KeyWords.OUT, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.OUT, "");
			
			if(out.startsWith(KeyWords.GET))
				out = GET.getKeyWordAction().run(out);
			else if(out.startsWith(KeyWords.ADD))
				out = ADD.getKeyWordAction().run(out);
			else if(out.startsWith(KeyWords.SUB))
				out = SUB.getKeyWordAction().run(out);
			else if(out.startsWith(KeyWords.MUL))
				out = MUL.getKeyWordAction().run(out);
			else if(out.startsWith(KeyWords.DIV))
				out = DIV.getKeyWordAction().run(out);
			
			replaceVars();
			
			System.out.println(out);
			
			return out;
		}
	}),
	JMP(KeyWords.JMP, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.JMP, "");
			String[] parameters = out.split("(?= )");
			boolean b = Boolean.parseBoolean(replaceVars(parameters[0]));
			
			if(b)
				return "oJUMP:" + replaceVars(parameters[1]).replaceFirst(" ", "");
			
			return out;
		}
	}),
	END(KeyWords.END, new OttoKeyWordAction() {
		@Override
		public String run(String input) {
			String out = input.replaceFirst(KeyWords.END, "");
			boolean b = Boolean.parseBoolean(replaceVars(out.split(" ")[0]));
			
			if(b)
				return null;
			
			return out;
		}
	});
	
	private static final Map<String, String> VARIABLES = new HashMap<>();
	
	private final String ottoLanguageWord;
	private final OttoKeyWordAction keyWordAction;
	
	OttoKeyWord(String ottoLanguageWord, OttoKeyWordAction keyWordAction) {
		this.ottoLanguageWord = ottoLanguageWord;
		this.keyWordAction = keyWordAction;
	}
	
	public String getOttoLanguageWord() {
		return this.ottoLanguageWord;
	}
	
	public String getOttoLanguageWordEscaped() {
		return "\\" + this.getOttoLanguageWord();
	}
	
	public OttoKeyWordAction getKeyWordAction() {
		return this.keyWordAction;
	}
	
	private static String joinAfter(String[] array, int index) {
		StringBuilder joined = new StringBuilder();
		
		for(int i = index; i < array.length; i++) {
			joined.append(" ").append(array[i]);
		}
		
		return joined.toString();
	}
	
	private static String replaceVars(String str) {
		for(String s : str.split("(?= )")) {
			s = removeTabs(s);
			if(s.startsWith(VAR_SIGN.getOttoLanguageWord()) && VARIABLES.containsKey(s))
				str = " " + str.replace(s, VARIABLES.get(s));
		}
		
		return removeTabs(str);
	}
	
	private static void replaceVars() {
		for(Map.Entry<String, String> entry : VARIABLES.entrySet()) {
			entry.setValue(replaceVars(entry.getValue()));
		}
	}
	
	private static double getDouble(String str) {
		Double a = null;
		
		if(str.startsWith(VAR_SIGN.getOttoLanguageWord()))
			a = Double.parseDouble(VARIABLES.get(str));
		
		if(a == null && str.matches("-?\\d+(\\.\\d+)?"))
			a = Double.parseDouble(str);
		
		if(a == null)
			return 0.0;
		
		return a;
	}
	
	public static String removeTabs(String str) {
		if(Character.isWhitespace(str.charAt(0)))
			return removeTabs(str.replaceFirst(Character.toString(str.charAt(0)), ""));
		
		return str;
	}
	
	public static class OttoKeyWordAction {
		public String run(String input) {
			return input;
		}
	}
}
