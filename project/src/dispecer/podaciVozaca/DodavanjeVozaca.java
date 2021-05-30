package dispecer.podaciVozaca;

import automobili.Automobil;
import enumi.StatusVozacaIautomobila;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import osobe.Osoba;
import enumi.Pol;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodavanjeVozaca extends JFrame{

    private Container c;
    private JLabel ime;
    private JTextField time;
    private JLabel prezime;
    private JTextField tprezime;
    private JLabel korisnickoIme;
    private JTextField tkorisnickoIme;
    private JLabel lozinka;
    private JPasswordField tlozinka;
    private JLabel adresa;
    private JTextField tadresa;
    private JLabel pol;
    private JRadioButton muski;
    private JRadioButton zenski;
    private ButtonGroup polDugme;
    private JLabel jmbg;
    private JTextField tjmbg;
    private JLabel brojTelefona;
    private JTextField tbrojTelefona;
    private JLabel plata;
    private JTextField tplata;
    private JLabel brojClanskeKarte;
    private JTextField tbrojClanskeKarte;
    private JLabel automobil;
    private JComboBox<Automobil> slobodniAutomobil;
    private JButton btnOK;
    private Liste ucitavanje;
    private Vozac vozac;
    private Osoba osoba;

    public DodavanjeVozaca(Liste ucitavanje, Vozac vozac) {
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;

        setTitle("Dodavanje Vozaca");
        setSize(900, 570);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        ime = new JLabel("Ime:");
        ime.setFont(new Font("Arial", Font.PLAIN, 18));
        ime.setSize(100, 20);
        ime.setLocation(40, 60);
        c.add(ime);

        time = new JTextField();
        time.setFont(new Font("Arial", Font.PLAIN, 15));
        time.setSize(190, 35);
        time.setLocation(180, 55);
        c.add(time);

        prezime = new JLabel("Prezime:");
        prezime.setFont(new Font("Arial", Font.PLAIN, 18));
        prezime.setSize(100, 20);
        prezime.setLocation(40, 120);
        c.add(prezime);

        tprezime = new JTextField();
        tprezime.setFont(new Font("Arial", Font.PLAIN, 15));
        tprezime.setSize(190, 35);
        tprezime.setLocation(180, 115);
        c.add(tprezime);

        korisnickoIme = new JLabel("Korisnicko ime: ");
        korisnickoIme.setFont(new Font("Arial", Font.PLAIN, 18));
        korisnickoIme.setSize(130, 20);
        korisnickoIme.setLocation(40, 180);
        c.add(korisnickoIme);

        tkorisnickoIme = new JTextField();
        tkorisnickoIme.setFont(new Font("Arial", Font.PLAIN, 15));
        tkorisnickoIme.setSize(190, 35);
        tkorisnickoIme.setLocation(180, 175);
        c.add(tkorisnickoIme);

        lozinka = new JLabel("Lozinka: ");
        lozinka.setFont(new Font("Arial", Font.PLAIN, 18));
        lozinka.setSize(100, 20);
        lozinka.setLocation(40, 240);
        c.add(lozinka);

        tlozinka = new JPasswordField();
        tlozinka.setFont(new Font("Arial", Font.PLAIN, 15));
        tlozinka.setSize(190, 35);
        tlozinka.setLocation(180, 235);
        c.add(tlozinka);

        adresa = new JLabel("Adresa: ");
        adresa.setFont(new Font("Arial", Font.PLAIN, 18));
        adresa.setSize(100, 20);
        adresa.setLocation(40, 300);
        c.add(adresa);

        tadresa = new JTextField();
        tadresa.setFont(new Font("Arial", Font.PLAIN, 15));
        tadresa.setSize(190, 35);
        tadresa.setLocation(180, 295);
        c.add(tadresa);

        pol = new JLabel("Pol: ");
        pol.setFont(new Font("Arial", Font.PLAIN, 18));
        pol.setSize(100, 20);
        pol.setLocation(490, 300);
        c.add(pol);

        muski = new JRadioButton("Muski");
        muski.setFont(new Font("Arial", Font.PLAIN, 15));
        muski.setSelected(true);
        muski.setSize(75, 20);
        muski.setLocation(650, 300);
        c.add(muski);

        zenski = new JRadioButton("Zenski");
        zenski.setFont(new Font("Arial", Font.PLAIN, 15));
        zenski.setSelected(false);
        zenski.setSize(80, 20);
        zenski.setLocation(740, 300);
        c.add(zenski);

        polDugme = new ButtonGroup();
        polDugme.add(muski);
        polDugme.add(zenski);

        jmbg = new JLabel("JMBG: ");
        jmbg.setFont(new Font("Arial", Font.PLAIN, 18));
        jmbg.setSize(100, 20);
        jmbg.setLocation(490, 60);
        c.add(jmbg);

        tjmbg = new JTextField();
        tjmbg.setFont(new Font("Arial", Font.PLAIN, 15));
        tjmbg.setSize(190, 35);
        tjmbg.setLocation(650, 55);
        c.add(tjmbg);

        brojTelefona = new JLabel("Broj Telefona: ");
        brojTelefona.setFont(new Font("Arial", Font.PLAIN, 18));
        brojTelefona.setSize(150, 20);
        brojTelefona.setLocation(490, 120);
        c.add(brojTelefona);

        tbrojTelefona = new JTextField();
        tbrojTelefona.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojTelefona.setSize(190, 35);
        tbrojTelefona.setLocation(650, 115);
        c.add(tbrojTelefona);

        plata = new JLabel("Plata: ");
        plata.setFont(new Font("Arial", Font.PLAIN, 18));
        plata.setSize(100, 20);
        plata.setLocation(490, 180);
        c.add(plata);

        tplata = new JTextField();
        tplata.setFont(new Font("Arial", Font.PLAIN, 15));
        tplata.setSize(190, 35);
        tplata.setLocation(650, 175);
        c.add(tplata);

        brojClanskeKarte = new JLabel("Br. clanske karte: ");
        brojClanskeKarte.setFont(new Font("Arial", Font.PLAIN, 18));
        brojClanskeKarte.setSize(150, 20);
        brojClanskeKarte.setLocation(490, 240);
        c.add(brojClanskeKarte);

        tbrojClanskeKarte = new JTextField();
        tbrojClanskeKarte.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojClanskeKarte.setSize(190, 35);
        tbrojClanskeKarte.setLocation(650, 235);
        c.add(tbrojClanskeKarte);

        automobil = new JLabel("Automobil:");
        automobil.setFont(new Font("Arial", Font.PLAIN, 18));
        automobil.setSize(190, 35);
        automobil.setLocation(40, 350);
        c.add(automobil);

        DoublyLinkedList<Integer> auto = ucitavanje.listaSlobodnihAutomobila();
        String[] array = new String[auto.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = String.valueOf(auto.get(i));
        }
        slobodniAutomobil = new JComboBox(array);
        slobodniAutomobil.setFont(new Font("Arial", Font.PLAIN, 15));
        slobodniAutomobil.setSize(190, 35);
        slobodniAutomobil.setLocation(180, 350);
        c.add(slobodniAutomobil);

        btnOK = new JButton("Potvrdi");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 19));
        btnOK.setSize(180, 40);
        btnOK.setLocation(350, 450);
        btnOK.setBackground(Color.BLUE);
        c.add(btnOK);

        this.getRootPane().setDefaultButton(btnOK);


        initListeners();

    }
    private void initListeners() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(proveraPodataka() == true){

                    String unosIme = time.getText().trim();
                    String unosPrezime = tprezime.getText().trim();
                    String unosKorisnickoIme = tkorisnickoIme.getText().trim();
                    String unosLozinka = new String (tlozinka.getPassword());
                    String unosAdresa = tadresa.getText().trim();
                    String unosJMBG = tjmbg.getText().trim();
                    String unosBrojTelefona = tbrojTelefona.getText().trim();
                    double unosPlata = Double.parseDouble(tplata.getText().trim());
                    int unosBrojClanskeKarte = Integer.parseInt(tbrojClanskeKarte.getText().trim());
                    double ocena = 0;
                    StatusVozacaIautomobila statusVozaca = StatusVozacaIautomobila.SLOBODAN;

                    boolean istoKorisnickoImeVozaca = ucitavanje.istoKorisnickoImeVozaca(unosKorisnickoIme);

                    if(istoKorisnickoImeVozaca == false) {

                        Automobil automobil = new Automobil();
                        if (slobodniAutomobil.getSelectedItem() != null) {
                            int automobilID = Integer.parseInt(slobodniAutomobil.getSelectedItem().toString());
                            automobil.setId(automobilID);

                            DoublyLinkedList<Automobil> sviAuti = ucitavanje.neobrisaniAutomobili();
                            int indexGdeSeNalazi = ucitavanje.pronadjiAutomobilBinarySearch(sviAuti,automobilID);
                            Automobil automobil1 = sviAuti.get(indexGdeSeNalazi);

                            if (automobilID == automobil1.getId()) {
                                automobil1.setStatusAutomobila(StatusVozacaIautomobila.ZAUZET);
                                ucitavanje.snimanjeAutomobila(TaxiSluzbaMain.AUTOMOBILI_FAJL);
                            }
                        } else {
                            automobil.setId(0);
                        }

                        if (osoba != null) {
                            Vozac vozac = (Vozac) osoba;
                            vozac.setIme(unosIme);
                            vozac.setPrezime(unosPrezime);
                            vozac.setKorisnickoIme(unosKorisnickoIme);
                            vozac.setLozinka(unosLozinka);
                            vozac.setAdresa(unosAdresa);
                            vozac.setJmbg(unosJMBG);
                            vozac.setBrojTelefona(unosBrojTelefona);
                            vozac.setPlata(unosPlata);
                            vozac.setBrojClanskeKarte(unosBrojClanskeKarte);
                            vozac.setStatusVozaca(statusVozaca);
                        }


                        if (muski.isSelected()) {
                            Pol pol = Pol.MUSKI;
                            Vozac vozac = new Vozac(unosKorisnickoIme, unosLozinka, unosIme, unosPrezime, unosJMBG, unosAdresa, pol, unosBrojTelefona, true, unosPlata, unosBrojClanskeKarte, automobil, ocena, statusVozaca);
                            ucitavanje.getVozaci().add(vozac);
                        } else if (zenski.isSelected()) {
                            Pol pol = Pol.ZENSKI;
                            Vozac vozac = new Vozac(unosKorisnickoIme, unosLozinka, unosIme, unosPrezime, unosJMBG, unosAdresa, pol, unosBrojTelefona, true, unosPlata, unosBrojClanskeKarte, automobil, ocena, statusVozaca);
                            ucitavanje.getVozaci().add(vozac);
                        }
                        ucitavanje.dodavanjeKorisnika();
                        JOptionPane.showMessageDialog(null, "Vozac je uspesno dodat!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        DodavanjeVozaca.this.dispose();
                        DodavanjeVozaca.this.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null, "Vozac sa unesenim korisnicim imenom vec postoji, pokusajte ponovo.","Obaestenje",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    public boolean proveraPodataka(){
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if(time.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti ime! \n";
            ok = false;
        }
        if(tprezime.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti prezime! \n";
            ok = false;
        }
        if(tkorisnickoIme.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti korisnicko ime! \n";
            ok = false;
        }
        if(tlozinka.getPassword().equals("")){
            obavestenjeZaGresku += "Morate uneti sifru! \n";
            ok = false;
        }
        if(tadresa.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti adresu! \n";
            ok = false;
        }
        if(tjmbg.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za jmbg ne sme biti prazno! \n";
        }
        try {
            Integer.parseInt(tjmbg.getText().trim());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Jmbg mora biti broj! \n";
            ok = false;
        }
        if(tbrojTelefona.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za broj telefona ne sme biti prazno! \n";
        }
        try{
            Integer.parseInt(tbrojTelefona.getText().trim());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Broj telefona mora biti broj! \n";
            ok = false;
        }
        if(tplata.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za platu ne sme biti prazno! \n";
        }
        try {
            Double.parseDouble(tplata.getText().trim());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Plata mora biti broj! \n";
            ok = false;
        }
        if(tbrojClanskeKarte.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za broj clanske karte ne sme biti prazno! \n";
        }
        try{
            Integer.parseInt(tbrojClanskeKarte.getText().trim());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Broj clanske karte mora biti broj! \n";
            ok = false;
        }

        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci!", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }
}