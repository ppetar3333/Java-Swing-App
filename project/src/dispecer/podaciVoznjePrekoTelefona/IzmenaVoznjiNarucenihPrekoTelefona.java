package dispecer.podaciVoznjePrekoTelefona;

import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzmenaVoznjiNarucenihPrekoTelefona extends PrikazVoznjiPutemTelefona{

    private JButton btnEdit = new JButton("Izmeni");

    private NarucivanjeVoznjePrekoTelefona voznja;

    public IzmenaVoznjiNarucenihPrekoTelefona(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja){
        super(ucitavanje);
        this.voznja = voznja;
        setTitle("Izmena voznji kreiranih putem aplikacije");
        initGui();
        initListeners();
    }

    private void initGui(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
        btnEdit.setIcon(deleteIcon);
        mainJtoolBar.add(btnEdit);
        add(mainJtoolBar, BorderLayout.NORTH);
    }

    private void initListeners() {
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == - 1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String id = tableModel.getValueAt(red,0).toString();
                    int nadjiId = Integer.parseInt(id);

                    DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> sveVoznjePrekoTelefona = ucitavanje.neobrisaneVoznjeKreiranePutemTelefona();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeTelefonBinarySearch(sveVoznjePrekoTelefona,nadjiId);
                    NarucivanjeVoznjePrekoTelefona voznja = sveVoznjePrekoTelefona.get(indexGdeSeNalazi);

                    if (voznja != null){
                        int izbor = JOptionPane.showConfirmDialog(null,"Da li ste sigurni da zelite da izmenite voznju? ", "Potvrda brisanja", JOptionPane.YES_NO_OPTION );
                        if (izbor == JOptionPane.YES_OPTION){
                            ProzorZaIzmenuVoznjiPutemTelefona prozorZaIzmenuVoznjiPutemTelefona = new ProzorZaIzmenuVoznjiPutemTelefona(ucitavanje, (NarucivanjeVoznjePrekoTelefona) voznja);
                            prozorZaIzmenuVoznjiPutemTelefona.setVisible(true);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu voznju!", "Greska", JOptionPane.ERROR_MESSAGE);

                    }
                }

            }
        });
    }
}
