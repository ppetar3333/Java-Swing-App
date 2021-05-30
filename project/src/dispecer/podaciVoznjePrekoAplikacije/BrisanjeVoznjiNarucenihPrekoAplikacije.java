package dispecer.podaciVoznjePrekoAplikacije;

import automobili.Voznja;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrisanjeVoznjiNarucenihPrekoAplikacije extends PrikazVoznjiPutemAplikacije {

    private JButton btnDelete = new JButton();

    public BrisanjeVoznjiNarucenihPrekoAplikacije(Liste ucitavanje) {
        super(ucitavanje);
        setTitle("Brisanje voznji narucenih putem aplikacije");
        initGUI();
        initListeners();
    }

    private void initGUI() {
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
        btnDelete.setIcon(deleteIcon);
        mainJtoolBar.add(btnDelete);
        add(mainJtoolBar, BorderLayout.NORTH);
    }

    private void initListeners() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null,"Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String id = tableModel.getValueAt(red,0).toString();
                    int nadjiId = Integer.parseInt(id);

                    DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sveVoznjePrekoAplikacije = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeAplikacijaBinarySearch(sveVoznjePrekoAplikacije,nadjiId);
                    NarucivanjeVoznjePrekoAplikacije voznja = sveVoznjePrekoAplikacije.get(indexGdeSeNalazi);


                    if (voznja != null) {
                        int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete voznju?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if (izbor == JOptionPane.YES_OPTION) {
                            voznja.setObrisan(false);
                            tableModel.removeRow(red);
                            ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu voznju!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }


}
