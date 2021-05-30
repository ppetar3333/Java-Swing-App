package dispecer.pretragaAutomobila;

import automobili.Automobil;
import liste.doublyLinkedList.DoublyLinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.jar.JarEntry;

public class ProzorZaPretraguPoGodiniProizvodnje extends JFrame {

    private DefaultTableModel table_model;
    private JTable automobiliTabela;

    public ProzorZaPretraguPoGodiniProizvodnje(DoublyLinkedList<Automobil> automobils) {
        setTitle("Prikaz pretrage");
        setSize(1050, 200);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGui(automobils);
    }

    private void initGui(DoublyLinkedList<Automobil> automobils) {
        String[] zaglavlje = new String[]{"ID", "Model", "Proizvodjac", "Godina proizvodnje", "Broj registarske oznake", "Broj taksi vozila", "Vrsta automobila", "Status Automobila", "Pet Friendly"};
        Object[][] sadrzaj = new Object[automobils.size()][zaglavlje.length];
        for (int i = 0; i < automobils.size(); i++){
            Automobil automobil = automobils.get(i);
            if (automobil.isObrisan()){
                sadrzaj[i][0] = automobil.getId();
                sadrzaj[i][1] = automobil.getModel();
                sadrzaj[i][2] = automobil.getProizvodjac();
                sadrzaj[i][3] = automobil.getGodinaProizvodnje();
                sadrzaj[i][4] = automobil.getRegistarskiBroj();
                sadrzaj[i][5] = automobil.getBrojVozila();
                sadrzaj[i][6] = automobil.getVrstaVozila().toString().toLowerCase().replace("_"," ");
                sadrzaj[i][7] = automobil.getStatusAutomobila().toString().toLowerCase();
                if(automobil.isPetFriendly()){
                    sadrzaj[i][8] = "da";
                }else{
                    sadrzaj[i][8] = "ne";
                }
            }
        }
        table_model = new DefaultTableModel(sadrzaj, zaglavlje);

        automobiliTabela = new JTable(table_model);

        automobiliTabela.setRowSelectionAllowed(true);
        automobiliTabela.setColumnSelectionAllowed(false);
        automobiliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        automobiliTabela.setDefaultEditor(Object.class, null);
        automobiliTabela.getTableHeader().setReorderingAllowed(false);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table_model);
        automobiliTabela.setRowSorter(sorter);

        JScrollPane jsp = new JScrollPane(automobiliTabela);
        add(jsp, BorderLayout.CENTER);
    }

}
