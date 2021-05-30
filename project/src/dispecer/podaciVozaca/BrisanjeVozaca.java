package dispecer.podaciVozaca;

import automobili.Automobil;
import enumi.StatusVozacaIautomobila;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BrisanjeVozaca extends PrikazVozaca {

    private JButton btnDelete = new JButton("Obrisi");

    public BrisanjeVozaca(Liste ucitavanje,Vozac vozac) {
        super(ucitavanje,vozac);
        setTitle("Brisanje Vozaca");
        initGUI();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
        btnDelete.setIcon(deleteIcon);
        mainToolBar.add(btnDelete);
        add(btnDelete, BorderLayout.NORTH);
    }

    private void initListeners(){
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel)vozaciTabela.getModel();
                    String korisnickoIme = tableModel.getValueAt(red, 0).toString();
                    Vozac vozac = ucitavanje.nadjiVozaca(korisnickoIme);
                    String idAutomobilaString = tableModel.getValueAt(red,8).toString();
                    if(idAutomobilaString.equals("Vozac nema automobil")){
                        int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete vozaca: " + vozac.getIme().substring(0, 1).toUpperCase() + vozac.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                        if( izbor == JOptionPane.YES_OPTION) {
                            tableModel.removeRow(red);
                            vozac.setObrisan(false);
                            ucitavanje.dodavanjeKorisnika();
                        }
                    }else {


                        int idAutomobila = Integer.parseInt(idAutomobilaString);

                        DoublyLinkedList<Automobil> sviAuti = ucitavanje.neobrisaniAutomobili();
                        int indexGdeSeNalazi = ucitavanje.pronadjiAutomobilBinarySearch(sviAuti,idAutomobila);
                        Automobil automobil = sviAuti.get(indexGdeSeNalazi);



                        if (vozac != null) {
                            int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete vozaca: " + vozac.getIme().substring(0, 1).toUpperCase() + vozac.getIme().substring(1) + "?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                            if (izbor == JOptionPane.YES_OPTION) {
                                vozac.setObrisan(false);
                                tableModel.removeRow(red);
                                if (idAutomobila == vozac.getAutomobili().getId()) {
                                    automobil.setStatusAutomobila(StatusVozacaIautomobila.SLOBODAN);
                                } else if (automobil.getStatusAutomobila() == StatusVozacaIautomobila.SLOBODAN) {
                                    automobil.setStatusAutomobila(StatusVozacaIautomobila.SLOBODAN);
                                }
                                ucitavanje.dodavanjeKorisnika();
                                ucitavanje.snimanjeAutomobila(TaxiSluzbaMain.AUTOMOBILI_FAJL);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog vozaca!", "Greska", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }
}
