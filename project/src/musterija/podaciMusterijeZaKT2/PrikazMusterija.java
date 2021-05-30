package musterija.podaciMusterijeZaKT2;

import liste.Liste;
import osobe.Musterija;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class PrikazMusterija extends JFrame {

    public JToolBar mainToolBar = new JToolBar();

    public DefaultTableModel table_model;
    public JTable musterijaTabela;

    public Liste ucitavanje;
    public Musterija musterija;

    public PrikazMusterija(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.musterija = musterija;
        setTitle("Prikaz musterija");
        setSize(750, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGui();
    }

    private void initGui(){

        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"Korisnicko ime", "Ime", "Prezime", "Adresa", "Pol", "Broj telefona"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneMusterije().size()][zaglavnje.length];
        for (int i = 0; i < ucitavanje.neobrisaneMusterije().size(); i++) {
            Musterija musterija = ucitavanje.neobrisaneMusterije().get(i);
            if(musterija.isObrisan()) {
                sadrzaj[i][0] = musterija.getKorisnickoIme();
                sadrzaj[i][1] = musterija.getIme();
                sadrzaj[i][2] = musterija.getPrezime();
                sadrzaj[i][3] = musterija.getAdresa();
                sadrzaj[i][4] = musterija.getPol().toString().toLowerCase();
                sadrzaj[i][5] = musterija.getBrojTelefona();
            }
        }


        table_model = new DefaultTableModel(sadrzaj, zaglavnje);

        musterijaTabela = new JTable(table_model);

        musterijaTabela.setRowSelectionAllowed(true);
        musterijaTabela.setColumnSelectionAllowed(false);
        musterijaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        musterijaTabela.setDefaultEditor(Object.class, null);
        musterijaTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        musterijaTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(musterijaTabela);
        add(jsp, BorderLayout.CENTER);
    }
}
