package musterija.probaZaAlgoritme;

import dispecer.dodeljivanjeVoznje.DodeljivanjeVoznje;
import enumi.StatusVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import osobe.Vozac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UcestvujUaukciji extends DodeljivanjeVoznje {

    private JButton btnUcestvujUaukciji = new JButton("Ucestvuj u aukciji");

    public UcestvujUaukciji(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja) {
        super(ucitavanje, voznja);
        setTitle("Aukcija");
        initGUI();
        initListeners();
    }

    private void initGUI(){
        add(btnUcestvujUaukciji, BorderLayout.NORTH);
        remove(btnOsvezi);
    }

    private void initListeners(){
        btnUcestvujUaukciji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> sveVoznjePrekoTelefona = ucitavanje.neobrisaneVoznjeKreiranePutemTelefona();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeTelefonBinarySearch(sveVoznjePrekoTelefona,id);
                    NarucivanjeVoznjePrekoTelefona trazenaVoznja = sveVoznjePrekoTelefona.get(indexGdeSeNalazi);

                    DoublyLinkedList<Aukcija> aukcijaDoublyLinkedList = ucitavanje.getIstorijaAukcija();
                    DoublyLinkedList<String> listaVozacaKojiUcestvujuUaukciji = new DoublyLinkedList<>();
                    String ulogovanVozac = ucitavanje.ulogovanKorisnik();
                    for(Aukcija aukcija : aukcijaDoublyLinkedList){
                        if(trazenaVoznja.getId() == aukcija.getIDvoznje()){
                            listaVozacaKojiUcestvujuUaukciji.add(aukcija.getVozacKojiUcestvujeUaukciji());
                        }
                    }
                    DoublyLinkedList<String> poredjenjeVozaca = new DoublyLinkedList<>();
                    for(String s : listaVozacaKojiUcestvujuUaukciji){
                        if (ulogovanVozac.equals(s)){
                            poredjenjeVozaca.add(s);
                        }
                    }
                    if(poredjenjeVozaca.isEmpty()) {
                        ProzorZaUnosVremenaVozaca prozorZaUnosVremenaVozaca = new ProzorZaUnosVremenaVozaca(ucitavanje, trazenaVoznja);
                        prozorZaUnosVremenaVozaca.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Za datu voznju, vec ucestvujete u aukciji, pokusajte sa drugom voznjom.","Obavestenje",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

}
