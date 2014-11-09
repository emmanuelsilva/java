package edu.up.math.simplexsolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.PivotSelectionRule;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class LinearProgramingSolver {

	private EquationParser parser;
	
	public LinearProgramingSolver() {
		parser = new EquationParser();
	}
	
	public Result solve(GoalType type, String strFunction, String subjectTo) {
		List<Double> coefficients = parser.extractCoefficientValues(strFunction);
		List<String> variables = parser.extractVariables(strFunction);
		String[] subjectLines = subjectTo.split("\n");
		Double sumOfConstants = parser.sumOfConstants(strFunction);
		
		LinearObjectiveFunction function = new LinearObjectiveFunction(toDoubleArray(coefficients), sumOfConstants);
		Collection<LinearConstraint> constraints = new ArrayList<LinearConstraint>();
		
		for (String subject : subjectLines)  {
			List<Double> allSubjectValues = parser.extractCoefficientValues(subject);
			List<Double> subjectCoefficients = allSubjectValues.subList(0, allSubjectValues.size()-1);
			List<String> subjectVariables = parser.extractVariables(subject);
			
			Map<String, Double> variablesValues = new LinkedHashMap<>();
			
			for (String variable : variables) {
				if (!subjectVariables.contains(variable)) {
					variablesValues.put(variable, 0.0);
				} else {
					int index = subjectVariables.indexOf(variable);
					Double variableValue = subjectCoefficients.get(index);
					variablesValues.put(variable, variableValue);
				}
			}
			
			double value = allSubjectValues.get(allSubjectValues.size()-1);
			Relationship relationship = parser.getSubjectToRelationship(subject);
			
			LinearConstraint constraint = new LinearConstraint(toDoubleArray(variablesValues.values()), relationship, value);
			constraints.add(constraint);
		}
	
		SimplexSolver solver = new SimplexSolver();
		PointValuePair solution = solver.optimize(function, new LinearConstraintSet(constraints), type, new NonNegativeConstraint(true), PivotSelectionRule.BLAND);
		
		Result result = new Result(solution.getValue());
		
		for (int i = 0; i<variables.size(); i++) {
			result.addSolution(variables.get(i), solution.getPoint()[i]);
		}
		
		return result;
	}
	
	private double[] toDoubleArray(Collection<Double> doubles) {
		double[] array = new double[doubles.size()];
		
		int i = 0;
		
		for (Iterator<Double> iterator = doubles.iterator(); iterator.hasNext();) {
			array[i++] = iterator.next();
		}
		
		return array;
	}
	
}
