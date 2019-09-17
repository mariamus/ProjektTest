package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

	private ArrayList<Dosis> doser;

	public DagligSkaev(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, ArrayList<Dosis> doser) {
		super(startDen, slutDen, laegemiddel);
		this.doser = doser;
	}

	public ArrayList<Dosis> getDoser() {
		return doser;
	}

	public Dosis opretDosis(LocalTime tid, double antal) {
		if (doser == null) {
			throw new NullPointerException("Doser er ikke initialiseret");
		} else {
			Dosis dosis = new Dosis(tid, antal);
			doser.add(dosis);
			
			return dosis;
		}
	}

	@Override
	public double samletDosis() {
		double sum = 0;
		for (Dosis d : doser) {
			sum += d.getAntal();
		}

		return sum * super.antalDage();
	}

	@Override
	public double doegnDosis() {
		double sum = 0;
		for (Dosis d : doser) {
			sum += d.getAntal();
		}

		return sum;
	}

	@Override
	public String getType() {

		return "Skaev";
	}
}
