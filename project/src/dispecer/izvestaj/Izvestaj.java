package dispecer.izvestaj;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Izvestaj extends JFrame {

    private JLabel datum = new JLabel("Unesi datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;

    public Izvestaj(Liste ucitavanje, String[] days) {
        this.ucitavanje = ucitavanje;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGUI();
        initActions(days);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(datum);
        add(datumUnos);
        add(new JLabel());
        add(btnOk,"split 2");
        this.getRootPane().setDefaultButton(btnOk);
        add(btnCancel);
    }
    private void initActions(String[] days){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(validacija() == true) {

                    String unosDatuma = datumUnos.getText().trim();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate parsiranjeUnesenogDatuma = LocalDate.parse(unosDatuma, formatter);

                    for (int i = 0; i < days.length; i++) {
                        days[i] = parsiranjeUnesenogDatuma.minusDays(days.length - i - 1).toString();
                    }
                    DoublyLinkedList<String> listaDana = new DoublyLinkedList<>();
                    for (String x : days) {
                        listaDana.add(x);
                    }

                    // UKUPAN BROJ VOZNJI KREIRANIH PUTEM TELEFONA
                    DoublyLinkedList<String> listaVoznjiTelefon = ucitavanje.ukupanBrojVoznjiPrekoTelefona();
                    int ukupanBrojVoznjiPrekoTelefona = 0;
                    for(String y : listaDana){
                        for(String x : listaVoznjiTelefon){
                            if(y.equals(x)){
                                ukupanBrojVoznjiPrekoTelefona++;
                            }
                        }
                    }

                    // UKUPAN BROJ VOZNJI KREIRANIH PUTEM APLIKACIJE
                    DoublyLinkedList<String> listaVoznjiAplikacija = ucitavanje.ukupanBrojVoznjiPrekoAplikacije();
                    int ukupanBrojVoznjiPrekoAplikacije = 0;
                    for(String y : listaDana){
                        for(String x : listaVoznjiAplikacija){
                            if(y.equals(x)){
                                ukupanBrojVoznjiPrekoAplikacije++;
                            }
                        }
                    }

                    // UKUPAN BROJ SVIH VOZNJI
                    int ukupanBrojSvihVoznji = ukupanBrojVoznjiPrekoTelefona + ukupanBrojVoznjiPrekoAplikacije;


                    // PRAVLJENJE NOVE LISTE BEZ DUPLIRANIH ID-EVA
                    String datumiZaTelefon;
                    String datumiZaAplikaciju;
                    DoublyLinkedList<Integer> novaListaTelefoni = new DoublyLinkedList<>();
                    DoublyLinkedList<Integer> novaListaAplikacija = new DoublyLinkedList<>();
                    for(String y : listaDana){
                        for(String x : listaVoznjiTelefon){
                            if(y.equals(x)){
                                datumiZaTelefon = x;
                                DoublyLinkedList<Integer> listaIdevaTelefon = ucitavanje.nadjiVoznjuNarucenuPrekoTelefonaPoDatumu(datumiZaTelefon);
                                for(Integer q : listaIdevaTelefon) {
                                    novaListaTelefoni.add(q);
                                }
                            }
                        }
                        for(String q : listaVoznjiAplikacija){
                            if(y.equals(q)){
                                datumiZaAplikaciju = q;
                                DoublyLinkedList<Integer> listaIdevaAplikacija = ucitavanje.nadjiVoznjuNarucenuPrekoAplikacijePoDatumu(datumiZaAplikaciju);
                                for(Integer g : listaIdevaAplikacija){
                                    novaListaAplikacija.add(g);
                                }
                            }
                        }
                    }
                    // PROSECNO TRAJANJE VOZNJI
                    double rezultatTelefoni;
                    double sumaTrajanjaVoznjeTelefoni = 0;
                    Set<Integer> listaBezDupliranihIDevaTelefon = findDuplicatesIntegers(novaListaTelefoni);
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaTelefon){
                        rezultatTelefoni = ucitavanje.ukupnoTrajanjeVoznjiTelefoni(idKojiTrebaPronaci);
                        sumaTrajanjaVoznjeTelefoni += rezultatTelefoni;
                    }
                    double rezultatAplikacija;
                    double sumaTrajanjaVoznjeAplikacija = 0;
                    Set<Integer> listaBezDupliranihIDevaAplikacija = findDuplicatesIntegers(novaListaAplikacija);
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaAplikacija){
                        rezultatAplikacija = ucitavanje.ukupnoTrajanjeVoznjiAplikacija(idKojiTrebaPronaci);
                        sumaTrajanjaVoznjeAplikacija += rezultatAplikacija;
                    }
                    double ukupnoTrajanjeVoznjiTelefonIaplikacija = sumaTrajanjaVoznjeTelefoni + sumaTrajanjaVoznjeAplikacija;
                    double averageDoubleTrajanje = ukupnoTrajanjeVoznjiTelefonIaplikacija / ukupanBrojSvihVoznji;
                    int prosecnoTrajanjeVoznje = (int) averageDoubleTrajanje;


                    // PROSECNA KILOMETRAZA
                    double rezultatTelefoni1;
                    double sumaPredjenihKilometaraTelefoni = 0;
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaTelefon){
                        rezultatTelefoni1 = ucitavanje.ukupnaKilometrazaTelefoni(idKojiTrebaPronaci);
                        sumaPredjenihKilometaraTelefoni += rezultatTelefoni1;
                    }
                    double rezultatAplikacija1;
                    double sumaPredjenihKilometaraAplikacija = 0;
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaAplikacija){
                        rezultatAplikacija1 = ucitavanje.ukupnaKilometrazaAplikacija(idKojiTrebaPronaci);
                        sumaPredjenihKilometaraAplikacija += rezultatAplikacija1;
                    }
                    double ukupnaKilometrazaTelefoniIaplikacija = sumaPredjenihKilometaraTelefoni + sumaPredjenihKilometaraAplikacija;
                    double averageDoubleKM = ukupnaKilometrazaTelefoniIaplikacija / ukupanBrojSvihVoznji;
                    int prosecnaKilometraza = (int) averageDoubleKM;


                    // UKUPNA ZARADA ZA SVE VOZNJE
                    double rezultatTelefoni2;
                    double sumaZaradeTelefoni = 0;
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaTelefon){
                        rezultatTelefoni2 = ucitavanje.ukupnaZaradaTelefoni(idKojiTrebaPronaci);
                        sumaZaradeTelefoni += rezultatTelefoni2;
                    }
                    double rezultatAplikacija2;
                    double sumaZaradeAplikacija = 0;
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaAplikacija){
                        rezultatAplikacija2 = ucitavanje.ukupnaZaradaAplikacija(idKojiTrebaPronaci);
                        sumaZaradeAplikacija += rezultatAplikacija2;
                    }
                    double ukupnaZaradaZaSveVoznjeDouble = sumaZaradeTelefoni + sumaZaradeAplikacija;
                    int ukupnaZaradaZaSveVoznje = (int) ukupnaZaradaZaSveVoznjeDouble;

                    // PROSECNA ZARADA PO VOZNJI

                    int prosecnaZaradaPoVoznji = (int) (ukupnaZaradaZaSveVoznjeDouble / ukupanBrojSvihVoznji);


                    // UKUPAN BROJ AKTIVNIH VOZACA
                    DoublyLinkedList<String> novaListaVozaca = new DoublyLinkedList<>();
                    for(Integer idKojiTrebaPronaci : listaBezDupliranihIDevaTelefon){
                        DoublyLinkedList<String> listaSvihKorisnickihImenaVozaca = ucitavanje.listaKorisnickihImenaVozaca(idKojiTrebaPronaci);
                        for(String f : listaSvihKorisnickihImenaVozaca){
                            novaListaVozaca.add(f);
                        }
                    }
                    Set<String> listaBezDupliranihVozaca = findDuplicatesStrings(novaListaVozaca);
                    int count = 0;
                    for(String s : listaBezDupliranihVozaca){
                        count++;
                    }
                    int ukupanBrojAktivnihVozaca = count;

                    if(ukupanBrojSvihVoznji == 0){
                        JOptionPane.showMessageDialog(null, "Nazalost, za uneti datum, nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    }else {
                        ProzorZaPrikazIzvestaja prozorZaPrikazDnevnogIzvestaja = new ProzorZaPrikazIzvestaja(ukupanBrojSvihVoznji, ukupanBrojVoznjiPrekoAplikacije, ukupanBrojVoznjiPrekoTelefona, prosecnoTrajanjeVoznje, prosecnaKilometraza, ukupnaZaradaZaSveVoznje, prosecnaZaradaPoVoznji, ukupanBrojAktivnihVozaca);
                        prozorZaPrikazDnevnogIzvestaja.setVisible(true);
                    }

                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                Izvestaj.this.setVisible(false);
                Izvestaj.this.dispose();
            }
        });
    }
    public boolean validacija() {

        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if(datumUnos.getText().equals("")){
            obavestenjeZaGresku += "\nMorate uneti datum! \n";
            ok = false;
        }

        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci!", JOptionPane.WARNING_MESSAGE);
        }

        return ok;
    }
    private Set<Integer> findDuplicatesIntegers(DoublyLinkedList<Integer> list){
        Set<Integer> items = new HashSet<Integer>();
        Set<Integer> duplicates = new HashSet<Integer>();
        for (Integer item : list) {
            if (items.contains(item)) {
                duplicates.remove(item);
            } else {
                items.add(item);
            }
        }
        return items;
    }
    private Set<String> findDuplicatesStrings(DoublyLinkedList<String> list){
        Set<String> items = new HashSet<String>();
        Set<String> duplicates = new HashSet<String>();
        for (String item : list) {
            if (items.contains(item)) {
                duplicates.remove(item);
            } else {
                items.add(item);
            }
        }
        return items;
    }
}

