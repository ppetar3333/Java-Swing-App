package musterija.probaZaAlgoritme;

import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import liste.Liste;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
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

public class NaruciVoznjuTelefonomAukcija extends JFrame{

    private JLabel adresaPolaska = new JLabel("Adresa polaska");
    private JTextField tadresaPolaska = new JTextField(29);
    private JLabel adresaDolaska = new JLabel("Adresa dolaska");
    private JTextField tadresaDolaska = new JTextField(29);
    private JLabel izaberi = new JLabel("Izaberi");
    private JComboBox<String> mogucnosti;
    private JButton naruci = new JButton("Naruci");
    private JButton odustani = new JButton("Odustani");
    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoTelefona narucivanjeVoznjePrekoTelefona;
    private Musterija musterija;

    public NaruciVoznjuTelefonomAukcija(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.musterija = musterija;
        setTitle(musterija.getIme().substring(0,1).toUpperCase() + musterija.getIme().substring(1) + ", Naruci voznju telefonom");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGui();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGui(){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(adresaPolaska);
        add(tadresaPolaska);
        add(adresaDolaska);
        add(tadresaDolaska);
        String[] mogucnostiZaBiranje = new String[]{"Svejedno","Najbrzi vozac","Najbolje ocenjen vozac","Pet friendly automobil","Najnoviji automobil"};
        mogucnosti = new JComboBox(mogucnostiZaBiranje);
        add(izaberi);
        add(mogucnosti);
        add(new JLabel());
        add(naruci,"split 2");
        this.getRootPane().setDefaultButton(naruci);
        add(odustani);

    }

    private void initListeners(){
        naruci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                    int id = ucitavanje.generisiNoviIdZaVoznjePutemTelefona();
                    LocalDateTime trenutnoVreme = LocalDateTime.now();
                    String adresaPolaska = tadresaPolaska.getText().trim();
                    String adresaDolaska = tadresaDolaska.getText().trim();

                    String selektovanaMogucnost = (String) mogucnosti.getSelectedItem();

                    Vozac vozac = new Vozac();
                    vozac.setKorisnickoIme("");
                    if(narucivanjeVoznjePrekoTelefona != null){
                        narucivanjeVoznjePrekoTelefona.setId(id);
                        narucivanjeVoznjePrekoTelefona.setDatumIvremePorudzbine(trenutnoVreme);
                        narucivanjeVoznjePrekoTelefona.setAdresaPolaska(adresaPolaska);
                        narucivanjeVoznjePrekoTelefona.setAdresaDestinacije(adresaDolaska);
                        narucivanjeVoznjePrekoTelefona.setVozac(vozac);
                    }
                    try {
                        File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                        Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                        while (citanjeUlogovanogKorisnika.hasNextLine()) {
                            String data = citanjeUlogovanogKorisnika.nextLine();
                            Musterija ulogovanaMusterija = new Musterija();
                            ulogovanaMusterija.setKorisnickoIme(data);
                            NarucivanjeVoznjePrekoTelefona narucivanjeVoznjePrekoTelefona = new NarucivanjeVoznjePrekoTelefona(id,trenutnoVreme,adresaPolaska,adresaDolaska,ulogovanaMusterija,vozac,0,0, StatusVoznje.KREIRANA,true, StatusNaruceneVoznje.TELEFON,0,false,selektovanaMogucnost);
                            ucitavanje.getVoznjaTelefoni().add(narucivanjeVoznjePrekoTelefona);
                        }
                        citanjeUlogovanogKorisnika.close();
                    }  catch (IOException ioException) {
                        ioException.printStackTrace();
                        System.out.println("Greska");
                    }

                    JOptionPane.showMessageDialog(null,"Uspesno ste narucili voznju!","Cestitam",JOptionPane.INFORMATION_MESSAGE);
                    ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                    NaruciVoznjuTelefonomAukcija.this.dispose();
                    NaruciVoznjuTelefonomAukcija.this.setVisible(false);
                }
            }
        });
        odustani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od narucivanja voznje!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                NaruciVoznjuTelefonomAukcija.this.setVisible(false);
                NaruciVoznjuTelefonomAukcija.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String porukaObavestenja = "Molimo vas ispravite sta je potrebno! \n";
        if(tadresaPolaska.getText().equals("")){
            porukaObavestenja += "Polje za adresu polaska ne sme biti prazno! \n";
            ok = false;
        }
        if(tadresaDolaska.getText().equals("")){
            porukaObavestenja += "Polje za adresu dolaska ne sme biti prazno! \n";
            ok = false;
        }
        if(ok == false) {
            JOptionPane.showMessageDialog(null, porukaObavestenja, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;

    }

}
