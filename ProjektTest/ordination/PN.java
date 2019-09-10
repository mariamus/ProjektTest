package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;

public class PN extends Ordination {

	private double antalEnheder;
	private ArrayList<LocalDate> gangeGivet = new ArrayList<>();

	public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
		super(startDen, slutDen);
		this.antalEnheder = antalEnheder;
	}

	public boolean givDosis(LocalDate givesDen) {
		if (antalEnheder > 0) {
			if ((givesDen.isAfter(getStartDen()) || givesDen.equals(getStartDen()))
					&& (givesDen.isBefore(getSlutDen()) || givesDen.isEqual(getSlutDen()))) {
				gangeGivet.add(givesDen);
				Collections.sort(gangeGivet);
				return true;
			}
		}
		return false;
	}

	@Override
	public double doegnDosis() {
		try {
			return (gangeGivet.size() * antalEnheder) / ChronoUnit.DAYS.between(getStartDen(), getSlutDen());
		} catch (ArithmeticException e) {
			throw new ArithmeticException("Ikke muligt.");
		}
	}

	@Override
	public double samletDosis() {
		return this.antalEnheder * this.gangeGivet.size();
	}

	public int getAntalGangeGivet() {
		return gangeGivet.size();
	}

	public double getAntalEnheder() {
		return antalEnheder;
	}

	@Override
	public String getType() {
		return "Type : PN";
	}

}
