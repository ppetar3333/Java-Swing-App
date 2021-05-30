package dispecer.izvestaj;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class DnevniIzvestaj extends JFrame {

    private JLabel datum = new JLabel("Unesi datum: ");
    private JTextField datumUnos = new JTextField(20);
    private JButton btnOk = new JButton("Ok");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;

    public DnevniIzvestaj(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        setTitle("Dnevni izvestaj");
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
        add(datum);
        add(datumUnos);
        add(new JLabel());
        add(btnOk,"split 2");
        this.getRootPane().setDefaultButton(btnOk);
        add(btnCancel);
    }

    private void initActions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true) {
                    String unosDatuma = datumUnos.getText().trim();

                    boolean voznje = ucitavanje.nadjiDatum(unosDatuma);

                    if (voznje == false) {
                        JOptionPane.showMessageDialog(null, "Nazalost, za uneti datum, nema voznji.", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        String uneseniDatum = datumUnos.getText().trim();

                        int ukupanBrojVoznjiAplikacija = ucitavanje.uporediDatumIvoznjeAplikacijom(uneseniDatum);
                        int ukupanBrojVoznjiTelefon = ucitavanje.uporediDatumIvoznjeTelefonom(uneseniDatum);
                        int ukupanBrojSvihVoznji = ukupanBrojVoznjiAplikacija + ukupanBrojVoznjiTelefon;
                        int prosecnoTrajanjeVoznji = (int) ucitavanje.uporediDatumItrajanjeVoznje(uneseniDatum);
                        int prosecnaKilometraza = (int) ucitavanje.uporediDatumIkilometrazu(uneseniDatum);
                        int ukupnaZaradaZaSveVoznje = (int) ucitavanje.ukupnaZaradaZaSveVoznje(uneseniDatum);
                        int prosecnaZaradaPoVoznji = ukupnaZaradaZaSveVoznje / ukupanBrojSvihVoznji;
                        DoublyLinkedList<String> spisakVozacaKojiSuVozili = ucitavanje.spisakVozacaKojiSuVozili(uneseniDatum);
                        Set<String> listaBezDupliranihVozaca = findDuplicates(spisakVozacaKojiSuVozili);
                        int count = 0;
                        for(String q : listaBezDupliranihVozaca){
                            count++;
                        }
                        int brojVozacaKojiSuVozili = count;
                        System.out.println(brojVozacaKojiSuVozili);

                        ProzorZaPrikazIzvestaja prozorZaPrikazIzvestaja = new ProzorZaPrikazIzvestaja(ukupanBrojSvihVoznji, ukupanBrojVoznjiAplikacija, ukupanBrojVoznjiTelefon, prosecnoTrajanjeVoznji, prosecnaKilometraza, ukupnaZaradaZaSveVoznje, prosecnaZaradaPoVoznji, brojVozacaKojiSuVozili);
                        prozorZaPrikazIzvestaja.setVisible(true);
                    }
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                DnevniIzvestaj.this.setVisible(false);
                DnevniIzvestaj.this.dispose();
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
    public Set<String> findDuplicates(DoublyLinkedList<String> list){
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
