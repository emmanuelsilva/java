package edu.up.math.simplexsolver;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class TestFunction {

	private String function;
	private List<String> variables;
	private List<Double> values;
	private double solution;
	private List<String> subjectTo;
	private GoalType type;

	public TestFunction(String function) {
		this.function = function;
		subjectTo = new ArrayList<String>();
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}

	public List<Double> getValues() {
		return values;
	}

	public void setValues(List<Double> values) {
		this.values = values;
	}

	public double getSolution() {
		return solution;
	}

	public void setSolution(double solution) {
		this.solution = solution;
	}

	public List<String> getSubjectTo() {
		return subjectTo;
	}

	public void setSubjectTo(List<String> subjectTo) {
		this.subjectTo = subjectTo;
	}
	
	public GoalType getType() {
		return type;
	}

	public void setType(GoalType type) {
		this.type = type;
	}

	public String getSubjectToAsString() {
		StringBuilder subject = new StringBuilder();
		
		for (String s : subjectTo) {
			subject.append(s);
			subject.append("\n");
		}
		
		return subject.toString();
	}
}
