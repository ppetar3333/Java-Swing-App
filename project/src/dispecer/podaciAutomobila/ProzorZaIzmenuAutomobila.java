package dispecer.podaciAutomobila;

import automobili.Automobil;
import enumi.StatusVozacaIautomobila;
import enumi.VrstaVozila;
import main.TaxiSluzbaMain;
import net.miginfocom.swing.MigLayout;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProzorZaIzmenuAutomobila extends JFrame {

    private JLabel model = new JLabel("Model");
    private JTextField tmodel = new JTextField(20);
    private JLabel proizvodjac = new JLabel("Proizvodjac");
    private JTextField tproizvodjac = new JTextField(20);
    private JLabel godinaProizvodnje = new JLabel("Godina proizvodnje");
    private JTextField tgodinaProizvodnje = new JTextField(20);
    private JLabel brojRegistarskeOznake = new JLabel("Broj registarske oznake");
    private JTextField tbrojRegistarskeOznake = new JTextField(20);
    private JLabel brojTaksiVozila = new JLabel("Broj taksi vozila");
    private JTextField tbrojTaksiVozila = new JTextField(20);
    private JLabel vrstaVozila = new JLabel("Vrsta vozila");
    private JComboBox<VrstaVozila> vrstaVozilaJComboBox = new JComboBox<VrstaVozila>(VrstaVozila.values());
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private Liste ucitavanje;
    private Automobil automobil;
    
    public ProzorZaIzmenuAutomobila(Liste ucitavanje, Automobil automobil){
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;
        setTitle("Izmena automobila");
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
        add(model);
        add(tmodel);
        add(proizvodjac);
        add(tproizvodjac);
        add(godinaProizvodnje);
        add(tgodinaProizvodnje);
        add(brojRegistarskeOznake);
        add(tbrojRegistarskeOznake);
        add(brojTaksiVozila);
        add(tbrojTaksiVozila);
        add(vrstaVozila);
        add(vrstaVozilaJComboBox);
        add(new JLabel());
        add(btnOk, "split 2");
        add(btnCancel);

        if(this.automobil != null){
            popunjavanjeTextField();
        }

    }

    private void initListeners() {
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (proveraUnetihPodataka() == true){
                    int id = automobil.getId();
                    String model = tmodel.getText().trim();
                    String proizvodjac = tproizvodjac.getText().trim();
                    int godinaProizvodnje = Integer.parseInt(tgodinaProizvodnje.getText().trim());
                    String brojRegistarskeOznake = tbrojRegistarskeOznake.getText().trim();
                    int brojTaksiVozila = Integer.parseInt(tbrojTaksiVozila.getText().trim());
                    VrstaVozila vrstaVozila = (VrstaVozila) vrstaVozilaJComboBox.getSelectedItem();
                    boolean obrisan = automobil.isObrisan();
                    StatusVozacaIautomobila statusVozacaIautomobila = automobil.getStatusAutomobila();
                    boolean petFriendly = automobil.isPetFriendly();
                    if (automobil == null){
                        automobil = new Automobil(id,model,proizvodjac,godinaProizvodnje,brojRegistarskeOznake,brojTaksiVozila,vrstaVozila,obrisan, statusVozacaIautomobila,petFriendly);
                    }else{
                        automobil.setId(id);
                        automobil.setModel(model);
                        automobil.setProizvodjac(proizvodjac);
                        automobil.setGodinaProizvodnje(godinaProizvodnje);
                        automobil.setRegistarskiBroj(brojRegistarskeOznake);
                        automobil.setBrojVozila(brojTaksiVozila);
                        automobil.setVrstaVozila(vrstaVozila);
                        automobil.setObrisan(obrisan);
                        automobil.setPetFriendly(petFriendly);
                        automobil.setStatusAutomobila(statusVozacaIautomobila);
                    }
                    ucitavanje.snimanjeAutomobila(TaxiSluzbaMain.AUTOMOBILI_FAJL);
                    JOptionPane.showMessageDialog(null, "Automobil je uspesno izmenjen!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
                    ProzorZaIzmenuAutomobila.this.setVisible(false);
                    ProzorZaIzmenuAutomobila.this.dispose();
                }
            }
        });
    }

    private void popunjavanjeTextField() {
        tmodel.setText(automobil.getModel());
        tproizvodjac.setText(automobil.getProizvodjac());
        tgodinaProizvodnje.setText(String.valueOf(automobil.getGodinaProizvodnje()));
        tbrojRegistarskeOznake.setText(automobil.getRegistarskiBroj());
        tbrojTaksiVozila.setText(String.valueOf(automobil.getBrojVozila()));
        vrstaVozilaJComboBox.setSelectedItem(automobil.getVrstaVozila());
    }

    private boolean proveraUnetihPodataka() {
        boolean ok = true;
        String obavestenjeZaGresku = "Napravili ste neke greske pri unosu, molimo vas ispravite! \n";

        if (tmodel.getText().trim().equals("")) {
            obavestenjeZaGresku += "Morate uneti model automobila!\n";
            ok = false;
        }
        if (tproizvodjac.getText().trim().equals("")) {
            obavestenjeZaGresku += "Morate uneti proizvodjaca za automobil!\n";
            ok = false;
        }
        if(tgodinaProizvodnje.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za godinu proizvodnje ne sme biti prazno!\n";
        }
        try {
            Integer.parseInt(tgodinaProizvodnje.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Godina proizvodnje mora biti broj!\n";
            ok = false;
        }
        if (tbrojRegistarskeOznake.getText().trim().equals("")){
            obavestenjeZaGresku += "Morate uneti broj registarske oznake! \n";
            ok = false;
        }
        if(tbrojTaksiVozila.getText().trim().equals("")){
            obavestenjeZaGresku += "Polje za broj taksi vozila ne sme biti prazno!\n";
        }
        try {
            Integer.parseInt(tbrojTaksiVozila.getText().trim());
        } catch (NumberFormatException e) {
            obavestenjeZaGresku += "Broj taksi vozila mora biti broj!\n";
            ok = false;
        }
        if (ok == false) {
            JOptionPane.showMessageDialog(null, obavestenjeZaGresku, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }


}
