package edu.up.math.simplexsolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

public class FunctionSampleFactory {

	public static List<TestFunction> createSampleFunctions() {
		List<TestFunction> functions = new ArrayList<>();

		TestFunction f1 = new TestFunction("10x - 57y - 9z - 24w");
		f1.setVariables(Arrays.asList("x", "y", "z", "w"));
		f1.setValues(Arrays.asList(10.0, -57.0, -9.0, -24.0));
		f1.setSolution(1.0d);
		f1.setType(GoalType.MAXIMIZE);
		
		f1.getSubjectTo().add("0.5x - 5.5y - 2.5z + 9w <= 0");
		f1.getSubjectTo().add("0.5x - 1.5y - 0.5z + 1w <= 0");
		f1.getSubjectTo().add("1x + 0y + 0z + 0w <= 1");

		functions.add(f1);

		TestFunction f2 = new TestFunction("-2x + y - 5z");
		f2.setVariables(Arrays.asList("x", "y", "z"));
		f2.setValues(Arrays.asList(-2.0, 1.0, -5.0));
		
		
		return functions;
	}

}
