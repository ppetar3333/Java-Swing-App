package vozac.zavrsavanjeVoznje;

import automobili.Voznja;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import vozac.prikazVoznji.PrikazVoznjiZakazanihPrekoAplikacije;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class ZavrsavanjeVoznjePutemAplikacije extends JFrame {

    private JToolBar mainJtoolBar = new JToolBar();
    private JButton btnZavrsi = new JButton("Zavrsi voznju");
    private DefaultTableModel tableModel;
    private JTable voznjeTabela;

    private Liste ucitavanje;

    public ZavrsavanjeVoznjePutemAplikacije(Liste ucitavanje){
        this.ucitavanje = ucitavanje;
        mainJtoolBar.add(btnZavrsi);
        setTitle("Zavrsavanje voznje");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        initListeners();
    }

    private void initGUI(){
        add(mainJtoolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje", "Napomena"};
        Object[][] sadrzaj = new Object[ucitavanje.prikazVoznjeZaZavrsavanjeVoznjePutemAplikacije().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.prikazVoznjeZaZavrsavanjeVoznjePutemAplikacije().size(); i++){
            NarucivanjeVoznjePrekoAplikacije voznje = ucitavanje.prikazVoznjeZaZavrsavanjeVoznjePutemAplikacije().get(i);
            sadrzaj[i][0] = voznje.getId();
            sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            sadrzaj[i][2] = voznje.getAdresaPolaska();
            sadrzaj[i][3] = voznje.getAdresaDestinacije();
            sadrzaj[i][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
            if(voznje.getVozac().getKorisnickoIme() != "") {
                sadrzaj[i][5] = voznje.getVozac().getIme().substring(0, 1).toUpperCase() + voznje.getVozac().getIme().substring(1);
            }else{
                sadrzaj[i][5] = "Nema slobodan vozac";
            }
            if(voznje.getBrojKMpredjenih() == 0){
                sadrzaj[i][6] = "/";
            }else {
                sadrzaj[i][6] = voznje.getBrojKMpredjenih();
            }
            if(voznje.getTrajanjVoznje() == 0){
                sadrzaj[i][7] = "/";
            }else {
                sadrzaj[i][7] = voznje.getTrajanjVoznje();
            }
            sadrzaj[i][8] = voznje.getStatusVoznje();
            sadrzaj[i][9] = voznje.getNapomena();

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

                    DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sveVoznjePrekoAplikacije = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeAplikacijaBinarySearch(sveVoznjePrekoAplikacije,id);
                    NarucivanjeVoznjePrekoAplikacije nadjiVoznju = sveVoznjePrekoAplikacije.get(indexGdeSeNalazi);

                    int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da zavrsite voznju?", "Potvrda prihvatanja", JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        ProzorZaUnosPodatakaZaZavrsenuVoznjuPutemAplikacije podaci = new ProzorZaUnosPodatakaZaZavrsenuVoznjuPutemAplikacije(ucitavanje,nadjiVoznju);
                        podaci.setVisible(true);
                        ZavrsavanjeVoznjePutemAplikacije.this.setVisible(false);
                        ZavrsavanjeVoznjePutemAplikacije.this.dispose();
                    }
                }
            }
        });
    }

}
