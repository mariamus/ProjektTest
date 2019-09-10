package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

	private  ArrayList<Dosis> doser = new ArrayList<>();
	private LocalDate startDen;
	private LocalDate slutDen;

	public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
		super(startDen, slutDen);
		this.startDen = startDen;
		this.slutDen = slutDen;
	}

	public void opretDosis(LocalTime tid, double antal) {
		Dosis d = new Dosis(tid, antal);

		doser.add(d);

	}

	public ArrayList<Dosis> getDoser() {
		return doser;
	}

	/**
	 * Returnerer den totale dosis der er givet i den periode ordinationen er gyldig
	 * 
	 * @return
	 */
	@Override
	public double samletDosis() {
		double antal = 0.0;
		for (int i = 0; i < doser.size(); i++) {
			antal += doser.get(i).getAntal();
		}
		return antal * antalDage();
	}

	/**
	 * Returnerer den gennemsnitlige dosis givet pr dag i den periode ordinationen
	 * er gyldig
	 * 
	 * @return
	 */
	@Override
	public double doegnDosis() {
		return samletDosis() / antalDage();
	}

	/**
	 * Returnerer ordinationstypen som en String
	 * 
	 * @return
	 */
	@Override
	public String getType() {
		return "Type : daglig skæv";
	}

}