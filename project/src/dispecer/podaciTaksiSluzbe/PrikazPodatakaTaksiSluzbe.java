package dispecer.podaciTaksiSluzbe;

import liste.Liste;
import taksiSluzba.TaksiSluzba;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class PrikazPodatakaTaksiSluzbe extends JFrame {

    public JToolBar mainToolBar = new JToolBar();

    public DefaultTableModel table_model;
    public JTable taksiSluzbaTabela;

    public Liste ucitavanje;
    public TaksiSluzba taksiSluzba;

    public PrikazPodatakaTaksiSluzbe(Liste ucitavanje,TaksiSluzba taksiSluzba){
        this.ucitavanje = ucitavanje;
        this.taksiSluzba = taksiSluzba;
        setTitle("Prikaz podataka taksi sluzbe");
        setSize(900,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
    }

    private void initGUI() {
        add(mainToolBar, BorderLayout.SOUTH);
        String[] zaglavlje = new String[]{"ID","PIB", "Naziv", "Adresa", "Cena starta voznje", "Cena po kilometru"};
        Object[][] sadrzaj = new Object[ucitavanje.getTaksiSluzbe().size()][zaglavlje.length];
        for (int i = 0; i < ucitavanje.getTaksiSluzbe().size(); i ++){
            TaksiSluzba taksiSluzba = ucitavanje.getTaksiSluzbe().get(i);
            sadrzaj[i][0] = taksiSluzba.getId();
            sadrzaj[i][1] = taksiSluzba.getPib();
            sadrzaj[i][2] = taksiSluzba.getNaziv();
            sadrzaj[i][3] = taksiSluzba.getAdresa();
            sadrzaj[i][4] = taksiSluzba.getCenaStartaVoznje() + " din";
            sadrzaj[i][5] = taksiSluzba.getCenaPoKilometru() + " din";
        }
        table_model = new DefaultTableModel(sadrzaj, zaglavlje);
        taksiSluzbaTabela = new JTable(table_model);

        taksiSluzbaTabela.setRowSelectionAllowed(true);
        taksiSluzbaTabela.setColumnSelectionAllowed(false);
        taksiSluzbaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taksiSluzbaTabela.setDefaultEditor(Object.class, null);
        taksiSluzbaTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        taksiSluzbaTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(taksiSluzbaTabela);
        add(jsp, BorderLayout.CENTER);
    }


}
