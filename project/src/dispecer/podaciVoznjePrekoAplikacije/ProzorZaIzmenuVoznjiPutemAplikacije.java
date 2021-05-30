package dispecer.podaciVoznjePrekoAplikacije;

import enumi.StatusNaruceneVoznje;
import enumi.StatusVoznje;
import liste.Liste;
import main.TaxiSluzbaMain;
import musterija.narucivanjeVoznjePrekoAplikacije.NarucivanjeVoznjePrekoAplikacije;
import net.miginfocom.swing.MigLayout;
import osobe.Musterija;
import osobe.Vozac;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProzorZaIzmenuVoznjiPutemAplikacije extends JFrame {

    private JLabel datumIVremePorudzbine = new JLabel("Datum i vreme porudzbine");
    private JTextField tdatumIVremePorudzbine = new JTextField(20);
    private JLabel adresaPolaska = new JLabel("Adresa polaska");
    private JTextField tadresaPolaska = new JTextField(20);
    private JLabel adresaDestinacije = new JLabel("Adresa destinacije");
    private JTextField tadresaDestinacije = new JTextField(20);
    private JLabel musterija = new JLabel("Musterija");
    private JTextField tmusterija = new JTextField(20);
    private JLabel vozac = new JLabel("Vozac");
    private JTextField tvozac = new JTextField(20);
    private JLabel brojPredjenihKilometara = new JLabel("Broj predjenih kilometara");
    private JTextField tbrojPredjenihKilometara = new JTextField(20);
    private JLabel trajanjeVoznje = new JLabel("Trajanje voznje");
    private JTextField ttrajanjeVoznje = new JTextField(20);
    private JLabel statusVoznje = new JLabel("Status voznje");
    private JComboBox<StatusVoznje> statusVoznjeJComboBox= new JComboBox<StatusVoznje>(StatusVoznje.values());
    private JLabel napomena = new JLabel("Napomena");
    private JTextField tnapomena = new JTextField(20);

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private Liste ucitavanje;
    private NarucivanjeVoznjePrekoAplikacije voznja;

    public ProzorZaIzmenuVoznjiPutemAplikacije(Liste ucitavanje, NarucivanjeVoznjePrekoAplikacije voznja){
        this.ucitavanje = ucitavanje;
        this.voznja = voznja;
        setTitle("Izmena voznji kreiranih putem aplikacije");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initGUI();
        initListeners();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(datumIVremePorudzbine);
        add(tdatumIVremePorudzbine);
        add(adresaPolaska);
        add(tadresaPolaska);
        add(adresaDestinacije);
        add(tadresaDestinacije);
        add(musterija);
        add(tmusterija);
        add(vozac);
        add(tvozac);
        add(brojPredjenihKilometara);
        add(tbrojPredjenihKilometara);
        add(trajanjeVoznje);
        add(ttrajanjeVoznje);
        add(statusVoznje);
        add(statusVoznjeJComboBox);
        add(napomena);
        add(tnapomena);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);

        tmusterija.setEditable(false);
        tvozac.setEditable(false);

        if (this.voznja != null){
            popunjavanjeTextField();
        }
    }


    private void initListeners() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveraUnetihPodataka() == true){
                    int id = voznja.getId();
                    LocalDateTime datumIVremePorudzbine = voznja.getDatumIvremePorudzbine();
                    String adresaPolaska = tadresaPolaska.getText().trim();
                    String adresaDestinacije = tadresaDestinacije.getText().trim();
                    String musterija = voznja.getMusterija().getKorisnickoIme();
                    Musterija musterija1 = new Musterija();
                    musterija1.setKorisnickoIme(musterija);
                    Vozac vozac = voznja.getVozac();
                    vozac.getKorisnickoIme();
                    double brojPredjenihKilometara = Double.parseDouble(tbrojPredjenihKilometara.getText().trim());
                    double trajanjeVoznje = Double.parseDouble(ttrajanjeVoznje.getText().trim());
                    StatusVoznje statusVoznje = (StatusVoznje) statusVoznjeJComboBox.getSelectedItem();
                    String napomena = tnapomena.getText().trim();
                    boolean obrisan = voznja.isObrisan();
                    StatusNaruceneVoznje statusNaruceneVoznje = voznja.getStatusNaruceneVoznje();
                    double cenaVoznje = voznja.getCenaVoznje();
                    boolean ocenjenVozac = voznja.isOcenjenVozac();
                    String izborMusterije = voznja.getIzborMusterijePriNarucivanju();

                    if (voznja == null){
                        voznja = new NarucivanjeVoznjePrekoAplikacije(id,datumIVremePorudzbine,adresaPolaska,adresaDestinacije,musterija1,vozac,brojPredjenihKilometara,trajanjeVoznje,statusVoznje,obrisan,statusNaruceneVoznje,cenaVoznje,ocenjenVozac,izborMusterije,napomena);
                    } else {
                        voznja.setId(id);
                        voznja.setDatumIvremePorudzbine(datumIVremePorudzbine);
                        voznja.setAdresaPolaska(adresaPolaska);
                        voznja.setAdresaDestinacije(adresaDestinacije);
                        voznja.setMusterija(musterija1);
                        voznja.setVozac(vozac);
                        voznja.setBrojKMpredjenih(brojPredjenihKilometara);
                        voznja.setTrajanjVoznje(trajanjeVoznje);
                        voznja.setStatusVoznje(statusVoznje);
                        voznja.setNapomena(napomena);
                        voznja.setObrisan(obrisan);
                        voznja.setStatusNaruceneVoznje(statusNaruceneVoznje);
                    }
                    ucitavanje.snimanjeVoznji(TaxiSluzbaMain.VOZNJE_FAJL);
                    JOptionPane.showMessageDialog(null,"Uspesno ste izmenili voznju!","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                    ProzorZaIzmenuVoznjiPutemAplikacije.this.setVisible(false);
                    ProzorZaIzmenuVoznjiPutemAplikacije.this.dispose();
                }
            }
        });
    }

    private void popunjavanjeTextField() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        tdatumIVremePorudzbine.setText(voznja.getDatumIvremePorudzbine().format(formatter));
        tadresaPolaska.setText(voznja.getAdresaPolaska());
        tadresaDestinacije.setText(voznja.getAdresaDestinacije());
        tmusterija.setText(voznja.getMusterija().getKorisnickoIme());
        tvozac.setText(voznja.getVozac().getKorisnickoIme());
        tbrojPredjenihKilometara.setText(String.valueOf(voznja.getBrojKMpredjenih()));
        ttrajanjeVoznje.setText(String.valueOf(voznja.getTrajanjVoznje()));
        statusVoznjeJComboBox.setSelectedItem(voznja.getStatusVoznje());
        tnapomena.setText(voznja.getNapomena());
    }

    private boolean proveraUnetihPodataka(){
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n\n";

        if (tdatumIVremePorudzbine.getText().equals("")){
            obavestenjeZaGresku += "Polje za datum i vreme porudzbine ne sme ostati prazno! \n";
            ok = false;
        }
        //TODO
        if(ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Morate popuniti polja!", JOptionPane.WARNING_MESSAGE);
        }
        return ok;

    }



}
