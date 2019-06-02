package io.github.alphahelixdev.ottolanguage.independency;

import com.google.common.collect.ImmutableMap;
import io.github.alphahelixdev.ottolanguage.v2.OttoKeyWordAction;

import java.util.Map;

public interface KeyWords {
	
	String COMMENT = "++";
	String SEPARATOR = "-";
	String VARIABLE = "_";
	String KEY = "$$";
	
	String TEXT = "Otto "; //Otto <varName> <value>
	String BOOLEAN = "Uni ";
	String NUMBER = "Guericke ";
	
	String ADD = "Uni von ";
	String SUB = "von Uni ";
	String MUL = "Otto von ";
	String DIV = "von Otto ";
	
	String BIG = "Guericke von ";
	String LOW = "von Guericke ";
	String ODD = "von Magdeburg ";
	String EVEN = "Magdeburg von ";
	String EQUAL = "Magdeburg Guericke ";
	String UNEQUAL = "Guericke Magdeburg ";
	String MODULO = "Uni Magdeburg ";
	String LOWEQUAL = "Otto Uni ";
	String BIGEQUAL = "Uni Otto";
	
	String OUT = "Otto Guericke ";
	String GET = "Guericke Otto ";
	
	String JMP = "Magdeburg Uni ";
	
	String END = "Otto von Guericke Uni Magdeburg ";
	
	Map<String, OttoKeyWordAction> ACTIONS = ImmutableMap.of(
			COMMENT, input -> null
	);
}
