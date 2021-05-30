package osobe;

import enumi.Odeljenje;
import enumi.Pol;

public class Dispecar extends Osoba {

	private double plata;
	private String brojTelefonskeLinije;
	private Odeljenje odeljenje;

	public Dispecar() {
		this.plata = 0;
		this.brojTelefonskeLinije = "";
		this.odeljenje = null;
	}

	public Dispecar(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
                    Pol pol, String brojTelefona, boolean obrisan, double plata, String brojTelefonskeLinije, Odeljenje odeljenje) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
		this.plata = plata;
		this.brojTelefonskeLinije = brojTelefonskeLinije;
		this.odeljenje = odeljenje;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getBrojTelefonskeLinije() {
		return brojTelefonskeLinije;
	}

	public void setBrojTelefonskeLinije(String brojTelefonskeLinije) {
		this.brojTelefonskeLinije = brojTelefonskeLinije;
	}

	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}

	@Override
	public String toString() {
		return "Dispecar{" +
				"plata=" + plata +
				", brojTelefonskeLinije='" + brojTelefonskeLinije + '\'' +
				", odeljenje=" + odeljenje +
				"} " + super.toString();
	}
}