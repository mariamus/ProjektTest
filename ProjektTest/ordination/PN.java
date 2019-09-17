package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination {

	private double antalEnheder;
	private ArrayList<LocalDate> datoerGivet = new ArrayList<>();

	public PN(LocalDate startDen, LocalDate slutDen, Laegemiddel laegemiddel, double antalEnheder) {
		super(startDen, slutDen, laegemiddel);
		if (antalEnheder <= 0) {
			throw new IllegalArgumentException("Antal enheder må ikke være under 0");
		} else {
			this.antalEnheder = antalEnheder;
		}
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
		try {
			if (givesDen.compareTo(super.getStartDen()) >= 0 && givesDen.compareTo(super.getSlutDen()) <= 0) {
				datoerGivet.add(givesDen);
				return true;
			}
			else {
				throw new IllegalArgumentException("Ordinationen forsøges anvendt uden for den givne periode");
			}
		} catch (NullPointerException e) {
			System.out.println(e);
			return false;
		}
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

	public ArrayList<LocalDate> getDatoerGivet() {
		return datoerGivet;
	}

	public double getAntalEnheder() {
		return antalEnheder;
	}

	public String getType() {
		return "PN";
	}
}
