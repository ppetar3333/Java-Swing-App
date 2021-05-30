package musterija.narucivanjeVoznjePrekoAplikacije;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NarucivanjeVoznjePrekoAplikacije extends Voznja {

    private String napomena;

    public NarucivanjeVoznjePrekoAplikacije(){
        this.napomena = "";
    }

    public NarucivanjeVoznjePrekoAplikacije(int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje, boolean obrisan, StatusNaruceneVoznje statusNaruceneVoznje, double cenaVoznje, boolean ocenjenVozac, String izborMusterijePriNarucivanju, String napomena) {
        super(id, datumIvremePorudzbine, adresaPolaska, adresaDestinacije, musterija, vozac, brojKMpredjenih, trajanjVoznje, statusVoznje, obrisan, statusNaruceneVoznje, cenaVoznje, ocenjenVozac, izborMusterijePriNarucivanju);
        this.napomena = napomena;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    @Override
    public String toString() {
        return "NarucivanjeVoznjePrekoAplikacije{} " + super.toString();
    }

    public String pripremiZaSnimanjePrekoAplikacije() {
        return id + "," + datumIvremePorudzbine.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "," + adresaPolaska + "," + adresaDestinacije + "," + musterija.getKorisnickoIme() + "," + vozac.getKorisnickoIme() + "," + brojKMpredjenih + "," + trajanjVoznje + "," + statusVoznje + "," + napomena + "," + obrisan + "," + statusNaruceneVoznje + "," + cenaVoznje + "," + ocenjenVozac + "," + izborMusterijePriNarucivanju +"\n";
    }
}
