package musterija.istorijaVoznji;

import enumi.StatusVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import osobe.Musterija;
import osobe.Vozac;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class IstorijaVoznjiMusterijaAplikacija extends JFrame {

    private JToolBar mainJToolBar = new JToolBar();

    private DefaultTableModel table_model;
    private JTable istorijaVoznjeTabela;
    private JButton oceniVozaca = new JButton("Oceni vozaca");

    private Liste ucitavanje;
    private Musterija ulogovanaMusterija;
    private Vozac vozac;

    public IstorijaVoznjiMusterijaAplikacija(Liste ucitavanje, Musterija musterija){
        this.ucitavanje = ucitavanje;
        this.ulogovanaMusterija = ulogovanaMusterija;
        setTitle("Prikaz istorije sopstvene voznje " + "(" + musterija.getIme().substring(0,1).toUpperCase() + musterija.getIme().substring(1) + ")");
        setSize(1050,300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        initGUI();
        initListeners();
    }

    private void initGUI() {
        add(oceniVozaca, BorderLayout.NORTH);
        String[] zaglavnje = new String[] {"ID","Datum i vreme porudzbine","Adresa polaska","Adresa destinacije","Vozac","Broj predjenih km","Trajanje voznje","Status voznje", "Napomena"};
        Object[][] sadrzaj = new Object[ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size()][zaglavnje.length];
        int j = 0;
        for (int i = 0; i < ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().size(); i ++){
            NarucivanjeVoznjePrekoAplikacije voznje = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije().get(i);

            Musterija ulogovanaMusterija = null;
            try {
                File ulogovanKorisnik = new File("src/fajlovi/ulogovanKorisnik.txt");
                Scanner citanjeUlogovanogKorisnika = new Scanner(ulogovanKorisnik);
                while (citanjeUlogovanogKorisnika.hasNextLine()) {
                    String data = citanjeUlogovanogKorisnika.nextLine();
                    ulogovanaMusterija = new Musterija();
                    ulogovanaMusterija.setKorisnickoIme(data);
                }
                citanjeUlogovanogKorisnika.close();
            }  catch (IOException ioException) {
                ioException.printStackTrace();
                System.out.println("Greska");
            }

            if(voznje.getStatusVoznje().equals(StatusVoznje.ZAVRSENA) && voznje.getMusterija().getKorisnickoIme().equals(ulogovanaMusterija.getKorisnickoIme())){
                sadrzaj[j][0] = voznje.getId();
                sadrzaj[j][1] = voznje.getDatumIvremePorudzbine().format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm"));
                sadrzaj[j][2] = voznje.getAdresaPolaska();
                sadrzaj[j][3] = voznje.getAdresaDestinacije();
                sadrzaj[j][4] = voznje.getVozac().getKorisnickoIme();
                sadrzaj[j][5] = voznje.getBrojKMpredjenih();
                sadrzaj[j][6] = voznje.getTrajanjVoznje();
                sadrzaj[j][7] = voznje.getStatusVoznje();
                sadrzaj[j][8] = voznje.getNapomena();
                j++;
            }
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

    private void initListeners(){
        oceniVozaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = istorijaVoznjeTabela.getSelectedRow();
                if( red == -1){
                    JOptionPane.showMessageDialog(null,"Morate izabrati bar jedan redu tabeli!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    DefaultTableModel tableModel = (DefaultTableModel) istorijaVoznjeTabela.getModel();
                    String korisnickoIme = tableModel.getValueAt(red, 4).toString();
                    Vozac vozac = ucitavanje.nadjiVozaca(korisnickoIme);

                    String idVoznjeString = tableModel.getValueAt(red,0).toString();
                    int idVoznje = Integer.parseInt(idVoznjeString);

                    DoublyLinkedList<NarucivanjeVoznjePrekoAplikacije> sveVoznjePrekoAplikacije = ucitavanje.neobrisaneVoznjeKreiranePutemAplikacije();
                    int indexGdeSeNalazi = ucitavanje.pronadjiVoznjeAplikacijaBinarySearch(sveVoznjePrekoAplikacije,idVoznje);
                    NarucivanjeVoznjePrekoAplikacije voznja = sveVoznjePrekoAplikacije.get(indexGdeSeNalazi);

                    if(voznja.isOcenjenVozac() == false) {
                        ProzorZaOcenjivanjeVoznjiAplikacija prozorZaOcenjivanje = new ProzorZaOcenjivanjeVoznjiAplikacija(ucitavanje, vozac, voznja);
                        prozorZaOcenjivanje.setVisible(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"Vec ste ocenili ovu voznju!","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }
}
