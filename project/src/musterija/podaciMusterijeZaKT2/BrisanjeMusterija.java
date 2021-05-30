package musterija.podaciMusterijeZaKT2;

import liste.Liste;
import osobe.Musterija;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrisanjeMusterija extends PrikazMusterija{

    private JButton btnDelete = new JButton();

    public BrisanjeMusterija(Liste ucitavanje, Musterija musterija) {
        super(ucitavanje, musterija);
        setTitle("Brisanje Musterije");
        initGUI();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
        btnDelete.setIcon(deleteIcon);
        mainToolBar.add(btnDelete);
        add(mainToolBar, BorderLayout.NORTH);
    }

    private void initListeners(){
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = musterijaTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) musterijaTabela.getModel();
                    String korisnickoIme = tableModel.getValueAt(red,0).toString();
                    Musterija musterija = ucitavanje.nadjiMusteriju(korisnickoIme);
                    if (musterija != null) {
                        int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete musteriju: " + musterija.getIme().substring(0, 1).toUpperCase() + musterija.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if (izbor == JOptionPane.YES_OPTION) {
                            tableModel.removeRow(red);
                            musterija.setObrisan(false);
                            ucitavanje.dodavanjeKorisnika();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu musteriju!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }
}
