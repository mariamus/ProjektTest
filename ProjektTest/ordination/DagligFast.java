package ordination;

import java.time.*;

public class DagligFast extends Ordination {

	private Dosis[] doser = new Dosis[4];

	public DagligFast(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, Dosis[] doser) {
		// TODO fix antal, saa doser skal vaere fire doser
		super(startDen, slutDen, laegemiddel);
		this.doser = doser;
	}

	public Dosis[] getDoser() {
		return doser;
	}

	@Override
	public double samletDosis() {
		double maengde = 0;
		for (int i = 0; i < doser.length; i++) {
			maengde += doser[i].getAntal();
		}
		return maengde * super.antalDage();
	}

	@Override
	public double doegnDosis() {
		double maengde = 0;
		for (int i = 0; i < doser.length; i++) {
			maengde += doser[i].getAntal();
		}
		return maengde;
	}

	@Override
	public String getType() {
		return "Fast";
	}
}
