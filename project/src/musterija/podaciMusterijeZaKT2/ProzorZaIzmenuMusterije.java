package musterija.podaciMusterijeZaKT2;

import enumi.Pol;
import liste.Liste;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaIzmenuMusterije extends JFrame {

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
    private JLabel pol = new JLabel("Pol");
    private JComboBox<Pol> polJComboBox = new JComboBox<Pol>(Pol.values());
    private JLabel brojTelefona = new JLabel("Broj telefona");
    private JTextField tbrojTelefona = new JTextField(20);

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;
    private Musterija musterija;

    public ProzorZaIzmenuMusterije(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.musterija = musterija;
        setTitle("Izmena musterije: " + musterija.getIme().substring(0,1).toUpperCase() + musterija.getIme().substring(1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        initListeners();
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
        add(brojTelefona);
        add(tbrojTelefona);
        add(pol);
        add(polJComboBox);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);

        if(this.musterija != null){
            popunjavanjeTextField();
        }
    }

    private void initListeners(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveraUnesenihPodataka() == true){
                    String korisnickoIme = tkorisnickoIme.getText().trim();
                    String lozinka = new String(tlozinka.getPassword()).trim();
                    String ime = time.getText().trim();
                    String prezime = tprezime.getText().trim();
                    String adresa = tadresa.getText().trim();
                    String brojTelefona = tbrojTelefona.getText().trim();
                    Pol pol = (Pol) polJComboBox.getSelectedItem();
                    String jmbg = musterija.getJmbg();
                    boolean obrisan = musterija.isObrisan();
                    if (musterija == null) {
                        musterija = new Musterija(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan);
                    } else {
                        musterija.setKorisnickoIme(korisnickoIme);
                        musterija.setLozinka(lozinka);
                        musterija.setIme(ime);
                        musterija.setPrezime(prezime);
                        musterija.setJmbg(jmbg);
                        musterija.setAdresa(adresa);
                        musterija.setPol(pol);
                        musterija.setBrojTelefona(brojTelefona);
                    }
                    ucitavanje.dodavanjeKorisnika();
                    JOptionPane.showMessageDialog(null, "Musterija: " + musterija.getIme().substring(0, 1).toUpperCase() + musterija.getIme().substring(1) + " je uspesno izmenjen!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    ProzorZaIzmenuMusterije.this.setVisible(false);
                    ProzorZaIzmenuMusterije.this.dispose();
                }
            }
        });
    }

    private void popunjavanjeTextField(){
        tkorisnickoIme.setText(musterija.getKorisnickoIme());
        tlozinka.setText(musterija.getLozinka());
        time.setText(musterija.getIme());
        tprezime.setText(musterija.getPrezime());
        tadresa.setText(musterija.getAdresa());
        tbrojTelefona.setText(musterija.getBrojTelefona());
        polJComboBox.setSelectedItem(musterija.getPol());
    }

    private boolean proveraUnesenihPodataka(){
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n\n";

        // TODO provera podataka
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
        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
