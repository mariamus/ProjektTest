package test;


import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Test;

import ordination.DagligFast;

public class DagligFastTest {

	@Test
	public void testSamletDosis() {
		//Arrange
		DagligFast Df1 = new DagligFast(1.0, 2.0, 0.0, 1.0, LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 11));
		DagligFast Df2 = new DagligFast(2.0, 2.0, 0.0, 0.0, LocalDate.of(2019, 1, 15), LocalDate.of(2019, 1, 17));
		DagligFast Df3 = new DagligFast(1.0, 0.0, 1.0, 1.0, LocalDate.of(2019, 1, 20), LocalDate.of(2019, 1, 24));
		//Act
		double dosis1 = Df1.samletDosis();
		double dosis2 = Df2.samletDosis();
		double dosis3 = Df3.samletDosis();
		//Assert
		assertEquals(8.0, dosis1, 0.1);
		assertEquals(12.0, dosis2, 0.1);
		assertEquals(15.0, dosis3, 0.1);
	}

	@Test
	public void testDoegnDosis() {
		//Arrange
		DagligFast Df1 = new DagligFast(1.0, 2.0, 0.0, 1.0, LocalDate.of(2019, 1, 10), LocalDate.of(2019, 1, 11));
		DagligFast Df2 = new DagligFast(2.0, 2.0, 0.0, 0.0, LocalDate.of(2019, 1, 15), LocalDate.of(2019, 1, 17));
		DagligFast Df3 = new DagligFast(1.0, 0.0, 1.0, 1.0, LocalDate.of(2019, 1, 20), LocalDate.of(2019, 1, 24));
		//Ace
		double doegnDosis1 = Df1.doegnDosis();
		double doegnDosis2 = Df2.doegnDosis();
		double doegnDosis3 = Df3.doegnDosis();
		//Assert
		assertEquals(4.0, doegnDosis1, 0.1);
		assertEquals(4.0, doegnDosis2, 0.1);
		assertEquals(3.0, doegnDosis3, 0.1);

		
	
	}

}
