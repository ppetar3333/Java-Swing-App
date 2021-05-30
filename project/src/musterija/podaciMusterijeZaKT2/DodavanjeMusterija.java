package musterija.podaciMusterijeZaKT2;

import enumi.Pol;
import liste.Liste;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Osoba;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodavanjeMusterija extends JFrame {

    private JLabel korisnickoIme = new JLabel("Korisnicko ime");
    private JTextField tkorisnickoIme = new JTextField(20);
    private JLabel lozinka = new JLabel("Lozinka");
    private JPasswordField tlozinka = new JPasswordField(20);
    private JLabel ime = new JLabel("Ime");
    private JTextField time = new JTextField(20);
    private JLabel prezime = new JLabel("Prezime");
    private JTextField tprezime = new JTextField(20);
    private JLabel jmbg = new JLabel("JMBG");
    private JTextField tjmbg = new JTextField(20);
    private JLabel adresa = new JLabel("Adresa");
    private JTextField tadresa = new JTextField(20);
    private JLabel pol = new JLabel("Pol");
    private JComboBox<Pol> polJComboBox = new JComboBox<Pol>(Pol.values());
    private JLabel brojTelefona = new JLabel("Broj telefona");
    private JTextField tbrojTelefona = new JTextField(20);
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;
    private Musterija musterija;
    private Osoba osoba;

    public DodavanjeMusterija(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.musterija = musterija;
        setTitle("Dodavanje musterije");
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
                    String unosIme = time.getText().trim();
                    String unosPrezime = tprezime.getText().trim();
                    String unosLozinka = new String(tlozinka.getPassword());
                    String unosKorisnickoIme = tkorisnickoIme.getText().trim();
                    String unosAdresa = tadresa.getText().trim();
                    String unosJmbg = tjmbg.getText().trim();
                    String brojTelefona = tbrojTelefona.getText().trim();
                    Pol pol = Pol.valueOf(polJComboBox.getSelectedItem().toString());

                    boolean istoKorisnickoImeMusterije = ucitavanje.istoKorisnickoImeMusterije(unosKorisnickoIme);

                    if(istoKorisnickoImeMusterije == false) {
                        if (osoba != null) {
                            Musterija musterija = (Musterija) osoba;
                            musterija.setIme(unosIme);
                            musterija.setPrezime(unosPrezime);
                            musterija.setKorisnickoIme(unosKorisnickoIme);
                            musterija.setLozinka(unosLozinka);
                            musterija.setAdresa(unosAdresa);
                            musterija.setJmbg(unosJmbg);
                            musterija.setBrojTelefona(brojTelefona);
                            musterija.setPol(pol);
                        }

                        Musterija musterija = new Musterija(unosKorisnickoIme, unosLozinka, unosIme, unosPrezime, unosJmbg, unosAdresa, pol, brojTelefona, true);
                        ucitavanje.getMusterije().add(musterija);
                        ucitavanje.dodavanjeKorisnika();
                        JOptionPane.showMessageDialog(null, "Musterija je uspesno dodata!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        DodavanjeMusterija.this.dispose();
                        DodavanjeMusterija.this.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Musterija sa korisnickim imenom vec postoji, pokusajte ponovo.","Obavestenje",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od dodavanja nove musterije!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                DodavanjeMusterija.this.setVisible(false);
                DodavanjeMusterija.this.dispose();
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
        if(tjmbg.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za jmbg ne sme biti prazno!\n";
        }
        try{
            Integer.parseInt(tjmbg.getText());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "JMBG mora biti broj! \n";
            ok = false;
        }
        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
