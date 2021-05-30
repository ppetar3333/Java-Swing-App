package musterija;

import main.TaxiSluzbaMain;
import musterija.istorijaVoznji.IstorijaVoznjiMusterijaAplikacija;
import musterija.istorijaVoznji.IstorijaVoznjiMusterijaTelefon;
import musterija.narucivanjeVoznjePrekoAplikacije.ProzorZaNarucivanjePutemAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjePrekoTelefonaProzor;
import musterija.probaZaAlgoritme.NaruciVoznjuTelefonomAukcija;
import osobe.Dispecar;
import osobe.Musterija;
import liste.Liste;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogucnostiMusterije extends JFrame{

    private JMenuBar musterijaMenu = new JMenuBar();

    private JMenu funkcionalnostMusterije = new JMenu("Voznje");
    private JMenuItem istorijaVoznjiAplikacija = new JMenuItem("Istorija voznje kreiranih putem aplikacije");
    private JMenuItem istorijaVoznjiTelefon = new JMenuItem("Istorija voznje kreiranih putem telefona");
    private JMenuItem narucivanjeVoznjePrekoAplikacije = new JMenuItem("Narucivanje voznje preko aplikacije");
    private JMenuItem narucivanjeVoznjePrekoTelefona = new JMenuItem("Narucivanje voznje preko telefona");
    private JMenuItem probaZaAlgoritme = new JMenuItem("Proba za algoritme");

    private JMenu odjava = new JMenu("Odjava");
    private JMenuItem potvrdaZaOdjavu = new JMenuItem("Potvrdi");
    private JMenuItem odustaniZaOdjavu = new JMenuItem("Odustani");

    private Liste ucitavanje;
    private Musterija prijavljenaMusterija;
    private Dispecar dispecar;

    public MogucnostiMusterije(Liste ucitavanje, Musterija prijavljenaMusterija){
        this.ucitavanje = ucitavanje;
        this.prijavljenaMusterija = prijavljenaMusterija;
        setTitle("Dobrodosli " + prijavljenaMusterija.getIme().substring(0, 1).toUpperCase() + prijavljenaMusterija.getIme().substring(1) + " (Musterija)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        initGUI();
        initListeners();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        setJMenuBar(musterijaMenu);
        musterijaMenu.add(funkcionalnostMusterije);
        funkcionalnostMusterije.add(istorijaVoznjiAplikacija);
        funkcionalnostMusterije.add(istorijaVoznjiTelefon);
        funkcionalnostMusterije.add(narucivanjeVoznjePrekoAplikacije);
        funkcionalnostMusterije.add(narucivanjeVoznjePrekoTelefona);
        funkcionalnostMusterije.add(probaZaAlgoritme);
        musterijaMenu.add(odjava);
        odjava.add(potvrdaZaOdjavu);
        odjava.add(odustaniZaOdjavu);
    }

    private void initListeners(){
        istorijaVoznjiAplikacija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IstorijaVoznjiMusterijaAplikacija istorijaVoznjiMusterija = new IstorijaVoznjiMusterijaAplikacija(ucitavanje, prijavljenaMusterija);
                istorijaVoznjiMusterija.setVisible(true);
            }
        });
        istorijaVoznjiTelefon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IstorijaVoznjiMusterijaTelefon istorijaVoznjiMusterija = new IstorijaVoznjiMusterijaTelefon(ucitavanje, prijavljenaMusterija);
                istorijaVoznjiMusterija.setVisible(true);
            }
        });
        narucivanjeVoznjePrekoAplikacije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProzorZaNarucivanjePutemAplikacije prozorZaNarucivanjePutemAplikacije = new ProzorZaNarucivanjePutemAplikacije(ucitavanje, prijavljenaMusterija);
                prozorZaNarucivanjePutemAplikacije.setVisible(true);
            }
        });
        narucivanjeVoznjePrekoTelefona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NarucivanjePrekoTelefonaProzor narucivanjePrekoTelefonaProzor = new NarucivanjePrekoTelefonaProzor(ucitavanje,prijavljenaMusterija);
                narucivanjePrekoTelefonaProzor.setVisible(true);
            }
        });
        potvrdaZaOdjavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste se odjavili!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                MogucnostiMusterije.this.dispose();
                MogucnostiMusterije.this.setVisible(false);
                TaxiSluzbaMain.main(null);
            }
        });
        odustaniZaOdjavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste odustali od odjave!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
            }
        });


        //PROBA ZA ALGORITME
        probaZaAlgoritme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NaruciVoznjuTelefonomAukcija probaZaAlgoritme = new NaruciVoznjuTelefonomAukcija(ucitavanje,prijavljenaMusterija);
                probaZaAlgoritme.setVisible(true);
            }
        });
    }
}
