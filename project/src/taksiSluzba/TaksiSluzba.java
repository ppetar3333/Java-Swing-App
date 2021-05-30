package taksiSluzba;

public class TaksiSluzba {
    private int id;
    private String pib;
    private String naziv;
    private String adresa;
    private double cenaStartaVoznje;
    private double cenaPoKilometru;

    public TaksiSluzba() {
        this.id = 0;
        this.pib = "";
        this.naziv = "";
        this.adresa = "";
        this.cenaStartaVoznje = 0;
        this.cenaPoKilometru = 0;
    }

    public TaksiSluzba(int id, String pib, String naziv, String adresa, double cenaStartaVoznje, double cenaPoKilometru) {
        this.id = id;
        this.pib = pib;
        this.naziv = naziv;
        this.adresa = adresa;
        this.cenaStartaVoznje = cenaStartaVoznje;
        this.cenaPoKilometru = cenaPoKilometru;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public double getCenaStartaVoznje() {
        return cenaStartaVoznje;
    }

    public void setCenaStartaVoznje(double cenaStartaVoznje) {
        this.cenaStartaVoznje = cenaStartaVoznje;
    }

    public double getCenaPoKilometru() {
        return cenaPoKilometru;
    }

    public void setCenaPoKilometru(double cenaPoKilometru) {
        this.cenaPoKilometru = cenaPoKilometru;
    }


    @Override
    public String toString() {
        return "TaksiSluzba{" +
                "id=" + id +
                ", pib='" + pib + '\'' +
                ", naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", cenaStartaVoznje=" + cenaStartaVoznje +
                ", cenaPoKilometru=" + cenaPoKilometru +
                '}';
    }

    public String pripremiZaSnimanjeTaksiSluzbu() {
        return id + "," + pib + "," + naziv + "," + adresa + "," + cenaStartaVoznje + "," + cenaPoKilometru + "\n";
    }
}
