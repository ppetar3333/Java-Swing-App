package osobe;

import automobili.Automobil;
import enumi.Pol;
import enumi.StatusVozacaIautomobila;

public class Vozac extends Osoba {

	private double plata;
	private int brojClanskeKarte;
	private Automobil automobili;
	private double ocena;
	private StatusVozacaIautomobila statusVozaca;

	public Vozac() {
		this.plata = 0;
		this.brojClanskeKarte = 0;
		this.automobili = null;
		this.ocena = 0;
		this.statusVozaca = null;
	}

	public Vozac(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa,
				 Pol pol, String brojTelefona, boolean obrisan,double plata, int brojClanskeKarte, Automobil automobili, double ocena, StatusVozacaIautomobila statusVozaca) {
		super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobili = automobili;
		this.ocena = ocena;
		this.statusVozaca = statusVozaca;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public int getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(int brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}

	public Automobil getAutomobili() {
		return automobili;
	}

	public void setAutomobili(Automobil automobili) {
		this.automobili = automobili;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public StatusVozacaIautomobila getStatusVozaca() {
		return statusVozaca;
	}

	public void setStatusVozaca(StatusVozacaIautomobila statusVozaca) {
		this.statusVozaca = statusVozaca;
	}

	@Override
	public String toString() {
		return "Vozac{" +
				"plata=" + plata +
				", brojClanskeKarte=" + brojClanskeKarte +
				", automobili=" + automobili +
				", ocena=" + ocena +
				", statusVozaca=" + statusVozaca +
				"} " + super.toString();
	}
}