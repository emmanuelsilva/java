package edu.up.math.simplexsolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.PivotSelectionRule;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.junit.Test;

public class SimplexSolverTest {
	
	@Test
	public void testCommonsLibrary() {
		LinearObjectiveFunction f = new LinearObjectiveFunction(new double[] { 0.5, 3, 1, 4}, 0);
		
		Collection<LinearConstraint> constraints = new ArrayList<>();
		constraints.add(new LinearConstraint(new double[] { 1, 1, 1, 1 }, Relationship.LEQ, 40));
		constraints.add(new LinearConstraint(new double[] { 2, 1, -1, -1 }, Relationship.GEQ, 10));
		constraints.add(new LinearConstraint(new double[] { 0, -1, 0, 1 }, Relationship.GEQ, 10));

		SimplexSolver solver = new SimplexSolver();
		PointValuePair solution = solver.optimize(f, new LinearConstraintSet(constraints), GoalType.MAXIMIZE, new NonNegativeConstraint(true), PivotSelectionRule.BLAND);
		System.out.println(solution.getValue());
		
		for (int i = 0; i<solution.getPoint().length; i++) {
			System.out.println(solution.getPoint()[i]);
		}
	}
	
	@Test
	public void testSomeFunctions() {
		LinearProgramingSolver solver = new LinearProgramingSolver();
		
		List<TestFunction> functions = FunctionSampleFactory.createSampleFunctions();
		
		for (TestFunction function : functions) {
			if (!function.getSubjectTo().isEmpty()) {
				Result result = solver.solve(function.getType(), function.getFunction(), function.getSubjectToAsString());
				System.out.println("testando " + function.getFunction());
				System.out.println("solucao: " + result.getValue());
				
				for (String variable : result.getSolutions().keySet()) {
					System.out.println(variable + " = " + result.getSolutions().get(variable));
				}
			}
		}
	}
	
}
