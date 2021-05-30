package dispecer.podaciVozaca;

import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmenaVozaca extends PrikazVozaca {

    private JButton btnEdit = new JButton("Izmeni");
    private JButton btnOsvezi = new JButton("Osvezi tabelu");

    public IzmenaVozaca(Liste ucitavanje,Vozac vozac) {
        super(ucitavanje,vozac);
        setTitle("Izmena vozaca");
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

    private void initListeners(){
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel)vozaciTabela.getModel();
                    String korisnickoIme = tableModel.getValueAt(red, 0).toString();
                    Vozac vozac = ucitavanje.nadjiVozaca(korisnickoIme);
                    if( vozac != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da izmenite vozaca: " + vozac.getIme().substring(0,1).toUpperCase() + vozac.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if ( izbor == JOptionPane.YES_OPTION ){
                            ProzorZaIzmenuVozaca prozorZaIzmenuVozaca = new ProzorZaIzmenuVozaca(ucitavanje,vozac);
                            prozorZaIzmenuVozaca.setVisible(true);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog vozaca, molim ovas osvezite tabelu.", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnOsvezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IzmenaVozaca.this.setVisible(false);
                IzmenaVozaca.this.dispose();
                IzmenaVozaca izmenaVozaca = new IzmenaVozaca(ucitavanje,vozac);
                izmenaVozaca.setVisible(true);
            }
        });
    }
}
