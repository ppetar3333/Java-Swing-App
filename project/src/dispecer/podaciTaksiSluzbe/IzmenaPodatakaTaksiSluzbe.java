package dispecer.podaciTaksiSluzbe;

import automobili.Automobil;
import dispecer.podaciVozaca.IzmenaVozaca;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import taksiSluzba.TaksiSluzba;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmenaPodatakaTaksiSluzbe extends PrikazPodatakaTaksiSluzbe{

    private JButton btnEdit = new JButton();
    private JButton btnOsvezi = new JButton("Osvezi tabelu");

    public IzmenaPodatakaTaksiSluzbe(Liste ucitavanje, TaksiSluzba taksiSluzba) {
        super(ucitavanje,taksiSluzba);
        setTitle("Izmena taksi sluzbi");
        initGui();
        initListeners();
    }

    private void initGui() {
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        mainToolBar.add(btnOsvezi);
        add(mainToolBar, BorderLayout.NORTH);
        add(btnOsvezi, BorderLayout.SOUTH);
    }

        private void initListeners() {
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = taksiSluzbaTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati barem jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) taksiSluzbaTabela.getModel();

                    String idString = tableModel.getValueAt(red,0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<TaksiSluzba> taksiSluzbe = ucitavanje.taksiSluzba();
                    int indexGdeSeNalazi = ucitavanje.pronadjiTaksiSluzbuBinarySearch(taksiSluzbe,id);
                    TaksiSluzba taksiSluzba = taksiSluzbe.get(indexGdeSeNalazi);

                    if (taksiSluzba != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da izmenite taksi sluzbu: " + taksiSluzba.getNaziv().substring(0,1).toUpperCase() + taksiSluzba.getNaziv().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION );
                        if (izbor == JOptionPane.YES_OPTION){
                            ProzorZaIzmenuTaksiSluzbe prozorZaIzmenuTaksiSluzbe = new ProzorZaIzmenuTaksiSluzbe(ucitavanje, taksiSluzba);
                            prozorZaIzmenuTaksiSluzbe.setVisible(true);
                        }else{
                            JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu taksi sluzbu!", "Greska", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }
            }
        });
            btnOsvezi.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    IzmenaPodatakaTaksiSluzbe.this.setVisible(false);
                    IzmenaPodatakaTaksiSluzbe.this.dispose();
                    IzmenaPodatakaTaksiSluzbe izmenaPodatakaTaksiSluzbe = new IzmenaPodatakaTaksiSluzbe(ucitavanje, taksiSluzba);
                    izmenaPodatakaTaksiSluzbe.setVisible(true);
                }
            });
    }

}


