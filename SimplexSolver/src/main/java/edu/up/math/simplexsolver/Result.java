package edu.up.math.simplexsolver;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {

	private Double value;
	private Map<String, Double> solutions;
	
	public Result() {
		solutions = new LinkedHashMap<String, Double>();
	}
	
	public Result(Double value) {
		this();
		this.value = value;
	}
	
	public Result(Double value, Map<String, Double> solutions) {
		this.value = value;
		this.solutions = solutions;
	}
	
	public void addSolution(String variable, double solution) {
		solutions.put(variable, solution);
	}
	
	public Double getValue() {
		return value;
	}
	
	public Map<String, Double> getSolutions() {
		return solutions;
	}
	
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		result.append("Solução ótima: ");
		result.append(getValue());
		result.append(" [");
		
		for (String variable : getSolutions().keySet()) {
			result.append(variable + " = " + getSolutions().get(variable) + " ");
		}
		
		result.append("]");
		
		return result.toString();
	}
}
