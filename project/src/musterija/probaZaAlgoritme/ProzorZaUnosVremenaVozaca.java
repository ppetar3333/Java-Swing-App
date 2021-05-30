package musterija.probaZaAlgoritme;

import liste.Liste;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import musterija.probaZaAlgoritme.Aukcija;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ProzorZaUnosVremenaVozaca extends JFrame {

    private JLabel unosVremena = new JLabel("Koliko minuta ti je potrebno do date adrese?");
    private JTextField tunosVremena = new JTextField(20);
    private JButton btnOK = new JButton("Potvrdi");
    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoTelefona trazenaVoznja;
    public ProzorZaUnosVremenaVozaca(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona trazenaVoznja){
        this.ucitavanje = ucitavanje;
        this.trazenaVoznja = trazenaVoznja;
        setTitle("Aukcija");
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
        add(unosVremena);
        add(tunosVremena);
        add(new JLabel());
        add(btnOK,"split 1");
        this.getRootPane().setDefaultButton(btnOK);

    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                    String unosVremenaString = tunosVremena.getText().trim();
                    Vozac ulogovanVozac = new Vozac();
                    try {
                        File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                        Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                        while (citanjeUlogovanogKorisnika.hasNextLine()) {
                            String data = citanjeUlogovanogKorisnika.nextLine();
                            ulogovanVozac.setKorisnickoIme(data);
                        }
                        citanjeUlogovanogKorisnika.close();
                    }  catch (IOException ioException) {
                        ioException.printStackTrace();
                        System.out.println("Greska");
                    }
                    Vozac vozac = ucitavanje.nadjiVozaca(ulogovanVozac.getKorisnickoIme());

                    String slektovanaMogucnost = trazenaVoznja.getIzborMusterijePriNarucivanju();
                    int idVoznje = trazenaVoznja.getId();
                    int unosVremena = Integer.parseInt(unosVremenaString);
                    String vozacKojiUcestvujeUaukciji = vozac.getKorisnickoIme();
                    double ocenaVozaca = vozac.getOcena();
                    int godisteAutomobilaVozaca = vozac.getAutomobili().getGodinaProizvodnje();
                    boolean petFriendly = vozac.getAutomobili().isPetFriendly();

                    Aukcija aukcija = new Aukcija(slektovanaMogucnost,idVoznje,vozacKojiUcestvujeUaukciji,unosVremena,ocenaVozaca,petFriendly,godisteAutomobilaVozaca,false);
                    ucitavanje.getIstorijaAukcija().add(aukcija);
                    ucitavanje.snimiIstorijuAukcija("istorijaAukcija.txt");


                    JOptionPane.showMessageDialog(null,"Ucestvujete u aukciji, hvala.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);

                    ProzorZaUnosVremenaVozaca.this.dispose();
                    ProzorZaUnosVremenaVozaca.this.setVisible(false);
                }
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String porukaObavestenja = "Molimo Vas ispravite sta je potrebno! \n";
        if(tunosVremena.getText().trim().equals("")){
            porukaObavestenja += "Polje za unos vremena ne sme biti prazno!\n";
        }
        try{
            Double.parseDouble(tunosVremena.getText().trim());
        }catch (NumberFormatException e){
            porukaObavestenja += "Unos vremena mora biti broj! \n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, porukaObavestenja, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
