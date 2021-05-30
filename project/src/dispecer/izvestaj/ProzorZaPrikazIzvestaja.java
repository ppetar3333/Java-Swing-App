package dispecer.izvestaj;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;

public class ProzorZaPrikazIzvestaja extends JFrame {

    private JLabel ukupanBrojVoznji = new JLabel("Ukupan broj voznji ");
    private JTextField tukupanBrojVoznji = new JTextField(12);
    private JLabel ukupanBrojVoznjiTelefon = new JLabel("Ukupan broj voznji narucenih putem telefona");
    private JTextField tukupanBrojVoznjiTelefon = new JTextField(12);
    private JLabel ukupanBrojVoznjiAplikacija = new JLabel("Ukupan broj voznji narucenih putem aplikacije");
    private JTextField tukupanBrojVoznjiAplikacija = new JTextField(12);
    private JLabel prosecnoTrajanjeVoznje = new JLabel("Prosecno trajanje voznje ");
    private JTextField tprosecnoTrajanjeVoznje = new JTextField(12);
    private JLabel prosecanBrojPredjenihKm = new JLabel("Prosecna kilometraza ");
    private JTextField tprosecanBrojPredjenihKm = new JTextField(12);
    private JLabel ukupnaZaradaZaSveVoznje = new JLabel("Ukupna zarada za sve voznje ");
    private JTextField tukupnaZaradaZaSveVoznje = new JTextField(12);
    private JLabel prosecnaZaradaPoVoznji = new JLabel("Prosecna zarada po voznji ");
    private JTextField tprosecnaZaradaPoVoznji = new JTextField(12);
    private JLabel ukupanBrojVozacaKojiSuVozili = new JLabel("Ukupan broj aktivnih vozaca ");
    private JTextField tukupanBrojVozacaKojiSuVozili = new JTextField(12);

    public Liste ucitavanje;

    public ProzorZaPrikazIzvestaja(int ukupanBrojSvihVoznji, int ukupanBrojVoznjiAplikacija, int ukupanBrojVoznjiTelefon, int prosecnoTrajanjeVoznji, int prosecnaKilometraza, int ukupnaZaradaZaSveVoznje, int prosecnaZaradaPoVoznji, int brojVozacaKojiSuVozili) {
        setTitle("Prikaz izvestaja");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        popunjavanjePolja(ukupanBrojSvihVoznji,ukupanBrojVoznjiAplikacija,ukupanBrojVoznjiTelefon,prosecnoTrajanjeVoznji,prosecnaKilometraza,ukupnaZaradaZaSveVoznje,prosecnaZaradaPoVoznji,brojVozacaKojiSuVozili);
    }


    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(ukupanBrojVoznji);
        add(tukupanBrojVoznji);
        add(ukupanBrojVoznjiTelefon);
        add(tukupanBrojVoznjiTelefon);
        add(ukupanBrojVoznjiAplikacija);
        add(tukupanBrojVoznjiAplikacija);
        add(prosecnoTrajanjeVoznje);
        add(tprosecnoTrajanjeVoznje);
        add(prosecanBrojPredjenihKm);
        add(tprosecanBrojPredjenihKm);
        add(ukupnaZaradaZaSveVoznje);
        add(tukupnaZaradaZaSveVoznje);
        add(prosecnaZaradaPoVoznji);
        add(tprosecnaZaradaPoVoznji);
        add(ukupanBrojVozacaKojiSuVozili);
        add(tukupanBrojVozacaKojiSuVozili);

        tukupanBrojVoznji.setEditable(false);
        tukupanBrojVoznjiAplikacija.setEditable(false);
        tukupanBrojVoznjiTelefon.setEditable(false);
        tprosecanBrojPredjenihKm.setEditable(false);
        tprosecnoTrajanjeVoznje.setEditable(false);
        tukupnaZaradaZaSveVoznje.setEditable(false);
        tprosecnaZaradaPoVoznji.setEditable(false);
        tukupanBrojVozacaKojiSuVozili.setEditable(false);

    }

    private void popunjavanjePolja(int ukupanBrojSvihVoznji, int ukupanBrojVoznjiAplikacija, int ukupanBrojVoznjiTelefon, int prosecnoTrajanjeVoznji, int prosecnaKilometraza, int ukupnaZaradaZaSveVoznje, int prosecnaZaradaPoVoznji, int brojVozacaKojiSuVozili) {

        tukupanBrojVoznji.setText(String.valueOf(ukupanBrojSvihVoznji));
        tukupanBrojVoznjiAplikacija.setText(String.valueOf(ukupanBrojVoznjiAplikacija));
        tukupanBrojVoznjiTelefon.setText(String.valueOf(ukupanBrojVoznjiTelefon));
        tprosecnoTrajanjeVoznje.setText(prosecnoTrajanjeVoznji + " min");
        tprosecanBrojPredjenihKm.setText(prosecnaKilometraza + " km");
        tukupnaZaradaZaSveVoznje.setText(ukupnaZaradaZaSveVoznje + " din");
        tprosecnaZaradaPoVoznji.setText(prosecnaZaradaPoVoznji + " din");
        tukupanBrojVozacaKojiSuVozili.setText(String.valueOf(brojVozacaKojiSuVozili));

    }


}
