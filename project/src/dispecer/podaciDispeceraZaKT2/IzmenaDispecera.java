package dispecer.podaciDispeceraZaKT2;

import liste.Liste;
import osobe.Dispecar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmenaDispecera extends PrikazDispecera{

    private JButton btnEdit = new JButton();

    public IzmenaDispecera(Liste ucitavanje, Dispecar dispecer) {
        super(ucitavanje, dispecer);
        setTitle("Izmena dispecera");
        initGUI();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        add(mainToolBar, BorderLayout.NORTH);
    }

    private void initListeners(){
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = dispecerTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) dispecerTabela.getModel();
                    String korisnickoIme = tableModel.getValueAt(red, 0).toString();
                    Dispecar dispecar = ucitavanje.nadjiDispecera(korisnickoIme);
                    if (dispecar != null) {
                        int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da izmenite dispecera: " + dispecar.getIme().substring(0, 1).toUpperCase() + dispecar.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if (izbor == JOptionPane.YES_OPTION) {
                            ProzorZaIzmenuDispecera prozorZaIzmenuDispecera = new ProzorZaIzmenuDispecera(ucitavanje,dispecer);
                            prozorZaIzmenuDispecera.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog dispecera!", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

}
