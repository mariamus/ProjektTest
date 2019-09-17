package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import ordination.DagligFast;
import ordination.Dosis;
import ordination.Laegemiddel;

public class DagligFastTest {

	Dosis d = new Dosis(LocalTime.of(05, 00), 1);
	Dosis d2 = new Dosis(LocalTime.of(11, 00), 2);
	Dosis d3 = new Dosis(LocalTime.of(17, 00), 1);
	Dosis d4 = new Dosis(LocalTime.of(23, 00), 0);
	Dosis d5 = new Dosis(LocalTime.of(00, 00), 0);

	Dosis[] doser = { d, d2, d3, d4 };
	Dosis[] doser2 = { d5, d5, d5, d5 };

	Laegemiddel lm = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

	DagligFast df = new DagligFast(LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 12), lm, doser);
	DagligFast df2 = new DagligFast(LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 12), lm, doser2);

	// Test af implementationen af Ordination klassens antalDage() metode
	@Test
	public void testAntalDage3dage() {
		assertEquals(3, df.antalDage());
	}

	// Test af implementationen af Ordination klassens antalDage() metode
	@Test
	public void testAntalDage1dag() {
		DagligFast df3 = new DagligFast(LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 10), lm, doser2);

		assertEquals(1, df3.antalDage());
	}

	@Test
	public void testDagligFast() {
		assertNotNull(df);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUgyldigDatoDagligFast() {
		DagligFast df2 = new DagligFast(LocalDate.of(2019, 10, 10), LocalDate.of(2019, 9, 9), null, doser);
		assertNotNull(df2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testUgyldigDosis() {
		Dosis d6 = new Dosis(LocalTime.of(23, 00), -1);
		assertNotNull(d6);
		fail("Mangler exception");
	}

	@Test
	public void testSamletDosis() {
		assertEquals(12, df.samletDosis(), 0.00001);
		assertEquals(0, df2.samletDosis(), 0.00001);

	}

	@Test
	public void testDoegnDosis() {
		assertEquals(4, df.doegnDosis(), 0.00001);
		assertEquals(0, df2.doegnDosis(), 0.00001);

	}
}
