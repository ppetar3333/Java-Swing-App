package dispecer.dodeljivanjeVoznje;

import automobili.Voznja;
import enumi.StatusVoznje;
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

public class DodeljivanjeVoznje extends JFrame {

    private DefaultTableModel tableModel;
    private JToolBar mainToolBar = new JToolBar();
    public JTable voznjeTabela;
    private JButton btnEdit = new JButton("Dodeli");
    public JButton btnOsvezi = new JButton("Osvezi tabelu");
    public Liste ucitavanje;
    public NarucivanjeVoznjePrekoTelefona voznja;

    public DodeljivanjeVoznje(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja) {
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Dodeljivanje voznji");
        setSize(1100, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        initTable();
        initListeners();
    }

    private void initGUI(){
        ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
        btnEdit.setIcon(deleteIcon);
        mainToolBar.add(btnEdit);
        mainToolBar.add(btnOsvezi);
        add(btnEdit, BorderLayout.NORTH);
        add(btnOsvezi, BorderLayout.SOUTH);
    }

    private void initTable(){
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Musterija","Vozac","Broj predjenih km","Trajanje voznje","Status voznje"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().size()][zaglavnje.length];
        for(int i = 0; i < ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().size(); i++){
            Voznja voznje = ucitavanje.neobrisaneIkreiraneVoznjeNarucenePutemTelefona().get(i);
            sadrzaj[i][0] = voznje.getId();
            sadrzaj[i][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            sadrzaj[i][2] = voznje.getAdresaPolaska();
            sadrzaj[i][3] = voznje.getAdresaDestinacije();
            sadrzaj[i][4] = voznje.getMusterija().getIme().substring(0,1).toUpperCase() + voznje.getMusterija().getIme().substring(1);
            sadrzaj[i][5] = "/";
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

    private void initListeners(){
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();
                if (red == -1){
                    JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan red u tabeli!", "Greska", JOptionPane.WARNING_MESSAGE);
                }else{
                    DefaultTableModel tableModel = (DefaultTableModel) voznjeTabela.getModel();
                    String idString = tableModel.getValueAt(red, 0).toString();
                    int id = Integer.parseInt(idString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoTelefona> sveVoznjePrekoTelefona = ucitavanje.neobrisaneVoznjeKreiranePutemTelefona();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeTelefonBinarySearch(sveVoznjePrekoTelefona,id);
                    NarucivanjeVoznjePrekoTelefona voznja = sveVoznjePrekoTelefona.get(indexGdeSeNalazi);


                    if (voznja.getStatusVoznje().equals(StatusVoznje.KREIRANA)) {
                        int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da dodelite ovu voznju?", "Potvrda", JOptionPane.YES_NO_OPTION);
                        if (izbor == JOptionPane.YES_OPTION) {
                            ProzorZaDodeljivanjeVoznji prozorZaDodeljivanjeVoznji = new ProzorZaDodeljivanjeVoznji(ucitavanje, voznja);
                            prozorZaDodeljivanjeVoznji.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Voznja je vec dodeljena, molimo vas osvezite tabelu.", "Greska", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        btnOsvezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodeljivanjeVoznje.this.setVisible(false);
                DodeljivanjeVoznje.this.dispose();
                DodeljivanjeVoznje dodeljivanjeVoznje = new DodeljivanjeVoznje(ucitavanje,voznja);
                dodeljivanjeVoznje.setVisible(true);
            }
        });
    }
}

