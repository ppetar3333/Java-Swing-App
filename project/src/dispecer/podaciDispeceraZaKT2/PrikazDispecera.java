package dispecer.podaciDispeceraZaKT2;

import liste.Liste;
import osobe.Dispecar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class PrikazDispecera extends JFrame {

    public JToolBar mainToolBar = new JToolBar();

    public DefaultTableModel table_model;
    public JTable dispecerTabela;

    public Liste ucitavanje;
    public Dispecar dispecer;

    public PrikazDispecera(Liste ucitavanje, Dispecar dispecer) {
        this.ucitavanje = ucitavanje;
        this.dispecer = dispecer;
        setTitle("Prikaz dispecera");
        setSize(950, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGui();
    }

    private void initGui(){
        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavnje = new String[] {"Korisnicko ime", "Ime", "Prezime", "Adresa", "Pol", "Broj telefona", "Plata","Broj telefonske linije"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaniDispeceri().size()][zaglavnje.length];
        for (int i = 0; i < ucitavanje.neobrisaniDispeceri().size(); i++) {
            Dispecar dispecar = ucitavanje.neobrisaniDispeceri().get(i);
            if(dispecar.isObrisan()) {
                sadrzaj[i][0] = dispecar.getKorisnickoIme();
                sadrzaj[i][1] = dispecar.getIme().substring(0,1).toUpperCase() + dispecar.getIme().substring(1);
                sadrzaj[i][2] = dispecar.getPrezime().substring(0,1).toUpperCase() + dispecar.getPrezime().substring(1);
                sadrzaj[i][3] = dispecar.getAdresa();
                sadrzaj[i][4] = dispecar.getPol().toString().toLowerCase();
                sadrzaj[i][5] = dispecar.getBrojTelefona();
                sadrzaj[i][6] = dispecar.getPlata();
                sadrzaj[i][7] = dispecar.getBrojTelefonskeLinije();
            }
        }


        table_model = new DefaultTableModel(sadrzaj, zaglavnje);

        dispecerTabela = new JTable(table_model);

        dispecerTabela.setRowSelectionAllowed(true);
        dispecerTabela.setColumnSelectionAllowed(false);
        dispecerTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dispecerTabela.setDefaultEditor(Object.class, null);
        dispecerTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        dispecerTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(dispecerTabela);
        add(jsp, BorderLayout.CENTER);
    }
}
