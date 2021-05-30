package musterija.istorijaVoznji;

import liste.Liste;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaOcenjivanjeVoznjiAplikacija extends JFrame {

    private JLabel oceniVozaca = new JLabel("Oceni vozaca ");
    private JComboBox<Double> ocene;
    private JButton btnOK = new JButton("Potvrdi");
    private Liste ucitavanje;
    private Vozac vozac;
    private NarucivanjeVoznjePrekoAplikacije voznja;

    public ProzorZaOcenjivanjeVoznjiAplikacija(Liste ucitavanje, Vozac vozac, NarucivanjeVoznjePrekoAplikacije voznja){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        this.voznja = voznja;
        setTitle("Oceni vozaca");
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
        add(oceniVozaca);
        Double[] array = new Double[]{1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0};
        ocene = new JComboBox(array);
        add(ocene);
        add(new JLabel());
        add(btnOK,"split 2");
        this.getRootPane().setDefaultButton(btnOK);

    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double selektovanaOcena = (double) ocene.getSelectedItem();
                double ocenaVozaca = vozac.getOcena();
                double novaOcena = (selektovanaOcena + ocenaVozaca) / 2.0;
                double novaOcenaZaokruzena = Math.round(novaOcena);
                vozac.setOcena(novaOcenaZaokruzena);
                ucitavanje.dodavanjeKorisnika();
                voznja.setOcenjenVozac(true);
                ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                JOptionPane.showMessageDialog(null,"Uspesno ste ocenili vozaca!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                ProzorZaOcenjivanjeVoznjiAplikacija.this.setVisible(false);
                ProzorZaOcenjivanjeVoznjiAplikacija.this.dispose();
            }
        });
    }
}
