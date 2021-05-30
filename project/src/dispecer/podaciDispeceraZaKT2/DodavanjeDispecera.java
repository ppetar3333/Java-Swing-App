package dispecer.podaciDispeceraZaKT2;

import enumi.Odeljenje;
import enumi.Pol;
import liste.Liste;
import net.miginfocom.swing.MigLayout;
import osobe.Dispecar;
import osobe.Osoba;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodavanjeDispecera extends JFrame{

    private JLabel korisnickoIme = new JLabel("Korisnicko ime");
    private JTextField tkorisnickoIme = new JTextField(20);
    private JLabel lozinka = new JLabel("Lozinka");
    private JPasswordField tlozinka = new JPasswordField(20);
    private JLabel ime = new JLabel("Ime");
    private JTextField time = new JTextField(20);
    private JLabel prezime = new JLabel("Prezime");
    private JTextField tprezime = new JTextField(20);
    private JLabel adresa = new JLabel("Adresa");
    private JTextField tadresa = new JTextField(20);
    private JLabel jmbg = new JLabel("JMBG");
    private JTextField tjmbg = new JTextField(20);
    private JLabel pol = new JLabel("Pol");
    private JComboBox<Pol> polJComboBox = new JComboBox<Pol>(Pol.values());
    private JLabel brojTelefona = new JLabel("Broj telefona");
    private JTextField tbrojTelefona = new JTextField(20);
    private JLabel plata = new JLabel("Plata");
    private JTextField tplata = new JTextField(20);
    private JLabel brojTelefonskeLinije = new JLabel("Broj telefonske linije");
    private JTextField tbrojTelefonskeLinije = new JTextField(20);
    private JLabel odeljenje = new JLabel("Odeljenje na kojem radi");
    private JComboBox<Odeljenje> odeljenjeJComboBox = new JComboBox<Odeljenje>(Odeljenje.values());

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;
    private Dispecar dispecar;
    private Osoba osoba;

    public DodavanjeDispecera(Liste ucitavanje, Dispecar dispecar){
        this.ucitavanje = ucitavanje;
        this.dispecar = dispecar;
        setTitle("Dodavanje dispecera");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
        initActions();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(korisnickoIme);
        add(tkorisnickoIme);
        add(lozinka);
        add(tlozinka);
        add(ime);
        add(time);
        add(prezime);
        add(tprezime);
        add(adresa);
        add(tadresa);
        add(jmbg);
        add(tjmbg);
        add(brojTelefona);
        add(tbrojTelefona);
        add(plata);
        add(tplata);
        add(brojTelefonskeLinije);
        add(tbrojTelefonskeLinije);
        add(odeljenje);
        add(odeljenjeJComboBox);
        add(pol);
        add(polJComboBox);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);
    }

    private void initActions(){

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(proveraUnesenihPodataka() == true){
                    String korisnickoIme = tkorisnickoIme.getText().trim();
                    String lozinka = new String(tlozinka.getPassword()).trim();
                    String ime = time.getText().trim();
                    String prezime = tprezime.getText().trim();
                    String adresa = tadresa.getText().trim();
                    String brojTelefona = tbrojTelefona.getText().trim();
                    Pol pol = (Pol) polJComboBox.getSelectedItem();
                    String jmbg = tjmbg.getText().trim();
                    boolean obrisan = dispecar.isObrisan();
                    double plata = Double.parseDouble(tplata.getText().trim());
                    String brojTelefonskeLinije = tbrojTelefonskeLinije.getText().trim();
                    Odeljenje odeljenje = (Odeljenje) odeljenjeJComboBox.getSelectedItem();

                    boolean istoKorisnickoImeDispecera = ucitavanje.istoKorisnickoImeDispecera(korisnickoIme);

                    if(istoKorisnickoImeDispecera == false) {
                        if (osoba != null) {
                            Dispecar dispecar = (Dispecar) osoba;
                            dispecar.setKorisnickoIme(korisnickoIme);
                            dispecar.setLozinka(lozinka);
                            dispecar.setIme(ime);
                            dispecar.setPrezime(prezime);
                            dispecar.setJmbg(jmbg);
                            dispecar.setAdresa(adresa);
                            dispecar.setPol(pol);
                            dispecar.setBrojTelefona(brojTelefona);
                            dispecar.setPlata(plata);
                            dispecar.setBrojTelefonskeLinije(brojTelefonskeLinije);
                            dispecar.setOdeljenje(odeljenje);
                        }

                        Dispecar dispecar = new Dispecar(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan, plata, brojTelefonskeLinije, odeljenje);
                        ucitavanje.getDispecari().add(dispecar);
                        ucitavanje.dodavanjeKorisnika();
                        JOptionPane.showMessageDialog(null, "Dispecer je uspesno dodat!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        DodavanjeDispecera.this.dispose();
                        DodavanjeDispecera.this.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Dispecer sa unesenim korisnickim imenom vec posotji, pokusajte ponovo.","Obavestenje",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od dodavanja novog dispecera!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                DodavanjeDispecera.this.setVisible(false);
                DodavanjeDispecera.this.dispose();
            }
        });
    }

    private boolean proveraUnesenihPodataka(){
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n\n";

        if(tkorisnickoIme.getText().equals("")){
            obavestenjeZaGresku += "Polje za korisnicko ime ne sme biti prazno! \n";
            ok = false;
        }
        if(tlozinka.getText().equals("")){
            obavestenjeZaGresku += "Polje za lozinku ne sme ostati prazno! \n";
            ok = false;
        }
        if(time.getText().equals("")){
            obavestenjeZaGresku += "Polje za ime ne sme biti prazno! \n";
            ok = false;
        }
        if(tprezime.getText().equals("")){
            obavestenjeZaGresku += "Polje za prezime ne sme biti prazno! \n";
            ok = false;
        }
        if(tadresa.getText().equals("")){
            obavestenjeZaGresku += "Polje za adresu ne sme biti prazno! \n";
            ok = false;
        }
        if(tbrojTelefona.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za broj telefona ne sme biti prazno!\n";
        }
        try{
            Integer.parseInt(tbrojTelefona.getText());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Broj telefona mora biti broj! \n";
            ok = false;
        }
        if(tplata.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za platu ne sme biti prazno!\n";
        }
        try{
            Double.parseDouble(tplata.getText());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Plata mora biti broj! \n";
            ok = false;
        }
        if(tbrojTelefonskeLinije.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za broj telefonske linije ne sme biti prazno!\n";
        }
        try{
            Integer.parseInt(tbrojTelefonskeLinije.getText());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Broj telefonske linije mora biti broj! \n";
            ok = false;
        }
        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
