package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import ordination.DagligFast;
import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.PN;
import ordination.Patient;

public class ControllerTest {

	Patient p;
	Laegemiddel lm;
	Controller c;

	@Before
	public void setup() {
		c = Controller.getTestController();
		p = new Patient("121256-0512", "Jane Jensen", 63.4);
		lm = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");

	}

	@Test
	public void testOpretPNOrdination() {
		PN pn = c.opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1), p, lm, 10.0);
		assertTrue(pn instanceof PN);

//		PN pn1 = c.opretPNOrdination(LocalDate.of(2019, 1, 3), LocalDate.of(2019, 1, 1), p, lm, 10.0);
//		assertFalse(pn1 instanceof PN);

		assertEquals(10, pn.getAntalEnheder(), 0);

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

		double[] antal = new double[2];
		LocalTime[] tid = new LocalTime[2];
		antal[0] = 2;
		tid[0] = LocalTime.of(9, 00);

		DagligSkaev ds = c.opretDagligSkaevOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 2, 1), p, lm, tid,
				antal);

		assertTrue(ds instanceof DagligSkaev);

		assertEquals(2, ds.getDoser().get(0).getAntal(), 0);

	}

	@Test
	public void testOrdinationPNAnvendt() {

		Patient p1 = new Patient("121256-0512", "Jane Jensen", 63.4);
		Laegemiddel lm1 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
		PN pn = Controller.getTestController().opretPNOrdination(LocalDate.of(2019, 1, 1), LocalDate.of(2019, 1, 12),
				p1, lm1, 12);
		Controller.getTestController().ordinationPNAnvendt(pn, LocalDate.of(2019, 1, 12));

//		Controller.getTestController().ordinationPNAnvendt(pn, LocalDate.of(2019, 1, 14));

	}

	@Test
	public void testAnbefaletDosisPrDoegn() {
		// Arrange
		Patient p1 = new Patient("121256-0512", "Jane Jensen", 1.0);
		Laegemiddel lm1 = new Laegemiddel("Methotrexat", 0.01, 0.015, 0.02, "Styk");

		// Act
		Double actual = Controller.getTestController().anbefaletDosisPrDoegn(p1, lm1);

		// Assert
		assertEquals(0.01, actual, 0);

		// Arrange
		Patient p2 = new Patient("121256-0512", "Jane Jensen", 13.0);
		Laegemiddel lm2 = new Laegemiddel("Paracetamol", 1.0, 1.5, 2.0, "Ml");
		// Act
		Double actual2 = Controller.getTestController().anbefaletDosisPrDoegn(p2, lm2);
		// Assert
		assertEquals(13.0, actual2, 0);

		// Arrange
		Patient p3 = new Patient("121256-0512", "Jane Jensen", 24.0);
		Laegemiddel lm3 = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
		// Act
		Double actual3 = Controller.getTestController().anbefaletDosisPrDoegn(p3, lm3);
		// Assert
		assertEquals(2.4, actual3, 0.01);

		// Arrange
		Patient p4 = new Patient("121256-0512", "Jane Jensen", 25.0);
		Laegemiddel lm4 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
		// Act
		Double actual4 = Controller.getTestController().anbefaletDosisPrDoegn(p4, lm4);
		// Assert
		assertEquals(0.625, actual4, 0);

		// Arrange
		Patient p5 = new Patient("121256-0512", "Jane Jensen", 26.0);
		Laegemiddel lm5 = new Laegemiddel("Paracetamol", 1.0, 1.5, 2.0, "Ml");
		// Act
		Double actual5 = Controller.getTestController().anbefaletDosisPrDoegn(p5, lm5);
		// Assert
		assertEquals(39, actual5, 0);

		// Arrange
		Patient p6 = new Patient("121256-0512", "Jane Jensen", 70.0);
		Laegemiddel lm6 = new Laegemiddel("Paracetamol", 1.0, 1.5, 2.0, "Ml");
		// Act
		Double actual6 = Controller.getTestController().anbefaletDosisPrDoegn(p6, lm6);
		// Assert
		assertEquals(105, actual6, 0);

		// Arrange
		Patient p7 = new Patient("121256-0512", "Jane Jensen", 119.0);
		Laegemiddel lm7 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
		// Act
		Double actual7 = Controller.getTestController().anbefaletDosisPrDoegn(p7, lm7);
		// Assert
		assertEquals(2.975, actual7, 0);

		// Arrange
		Patient p8 = new Patient("121256-0512", "Jane Jensen", 120.0);
		Laegemiddel lm8 = new Laegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
		// Act
		Double actual8 = Controller.getTestController().anbefaletDosisPrDoegn(p8, lm8);
		// Assert
		assertEquals(3, actual8, 0);

		// Arrange
		Patient p9 = new Patient("121256-0512", "Jane Jensen", 121.0);
		Laegemiddel lm9 = new Laegemiddel("Paracetamol", 1.0, 1.5, 2.0, "Ml");
		// Act
		Double actual9 = Controller.getTestController().anbefaletDosisPrDoegn(p9, lm9);
		// Assert
		assertEquals(242, actual9, 0);

		// Arrange
		Patient p10 = new Patient("121256-0512", "Jane Jensen", 140.0);
		Laegemiddel lm10 = new Laegemiddel("Methotrexat", 0.01, 0.015, 0.02, "Styk");

		// Act
		Double actual10 = Controller.getTestController().anbefaletDosisPrDoegn(p10, lm10);

		// Assert
		assertEquals(2.8, actual10, 0.01);

	}

}
