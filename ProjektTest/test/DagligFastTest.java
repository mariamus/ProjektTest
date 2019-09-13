package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import ordination.DagligFast;
import ordination.Dosis;

public class DagligFastTest {

	Dosis d = new Dosis(LocalTime.of(05, 00), 1);
	Dosis d2 = new Dosis(LocalTime.of(11, 00), 2);
	Dosis d3 = new Dosis(LocalTime.of(17, 00), 1);
	Dosis d4 = new Dosis(LocalTime.of(23, 00), 0);
	Dosis d5 = new Dosis(LocalTime.of(00, 00), 0);

	Dosis[] doser = { d, d2, d3, d4 };
	Dosis[] doser2 = { d5, d5, d5, d5 };

	private DagligFast df = new DagligFast(LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 12), null, doser);
	private DagligFast df2 = new DagligFast(LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 12), null, doser2);

	
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
