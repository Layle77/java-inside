package fr.umlv.javaInside;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SwitchesTests {
	@Test
	void intSwitchTest() {
		assertThrows(IllegalArgumentException.class,()->Switches.intSwitch(-1));
		assertEquals("zero",Switches.intSwitch(0));
		assertEquals("one",Switches.intSwitch(1));
		assertEquals("alot",Switches.intSwitch(2));
		
	} 
}
