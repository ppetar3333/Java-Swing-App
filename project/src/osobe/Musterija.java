package osobe;

import enumi.Pol;

public class Musterija extends Osoba {

    public Musterija() {}

    public Musterija(String korisnickoIme, String lozinka, String ime, String prezime, String jmbg, String adresa, Pol pol, String brojTelefona, boolean obrisan) {
        super(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
    }

    @Override
    public String toString() {
        return "Musterija{} " + super.toString();
    }
}
