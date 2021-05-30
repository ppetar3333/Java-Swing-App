package dispecer.dodeljivanjeVoznje;

import enumi.StatusNaruceneVoznje;
import enumi.StatusVozacaIautomobila;
import enumi.StatusVoznje;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoTelefona.NarucivanjeVoznjePrekoTelefona;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Vozac;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class ProzorZaDodeljivanjeVoznji extends JFrame{

    private JLabel adresaPolaska = new JLabel("Adresa polaska");
    private JTextField tadresaPolaska = new JTextField(20);
    private JLabel adresaDolaska = new JLabel("Adresa destinacije");
    private JTextField tadresaDolaska = new JTextField(20);
    private JLabel musterija = new JLabel("Musterija");
    private JTextField tmusterija = new JTextField(20);
    private JLabel vozac = new JLabel("Vozac");
    private JComboBox slobodniVozaci;
    private JLabel statusVoznje = new JLabel("Status voznje");
    private JComboBox<StatusVoznje> statusVoznjeJComboBox = new JComboBox<StatusVoznje>(StatusVoznje.values());
    private JButton dodeli = new JButton("Dodeli");
    private JButton btnCancel = new JButton("Odustani");

    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoTelefona voznja;

    public ProzorZaDodeljivanjeVoznji(Liste ucitavanje, NarucivanjeVoznjePrekoTelefona voznja) {
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Dodeli voznju vozacu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI(){
        MigLayout layout = new MigLayout("wrap 2");

        setLayout(layout);
        add(adresaPolaska);
        add(tadresaPolaska);
        add(adresaDolaska);
        add(tadresaDolaska);
        add(musterija);
        add(tmusterija);
        add(vozac);
        DoublyLinkedList<String> listaSlobodniVozaca = ucitavanje.listaSlobodnihVozaca();
        String[] array = new String[listaSlobodniVozaca.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = String.valueOf(listaSlobodniVozaca.get(i));
        }
        slobodniVozaci = new JComboBox(array);
        add(slobodniVozaci);
        add(statusVoznje);
        add(statusVoznjeJComboBox);
        add(new JLabel());
        add(dodeli, "split 2");
        this.getRootPane().setDefaultButton(dodeli);
        add(btnCancel);

        tadresaPolaska.setEditable(false);
        tadresaDolaska.setEditable(false);
        tmusterija.setEditable(false);

        if(this.voznja != null){
            popunjavanjePolja();
        }
    }

    private void initListeners(){
        dodeli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selektovanVozac = (String) slobodniVozaci.getSelectedItem();
                Vozac vozac = new Vozac();
                vozac.setKorisnickoIme(selektovanVozac);
                Vozac dodeljeniVozac = ucitavanje.nadjiVozaca(vozac.getKorisnickoIme());
                if(dodeljeniVozac == null){
                    JOptionPane.showMessageDialog(null,"Trenutno nema slobodnih vozaca, molim vas pokusajte kasnije.","Obavestenje",JOptionPane.INFORMATION_MESSAGE);
                }else {
                    StatusVoznje statusVoznje = (StatusVoznje) statusVoznjeJComboBox.getSelectedItem();
                    if (statusVoznje != StatusVoznje.DODELJENA) {
                        JOptionPane.showMessageDialog(null, "Status voznje mora biti promenjen u dodeljena!", "Greska", JOptionPane.WARNING_MESSAGE);
                    } else {
                        int id = voznja.getId();
                        LocalDateTime dateTime = voznja.getDatumIvremePorudzbine();
                        String adresaPolaska = tadresaPolaska.getText().trim();
                        String adresaDolaska = tadresaDolaska.getText().trim();
                        String musterija = voznja.getMusterija().getKorisnickoIme();
                        Musterija musterija1 = new Musterija();
                        musterija1.setKorisnickoIme(musterija);
                        dodeljeniVozac.setStatusVozaca(StatusVozacaIautomobila.ZAUZET);
                        ucitavanje.dodavanjeKorisnika();
                        double brojKmPredjenih = voznja.getBrojKMpredjenih();
                        double trajanjeVoznje = voznja.getTrajanjVoznje();
                        StatusVoznje statusVoznje1 = StatusVoznje.DODELJENA;
                        boolean obrisan = voznja.isObrisan();
                        StatusNaruceneVoznje statusNaruceneVoznje = voznja.getStatusNaruceneVoznje();
                        boolean ocenjenVozac = voznja.isOcenjenVozac();
                        String izborMusterijePriNarucivanju = voznja.getIzborMusterijePriNarucivanju();
                        if (voznja == null) {
                            voznja = new NarucivanjeVoznjePrekoTelefona(id, dateTime, adresaPolaska, adresaDolaska, musterija1, dodeljeniVozac, brojKmPredjenih, trajanjeVoznje, statusVoznje1, obrisan, statusNaruceneVoznje, 0,ocenjenVozac,izborMusterijePriNarucivanju);
                        } else {
                            voznja.setId(id);
                            voznja.setDatumIvremePorudzbine(dateTime);
                            voznja.setAdresaPolaska(adresaPolaska);
                            voznja.setAdresaDestinacije(adresaDolaska);
                            voznja.setMusterija(musterija1);
                            voznja.setVozac(dodeljeniVozac);
                            voznja.setBrojKMpredjenih(brojKmPredjenih);
                            voznja.setTrajanjVoznje(trajanjeVoznje);
                            voznja.setStatusVoznje(statusVoznje1);
                            voznja.setObrisan(obrisan);
                            voznja.setStatusNaruceneVoznje(statusNaruceneVoznje);
                        }
                        ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                        JOptionPane.showMessageDialog(null, "Uspesno ste dodelili voznju!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                        ProzorZaDodeljivanjeVoznji.this.setVisible(false);
                        ProzorZaDodeljivanjeVoznji.this.dispose();
                    }
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od dodeljivanja voznje","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                ProzorZaDodeljivanjeVoznji.this.setVisible(false);
                ProzorZaDodeljivanjeVoznji.this.dispose();
            }
        });
    }

    private void popunjavanjePolja(){
        tadresaPolaska.setText(voznja.getAdresaPolaska());
        tadresaDolaska.setText(voznja.getAdresaDestinacije());
        tmusterija.setText(voznja.getMusterija().getKorisnickoIme());
    }
}
