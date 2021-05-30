package vozac;

import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import osobe.Musterija;
import osobe.Vozac;
import liste.Liste;
import musterija.probaZaAlgoritme.UcestvujUaukciji;
import vozac.prikazVoznji.PrikazDodeljenihVoznjiKreiranihTelefonom;
import vozac.prikazVoznji.PrikazVoznjiZakazanihPrekoAplikacije;
import vozac.zavrsavanjeVoznje.ZavrsavanjeVoznjePutemAplikacije;
import vozac.zavrsavanjeVoznje.ZavrsavanjeVoznjePutemTelefona;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MogucnostiVozaca extends JFrame {

    private JMenuBar vozacMenu = new JMenuBar();

    private JMenu funkcionalnostiVozaca = new JMenu("Voznje");
    private JMenuItem prikazIstorijeSopstvenihVoznjiVoznjeTelefon = new JMenuItem("Prikaz istorije sopstvenih voznji kreirane putem telefona");
    private JMenuItem prikazIstorijeSopstvenihVoznjiVoznjeAplikacija = new JMenuItem("Prikaz istorije sopstvenih voznji kreirane putem aplikacije");
    private JMenuItem prikazVoznjeZakazanihPrekoAplikacije = new JMenuItem("Prikaz voznji zakazanih preko aplikacije");
    private JMenuItem prikazDodeljenihVoznji = new JMenuItem("Prikaz dodeljenih voznji kreiranih putem telefona");
    private JMenuItem sumiraneStatistikeVoznji = new JMenuItem("Prikaz sumiraze statistike voznji");
    private JMenuItem aukcijeVoznje = new JMenuItem("Ucestvuj u aukciji");
    private JMenuItem zavrsavanjeVoznje = new JMenuItem("Zavrsavanje voznje kreirane putem telefona");
    private JMenuItem zavrsavanjeVoznjePutemAplikacije = new JMenuItem("Zavrsavanje voznje kreirane putem aplikacije");


    private JMenu odjava = new JMenu("Odjava");
    private JMenuItem potvrdaZaOdjavu = new JMenuItem("Potvrdi");
    private JMenuItem odustaniZaOdjavu = new JMenuItem("Odustani");

    private Liste ucitavanje;
    private Vozac prijavljeniVozac;
    private Musterija musterija;
    private NarucivanjeVoznjePrekoTelefona voznja;

    public MogucnostiVozaca(Liste ucitavanje, Vozac prijavljeniVozac){
        this.ucitavanje = ucitavanje;
        this.prijavljeniVozac = prijavljeniVozac;
        setTitle("Dobrodosli " + prijavljeniVozac.getIme().substring(0, 1).toUpperCase() + prijavljeniVozac.getIme().substring(1) + " (Vozac)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        initGUI();
        initListeners();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        setJMenuBar(vozacMenu);

        vozacMenu.add(funkcionalnostiVozaca);
        funkcionalnostiVozaca.add(prikazIstorijeSopstvenihVoznjiVoznjeTelefon);
        funkcionalnostiVozaca.add(prikazIstorijeSopstvenihVoznjiVoznjeAplikacija);
        funkcionalnostiVozaca.add(prikazVoznjeZakazanihPrekoAplikacije);
        funkcionalnostiVozaca.add(prikazDodeljenihVoznji);
        funkcionalnostiVozaca.add(sumiraneStatistikeVoznji);
        funkcionalnostiVozaca.add(aukcijeVoznje);
        funkcionalnostiVozaca.add(zavrsavanjeVoznje);
        funkcionalnostiVozaca.add(zavrsavanjeVoznjePutemAplikacije);

        vozacMenu.add(odjava);
        odjava.add(potvrdaZaOdjavu);
        odjava.add(odustaniZaOdjavu);
    }

    private void initListeners(){
        prikazIstorijeSopstvenihVoznjiVoznjeTelefon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVoznjeZaIstorijuVoznjePrekoTelefona().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Trenutno nema voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    IstorijaVoznjeVozacTelefon prozorZaPrikazIstorijeVoznji = new IstorijaVoznjeVozacTelefon(ucitavanje, prijavljeniVozac);
                    prozorZaPrikazIstorijeVoznji.setVisible(true);
                }
            }
        });
        prikazIstorijeSopstvenihVoznjiVoznjeAplikacija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVoznjeZaIstorijuVoznjePrekoAplikacije().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Trenutno nema voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    IstorijaVoznjeVozacAplikacija prozorZaPrikazIstorijeVoznji = new IstorijaVoznjeVozacAplikacija(ucitavanje, prijavljeniVozac);
                    prozorZaPrikazIstorijeVoznji.setVisible(true);
                }
            }
        });
        prikazVoznjeZakazanihPrekoAplikacije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Trenutno nema voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    PrikazVoznjiZakazanihPrekoAplikacije prikazVoznjiZakazanihPrekoAplikacije = new PrikazVoznjiZakazanihPrekoAplikacije(ucitavanje, prijavljeniVozac);
                    prikazVoznjiZakazanihPrekoAplikacije.setVisible(true);
                }
            }
        });
        prikazDodeljenihVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazDodeljenihVoznji().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Nema dodeljenih voznji!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    PrikazDodeljenihVoznjiKreiranihTelefonom prikazDodeljenihVoznji = new PrikazDodeljenihVoznjiKreiranihTelefonom(ucitavanje);
                    prikazDodeljenihVoznji.setVisible(true);
                }
            }
        });
        sumiraneStatistikeVoznji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        aukcijeVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nazalost, nema kreiranih voznji.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    UcestvujUaukciji aukcijeVoznje = new UcestvujUaukciji(ucitavanje, voznja);
                    aukcijeVoznje.setVisible(true);
                }
            }
        });
        zavrsavanjeVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ucitavanje.prikazVoznjeZaZavrsavanjeVoznje().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nema prihvacenih voznji!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    ZavrsavanjeVoznjePutemTelefona zavrsavanjeVoznje = new ZavrsavanjeVoznjePutemTelefona(ucitavanje);
                    zavrsavanjeVoznje.setVisible(true);
                }
            }
        });
        zavrsavanjeVoznjePutemAplikacije.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ucitavanje.prikazVoznjeZaZavrsavanjeVoznjePutemAplikacije().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Nema prihvacenih voznji!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    ZavrsavanjeVoznjePutemAplikacije zavrsavanjeVoznjePutemAplikacije = new ZavrsavanjeVoznjePutemAplikacije(ucitavanje);
                    zavrsavanjeVoznjePutemAplikacije.setVisible(true);
                }
            }
        });
        potvrdaZaOdjavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste se odjavili!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                MogucnostiVozaca.this.dispose();
                MogucnostiVozaca.this.setVisible(false);
                TaxiSluzbaMain.main(null);
            }
        });
        odustaniZaOdjavu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Uspesno ste odustali od odjave!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
