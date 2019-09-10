package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination {

	private double antalEnheder;
	private ArrayList<LocalDate> datoerGivet = new ArrayList<>();

	public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antalEnheder) {
		super(startDen, slutDen, laegemiddel);
		this.antalEnheder = antalEnheder;
	}

	/**
	 * Registrerer at der er givet en dosis paa dagen givesDen Returnerer true hvis
	 * givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
	 * Retrurner false ellers og datoen givesDen ignoreres
	 * 
	 * @param givesDen
	 * @return
	 */
	public boolean givDosis(LocalDate givesDen) {
		if (givesDen.compareTo(super.getStartDen()) >= 0 && givesDen.compareTo(super.getSlutDen()) <= 0) {
			datoerGivet.add(givesDen);
			return true;
		}
		return false;
	}

	public double doegnDosis() {
		return (datoerGivet.size() * antalEnheder) / super.antalDage();
	}

	public double samletDosis() {
		return datoerGivet.size() * antalEnheder;
	}

	/**
	 * Returnerer antal gange ordinationen er anvendt
	 * 
	 * @return
	 */
	public int getAntalGangeGivet() {
		return datoerGivet.size();
	}

	public double getAntalEnheder() {
		return antalEnheder;
	}

	public String getType() {
		return "PN";
	}
}
