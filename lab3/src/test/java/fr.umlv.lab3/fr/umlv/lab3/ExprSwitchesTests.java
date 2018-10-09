package fr.umlv.lab3;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import fr.umlv.lab3.ExprSwitches.Msg;



class SwitchesTests {
	static class TestData {
		IntFunction function;
		int parameter;
		String expectedReturn;
		
		public TestData(IntFunction function,int parameter, String expectedReturn) 			{
			this.function = function;
			this.parameter = parameter;
			this.expectedReturn = expectedReturn;
		}
		public IntFunction getFunction() {
			return function;
		}
		public int getParameter() {
			return parameter;
		}
		public String getExpectedReturn() {
			return expectedReturn;
		}
	}
	
	@ParameterizedTest
	@MethodSource("testDataProvider")
	void intSwitchtest(TestData function) {
		assertEquals(function.expectedReturn, function.getFunction().apply(function.getParameter()));
	}
	@ParameterizedTest
	@MethodSource("getIntFunction")
	void intSwitchtestError(IntFunction<String> function) {
		assertThrows(IllegalArgumentException.class, () -> function.apply(-1));
	}
	
	@Test
	void StringSwitchtest() {
		assertEquals("zero", ExprSwitches.stringSwitch("foo"));
		assertEquals("one", ExprSwitches.stringSwitch("bar"));
		assertEquals("a lot", ExprSwitches.stringSwitch("baz"));
		assertEquals("zero", ExprSwitches.stringSwitch("viva zorg"));
	}
	@Test
	void StringSwitchtestError() {
		assertThrows(IllegalArgumentException.class, () -> ExprSwitches.stringSwitch("Toto"));
	}
	@Test
	void EnumSwitchtest() {
		assertEquals("zero", ExprSwitches.enumSwitch(Msg.DEBUG));
		assertEquals("one", ExprSwitches.enumSwitch(Msg.WARNING));
		assertEquals("a lot", ExprSwitches.enumSwitch(Msg.INFO));
		assertEquals("zero", ExprSwitches.enumSwitch(Msg.ERROR));
	}
	static Stream<TestData> testDataProvider() {
	    List<TestData> lst = List.of(new TestData(ExprSwitches::intSwitch, 0, "zero"), new TestData(ExprSwitches::intSwitch, 3, "zero"), new TestData(ExprSwitches::intSwitch2, 3, "zero"),new TestData(ExprSwitches::intSwitch, 1, "one"), new TestData(ExprSwitches::intSwitch, 2, "a lot"), new TestData(ExprSwitches::intSwitch2, 0, "zero"), new TestData(ExprSwitches::intSwitch2, 10, "one"), new TestData(ExprSwitches::intSwitch2, 100, "a lot"));
	    return lst.stream();
	}
	static Stream<IntFunction> getIntFunction() {
		List<IntFunction> lst = List.of(ExprSwitches::intSwitch, ExprSwitches::intSwitch2);
		return lst.stream();
	}
}

