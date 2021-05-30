package vozac.zavrsavanjeVoznje;

import enumi.StatusVozacaIautomobila;
import enumi.StatusVoznje;
import liste.Liste;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaUnosPodatakaVozacaZaZavrsenuVoznju extends JFrame {

    private JLabel unosBrojaKm = new JLabel("Predjeni kilometri u voznji");
    private JTextField tunosBrojaKm = new JTextField(20);
    private JLabel trajanjeVoznje = new JLabel("Trajanje voznje u minutama");
    private JTextField ttrajanjeVoznje = new JTextField(20);

    private JButton btnOK = new JButton("Potvrdi");
    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoTelefona nadjiVoznju;

    public ProzorZaUnosPodatakaVozacaZaZavrsenuVoznju(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona nadjiVoznju){
        this.ucitavanje = ucitavanje;
        this.nadjiVoznju = nadjiVoznju;
        setTitle("Unos podataka");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(unosBrojaKm);
        add(tunosBrojaKm);
        add(trajanjeVoznje);
        add(ttrajanjeVoznje);
        add(new JLabel());
        add(btnOK, "split 2");
        this.getRootPane().setDefaultButton(btnOK);
    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                    String unosBrojaKmString = tunosBrojaKm.getText().trim();
                    double unosBrojaKm = Double.parseDouble(unosBrojaKmString);
                    String unosTrajanjaVoznjeString = ttrajanjeVoznje.getText().trim();
                    double unosTrajanjaVoznje = Double.parseDouble(unosTrajanjaVoznjeString);
                    nadjiVoznju.setBrojKMpredjenih(unosBrojaKm);
                    nadjiVoznju.setTrajanjVoznje(unosTrajanjaVoznje);
                    nadjiVoznju.setStatusVoznje(StatusVoznje.ZAVRSENA);
                    String vozacString = nadjiVoznju.getVozac().getKorisnickoIme();
                    Vozac vozac = ucitavanje.nadjiVozaca(vozacString);
                    vozac.setStatusVozaca(StatusVozacaIautomobila.SLOBODAN);
                    double start = 150;
                    double cenaPoKilometru = 30;
                    double cenaVoznje = start + (unosBrojaKm * cenaPoKilometru);
                    nadjiVoznju.setCenaVoznje(cenaVoznje);
                    JOptionPane.showMessageDialog(null,"Cena voznje je: " + cenaVoznje + "din","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                    ucitavanje.dodavanjeKorisnika();
                    ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                    ProzorZaUnosPodatakaVozacaZaZavrsenuVoznju.this.setVisible(false);
                    ProzorZaUnosPodatakaVozacaZaZavrsenuVoznju.this.dispose();
                }
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String porukaObavestenja = "Molimo Vas ispravite sta je potrebno! \n";
        if(tunosBrojaKm.getText().trim().equals("")){
            porukaObavestenja += "Polje za unos broja kilometara ne sme biti prazno!\n";
        }
        try{
            Double.parseDouble(tunosBrojaKm.getText().trim());
        }catch (NumberFormatException e){
            porukaObavestenja += "Unos kilometara mora biti broj! \n";
            ok = false;
        }
        if(ttrajanjeVoznje.getText().trim().equals("")){
            porukaObavestenja += "Polje za trajanje voznje ne sme biti prazno!\n";
        }
        try{
            Double.parseDouble(ttrajanjeVoznje.getText().trim());
        }catch (NumberFormatException e){
            porukaObavestenja += "Unos trajanja voznje mora biti broj! \n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, porukaObavestenja, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
