package dispecer.podaciAutomobila;

import automobili.Automobil;
import enumi.StatusVozacaIautomobila;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrisanjeAutomobila extends PrikazAutomobila {

    private JButton btnDelete = new JButton();
    
    public BrisanjeAutomobila(Liste ucitavanje) {
        super(ucitavanje);
        setTitle("Brisanje Automobila");
        initGUI();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
        btnDelete.setIcon(deleteIcon);
        mainToolBar.add(btnDelete);
        add(mainToolBar, BorderLayout.NORTH);
    }


    private void initListeners() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = automobiliTabela.getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(null,"Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel) automobiliTabela.getModel();

                    String id = tableModel.getValueAt(red, 0).toString();
                    int nadjiId = Integer.parseInt(id);

                    DoublyLinkedList<Automobil> sviAuti = ucitavanje.neobrisaniAutomobili();
                    int indexGdeSeNalazi = ucitavanje.pronadjiAutomobilBinarySearch(sviAuti,nadjiId);
                    Automobil automobil = sviAuti.get(indexGdeSeNalazi);

                    if (automobil.getStatusAutomobila() == StatusVozacaIautomobila.SLOBODAN) {
                        if (automobil != null) {
                            int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete automobil: " + automobil.getModel() + " ?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                            if (izbor == JOptionPane.YES_OPTION) {
                                automobil.setObrisan(false);
                                tableModel.removeRow(red);
                                ucitavanje.snimanjeAutomobila(TaxiSluzbaMain.AUTOMOBILI_FAJL);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran automobil!", "Greska", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Mogu se obrisati samo slobodni automobili!","Greska",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

    
}
