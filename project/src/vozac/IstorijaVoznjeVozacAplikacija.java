package vozac;

import automobili.Voznja;
import enumi.StatusVoznje;
import liste.Liste;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import osobe.Musterija;
import osobe.Vozac;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IstorijaVoznjeVozacAplikacija extends JFrame {

    private JToolBar mainJToolBar = new JToolBar();

    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;

    private Liste ucitavanje;
    private Vozac ulogovaniVozac;

    public IstorijaVoznjeVozacAplikacija(Liste ucitavanje, Vozac ulogovaniVozac){
        this.ucitavanje = ucitavanje;
        this.ulogovaniVozac = ulogovaniVozac;
        setTitle("Prikaz istorije sopstvene voznje");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI(){
        add(mainJToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Broj predjenih km","Trajanje voznje","Status voznje", "Napomena"};
        Object[][] sadrzaj = new Object[ucitavanje.prikazVoznjeZaIstorijuVoznjePrekoAplikacije().size()][zaglavnje.length];
        int j = 0;
        for(int i = 0; i < ucitavanje.prikazVoznjeZaIstorijuVoznjePrekoAplikacije().size(); i++){
            NarucivanjeVoznjePrekoAplikacije voznje = ucitavanje.prikazVoznjeZaIstorijuVoznjePrekoAplikacije().get(i);
            sadrzaj[j][0] = voznje.getId();
            sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
            sadrzaj[j][2] = voznje.getAdresaPolaska();
            sadrzaj[j][3] = voznje.getAdresaDestinacije();
            sadrzaj[j][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
            sadrzaj[j][5] = voznje.getBrojKMpredjenih();
            sadrzaj[j][6] = voznje.getTrajanjVoznje();
            sadrzaj[j][7] = voznje.getStatusVoznje();
            sadrzaj[j][8] = voznje.getNapomena();
            j++;
        }
        table_model = new DefaultTableModel(sadrzaj, zaglavnje);
        istorijaVoznjeTabela = new JTable(table_model);

        istorijaVoznjeTabela.setRowSelectionAllowed(true);
        istorijaVoznjeTabela.setColumnSelectionAllowed(false);
        istorijaVoznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        istorijaVoznjeTabela.setDefaultEditor(Object.class, null);
        istorijaVoznjeTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        istorijaVoznjeTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(istorijaVoznjeTabela);
        add(jsp, BorderLayout.CENTER);
    }

}
