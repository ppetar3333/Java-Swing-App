package dispecer.podaciAutomobila;

import automobili.Automobil;
import dispecer.podaciVozaca.IzmenaVozaca;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmenaAutomobila extends PrikazAutomobila {

    private JButton btnEdit = new JButton("Izmeni");
    private JButton btnOsvezi = new JButton("Osvezi tabelu");

    public IzmenaAutomobila(Liste ucitavanje) {
        super(ucitavanje);
        setTitle("Izmena automobila");
        initGui();
        initListeners();
    }

    private void initGui(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        mainToolBar.add(btnOsvezi);
        add(btnEdit, BorderLayout.NORTH);
        add(btnOsvezi, BorderLayout.SOUTH);
    }

    private void initListeners() {
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = automobiliTabela.getSelectedRow();
                if (red == - 1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel)automobiliTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<Automobil> sviAuti = ucitavanje.neobrisaniAutomobili();
                    int indexGdeSeNalazi = ucitavanje.pronadjiAutomobilBinarySearch(sviAuti,id);
                    Automobil automobil = sviAuti.get(indexGdeSeNalazi);

                    if (automobil != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da izmenite automobil: " + automobil.getProizvodjac().substring(0,1).toUpperCase() + automobil.getProizvodjac().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION );
                        if (izbor == JOptionPane.YES_OPTION){
                            ProzorZaIzmenuAutomobila prozorZaIzmenuAutomobila = new ProzorZaIzmenuAutomobila(ucitavanje, automobil);
                            prozorZaIzmenuAutomobila.setVisible(true);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran automobil!", "Greska", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });
        btnOsvezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IzmenaAutomobila.this.setVisible(false);
                IzmenaAutomobila.this.dispose();
                IzmenaAutomobila izmenaAutomobila = new IzmenaAutomobila(ucitavanje);
                izmenaAutomobila.setVisible(true);
            }
        });
    }

}
