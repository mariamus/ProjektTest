package ordination;

import java.time.LocalTime;

public class Dosis {
    private LocalTime tid;
    private double antal;
    private Enhed enhed;

    public Dosis(LocalTime tid, double antal, Enhed enhed) {
        super();
        this.tid = tid;
        this.antal = antal;
        this.enhed = enhed;
    }

    public double getAntal() {
        return antal;
    }

    public void setAntal(double antal) {
        this.antal = antal;
    }

    public LocalTime getTid() {
        return tid;
    }

    public void setTid(LocalTime tid) {
        this.tid = tid;
    }

    public Enhed getEnhed() {
		return enhed;
	}

	public void setEnhed(Enhed enhed) {
		this.enhed = enhed;
	}

	@Override
    public String toString(){
        return "Kl: " + tid + "   antal:  " + antal;
    }

}
