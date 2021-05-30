package musterija.narucivanjeVoznjePrekoAplikacije;

import automobili.Voznja;
import enumi.StatusNaruceneVoznje;
import enumi.StatusVozacaIautomobila;
import enumi.StatusVoznje;
import liste.Liste;
import main.TaxiSluzbaMain;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Vozac;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ProzorZaNarucivanjePutemAplikacije extends JFrame {

    private JLabel adresaPolaska = new JLabel("Adresa polaska");
    private JTextField tadresaPolaska = new JTextField(20);

    private JLabel adresaDestinacije = new JLabel("Adresa destinacije");
    private JTextField tadresaDestinacije = new JTextField(20);

    private JLabel napomena = new JLabel("Napomena");
    private JTextField tnapomena = new JTextField(20);

    private JButton naruci = new JButton("Naruci");
    private JButton odustani = new JButton("Odustani");

    private Liste ucitavanje;
    private Voznja voznja;
    private Musterija musterija;
    private NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije;

    public ProzorZaNarucivanjePutemAplikacije(Liste ucitavanje, Musterija musterija) {
        this.ucitavanje = ucitavanje;
        this.musterija = musterija;
        setTitle(musterija.getIme().substring(0, 1).toUpperCase() + musterija.getIme().substring(1) + "Narucivanje putem aplikacije");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGui();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGui() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(adresaPolaska);
        add(tadresaPolaska);
        add(adresaDestinacije);
        add(tadresaDestinacije);
        add(napomena);
        add(tnapomena);
        add(new JLabel());
        add(naruci, "split 2");
        add(odustani);
    }

    private void initListeners() {
        naruci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true) {

                    int id = ucitavanje.generisiNoviIdZaVoznjePutemAplikacije();
                    LocalDateTime trenutnoVreme = LocalDateTime.now();
                    String adresaPolaska = tadresaPolaska.getText().trim();
                    String adresaDestinacije = tadresaDestinacije.getText().trim();
                    String napomena = tnapomena.getText().trim();
                    Vozac vozac = ucitavanje.nadjiVozacaKojiJeSlobodan();
                    if (vozac != null){
                        vozac.getKorisnickoIme();
                        vozac.setStatusVozaca(StatusVozacaIautomobila.ZAUZET);
                        ucitavanje.dodavanjeKorisnika();
                    }else {
                        Vozac nePostojiSlobodanVozac = new Vozac();
                        vozac = nePostojiSlobodanVozac;
                    }

                    if (narucivanjeVoznjePrekoAplikacije != null){
                        narucivanjeVoznjePrekoAplikacije.setId(id);
                        narucivanjeVoznjePrekoAplikacije.setDatumIvremePorudzbine(trenutnoVreme);
                        narucivanjeVoznjePrekoAplikacije.setAdresaPolaska(adresaPolaska);
                        narucivanjeVoznjePrekoAplikacije.setAdresaDestinacije(adresaDestinacije);
                        narucivanjeVoznjePrekoAplikacije.setNapomena(napomena);
                        narucivanjeVoznjePrekoAplikacije.setVozac(vozac);
                    }

                    try {
                        File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                        Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                        while (citanjeUlogovanogKorisnika.hasNextLine()) {
                            String data = citanjeUlogovanogKorisnika.nextLine();
                            Musterija ulogovanaMusterija = new Musterija();
                            ulogovanaMusterija.setKorisnickoIme(data);
                            NarucivanjeVoznjePrekoAplikacije narucivanjeVoznjePrekoAplikacije = new NarucivanjeVoznjePrekoAplikacije(id,trenutnoVreme,adresaPolaska,adresaDestinacije,ulogovanaMusterija,vozac,0,0, StatusVoznje.KREIRANA_NA_CEKANJU,true,StatusNaruceneVoznje.APLIKACIJA, 0,false,"Svejedno",napomena);
                            ucitavanje.getVoznjaAplikacije().add(narucivanjeVoznjePrekoAplikacije);
                        }
                        citanjeUlogovanogKorisnika.close();
                    }  catch (IOException ioException) {
                        ioException.printStackTrace();
                        System.out.println("Greska");
                    }
                    JOptionPane.showMessageDialog(null,"Uspesno ste narucili voznju!","Cestitam",JOptionPane.INFORMATION_MESSAGE);
                    ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                    ProzorZaNarucivanjePutemAplikacije.this.dispose();
                    ProzorZaNarucivanjePutemAplikacije.this.setVisible(false);
                }
            }
        });
        odustani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od narucivanja voznje!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                ProzorZaNarucivanjePutemAplikacije.this.setVisible(false);
                ProzorZaNarucivanjePutemAplikacije.this.dispose();
            }
        });
    }

    private boolean validacija() {
        boolean ok = true;
        String porukaObavestenja = "Molimo Vas ispravite sta je potrebno! \n";
        if (tadresaPolaska.getText().trim().equals("")) {
            porukaObavestenja += "Polje za adresu polaska mora biti popunjeno! \n";
            ok = false;
        }
        if (tadresaDestinacije.getText().trim().equals("")) {
            porukaObavestenja += "Polje za adresu destinacije mora biti popunjeno! \n";
            ok = false;
        }
        if (napomena.getText().trim().equals("")) {
            porukaObavestenja += "Polje za napomenu mora biti popunjeno! \n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, porukaObavestenja, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
