package vozac.zavrsavanjeVoznje;

import automobili.Voznja;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class ZavrsavanjeVoznjePutemTelefona extends JFrame {

    private JToolBar mainJtoolBar = new JToolBar();
    private JButton btnZavrsi = new JButton("Zavrsi voznju");
    private DefaultTableModel tableModel;
    private JTable voznjeTabela;

    private Liste ucitavanje;

    public ZavrsavanjeVoznjePutemTelefona(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        add(btnZavrsi,BorderLayout.NORTH);
        setTitle("Zavrsavanje voznje");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        initListeners();
    }


    private void initGUI(){
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.prikazVoznjeZaZavrsavanjeVoznje().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.prikazVoznjeZaZavrsavanjeVoznje().size(); i++){
            Voznja voznje = ucitavanje.prikazVoznjeZaZavrsavanjeVoznje().get(i);
            sadrzaj[i][0] = voznje.getId();
            sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            sadrzaj[i][2] = voznje.getAdresaPolaska();
            sadrzaj[i][3] = voznje.getAdresaDestinacije();
            sadrzaj[i][4] = voznje.getMusterija().getKorisnickoIme();
            sadrzaj[i][5] = voznje.getVozac().getKorisnickoIme();
            sadrzaj[i][6] = "/";
            sadrzaj[i][7] = "/";
            sadrzaj[i][8] = voznje.getStatusVoznje();
        }
        tableModel = new DefaultTableModel(sadrzaj, zaglavnje);
        voznjeTabela = new JTable(tableModel);

        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
        voznjeTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(voznjeTabela);
        add(jsp, BorderLayout.CENTER);
    }

    private void initListeners() {
        btnZavrsi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> sveVoznjePrekoTelefona = ucitavanje.neobrisaneVoznjeKreiranePutemTelefona();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeTelefonBinarySearch(sveVoznjePrekoTelefona,id);
                    NarucivanjeVoznjePrekoTelefona nadjiVoznju = sveVoznjePrekoTelefona.get(indexGdeSeNalazi);

                    int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da zavrsite voznju?", "Potvrda prihvatanja", JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        ProzorZaUnosPodatakaVozacaZaZavrsenuVoznju podaci = new ProzorZaUnosPodatakaVozacaZaZavrsenuVoznju(ucitavanje,nadjiVoznju);
                        podaci.setVisible(true);
                        ZavrsavanjeVoznjePutemTelefona.this.setVisible(false);
                        ZavrsavanjeVoznjePutemTelefona.this.dispose();
                    }
                }
            }
        });
    }
}
