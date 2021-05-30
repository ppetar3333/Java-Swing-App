package liste;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import automobili.Automobil;
import automobili.Voznja;
import enumi.*;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import musterija.probaZaAlgoritme.Aukcija;
import osobe.Dispecar;
import osobe.Musterija;
import enumi.Odeljenje;
import osobe.Vozac;
import taksiSluzba.TaksiSluzba;

public class Liste {

	private DoublyLinkedList<Musterija> musterije;
	private DoublyLinkedList<Dispecar> dispecari;
	private DoublyLinkedList<Vozac> vozaci;
	private DoublyLinkedList<TaksiSluzba> taksiSluzbe;
	private DoublyLinkedList<Automobil> automobili;
	private DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> voznjaTelefoni;
	private DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> voznjaAplikacije;
	private DoublyLinkedList<Aukcija> istorijaAukcija;

	public Liste() {
		this.musterije = new DoublyLinkedList<Musterija>();
		this.dispecari = new DoublyLinkedList<Dispecar>();
		this.vozaci = new DoublyLinkedList<Vozac>();
		this.taksiSluzbe = new DoublyLinkedList<TaksiSluzba>();
		this.automobili = new DoublyLinkedList<Automobil>();
		this.voznjaTelefoni = new DoublyLinkedList<NarucivanjeVoznjePrekoTelefona>();
		this.voznjaAplikacije = new DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije>();
		this.istorijaAukcija = new DoublyLinkedList<Aukcija>();
	}

	public DoublyLinkedList<Musterija> getMusterije() {
		return musterije;
	}

	public DoublyLinkedList<Dispecar> getDispecari() {
		return dispecari;
	}

	public DoublyLinkedList<Vozac> getVozaci() {
		return vozaci;
	}

	public void setVozaci(DoublyLinkedList<Vozac> vozaci) {
		this.vozaci = vozaci;
	}

	public DoublyLinkedList<TaksiSluzba> getTaksiSluzbe() {
		return taksiSluzbe;
	}

	public DoublyLinkedList<Automobil> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(DoublyLinkedList<Automobil> automobili) {
		this.automobili = automobili;
	}

	public DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> getVoznjaTelefoni() {
		return voznjaTelefoni;
	}

	public DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> getVoznjaAplikacije() {
		return voznjaAplikacije;
	}

	public DoublyLinkedList<Aukcija> getIstorijaAukcija() {
		return istorijaAukcija;
	}

	/*
			UCITAVANJE
	*/

	public void ucitajKorisnike(String imeFajla) {
		try {
			File korisniciFajl = new File("src/fajlovi/" + imeFajla);
			BufferedReader br = new BufferedReader(new FileReader(korisniciFajl));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(",");
				String korisnickoIme = split[0];
				String lozinka = split[1];
				String ime = split[2];
				String prezime = split[3];
				String jmbg = split[4];
				String adresa = split[5];
				Pol pol = Pol.valueOf(split[6].toUpperCase());
				String brojTelefona = split[7];
				String tipKorisnika = split[11];
				String obrisanString = split[12];
				boolean obrisan = Boolean.parseBoolean(obrisanString);
				if (tipKorisnika.equals("MUSTERIJA")) {
					Musterija musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
					musterije.add(musterija);
				} else if (tipKorisnika.equals("DISPECAR")) {
					String plataString = split[8];
					double plataDispecara = Double.parseDouble(plataString);
					String brojPozivnogTelefona = split[9];
					Odeljenje odeljenje = Odeljenje.valueOf(split[10]);
					Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan, plataDispecara, brojPozivnogTelefona, odeljenje);
					dispecari.add(dispecar);
				} else if (tipKorisnika.equals("VOZAC")) {
					String plataString = split[8];
					double plata = Double.parseDouble(plataString);
					String brojKarticeString = split[9];
					int brojKartice = Integer.parseInt(brojKarticeString);
					String ocenaString = split[13];
					double ocena = Double.parseDouble(ocenaString);
					String idAutomobilaString = split[10];
					int idAutomobila = Integer.parseInt(idAutomobilaString);
					StatusVozacaIautomobila statusVozaca = StatusVozacaIautomobila.valueOf(split[14]);
					Automobil automobil = new Automobil();
					for(Automobil automobil1 : automobili){
						if(idAutomobila == automobil1.getId()){
							automobil = automobil1;
						}
					}
					Vozac vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan, plata, brojKartice, automobil, ocena, statusVozaca);
					vozaci.add(vozac);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ucitajTaksiSluzbe(String imeFajla) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while ((line = br.readLine()) != null) {
				String[] podaci = line.trim().split(",");
				int id = Integer.parseInt(podaci[0]);
				String pib = podaci[1];
				String naziv = podaci[2];
				String adresa = podaci[3];
				double cenaStartaVoznje = Double.parseDouble(podaci[4]);
				double cenaPoKilometru = Double.parseDouble(podaci[5]);
				TaksiSluzba ts = new TaksiSluzba(id, pib, naziv, adresa, cenaStartaVoznje, cenaPoKilometru);
				taksiSluzbe.add(ts);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}

	public void ucitajAutomobila(String imeFajla){
		try{
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while((line = br.readLine()) != null){
				String[] podaci = line.trim().split(",");
				int id = Integer.parseInt(podaci[0]);
				String model = podaci[1];
				String proizvodjac = podaci[2];
				int godinaProizvodnje = Integer.parseInt(podaci[3]);
				String registarskiBroj = podaci[4];
				int brojVozila = Integer.parseInt(podaci[5]);
				VrstaVozila vrstaVozila = VrstaVozila.valueOf(podaci[6].toUpperCase());
				String obrisanString = podaci[7];
				boolean obrisan = Boolean.parseBoolean(obrisanString);
				StatusVozacaIautomobila statusVozacaIautomobila = StatusVozacaIautomobila.valueOf(podaci[8].toUpperCase());
				String petFriendlyString = podaci[9];
				boolean petFriendly = Boolean.parseBoolean(petFriendlyString);
				Automobil aut = new Automobil(id,model,proizvodjac,godinaProizvodnje,registarskiBroj,brojVozila,vrstaVozila,obrisan, statusVozacaIautomobila,petFriendly);
				automobili.add(aut);
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}
	public void ucitavanjeVoznji(String imeFajla){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while ((line = br.readLine()) != null){
				String[] podaci = line.trim().split(",");
				int id = Integer.parseInt(podaci[0]);
				String datumIvremePorudzbine = podaci[1];
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				LocalDateTime dateTime = LocalDateTime.parse(datumIvremePorudzbine,formatter);
				String adresaPolaska = podaci[2];
				String adresaDestinacije = podaci[3];
				double brojKMpredjenih = Double.parseDouble(podaci[6]);
				double trajanjVoznje = Double.parseDouble(podaci[7]);
				StatusVoznje statusVoznje = StatusVoznje.valueOf(podaci[8]);
				String obrisanString = podaci[10];
				boolean obrisan = Boolean.parseBoolean(obrisanString);
				StatusNaruceneVoznje cimeJeNarucenaVoznja = StatusNaruceneVoznje.valueOf(podaci[11]);
				double cenaVoznje = Double.parseDouble(podaci[12]);
				String daLiJeVozacOcenjenString = podaci[13];
				boolean ocenjenVozac = Boolean.parseBoolean(daLiJeVozacOcenjenString);
				String izborMusterijePriNarucivanjuVoznje = podaci[14];
				Musterija musterija = new Musterija();
				Vozac vozac = new Vozac();
				for(Musterija musterija1 : musterije){
					if(podaci[4].equalsIgnoreCase(musterija1.getKorisnickoIme())){
						musterija = musterija1;
					}
				}
				for(Vozac vozac1 : vozaci){
					if(podaci[5].equalsIgnoreCase(vozac1.getKorisnickoIme())){
						vozac = vozac1;
					}
				}
				if(cimeJeNarucenaVoznja.equals(StatusNaruceneVoznje.TELEFON)){
					NarucivanjeVoznjePrekoTelefona narucivanjeVoznjePrekoTelefona = new NarucivanjeVoznjePrekoTelefona(id,dateTime,adresaPolaska,adresaDestinacije,musterija,vozac,brojKMpredjenih,trajanjVoznje,statusVoznje,obrisan,cimeJeNarucenaVoznja,cenaVoznje,ocenjenVozac,izborMusterijePriNarucivanjuVoznje);
					voznjaTelefoni.add(narucivanjeVoznjePrekoTelefona);

				}else if(cimeJeNarucenaVoznja.equals(StatusNaruceneVoznje.APLIKACIJA)){
					String napomena = podaci[9];
					NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije = new NarucivanjeVoznjePrekoAplikacije(id,dateTime,adresaPolaska,adresaDestinacije,musterija,vozac,brojKMpredjenih,trajanjVoznje,statusVoznje,obrisan,cimeJeNarucenaVoznja,cenaVoznje,ocenjenVozac,izborMusterijePriNarucivanjuVoznje,napomena);
					voznjaAplikacije.add(narucivanjeVoznjePrekoAplikacije);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}
	// DEO IZ ALGORITAMA
	public void ucitajIstorijuAukcija(String imeFajla){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("src/fajlovi/" + imeFajla)));
			String line;
			while ((line = br.readLine()) != null) {
				String[] podaci = line.trim().split(",");
				String izborMusterije = podaci[0];
				int IDvoznje = Integer.parseInt(podaci[1]);
				String vozacKojiUcestvujeUaukciji = podaci[2];
				int vremeKojeJeUneoVozac = Integer.parseInt(podaci[3]);
				double ocenaVozaca = Double.parseDouble(podaci[4]);
				boolean petFriendly = Boolean.parseBoolean(podaci[5]);
				int godisteAutomobila = Integer.parseInt(podaci[6]);
				boolean dobioVoznju = Boolean.parseBoolean(podaci[7]);
				Aukcija aukcija = new Aukcija(izborMusterije,IDvoznje,vozacKojiUcestvujeUaukciji,vremeKojeJeUneoVozac,ocenaVozaca,petFriendly,godisteAutomobila,dobioVoznju);
				istorijaAukcija.add(aukcija);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom citanja fajla");
		}
	}


	/*
			DODAVANJE / SNIMANJE
	*/

	public void dodavanjeKorisnika() {
		try {
			File korisniciFajl = new File("src/fajlovi/korisnici.txt");
			String content = "";

			for (Vozac vozac : vozaci) {
				content += vozac.getKorisnickoIme() + "," +
						vozac.getLozinka() + "," +
						vozac.getIme() + "," +
						vozac.getPrezime() + "," +
						vozac.getJmbg() + "," +
						vozac.getAdresa() + "," +
						vozac.getPol() + "," +
						vozac.getBrojTelefona() + "," +
						vozac.getPlata() + "," +
						vozac.getBrojClanskeKarte() + "," +
						vozac.getAutomobili().getId() + "," + "VOZAC" + "," +
						vozac.isObrisan() + "," +
						vozac.getOcena() +  "," +
						vozac.getStatusVozaca() + "\n";
			}
			for (Musterija musterija : musterije) {
				content += musterija.getKorisnickoIme() + "," +
						musterija.getLozinka() + "," +
						musterija.getIme() + "," +
						musterija.getPrezime() + "," +
						musterija.getJmbg() + "," +
						musterija.getAdresa() + "," +
						musterija.getPol() + "," +
						musterija.getBrojTelefona() + "," + "," + "," + "," + "MUSTERIJA" + "," + musterija.isObrisan() + "\n";
			}
			for (Dispecar dispecar : dispecari) {
				content += dispecar.getKorisnickoIme() + "," +
						dispecar.getLozinka() + "," +
						dispecar.getIme() + "," +
						dispecar.getPrezime() + "," +
						dispecar.getJmbg() + "," +
						dispecar.getAdresa() + "," +
						dispecar.getPol() + "," +
						dispecar.getBrojTelefona() + "," +
						dispecar.getPlata() + "," +
						dispecar.getBrojTelefonskeLinije() + "," +
						dispecar.getOdeljenje() + "," + "DISPECAR" + "," + dispecar.isObrisan() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(korisniciFajl));
			writer.write(content);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom dodavanja korisnika");
		}
	}

	public void snimiTaksiSluzbe(String imeFajla) {

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (TaksiSluzba taksiSluzba : taksiSluzbe) {
				br.write(taksiSluzba.pripremiZaSnimanjeTaksiSluzbu());
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}

	public void snimanjeAutomobila(String imeFajla){
		try{
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (Automobil automobil : automobili){
				br.write(automobil.pripremiZaSnimanjeAutomobil());
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}

	public void snimanjeVoznji(String imeFajla){
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for(NarucivanjeVoznjePrekoTelefona voznjaTelefoni : voznjaTelefoni){
				br.write(voznjaTelefoni.pripremiZaSnimanje());
			}
			for(NarucivanjeVoznjePrekoAplikacije voznjaAplikacija : voznjaAplikacije){
				br.write(voznjaAplikacija.pripremiZaSnimanjePrekoAplikacije());
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}
	// DEO IZ ALGORITAMA
	public void snimiIstorijuAukcija(String imeFajla){
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/" + imeFajla)));
			for (Aukcija aukcija : istorijaAukcija) {
				br.write(aukcija.pripremiZaSnimanjeIstorijuAukcija());
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}


	/*
		SORTIRANJE LISTI
	*/
	DoublyLinkedList<Automobil> sortiranaListaAutomobila = new DoublyLinkedList<>();
	public void sortiranjeAutomobila(){
		DoublyLinkedList<Integer> listaAutomobila = new DoublyLinkedList<>();
		for(Automobil automobil : automobili){
			int autoID = automobil.getId();
			listaAutomobila.add(autoID);
		}
		listaAutomobila.sortListOfIntegers();
		for(Integer i : listaAutomobila) {
			for(Automobil automobil : automobili){
				if(automobil.getId() == i){
					sortiranaListaAutomobila.add(automobil);
				}
			}
		}
		try{
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/automobil.txt")));
			for (Automobil automobil : sortiranaListaAutomobila){
				br.write(automobil.pripremiZaSnimanjeAutomobil());
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}
	DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sortiranaListaVoznjiAplikacija = new DoublyLinkedList<>();
	DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> sortiranaListaVoznjiTelefon = new DoublyLinkedList<>();
	public void sortiranjeVoznji(){
		DoublyLinkedList<Integer> listaVoznjiAplikacija = new DoublyLinkedList<>();
		for(NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije : voznjaAplikacije){
			int voznjaID = voznjePrekoAplikacije.getId();
			listaVoznjiAplikacija.add(voznjaID);
		}
		listaVoznjiAplikacija.sortListOfIntegers();
		for(Integer i : listaVoznjiAplikacija) {
			for(NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije : voznjaAplikacije){
				if(voznjePrekoAplikacije.getId() == i){
					sortiranaListaVoznjiAplikacija.add(voznjePrekoAplikacije);
				}
			}
		}
		DoublyLinkedList<Integer> listaVoznjiTelefon = new DoublyLinkedList<>();
		for(NarucivanjeVoznjePrekoTelefona voznjePrekoTelefona : voznjaTelefoni){
			if(voznjePrekoTelefona.isObrisan()) {
				int voznjaID = voznjePrekoTelefona.getId();
				listaVoznjiTelefon.add(voznjaID);
			}
		}
		listaVoznjiTelefon.sortListOfIntegers();
		for(Integer i : listaVoznjiTelefon) {
			for(NarucivanjeVoznjePrekoTelefona voznjePrekoTelefona : voznjaTelefoni){
				if(voznjePrekoTelefona.getId() == i){
					sortiranaListaVoznjiTelefon.add(voznjePrekoTelefona);
				}
			}
		}
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File("src/fajlovi/voznje.txt")));
			for(NarucivanjeVoznjePrekoTelefona voznjaTelefoni : sortiranaListaVoznjiTelefon){
				br.write(voznjaTelefoni.pripremiZaSnimanje());
			}
			for(NarucivanjeVoznjePrekoAplikacije voznjaAplikacija : sortiranaListaVoznjiAplikacija){
				br.write(voznjaAplikacija.pripremiZaSnimanjePrekoAplikacije());
			}
			br.close();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Greska prilikom snimanja fajla");
		}
	}



	/*
			PRETRAGA

	*/

	public int pronadjiAutomobilBinarySearch(DoublyLinkedList<Automobil> array, int target){
		return binarySearchAutomobil(array,target,0,array.size());
	}

	public int binarySearchAutomobil(DoublyLinkedList<Automobil> array, int target, int low, int high){
		if(low > high){
			return 0;
		}
		int mid = (low+high)/2;
		if(array.get(mid).getId() == target){
			return mid;
		}
		else if(array.get(mid).getId() > target){
			return binarySearchAutomobil(array,target,low,mid-1);
		}else{
			return binarySearchAutomobil(array,target,mid+1,high);
		}
	}
	public int pronadjiVoznjeTelefonBinarySearch(DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> array, int target){
		return binarySearchVoznjeTelefon(array,target,0,array.size());
	}
	public int binarySearchVoznjeTelefon(DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> array, int target, int low, int high){
		if(low > high){
			return 0;
		}
		int mid = (low+high)/2;
		if(array.get(mid).getId() == target){
			return mid;
		}
		else if(array.get(mid).getId() > target){
			return binarySearchVoznjeTelefon(array,target,low,mid-1);
		}else{
			return binarySearchVoznjeTelefon(array,target,mid+1,high);
		}
	}
	public int pronadjiTaksiSluzbuBinarySearch(DoublyLinkedList<TaksiSluzba> array, int target){
		return binarySearchTaksiSluzba(array,target,0,array.size());
	}
	public int binarySearchTaksiSluzba(DoublyLinkedList<TaksiSluzba> array, int target, int low, int high){
		if(low > high){
			return 0;
		}
		int mid = (low+high)/2;
		if(array.get(mid).getId() == target){
			return mid;
		}
		else if(array.get(mid).getId() > target){
			return binarySearchTaksiSluzba(array,target,low,mid-1);
		}else{
			return binarySearchTaksiSluzba(array,target,mid+1,high);
		}
	}
	public int pronadjiVoznjeAplikacijaBinarySearch(DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> array, int target){
		return binarySearchVoznjeAplikacija(array,target,0,array.size());
	}
	public int binarySearchVoznjeAplikacija(DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> array, int target, int low, int high){
		if(low > high){
			return 0;
		}
		int mid = (low+high)/2;
		if(array.get(mid).getId() == target){
			return mid;
		}
		else if(array.get(mid).getId() > target){
			return binarySearchVoznjeAplikacija(array,target,low,mid-1);
		}else{
			return binarySearchVoznjeAplikacija(array,target,mid+1,high);
		}
	}

	public Vozac nadjiVozaca(String korisnickoIme){
		for(Vozac vozac : vozaci){
			if(vozac.getKorisnickoIme().equals(korisnickoIme) && vozac.isObrisan()){
				return vozac;
			}
		}
		return null;
	}
	public boolean istoKorisnickoImeVozaca(String korisnickoIme){
		for(Vozac vozac : vozaci){
			if(vozac.getKorisnickoIme().equals(korisnickoIme)){
				return true;
			}
		}
		return false;
	}
	public boolean istoKorisnickoImeDispecera(String korisnickoIme){
		for(Dispecar dispecar : dispecari){
			if(dispecar.getKorisnickoIme().equals(korisnickoIme)){
				return true;
			}
		}
		return false;
	}
	public boolean istoKorisnickoImeMusterije(String korisnickoIme){
		for(Musterija musterija : musterije){
			if(musterija.getKorisnickoIme().equals(korisnickoIme)){
				return true;
			}
		}
		return false;
	}
	public DoublyLinkedList<Vozac> nadjiVozacaPoPlati(double plataUnos){
		DoublyLinkedList<Vozac> sviVozaci = new DoublyLinkedList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getPlata() == plataUnos && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public DoublyLinkedList<Vozac> nadjiVozacaPoImenu(String unosIme){
		DoublyLinkedList<Vozac> sviVozaci = new DoublyLinkedList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getIme().equalsIgnoreCase(unosIme) && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}

	public DoublyLinkedList<Vozac> nadjiVozacaPoPrezimenu(String unosPrezime){
		DoublyLinkedList<Vozac> sviVozaci = new DoublyLinkedList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getPrezime().equalsIgnoreCase(unosPrezime) && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public DoublyLinkedList<Vozac> nadjiVozacaPoAutomobilu(String model){
		DoublyLinkedList<Vozac> sviVozaci = new DoublyLinkedList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.getAutomobili().getModel().equalsIgnoreCase(model) && vozac.isObrisan()){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public DoublyLinkedList<Vozac> rezultatKombinovanePretrage(String ime,String prezime,String model,double plata){
		DoublyLinkedList<Vozac> sviVozaci = new DoublyLinkedList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.isObrisan() && vozac.getIme().equalsIgnoreCase(ime) && vozac.getPrezime().equalsIgnoreCase(prezime) && vozac.getAutomobili().getModel().equalsIgnoreCase(model) && vozac.getPlata() == plata){
				sviVozaci.add(vozac);
			}
		}
		return sviVozaci;
	}
	public Vozac nadjiVozacaKojiJeSlobodan(){
		for(Vozac vozac : vozaci){
			if(vozac.isObrisan() && vozac.getStatusVozaca().equals(StatusVozacaIautomobila.SLOBODAN) && vozac.getAutomobili().getId() != 0){
				return vozac;
			}
		}
		return null;
	}
	public DoublyLinkedList<String> listaSlobodnihVozaca(){
		DoublyLinkedList<String> slobodniVozaci = new DoublyLinkedList<>();
		for(Vozac vozac : vozaci){
			if(vozac.isObrisan() && vozac.getStatusVozaca().equals(StatusVozacaIautomobila.SLOBODAN) && vozac.getAutomobili().getId() != 0){
				String vozaci = vozac.getKorisnickoIme();
				slobodniVozaci.add(vozaci);
			}
		}
		return slobodniVozaci;
	}

	public Musterija nadjiMusteriju(String korisnickoIme){
		for(Musterija musterija : musterije){
			if(musterija.getKorisnickoIme().equals(korisnickoIme) && musterija.isObrisan()){
				return musterija;
			}
		}
		return null;
	}

	public Dispecar nadjiDispecera(String korisnickoIme){
		for(Dispecar dispecar : dispecari){
			if(dispecar.getKorisnickoIme().equals(korisnickoIme) && dispecar.isObrisan()){
				return dispecar;
			}
		}
		return null;
	}
	public DoublyLinkedList<Integer> listaSlobodnihAutomobila(){
		DoublyLinkedList<Integer> slobodniAutomobil = new DoublyLinkedList<>();
		for(Automobil automobil : sortiranaListaAutomobila){
			if(automobil.getStatusAutomobila() == StatusVozacaIautomobila.SLOBODAN && automobil.isObrisan()){
				int autoID = automobil.getId();
				slobodniAutomobil.add(autoID);
			}
		}
		return slobodniAutomobil;
	}

	public NarucivanjeVoznjePrekoAplikacije voznjaKojaNemaVozaca(){
		for(NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije : sortiranaListaVoznjiAplikacija){
			if(voznjePrekoAplikacije.getVozac().getKorisnickoIme().equals("")){
				return voznjePrekoAplikacije;
			}
		}
		return null;
	}
	public DoublyLinkedList<String> listaVozacaBezAutomobila(){
		DoublyLinkedList<String> slobodanVozac = new DoublyLinkedList<>();
		for (Vozac vozac : vozaci){
			if (vozac.getAutomobili().getId() == 0 & vozac.isObrisan()){
				String vozacIme = vozac.getKorisnickoIme();
				slobodanVozac.add(vozacIme);
			}
		}
		return slobodanVozac;
	}

	public NarucivanjeVoznjePrekoAplikacije nadjiVoznjuZakazanuPrekoAplikacije(){
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			if(voznja.getStatusNaruceneVoznje().equals(StatusNaruceneVoznje.APLIKACIJA) && voznja.isObrisan()){
				return voznja;
			}
		}
		return null;
	}

	/*

		DNEVNI IZVESTAJ

	*/

	public boolean nadjiDatum(String datum){
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				return true;
			}
		}
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				return true;
			}
		}
		return false;
	}
	public int uporediDatumIvoznjeAplikacijom(String datum){
		int counter = 0;
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && voznja.getStatusNaruceneVoznje() == StatusNaruceneVoznje.APLIKACIJA && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				counter++;
			}
		}
		return counter;
	}
	public int uporediDatumIvoznjeTelefonom(String datum){
		int counter = 0;
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && voznja.getStatusNaruceneVoznje() == StatusNaruceneVoznje.TELEFON && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))) {
				counter++;
			}
		}
		return counter;
	}
	public double uporediDatumItrajanjeVoznje(String datum){
		double rezultat = 0;
		double counter = 0;
		double average;
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getTrajanjVoznje();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getTrajanjVoznje();
				counter++;
			}
		}
		average = rezultat/counter;
		return average;
	}
	public double uporediDatumIkilometrazu(String datum){
		double rezultat = 0;
		double counter = 0;
		double average;
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getBrojKMpredjenih();
				counter++;
			}
		}
		average = rezultat/counter;
		return average;
	}
	public double ukupnaZaradaZaSveVoznje(String datum){
		double rezultat = 0;
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getCenaVoznje();
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getCenaVoznje();
			}
		}
		return rezultat;
	}
	public DoublyLinkedList<String> spisakVozacaKojiSuVozili(String datum){
		DoublyLinkedList<String> spisakSvihVozaca = new DoublyLinkedList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for(NarucivanjeVoznjePrekoTelefona voznjePrekoTelefona : sortiranaListaVoznjiTelefon){
			if(voznjePrekoTelefona.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				String korisnickoImeVozaca = voznjePrekoTelefona.getVozac().getKorisnickoIme();
				spisakSvihVozaca.add(korisnickoImeVozaca);
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije : sortiranaListaVoznjiAplikacija){
			if(voznjePrekoAplikacije.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				String korisnickoImeVozaca = voznjePrekoAplikacije.getVozac().getKorisnickoIme();
				spisakSvihVozaca.add(korisnickoImeVozaca);
			}
		}
		return spisakSvihVozaca;
	}


	/*
		NEDELJENI, MESECNI, GODISNJI IZVESTAJ
	*/

	public DoublyLinkedList<String> ukupanBrojVoznjiPrekoTelefona(){
		DoublyLinkedList<String> telefon = new DoublyLinkedList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for(NarucivanjeVoznjePrekoTelefona telefoni : sortiranaListaVoznjiTelefon){
			if(telefoni.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || telefoni.getStatusVoznje().equals(StatusVoznje.ODBIJENA)) {
				String datum = telefoni.getDatumIvremePorudzbine().format(formatter);
				telefon.add(datum);
			}
		}
		return telefon;
	}
	public DoublyLinkedList<String> ukupanBrojVoznjiPrekoAplikacije(){
		DoublyLinkedList<String> aplikacija = new DoublyLinkedList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		for(NarucivanjeVoznjePrekoAplikacije telefoni : sortiranaListaVoznjiAplikacija){
			if(telefoni.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || telefoni.getStatusVoznje().equals(StatusVoznje.ODBIJENA)) {
				String datum = telefoni.getDatumIvremePorudzbine().format(formatter);
				aplikacija.add(datum);
			}
		}
		return aplikacija;
	}
	public DoublyLinkedList<Integer> nadjiVoznjuNarucenuPrekoTelefonaPoDatumu(String datum){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DoublyLinkedList<Integer> ukupanBrojLista = new DoublyLinkedList<>();
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				ukupanBrojLista.add(voznja.getId());
			}
		}
		return ukupanBrojLista;
	}
	public DoublyLinkedList<Integer> nadjiVoznjuNarucenuPrekoAplikacijePoDatumu(String datum){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DoublyLinkedList<Integer> ukupanBrojLista = new DoublyLinkedList<>();
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			if(voznja.getDatumIvremePorudzbine().format(formatter).equals(datum) && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				ukupanBrojLista.add(voznja.getId());
			}
		}
		return ukupanBrojLista;
	}
	public double ukupnoTrajanjeVoznjiTelefoni(int id){
		double trajanjeVoznje = 0;
		for(NarucivanjeVoznjePrekoTelefona voznjePrekoTelefona : sortiranaListaVoznjiTelefon){
			if(voznjePrekoTelefona.getId() == id && (voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				trajanjeVoznje = voznjePrekoTelefona.getTrajanjVoznje();
			}
		}
		return trajanjeVoznje;
	}
	public double ukupnoTrajanjeVoznjiAplikacija(int id){
		double trajanjeVoznje = 0;
		for(NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije : sortiranaListaVoznjiAplikacija){
			if(voznjePrekoAplikacije.getId() == id && (voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				trajanjeVoznje = voznjePrekoAplikacije.getTrajanjVoznje();
			}
		}
		return trajanjeVoznje;
	}
	public double ukupnaKilometrazaTelefoni(int id){
		double kilometraza = 0;
		for(NarucivanjeVoznjePrekoTelefona voznjePrekoTelefona : sortiranaListaVoznjiTelefon){
			if(voznjePrekoTelefona.getId() == id && (voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				kilometraza = voznjePrekoTelefona.getBrojKMpredjenih();
			}
		}
		return kilometraza;
	}
	public double ukupnaKilometrazaAplikacija(int id){
		double kilometraza = 0;
		for(NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije : sortiranaListaVoznjiAplikacija){
			if(voznjePrekoAplikacije.getId() == id && (voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				kilometraza = voznjePrekoAplikacije.getBrojKMpredjenih();
			}
		}
		return kilometraza;
	}
	public double ukupnaZaradaTelefoni(int id){
		double rezultat = 0;
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			if(voznja.getId() == id && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getCenaVoznje();
			}
		}
		return rezultat;
	}
	public double ukupnaZaradaAplikacija(int id){
		double rezultat = 0;
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			if(voznja.getId() == id && (voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznja.getStatusVoznje().equals(StatusVoznje.ODBIJENA))){
				rezultat += voznja.getCenaVoznje();
			}
		}
		return rezultat;
	}
	public DoublyLinkedList<String> listaKorisnickihImenaVozaca(int id){
		DoublyLinkedList<String> vozaci = new DoublyLinkedList<>();
		for(NarucivanjeVoznjePrekoTelefona voznjePrekoTelefona : sortiranaListaVoznjiTelefon){
			if(voznjePrekoTelefona.getId() == id && (voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoTelefona.getStatusVoznje().equals(StatusVoznje.ODBIJENA))) {
				String vozacKorisnickoIme = voznjePrekoTelefona.getVozac().getKorisnickoIme();
				vozaci.add(vozacKorisnickoIme);
			}
		}
		for(NarucivanjeVoznjePrekoAplikacije voznjePrekoAplikacije : sortiranaListaVoznjiAplikacija){
			if(voznjePrekoAplikacije.getId() == id && (voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) || voznjePrekoAplikacije.getStatusVoznje().equals(StatusVoznje.ODBIJENA))) {
				String vozacKorisnickoIme = voznjePrekoAplikacije.getVozac().getKorisnickoIme();
				vozaci.add(vozacKorisnickoIme);
			}
		}
		return vozaci;
	}

	/*
		GENERISI NOVI ID ZA VOZNJE
	*/

	public int generisiNoviIdZaVoznjePutemTelefona(){
		int maks = -1;
		for (NarucivanjeVoznjePrekoTelefona prekoTelefona : voznjaTelefoni) {
			if (prekoTelefona.getId() > maks) {
				maks = prekoTelefona.getId();
			}
		}
		return maks + 1;
	}
	public int generisiNoviIdZaVoznjePutemAplikacije() {
		int maks = -1;
		for (NarucivanjeVoznjePrekoAplikacije prekoAplikacije : voznjaAplikacije){
			if (prekoAplikacije.getId() > maks){
				maks = prekoAplikacije.getId();
			}
		}
		return maks + 1;
	}


	/*
			PROVERA ZA LOGIN
	*/

	public Musterija loginMusterija(String korisnickoIme, String lozinka) {
		for (Musterija musterija : musterije) {
			if (musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& musterija.getLozinka().equals(lozinka)) {
				return musterija;
			}
		}
		return null;
	}

	public Dispecar loginDispecar(String korisnickoIme, String lozinka) {
		for (Dispecar dispecar : dispecari) {
			if (dispecar.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& dispecar.getLozinka().equals(lozinka)) {
				return dispecar;
			}
		}
		return null;
	}

	public Vozac loginVozac(String korisnickoIme, String lozinka) {
		for (Vozac vozac : vozaci) {
			if (vozac.getKorisnickoIme().equalsIgnoreCase(korisnickoIme)
					&& vozac.getLozinka().equals(lozinka)) {
				return vozac;
			}
		}
		return null;
	}

	/*
		LISTE NEOBRISANIH ZA PRIKAZ
	*/

	public DoublyLinkedList<Vozac> neobrisaniVozaci(){
		DoublyLinkedList<Vozac> neobrisaniVozaci = new DoublyLinkedList<Vozac>();
		for(Vozac vozac : vozaci){
			if(vozac.isObrisan()){
				neobrisaniVozaci.add(vozac);
			}
		}
		return neobrisaniVozaci;
	}
	public DoublyLinkedList<Musterija> neobrisaneMusterije(){
		DoublyLinkedList<Musterija> neobrisaneMusterije = new DoublyLinkedList<Musterija>();
		for(Musterija musterija : musterije){
			if(musterija.isObrisan()){
				neobrisaneMusterije.add(musterija);
			}
		}
		return neobrisaneMusterije;
	}
	public DoublyLinkedList<Dispecar> neobrisaniDispeceri(){
		DoublyLinkedList<Dispecar> neobrisaniDispeceri = new DoublyLinkedList<Dispecar>();
		for(Dispecar dispecar : dispecari){
			if(dispecar.isObrisan()){
				neobrisaniDispeceri.add(dispecar);
			}
		}
		return neobrisaniDispeceri;
	}
	public DoublyLinkedList<Automobil> neobrisaniAutomobili(){
		DoublyLinkedList<Automobil> neobAutomobili = new DoublyLinkedList<Automobil>();
		for(Automobil automobil : automobili){
			if(automobil.isObrisan()){
				neobAutomobili.add(automobil);
			}
		}
		return neobAutomobili;
	}
	public DoublyLinkedList<TaksiSluzba> taksiSluzba(){
		DoublyLinkedList<TaksiSluzba> taksiSluzbaLista = new DoublyLinkedList<>();
		for(TaksiSluzba taksiSluzba : taksiSluzbe){
			taksiSluzbaLista.add(taksiSluzba);
		}
		return taksiSluzbaLista;
	}
	public DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznjeKreiranePutemTelefona(){
		DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznje = new DoublyLinkedList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			if(voznja.isObrisan()){
				neobrisaneVoznje.add(voznja);
			}
		}
		return neobrisaneVoznje;
	}
	public DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> neobrisaneIkreiraneVoznjeNarucenePutemTelefona(){
		DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> neobrisaneVoznje = new DoublyLinkedList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			if(voznja.isObrisan() && voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA)){
				neobrisaneVoznje.add(voznja);
			}
		}
		return neobrisaneVoznje;
	}
	public DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> prikazVoznjeZaZavrsavanjeVoznje(){
		DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> voznje = new DoublyLinkedList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			Vozac ulogovanVozac = null;
			try {
				File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
				Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
				while (citanjeUlogovanogKorisnika.hasNextLine()) {
					String data = citanjeUlogovanogKorisnika.nextLine();
					ulogovanVozac = new Vozac();
					ulogovanVozac.setKorisnickoIme(data);
					if(voznja.isObrisan() && voznja.getVozac().getKorisnickoIme().equalsIgnoreCase(ulogovanVozac.getKorisnickoIme()) && voznja.getStatusVoznje().equals(StatusVoznje.PRIHVACENA)){
						voznje.add(voznja);
					}
				}
				citanjeUlogovanogKorisnika.close();
			}  catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Greska");
			}
		}
		return voznje;
	}
	public String ulogovanKorisnik(){
		Vozac ulogovanVozac = null;
		try {
			File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
			Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
			while (citanjeUlogovanogKorisnika.hasNextLine()) {
				String data = citanjeUlogovanogKorisnika.nextLine();
				ulogovanVozac = new Vozac();
				ulogovanVozac.setKorisnickoIme(data);
			}
			citanjeUlogovanogKorisnika.close();
		}  catch (IOException ioException) {
			ioException.printStackTrace();
			System.out.println("Greska");
		}
		return ulogovanVozac.getKorisnickoIme();
	}

	public DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> prikazVoznjeZaZavrsavanjeVoznjePutemAplikacije(){
		DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> voznje = new DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije>();
		for(NarucivanjeVoznjePrekoAplikacije voznja : sortiranaListaVoznjiAplikacija){
			Vozac ulogovanVozac = null;
			try {
				File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
				Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
				while (citanjeUlogovanogKorisnika.hasNextLine()) {
					String data = citanjeUlogovanogKorisnika.nextLine();
					ulogovanVozac = new Vozac();
					ulogovanVozac.setKorisnickoIme(data);
					if(voznja.isObrisan() && voznja.getVozac().getKorisnickoIme().equalsIgnoreCase(ulogovanVozac.getKorisnickoIme()) && voznja.getStatusVoznje().equals(StatusVoznje.PRIHVACENA)){
						voznje.add(voznja);
					}
				}
				citanjeUlogovanogKorisnika.close();
			}  catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Greska");
			}
		}
		return voznje;
	}

	public DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> prikazDodeljenihVoznji(){
		DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> voznje = new DoublyLinkedList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : sortiranaListaVoznjiTelefon){
			Vozac ulogovanVozac = null;
			try {
				File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
				Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
				while (citanjeUlogovanogKorisnika.hasNextLine()) {
					String data = citanjeUlogovanogKorisnika.nextLine();
					ulogovanVozac = new Vozac();
					ulogovanVozac.setKorisnickoIme(data);
					if(voznja.isObrisan() && voznja.getVozac().getKorisnickoIme().equalsIgnoreCase(ulogovanVozac.getKorisnickoIme()) && voznja.getStatusVoznje().equals(StatusVoznje.DODELJENA)){
						voznje.add(voznja);
					}
				}
				citanjeUlogovanogKorisnika.close();
			}  catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Greska");
			}
		}
		return voznje;
	}
	public DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> neobrisaneVoznjeKreiranePutemAplikacije(){
		DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> neobrisaneVoznje = new DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije>();
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			if(voznja.isObrisan()){
				neobrisaneVoznje.add(voznja);
			}
		}
		return neobrisaneVoznje;
	}
	public DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> prikazVoznjeZaIstorijuVoznjePrekoTelefona(){
		DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> voznje = new DoublyLinkedList<NarucivanjeVoznjePrekoTelefona>();
		for(NarucivanjeVoznjePrekoTelefona voznja : voznjaTelefoni){
			Vozac ulogovanVozac = null;
			try {
				File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
				Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
				while (citanjeUlogovanogKorisnika.hasNextLine()) {
					String data = citanjeUlogovanogKorisnika.nextLine();
					ulogovanVozac = new Vozac();
					ulogovanVozac.setKorisnickoIme(data);
					if(voznja.isObrisan() && voznja.getVozac().getKorisnickoIme().equalsIgnoreCase(ulogovanVozac.getKorisnickoIme()) && voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA)){
						voznje.add(voznja);
					}
				}
				citanjeUlogovanogKorisnika.close();
			}  catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Greska");
			}
		}
		return voznje;
	}
	public DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> prikazVoznjeZaIstorijuVoznjePrekoAplikacije(){
		DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> voznje = new DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije>();
		for(NarucivanjeVoznjePrekoAplikacije voznja : voznjaAplikacije){
			Vozac ulogovanVozac = null;
			try {
				File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
				Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
				while (citanjeUlogovanogKorisnika.hasNextLine()) {
					String data = citanjeUlogovanogKorisnika.nextLine();
					ulogovanVozac = new Vozac();
					ulogovanVozac.setKorisnickoIme(data);
					if(voznja.isObrisan() && voznja.getVozac().getKorisnickoIme().equalsIgnoreCase(ulogovanVozac.getKorisnickoIme()) && voznja.getStatusVoznje().equals(StatusVoznje.ZAVRSENA)){
						voznje.add(voznja);
					}
				}
				citanjeUlogovanogKorisnika.close();
			}  catch (IOException ioException) {
				ioException.printStackTrace();
				System.out.println("Greska");
			}
		}
		return voznje;
	}

	//PRETRAGA AUTOMOBILA
	public DoublyLinkedList<Automobil> nadjiAutomobilPoModelu(String unosModela){
		DoublyLinkedList<Automobil> sviAutomobili = new DoublyLinkedList<Automobil>();
		for (Automobil automobil : automobili){
			if (automobil.getModel().equalsIgnoreCase(unosModela) && automobil.isObrisan()){
				sviAutomobili.add(automobil);
			}
		}
		return sviAutomobili;
	}

	public DoublyLinkedList<Automobil> nadjiAutomobilPoBrojuRegistarskeOznake(String unosRegistarskeOznake){
		DoublyLinkedList<Automobil> sviAutomobili = new DoublyLinkedList<Automobil>();
		for (Automobil automobil : automobili){
			if (automobil.getRegistarskiBroj().equalsIgnoreCase(unosRegistarskeOznake) && automobil.isObrisan()){
				sviAutomobili.add(automobil);
			}
		}
		return sviAutomobili;
	}

	public DoublyLinkedList<Automobil> nadjiAutomobilPoBrojuTaksiVozila(int unosBrojaTaksiVozila){
		DoublyLinkedList<Automobil> sviAutomobili = new DoublyLinkedList<>();
		for (Automobil automobil : automobili){
			if (automobil.getBrojVozila() == unosBrojaTaksiVozila && automobil.isObrisan()){
				sviAutomobili.add(automobil);
			}
		}
		return sviAutomobili;
	}

	public DoublyLinkedList<Automobil> nadjiAutomobilPoGodiniProizvodnje(int unosGodineProizvodnje){
		DoublyLinkedList<Automobil> sviAutomobili = new DoublyLinkedList<>();
		for (Automobil automobil : automobili){
			if (automobil.getGodinaProizvodnje() == unosGodineProizvodnje && automobil.isObrisan()){
				sviAutomobili.add(automobil);
			}
		}
		return sviAutomobili;
	}

	public DoublyLinkedList<Automobil> nadjiAutomobilPoProizvodjacu(String unosProizvodjaca){
		DoublyLinkedList<Automobil> sviAutomobili = new DoublyLinkedList<>();
		for (Automobil automobil : automobili){
			if (automobil.getProizvodjac().equalsIgnoreCase(unosProizvodjaca) && automobil.isObrisan()){
				sviAutomobili.add(automobil);
			}
		}
		return sviAutomobili;
	}

	public DoublyLinkedList<Automobil> rezultatKombinovanePretrageAutomobili(String model, String proizvodnjac, int godinaProizvodnje, int brojTaksiVozila, String brojRegistarskeOznake){
		DoublyLinkedList<Automobil> sviAutomobili = new DoublyLinkedList<>();
		for (Automobil automobil : automobili){
			if (automobil.isObrisan() && automobil.getModel().equals(model) && automobil.getProizvodjac().equals(proizvodnjac) && automobil.getGodinaProizvodnje() == godinaProizvodnje && automobil.getBrojVozila() == brojTaksiVozila && automobil.getRegistarskiBroj().equals(brojRegistarskeOznake)){
				sviAutomobili.add(automobil);
			}
		}
		return sviAutomobili;
	}

	public Aukcija vozacKojiJeDobioVoznju(String korisnickoIme){
		for(Aukcija aukcija : istorijaAukcija){
			if(aukcija.getVozacKojiUcestvujeUaukciji().equals(korisnickoIme)){
				return aukcija;
			}
		}
		return null;
	}

}