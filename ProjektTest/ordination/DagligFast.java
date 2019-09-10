package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DagligFast extends Ordination {

	private Dosis[] doser = new Dosis[4];

	public DagligFast(double morgen, double middag, double aften, double nat, LocalDate startDen, LocalDate slutDen) {
		super(startDen, slutDen);

		Dosis dosisMorgen = new Dosis(LocalTime.of(05, 00), morgen);
		Dosis dosisMiddag = new Dosis(LocalTime.of(11, 00), middag);
		Dosis dosisAften = new Dosis(LocalTime.of(17, 00), aften);
		Dosis dosisNat = new Dosis(LocalTime.of(23, 00), nat);

		doser[0] = dosisMorgen;
		doser[1] = dosisMiddag;
		doser[2] = dosisAften;
		doser[3] = dosisNat;
	}

	public void opretDosis(double morgen, double middag, double aften, double nat, LocalDate startDen,
			LocalDate slutDate) {

		Dosis dosisMorgen = new Dosis(LocalTime.of(05, 00), morgen);
		Dosis dosisMiddag = new Dosis(LocalTime.of(11, 00), middag);
		Dosis dosisAften = new Dosis(LocalTime.of(17, 00), aften);
		Dosis dosisNat = new Dosis(LocalTime.of(23, 00), nat);

		doser[0] = dosisMorgen;
		doser[1] = dosisMiddag;
		doser[2] = dosisAften;
		doser[3] = dosisNat;
	}

	@Override
	public double doegnDosis() {
		double total = 0.0;
		for (int i = 0; i < doser.length; i++) {
			total += doser[i].getAntal();
		}
		return total;
	}

	@Override
	public double samletDosis() {
		int days = ((int) ChronoUnit.DAYS.between(getStartDen(), getSlutDen()) + 1 );
		return days * doegnDosis();

	}

	public Dosis[] getDoser() {
		return doser;
	}

	@Override
	public String getType() {
		return "Fast";
	}
}