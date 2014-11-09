package edu.up.math.simplexsolver;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class EquationParserTest {

	@Test
	public void parseTests() {
		List<TestFunction> functions = FunctionSampleFactory.createSampleFunctions();
		
		for (TestFunction function : functions) {
			EquationParser parser = new EquationParser();
			
			List<String> variables = parser.extractVariables(function.getFunction());
			List<Double> values = parser.extractCoefficientValues(function.getFunction());
			
			Assert.assertArrayEquals(function.getFunction() + " - falha ao fazer parser das variaveis", variables.toArray(new String[variables.size()]), function.getVariables().toArray(new String[function.getVariables().size()]));
			Assert.assertArrayEquals(function.getFunction() + " - falha ao fazer parser dos valores", values.toArray(new Double[values.size()]), function.getValues().toArray(new Double[function.getValues().size()]));
		}
	}
}
