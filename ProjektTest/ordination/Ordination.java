package ordination;

import java.time.*;
import java.time.temporal.ChronoUnit;

public abstract class Ordination {
	private LocalDate startDen;
	private LocalDate slutDen;
	private Laegemiddel laegemiddel;

	public Ordination(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel) {
		// if-else lavet, saa ingen mennesker doer
		if (startDen.isAfter(slutDen)) {
			throw new IllegalArgumentException("Startdato er efter slutdato");
		} else {
			this.startDen = startDen;
			this.slutDen = slutDen;
			this.laegemiddel = laegemiddel;
		}
	}

	public LocalDate getStartDen() {
		return startDen;
	}

	public LocalDate getSlutDen() {
		return slutDen;
	}

	public Laegemiddel getLaegemiddel() {
		return laegemiddel;
	}

	public void setLaegemiddel(Laegemiddel laegemiddel) {
		this.laegemiddel = laegemiddel;
	}

	/**
	 * Antal hele dage mellem startdato og slutdato. Begge dage inklusive.
	 * 
	 * @return antal dage ordinationen gælder for
	 */
	public int antalDage() {
		return (int) ChronoUnit.DAYS.between(startDen, slutDen) + 1;
	}

	@Override
	public String toString() {
		return startDen.toString();
	}

	/**
	 * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
	 * 
	 * @return
	 */
	public abstract double samletDosis();

	/**
	 * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen
	 * er gyldig
	 * 
	 * @return
	 */
	public abstract double doegnDosis();

	/**
	 * Returnerer ordinationstypen som en String
	 * 
	 * @return
	 */
	public abstract String getType();
}
