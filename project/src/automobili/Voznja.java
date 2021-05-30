package automobili;

import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import osobe.Musterija;
import osobe.Vozac;
import java.time.LocalDateTime;

public abstract class Voznja {

    protected int id;
    protected LocalDateTime datumIvremePorudzbine;
    protected String adresaPolaska;
    protected String adresaDestinacije;
    protected Musterija musterija;
    protected Vozac vozac;
    protected double brojKMpredjenih;
    protected double trajanjVoznje;
    protected StatusVoznje statusVoznje;
    protected boolean obrisan;
    protected StatusNaruceneVoznje statusNaruceneVoznje;
    protected double cenaVoznje;
    protected boolean ocenjenVozac;
    protected String izborMusterijePriNarucivanju;

    public Voznja(){
        this.id = 0;
        this.datumIvremePorudzbine = null;
        this.adresaPolaska = "";
        this.adresaDestinacije = "";
        this.musterija = null;
        this.vozac = null;
        this.brojKMpredjenih = 0;
        this.trajanjVoznje = 0;
        this.statusVoznje = null;
        this.obrisan = true;
        this.statusNaruceneVoznje = null;
        this.cenaVoznje = 0;
        this.ocenjenVozac = false;
        this.izborMusterijePriNarucivanju = "";
    }

    public Voznja(int id, LocalDateTime datumIvremePorudzbine, String adresaPolaska, String adresaDestinacije, Musterija musterija, Vozac vozac, double brojKMpredjenih, double trajanjVoznje, StatusVoznje statusVoznje, boolean obrisan, StatusNaruceneVoznje statusNaruceneVoznje, double cenaVoznje, boolean ocenjenVozac, String izborMusterijePriNarucivanju) {
        this.id = id;
        this.datumIvremePorudzbine = datumIvremePorudzbine;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.musterija = musterija;
        this.vozac = vozac;
        this.brojKMpredjenih = brojKMpredjenih;
        this.trajanjVoznje = trajanjVoznje;
        this.statusVoznje = statusVoznje;
        this.obrisan = obrisan;
        this.statusNaruceneVoznje = statusNaruceneVoznje;
        this.cenaVoznje = cenaVoznje;
        this.ocenjenVozac = ocenjenVozac;
        this.izborMusterijePriNarucivanju = izborMusterijePriNarucivanju;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDatumIvremePorudzbine() {
        return datumIvremePorudzbine;
    }

    public void setDatumIvremePorudzbine(LocalDateTime datumIvremePorudzbine) {
        this.datumIvremePorudzbine = datumIvremePorudzbine;
    }

    public String getAdresaPolaska() {
        return adresaPolaska;
    }

    public void setAdresaPolaska(String adresaPolaska) {
        this.adresaPolaska = adresaPolaska;
    }

    public String getAdresaDestinacije() {
        return adresaDestinacije;
    }

    public void setAdresaDestinacije(String adresaDestinacije) {
        this.adresaDestinacije = adresaDestinacije;
    }

    public Musterija getMusterija() {
        return musterija;
    }

    public void setMusterija(Musterija musterija) {
        this.musterija = musterija;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public double getBrojKMpredjenih() {
        return brojKMpredjenih;
    }

    public void setBrojKMpredjenih(double brojKMpredjenih) {
        this.brojKMpredjenih = brojKMpredjenih;
    }

    public double getTrajanjVoznje() {
        return trajanjVoznje;
    }

    public void setTrajanjVoznje(double trajanjVoznje) {
        this.trajanjVoznje = trajanjVoznje;
    }

    public StatusVoznje getStatusVoznje() {
        return statusVoznje;
    }

    public void setStatusVoznje(StatusVoznje statusVoznje) {
        this.statusVoznje = statusVoznje;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public StatusNaruceneVoznje getStatusNaruceneVoznje() {
        return statusNaruceneVoznje;
    }

    public void setStatusNaruceneVoznje(StatusNaruceneVoznje statusNaruceneVoznje) {
        this.statusNaruceneVoznje = statusNaruceneVoznje;
    }

    public double getCenaVoznje() {
        return cenaVoznje;
    }

    public void setCenaVoznje(double cenaVoznje) {
        this.cenaVoznje = cenaVoznje;
    }

    public boolean isOcenjenVozac() {
        return ocenjenVozac;
    }

    public void setOcenjenVozac(boolean ocenjenVozac) {
        this.ocenjenVozac = ocenjenVozac;
    }

    public String getIzborMusterijePriNarucivanju() {
        return izborMusterijePriNarucivanju;
    }

    public void setIzborMusterijePriNarucivanju(String izborMusterijePriNarucivanju) {
        this.izborMusterijePriNarucivanju = izborMusterijePriNarucivanju;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "id=" + id +
                ", datumIvremePorudzbine=" + datumIvremePorudzbine +
                ", adresaPolaska='" + adresaPolaska + '\'' +
                ", adresaDestinacije='" + adresaDestinacije + '\'' +
                ", musterija=" + musterija +
                ", vozac=" + vozac +
                ", brojKMpredjenih=" + brojKMpredjenih +
                ", trajanjVoznje=" + trajanjVoznje +
                ", statusVoznje=" + statusVoznje +
                ", obrisan=" + obrisan +
                ", statusNaruceneVoznje=" + statusNaruceneVoznje +
                ", cenaVoznje=" + cenaVoznje +
                ", ocenjenVozac=" + ocenjenVozac +
                ", izborMusterijePriNarucivanju='" + izborMusterijePriNarucivanju + '\'' +
                '}';
    }
}
