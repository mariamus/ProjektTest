package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import ordination.PN;
import ordination.Patient;

public class PNTest {

	Patient testpatient;
	PN testpn;

	@Test
	public void testGivMinusDosis() {
		// Arrange
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), 4);
		// Act
		LocalDate datominus = LocalDate.now().minusDays(3);
		// Assert
		assertFalse(testpn.givDosis(datominus));
	}

	@Test
	public void testGivDosisNul() {
		// Arrange
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), 4);
		// Act
		LocalDate dato0 = LocalDate.now();
		// Assert
		assertTrue(testpn.givDosis(dato0));
	}

	@Test
	public void testGivDosis4() {
		// Arrange
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), 4);
		// Act
		LocalDate dato4 = LocalDate.now().plusDays(4);
		// Assert
		assertTrue(testpn.givDosis(dato4));
	}

	@Test
	public void testGivDosis7() {
		// Arrange
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), 4);
		// Act
		LocalDate dato7 = LocalDate.now().plusDays(7);
		// Assert
		assertTrue(testpn.givDosis(dato7));
	}

	@Test
	public void testGivDosis8() {
		// Arrange
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), 4);
		// Act
		LocalDate dato8 = LocalDate.now().plusDays(8);
		// Assert
		assertFalse(testpn.givDosis(dato8));
	}

	@Test
	public void testDoegnDosisNulEnheder() {
		// Arrange
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(5), 0);
		// Assert
		assertEquals(0, testpn.doegnDosis(), 00.1);

	}

	@Test
	public void testDoegnDosis1Enheder() {
		// Arrange
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(1), 1);
		// Act
		testpn.givDosis(LocalDate.now());
		// Assert
		assertEquals(1, testpn.doegnDosis(), 00.1);
	}

	@Test
	public void testDoegnDosisException1() {
		// Arrange
		testpn = new PN(LocalDate.now().minusDays(5), LocalDate.now(), 1);
		// Act
		try {
			for (int i = 0; i < 10; i++) {
				testpn.givDosis(LocalDate.now());
			}
		} catch (ArithmeticException e) {
			// Assert
			assertEquals("Ikke muligt.", e.getMessage());
		}
	}

	@Test
	public void testDoegnDosisException2() {
		try {
			testpn = new PN(LocalDate.now(), LocalDate.now(), 1);
		} catch (ArithmeticException e) {
			assertEquals("Ikke muligt.", e.getMessage());
		}
	}

	@Test
	public void testDoegnDosisException3() {
		testpn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), 5);
		try {
			for (int i = 0; i < 5; i++) {
				testpn.givDosis(LocalDate.now());
			}
		} catch (ArithmeticException e) {
			assertEquals("Ikke muligt.", e.getMessage());
		}
	}
}