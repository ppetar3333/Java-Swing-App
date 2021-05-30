package dispecer.podaciAutomobila;

import automobili.Automobil;
import enumi.StatusVozacaIautomobila;
import enumi.VrstaVozila;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import osobe.Osoba;
import osobe.Vozac;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodavanjeAutomobila extends JFrame {

    private Container c;
    private JLabel model;
    private JTextField tmodel;
    private JLabel proizvodjac;
    private JTextField tproizvodjac;
    private JLabel godinaProizvodnje;
    private JTextField tgodinaProizvodnje;
    private JLabel brojRegistarskeOznake;
    private JTextField tbrojRegistarskeOznake;
    private JLabel brojTaksiVozila;
    private JTextField tbrojTaksiVozila;
    private JLabel vrstaVozila;
    private JRadioButton putnickiAutomobil;
    private JRadioButton kombi;
    private ButtonGroup vrstaVozilaDugme;
    private JLabel vozac;
    private JComboBox<Vozac> slobodniVozac;
    private JLabel petFriendly;
    private JRadioButton da;
    private JRadioButton ne;
    private ButtonGroup petFriendlyDugme;
    private JButton btnOK;
    private Osoba osoba;
    private Liste ucitavanje;

    public DodavanjeAutomobila(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;
        setTitle("Dodavanje Automobila");
        setSize(900, 440);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        c = getContentPane();
        c.setLayout(null);

        model = new JLabel("Model:");
        model.setFont(new Font("Arial", Font.PLAIN, 18));
        model.setSize(100, 20);
        model.setLocation(40, 60);
        c.add(model);

        tmodel = new JTextField();
        tmodel.setFont(new Font("Arial", Font.PLAIN, 15));
        tmodel.setSize(190, 35);
        tmodel.setLocation(180, 55);
        c.add(tmodel);

        proizvodjac = new JLabel("Proizvodjac:");
        proizvodjac.setFont(new Font("Arial", Font.PLAIN, 18));
        proizvodjac.setSize(100, 20);
        proizvodjac.setLocation(40, 120);
        c.add(proizvodjac);

        tproizvodjac = new JTextField();
        tproizvodjac.setFont(new Font("Arial", Font.PLAIN, 15));
        tproizvodjac.setSize(190, 35);
        tproizvodjac.setLocation(180, 115);
        c.add(tproizvodjac);

        godinaProizvodnje = new JLabel("Godina proizvodnje: ");
        godinaProizvodnje.setFont(new Font("Arial", Font.PLAIN, 18));
        godinaProizvodnje.setSize(200, 20);
        godinaProizvodnje.setLocation(430, 120);
        c.add(godinaProizvodnje);

        tgodinaProizvodnje = new JTextField();
        tgodinaProizvodnje.setFont(new Font("Arial", Font.PLAIN, 15));
        tgodinaProizvodnje.setSize(190, 35);
        tgodinaProizvodnje.setLocation(650, 115);
        c.add(tgodinaProizvodnje);

        brojRegistarskeOznake = new JLabel("Broj registarske oznake: ");
        brojRegistarskeOznake.setFont(new Font("Arial", Font.PLAIN, 18));
        brojRegistarskeOznake.setSize(200, 20);
        brojRegistarskeOznake.setLocation(430, 60);
        c.add(brojRegistarskeOznake);

        tbrojRegistarskeOznake = new JTextField();
        tbrojRegistarskeOznake.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojRegistarskeOznake.setSize(190, 35);
        tbrojRegistarskeOznake.setLocation(650, 55);
        c.add(tbrojRegistarskeOznake);

        brojTaksiVozila = new JLabel("Broj taksi vozila: ");
        brojTaksiVozila.setFont(new Font("Arial", Font.PLAIN, 18));
        brojTaksiVozila.setSize(200, 20);
        brojTaksiVozila.setLocation(430, 180);
        c.add(brojTaksiVozila);

        tbrojTaksiVozila = new JTextField();
        tbrojTaksiVozila.setFont(new Font("Arial", Font.PLAIN, 15));
        tbrojTaksiVozila.setSize(190, 35);
        tbrojTaksiVozila.setLocation(650, 175);
        c.add(tbrojTaksiVozila);

        vrstaVozila = new JLabel("Vrsta Vozila: ");
        vrstaVozila.setFont(new Font("Arial", Font.PLAIN, 18));
        vrstaVozila.setSize(130, 20);
        vrstaVozila.setLocation(40, 180);
        c.add(vrstaVozila);

        putnickiAutomobil = new JRadioButton("Putnicki Automobil");
        putnickiAutomobil.setFont(new Font("Arial", Font.PLAIN, 15));
        putnickiAutomobil.setSelected(true);
        putnickiAutomobil.setSize(180, 20);
        putnickiAutomobil.setLocation(170, 180);
        c.add(putnickiAutomobil);

        kombi = new JRadioButton("Kombi");
        kombi.setFont(new Font("Arial", Font.PLAIN, 15));
        kombi.setSelected(false);
        kombi.setSize(120, 20);
        kombi.setLocation(330, 180);
        c.add(kombi);

        vrstaVozilaDugme = new ButtonGroup();
        vrstaVozilaDugme.add(putnickiAutomobil);
        vrstaVozilaDugme.add(kombi);

        petFriendly = new JLabel("Pet Friendly");
        petFriendly.setFont(new Font("Arial", Font.PLAIN, 18));
        petFriendly.setSize(130, 20);
        petFriendly.setLocation(40, 250);
        c.add(petFriendly);

        da = new JRadioButton("Da");
        da.setFont(new Font("Arial", Font.PLAIN, 15));
        da.setSelected(false);
        da.setSize(70, 20);
        da.setLocation(190, 250);
        c.add(da);

        ne = new JRadioButton("Ne");
        ne.setFont(new Font("Arial", Font.PLAIN, 15));
        ne.setSelected(true);
        ne.setSize(70, 20);
        ne.setLocation(270, 250);
        c.add(ne);

        petFriendlyDugme = new ButtonGroup();
        petFriendlyDugme.add(da);
        petFriendlyDugme.add(ne);

        vozac = new JLabel("Vozac:");
        vozac.setFont(new Font("Arial", Font.PLAIN, 18));
        vozac.setSize(190, 35);
        vozac.setLocation(430, 250);
        c.add(vozac);

        DoublyLinkedList<String> vozac = ucitavanje.listaVozacaBezAutomobila();
        String[] array = new String[vozac.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = String.valueOf(vozac.get(i));
        }
        slobodniVozac = new JComboBox(array);
        slobodniVozac.setFont(new Font("Arial", Font.PLAIN, 15));
        slobodniVozac.setSize(190, 35);
        slobodniVozac.setLocation(645, 250);
        c.add(slobodniVozac);

        btnOK = new JButton("Potvrdi");
        btnOK.setFont(new Font("Arial", Font.PLAIN, 19));
        btnOK.setSize(180, 40);
        btnOK.setLocation(350, 340);
        btnOK.setBackground(Color.BLUE);
        c.add(btnOK);

        initListeners();
    }

    private void initListeners() {

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveraPodataka() == true ) {

                    Automobil automobil = new Automobil();

                    String unosModel = tmodel.getText().trim();
                    String unosProizvodjac = tproizvodjac.getText().trim();
                    int unosGodinaProizvodnje = Integer.parseInt(tgodinaProizvodnje.getText().trim());
                    String unosBrojRegistarskeOznake = tbrojRegistarskeOznake.getText().trim();
                    int unosBrojTaksiVozila = Integer.parseInt(tbrojTaksiVozila.getText().trim());
                    StatusVozacaIautomobila statusAutomobila = StatusVozacaIautomobila.SLOBODAN;


                    automobil.setModel(unosModel);
                    automobil.setProizvodjac(unosProizvodjac);
                    automobil.setGodinaProizvodnje(unosGodinaProizvodnje);
                    automobil.setRegistarskiBroj(unosBrojRegistarskeOznake);
                    automobil.setBrojVozila(unosBrojTaksiVozila);
                    automobil.setObrisan(true);
                    automobil.setStatusAutomobila(statusAutomobila);

                    if (putnickiAutomobil.isSelected()) {
                        automobil.setVrstaVozila(VrstaVozila.PUTNICKI_AUTOMOBIL);
                    } else if (kombi.isSelected()) {
                        automobil.setVrstaVozila(VrstaVozila.KOMBI);
                    }
                    if (da.isSelected()){
                        automobil.setPetFriendly(true);
                    }else if(ne.isSelected()){
                        automobil.setPetFriendly(false);
                    }

                    DoublyLinkedList<Automobil> automobili = ucitavanje.getAutomobili();
                    int id = generisiNoviId(automobili);
                    automobil.setId(id);

                    if (slobodniVozac.getSelectedItem() != null){
                        String vozacKorisnickoIme = slobodniVozac.getSelectedItem().toString();
                        Vozac vozac1 = ucitavanje.nadjiVozaca(vozacKorisnickoIme);
                        if(vozac1 != null){
                            vozac1.setAutomobili(automobil);
                            automobil.setStatusAutomobila(StatusVozacaIautomobila.ZAUZET);
                            ucitavanje.dodavanjeKorisnika();
                        }
                    }

                    automobili.add(automobil);
                    ucitavanje.snimanjeAutomobila(TaxiSluzbaMain.AUTOMOBILI_FAJL);
                    JOptionPane.showMessageDialog(null, "Automobil je uspesno dodat!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    DodavanjeAutomobila.this.dispose();
                    DodavanjeAutomobila.this.setVisible(false);
                }
            }
        });
    }

    private int generisiNoviId(DoublyLinkedList<Automobil> automobili) {
        int maks = -1;
        for (Automobil a : automobili) {
            if (a.getId() > maks) {
                maks = a.getId();
            }
        }
        return maks + 1;
    }

    private boolean proveraPodataka() {
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if (tmodel.getText().trim().equals("")) {
            obavestenjeZaGresku += "Morate uneti model automobila!\n";
            ok = false;
        }
        if (tproizvodjac.getText().trim().equals("")) {
            obavestenjeZaGresku += "Morate uneti proizvodjaca za automobil!\n";
            ok = false;
        }
        if(tgodinaProizvodnje.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za godinu proizvodnje ne sme biti prazno!\n";
        }
        try {
            Integer.parseInt(tgodinaProizvodnje.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Godina proizvodnje mora biti broj!\n";
            ok = false;
        }
        if (tbrojRegistarskeOznake.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti broj registarske oznake! \n";
            ok = false;
        }
        if(tbrojTaksiVozila.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za broj taksi vozila ne sme biti prazno!\n";
        }
        try {
            Integer.parseInt(tbrojTaksiVozila.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Broj taksi vozila mora biti broj!\n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}


