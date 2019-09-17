package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import ordination.DagligSkaev;
import ordination.Dosis;
import ordination.Laegemiddel;

public class DagligSkaevTest {
	
	Laegemiddel lm = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
	
	Dosis d = new Dosis(LocalTime.of(02, 00), 1);
	Dosis d2 = new Dosis(LocalTime.of(06, 00), 2);
	Dosis d3 = new Dosis(LocalTime.of(10, 00), 1);
	Dosis d4 = new Dosis(LocalTime.of(14, 00), 0);
	Dosis d5 = new Dosis(LocalTime.of(18, 00), 0);
	
	Dosis[] doserArray = { d, d2, d3, d4 };

	
	


	
	@Test
	public void testDagligSkaev() {
		LocalDate startDate = LocalDate.now();
		LocalDate slutDate = LocalDate.now().plusDays(3);
		ArrayList<Dosis> doser = new ArrayList<>(Arrays.asList(doserArray));
		DagligSkaev ds = new DagligSkaev(startDate, slutDate, lm, doser);
		
		
		assertNotNull(ds);
		assertEquals(startDate, ds.getStartDen());
		assertEquals(slutDate, ds.getSlutDen());
		assertEquals(lm, ds.getLaegemiddel());
		assertEquals(doser, ds.getDoser());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDagligSkaevUgyldigDato() {
		LocalDate startDate = LocalDate.now();
		LocalDate slutDate = LocalDate.now().minusDays(3);
		ArrayList<Dosis> doser = new ArrayList<>(Arrays.asList(doserArray));
		DagligSkaev ds = new DagligSkaev(startDate, slutDate, lm, doser);
		
		fail("Datoen burde være ugyldig");
	}

	@Test
	public void testSamletDosis() {
		LocalDate startDate = LocalDate.now();
		LocalDate slutDate = LocalDate.now().plusDays(3);
		ArrayList<Dosis> doser = new ArrayList<>(Arrays.asList(doserArray));
		DagligSkaev ds = new DagligSkaev(startDate, slutDate, lm, doser);
		
		assertEquals(16, ds.samletDosis(), 0.000001);
	}

	@Test
	public void testDoegnDosis() {
		LocalDate startDate = LocalDate.now();
		LocalDate slutDate = LocalDate.now().plusDays(3);
		ArrayList<Dosis> doser = new ArrayList<>(Arrays.asList(doserArray));
		DagligSkaev ds = new DagligSkaev(startDate, slutDate, lm, doser);
		
		assertEquals(4, ds.doegnDosis(), 0.000001);
	}



	@Test
	public void testOpretDosis() {
		LocalDate startDate = LocalDate.now();
		LocalDate slutDate = LocalDate.now().plusDays(3);
		ArrayList<Dosis> doser = new ArrayList<>(Arrays.asList(doserArray));
		DagligSkaev ds = new DagligSkaev(startDate, slutDate, lm, doser);
		
		Dosis testDosis = ds.opretDosis(LocalTime.of(0, 0), 2);
		assertTrue(ds.getDoser().contains(testDosis));
		
	}

}
