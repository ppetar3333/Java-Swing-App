package dispecer.podaciVozaca;

import automobili.Automobil;
import enumi.Pol;
import enumi.StatusVozacaIautomobila;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaIzmenuVozaca extends JFrame {

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
    private JLabel plata = new JLabel("Plata");
    private JTextField tplata = new JTextField(20);
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;
    private Vozac vozac;

    public ProzorZaIzmenuVozaca(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Izmena vozaca " + vozac.getIme().substring(0,1).toUpperCase() + vozac.getIme().substring(1));
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
        add(plata);
        add(tplata);
        add(pol);
        add(polJComboBox);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);
        this.getRootPane().setDefaultButton(btnOk);

        if(this.vozac != null){
            popunjavanjeTextField();
        }
    }

    private void initListeners(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveraUnesenihPodataka() == true) {
                    String korisnickoIme = tkorisnickoIme.getText().trim();
                    String lozinka = new String(tlozinka.getPassword()).trim();
                    String ime = time.getText().trim();
                    String prezime = tprezime.getText().trim();
                    String adresa = tadresa.getText().trim();
                    String brojTelefona = tbrojTelefona.getText().trim();
                    double plata = Double.parseDouble(tplata.getText().trim());
                    Pol pol = (Pol) polJComboBox.getSelectedItem();
                    String jmbg = vozac.getJmbg();
                    int brojClanskeKarte = vozac.getBrojClanskeKarte();
                    boolean obrisan = vozac.isObrisan();
                    double ocena = vozac.getOcena();
                    StatusVozacaIautomobila statusVozaca = vozac.getStatusVozaca();
                    if (vozac == null) {
                        vozac = new Vozac(korisnickoIme, lozinka, ime, prezime, jmbg, adresa, pol, brojTelefona, obrisan, plata, brojClanskeKarte, new Automobil(), ocena, statusVozaca);
                    } else {
                        vozac.setKorisnickoIme(korisnickoIme);
                        vozac.setLozinka(lozinka);
                        vozac.setIme(ime);
                        vozac.setPrezime(prezime);
                        vozac.setJmbg(jmbg);
                        vozac.setAdresa(adresa);
                        vozac.setPol(pol);
                        vozac.setBrojTelefona(brojTelefona);
                        vozac.setPlata(plata);
                        vozac.setBrojClanskeKarte(brojClanskeKarte);
                        vozac.setObrisan(obrisan);
                        vozac.setStatusVozaca(statusVozaca);
                    }
                    ucitavanje.dodavanjeKorisnika();
                    JOptionPane.showMessageDialog(null, "Vozac: " + vozac.getIme().substring(0, 1).toUpperCase() + vozac.getIme().substring(1) + " je uspesno izmenjen!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    ProzorZaIzmenuVozaca.this.setVisible(false);
                    ProzorZaIzmenuVozaca.this.dispose();
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od izmene","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                ProzorZaIzmenuVozaca.this.setVisible(false);
                ProzorZaIzmenuVozaca.this.dispose();
            }
        });
    }

    private void popunjavanjeTextField(){
        tkorisnickoIme.setText(vozac.getKorisnickoIme());
        tlozinka.setText(vozac.getLozinka());
        time.setText(vozac.getIme());
        tprezime.setText(vozac.getPrezime());
        tadresa.setText(vozac.getAdresa());
        tbrojTelefona.setText(vozac.getBrojTelefona());
        tplata.setText(String.valueOf(vozac.getPlata()));
        polJComboBox.setSelectedItem(vozac.getPol());
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
        if(tplata.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za platu ne sme biti prazno!\n";
        }
        try {
            Double.parseDouble(tplata.getText());
        }catch (NumberFormatException e){
            obavestenjeZaGresku += "Plata mora biti broj! \n";
            ok = false;
        }
        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
