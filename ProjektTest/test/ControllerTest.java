package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import controller.Controller;
import ordination.DagligFast;
import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;

public class ControllerTest {

	Controller c = Controller.getTestController();
	Patient p = new Patient("121256-0512", "Jane Jensen", 63.4);
	Laegemiddel lm = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

	@Test
	public void testOpretPNOrdination() {
		PN pn = c.opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1), p, lm, 10.0);
		assertTrue(pn instanceof PN);

	}

	@Test
	public void testOpretPNOrdinationUgyldigDato() {

		try {
			PN pn = c.opretPNOrdination(LocalDate.of(2019, 1, 3), LocalDate.of(2019, 1, 1), p, lm, 10.0);
			assertFalse(pn instanceof PN);

		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Startdato er efter slutdato");
		}

	}

	@Test
	public void testOpretDagligFastOrdination() {
		DagligFast df = c.opretDagligFastOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1), p, lm, 1, 1, 2,
				0);

		assertTrue(df instanceof DagligFast);
		assertEquals(1, df.getDoser()[0].getAntal(), 0);
		assertEquals(1, df.getDoser()[1].getAntal(), 0);
		assertEquals(2, df.getDoser()[2].getAntal(), 0);
		assertEquals(0, df.getDoser()[3].getAntal(), 0);

	}

	@Test
	public void testOpretDagligSkaevOrdination() {

		double[] antal = new double[1];
		LocalTime[] tid = new LocalTime[1];
		antal[0] = 2;
		tid[0] = LocalTime.of(9, 00);

		DagligSkaev ds = c.opretDagligSkaevOrdination(LocalDate.of(2019, 1, 14), LocalDate.of(2019, 1, 15), p, lm, tid,
				antal);

		assertTrue(ds instanceof DagligSkaev);

		assertEquals(2, ds.getDoser().get(0).getAntal(), 0);

	}
	
	@Test
	public void testOrdinationPNAnvendt0dage() {
		// arrange
		LocalDate date = LocalDate.now();
		PN proNec = c.opretPNOrdination(LocalDate.now(), date, p, lm, 12);
		// act
		c.ordinationPNAnvendt(proNec, LocalDate.now());
		//assert
		assertTrue(proNec.getDatoerGivet().contains(date));
	}
	
	@Test
	public void testOrdinationPNAnvendt3dage() {
		// arrange
		LocalDate date = LocalDate.now().plusDays(3);
		PN proNec = c.opretPNOrdination(LocalDate.now(), date, p, lm, 12);
		// act
		c.ordinationPNAnvendt(proNec, date);
		//assert
		assertTrue(proNec.getDatoerGivet().contains(date));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOrdinationPNAnvendtUgyldigDato() {
		// arrange
		PN proNec = c.opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(3), p, lm, 12);
		// act
		c.ordinationPNAnvendt(proNec, LocalDate.now().plusDays(15));
		fail("Ordinationen forsøges anvendt uden for den tilladte peridode");
	}
}
