package fr.umlv.javainside.labOne;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SumsTests {
	@Test
	public void Sumtest() {
		assertEquals(4950,Sums.loopSum(100));
		assertEquals(4950,Sums.StreamSum(100));
	}
}