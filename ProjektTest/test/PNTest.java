package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import ordination.Laegemiddel;
import ordination.PN;

public class PNTest {
	
	LocalDate startDate = LocalDate.now();
	LocalDate slutDate = LocalDate.now().plusDays(3);
	Laegemiddel lm = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
	double antalEnheder = 3.5;
	
	PN pn = new PN(startDate, slutDate, lm, antalEnheder);



	@Test
	public void testPN() {
		assertNotNull(pn);
		assertEquals(startDate, pn.getStartDen());
		assertEquals(slutDate, pn.getSlutDen());
		assertEquals(lm, pn.getLaegemiddel());
		assertEquals(3.5, pn.getAntalEnheder(), 0.000001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPNMinusEnheder() {
		PN pn2 = new PN(startDate, slutDate, lm, -3.5);
		fail("Burde være ugyldig antal enheder");
	}

	@Test
	public void testGivDosis() {
		assertTrue(pn.givDosis(startDate));
		assertTrue(pn.getDatoerGivet().contains(startDate));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGivDosisUgyldigDato() {
		pn.givDosis(startDate.minusDays(3));
		fail("Burde være ugyldig dato");
	}
	
	@Test
	public void testSamletDosis() {
		pn.givDosis(startDate);
		pn.givDosis(slutDate);
		assertEquals(7, pn.samletDosis(), 0.00001);
	}

	@Test
	public void testDoegnDosis() {
		pn.givDosis(startDate);
		pn.givDosis(slutDate);
		
		assertEquals(1.75, pn.doegnDosis(), 0.000001);
	}
}