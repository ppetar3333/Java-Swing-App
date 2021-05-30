package automobili;

import enumi.StatusVozacaIautomobila;
import enumi.VrstaVozila;

import java.util.Comparator;

public class Automobil{

    private int id;
    private String model;
    private String proizvodjac;
    private int godinaProizvodnje;
    private String registarskiBroj;
    private int brojVozila;
    private VrstaVozila vrstaVozila;
    private boolean obrisan;
    private StatusVozacaIautomobila statusVozacaIautomobila;
    private boolean petFriendly;

    public Automobil(){
        this.id = 0;
        this.model = "";
        this.proizvodjac = "";
        this.godinaProizvodnje = 0;
        this.registarskiBroj = "";
        this.brojVozila = 0;
        this.vrstaVozila = null;
        this.obrisan = true;
        this.statusVozacaIautomobila = null;
        this.petFriendly = true;
    }

    public Automobil(int id, String model, String proizvodjac, int godinaProizvodnje, String registarskiBroj, int brojVozila, VrstaVozila vrstaVozila, boolean obrisan, StatusVozacaIautomobila statusVozacaIautomobila, boolean petFriendly) {
        this.id = id;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.godinaProizvodnje = godinaProizvodnje;
        this.registarskiBroj = registarskiBroj;
        this.brojVozila = brojVozila;
        this.vrstaVozila = vrstaVozila;
        this.obrisan = obrisan;
        this.statusVozacaIautomobila = statusVozacaIautomobila;
        this.petFriendly = petFriendly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getRegistarskiBroj() {
        return registarskiBroj;
    }

    public void setRegistarskiBroj(String registarskiBroj) {
        this.registarskiBroj = registarskiBroj;
    }

    public int getBrojVozila() {
        return brojVozila;
    }

    public void setBrojVozila(int brojVozila) {
        this.brojVozila = brojVozila;
    }

    public VrstaVozila getVrstaVozila() {
        return vrstaVozila;
    }

    public void setVrstaVozila(VrstaVozila vrstaVozila) {
        this.vrstaVozila = vrstaVozila;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public StatusVozacaIautomobila getStatusAutomobila() {
        return statusVozacaIautomobila;
    }

    public void setStatusAutomobila(StatusVozacaIautomobila statusVozacaIautomobila) {
        this.statusVozacaIautomobila = statusVozacaIautomobila;
    }

    public boolean isPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public String pripremiZaSnimanjeAutomobil() {
        return id + "," + model + "," + proizvodjac + "," + godinaProizvodnje + "," + registarskiBroj + "," + brojVozila +"," + vrstaVozila + "," + obrisan + "," + statusVozacaIautomobila + "," + petFriendly +  "\n";
    }

    @Override
    public String toString() {
        return "Automobil{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", godinaProizvodnje=" + godinaProizvodnje +
                ", registarskiBroj='" + registarskiBroj + '\'' +
                ", brojVozila=" + brojVozila +
                ", vrstaVozila=" + vrstaVozila +
                ", obrisan=" + obrisan +
                ", statusAutomobila=" + statusVozacaIautomobila +
                ", petFriendly=" + petFriendly +
                '}';
    }
}
