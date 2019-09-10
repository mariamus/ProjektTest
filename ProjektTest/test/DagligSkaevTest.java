package test;

import static org.junit.Assert.assertEquals;


import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import ordination.DagligSkaev;

public class DagligSkaevTest {

	@Test
	public void testSamletDosis() {

		// Arrange
		DagligSkaev Ds1 = new DagligSkaev(LocalDate.of(2019, 2, 26), LocalDate.of(2019, 2, 28));
		Ds1.opretDosis(LocalTime.of(9, 30), 2);
		DagligSkaev Ds2 = new DagligSkaev(LocalDate.of(2019, 3, 1), LocalDate.of(2019, 3, 26));
		Ds2.opretDosis(LocalTime.of(14, 00), 3);
		DagligSkaev Ds3 = new DagligSkaev(LocalDate.of(2019, 2, 26), LocalDate.of(2019, 3, 26));
		Ds3.opretDosis(LocalTime.of(00, 15), 1);
		
		// Act
		double actual1 = Ds1.samletDosis();
		double actual2 = Ds2.samletDosis();
		double actual3 = Ds3.samletDosis();

		// Assert
		assertEquals(6, actual1, 0);
		assertEquals(78, actual2, 0);
		assertEquals(29, actual3, 0);
	}

	@Test
	public void testDoegnDosis() {

		// Arrange
		DagligSkaev Ds1 = new DagligSkaev(LocalDate.of(2019, 2, 26), LocalDate.of(2019, 2, 28));
		Ds1.opretDosis(LocalTime.of(9, 30), 2);
		DagligSkaev Ds4 = new DagligSkaev(LocalDate.of(2019, 3, 1), LocalDate.of(2019, 3, 26));
		Ds4.opretDosis(LocalTime.of(14, 00), 3);
		Ds4.opretDosis(LocalTime.of(20, 00), 3);
		DagligSkaev Ds3 = new DagligSkaev(LocalDate.of(2019, 2, 26), LocalDate.of(2019, 3, 26));
		Ds3.opretDosis(LocalTime.of(00, 15), 1);
		
		// Act
		double actual1 = Ds1.doegnDosis();
		double actual4 = Ds4.doegnDosis();
		double actual3 = Ds3.doegnDosis();

		// Assert
		assertEquals(2, actual1, 0);
		assertEquals(6, actual4, 0);
		assertEquals(1, actual3, 0);
	}

}
