package edu.up.math.simplexsolver;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.stat.descriptive.summary.Sum;

public class EquationParser {
	
	public List<String> extractVariables(String function) {
		List<String> variables = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(function, " ");
		
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			
			for (char c : token.toCharArray()) {
				if (isVariable(c)) {
					variables.add(String.valueOf(c));
				}
			}
		}
		
		return variables;
	}
	
	public Double sumOfConstants(String formula) {
		formula = formula + " ";
		
		Sum sum = new Sum();
		
		String tmpNumber = "";
		boolean negative = false;
		boolean foundVariable = false;
		
		for (int i = 0; i<formula.length(); i++) {
			char c = formula.charAt(i);
			
			if (Character.isDigit(c) || c == '.') {
				tmpNumber += String.valueOf(c);
			} else if (isVariable(c)) {
				foundVariable = true;
			}
			else if (c == '-') {
				negative = true;
			}
			else if (c == ' ' && (tmpNumber.length() > 0)) {
				if (!foundVariable) {
					double num = 0;
					if (!tmpNumber.isEmpty()) {
						num = Double.parseDouble(tmpNumber);
					} else {
						num = 1; //expressao: x + y + z (sem numeros)
					}
					
					if (negative) {
						num = num * -1;
					}
					
					sum.increment(num);
				}
				
				tmpNumber = "";
				negative = false;
				foundVariable = false;
			}
		}
		
		return sum.getResult();
	}
	
	public List<Double> extractCoefficientValues(String formula) {
		formula = formula + " ";
		
		String tmpNumber = "";
		boolean negative = false;
		List<Double> values = new ArrayList<>();
		int qtdVariable = 0;
		
		for (char c : formula.toCharArray()) {
			if (Character.isDigit(c) || c == '.') {
				tmpNumber += String.valueOf(c);
			}
			else if (Character.isAlphabetic(c)) {
				qtdVariable++;
			}
			else if (c == '-') {
				negative = true;
			}
			else if (c == ' ' && (tmpNumber.length() > 0 || qtdVariable > 0)) {
				double num = 0;
				if (!tmpNumber.isEmpty()) {
					num = Double.parseDouble(tmpNumber);
				} else {
					num = 1; //expressao: x + y + z (sem numeros)
				}
				
				if (negative) {
					num = num * -1;
				}
				
				values.add(num);
				tmpNumber = "";
				negative = false;
				qtdVariable = 0;
			}
		}
		
		return values;
	}
	
	public Relationship getSubjectToRelationship(String subject) {
		if (subject.contains(Relationship.GEQ.toString())) {
			return Relationship.GEQ;
		} else if (subject.contains(Relationship.LEQ.toString())) {
			return Relationship.LEQ;
		}
		
		return Relationship.EQ;
	}
	
	private boolean isVariable(char c) {
		return (!Character.isDigit(c) && c != '-' && c != '+' && c != ',' && c != '>' && c != '<' && c != '=' && c != ' ' && c != '.');
	}
}
