package dispecer.pretragaAutomobila;

import automobili.Automobil;
import dispecer.pretragaVozaca.Kombinovana;
import dispecer.pretragaVozaca.ProzorZaPrikazKombinovanePretrage;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KombinovanaAutomobili extends JFrame {

    private JLabel model = new JLabel("Unesi model");
    private JTextField tmodel = new JTextField(20);
    private JLabel proizvodjac = new JLabel("Unesi proizvodjaca");
    private JTextField tproizvodjac = new JTextField(20);
    private JLabel godinaProizvodnje = new JLabel("Unesi godinu proizvodnje");
    private JTextField tgodinaProizvodnje = new JTextField(20);
    private JLabel brojRegistarskeOznake = new JLabel("Unesi broj registarske oznake");
    private JTextField tbrojRegistarskeOznake = new JTextField(20);
    private JLabel brojTaksiVozila = new JLabel("Unesi broj taksi vozila");
    private JTextField tbrojTaksiVozila = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Automobil automobil;

    public KombinovanaAutomobili(Liste ucitavanje, Automobil automobil){
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;
        setTitle("Kombinovana prtraga automobila");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGui();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }


    private void initGui() {
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
        add(new JLabel());
        add(btnOK, "split 2");
        this.getRootPane().setDefaultButton(btnOK);
        add(cancel);
    }

    private void initListeners() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosModel = tmodel.getText().trim();
                    String unosProizvodjac = tproizvodjac.getText().trim();
                    String unosbrojRegistarskeOznake = tbrojRegistarskeOznake.getText().trim();
                    String unosGodinaProizvodnjeString = tgodinaProizvodnje.getText().trim();
                    int unosGodinaProizvodnje = Integer.parseInt(unosGodinaProizvodnjeString);
                    String unosBrojaTaksiVozilaString = tbrojTaksiVozila.getText().trim();
                    int unosBrojaTaksiVozila = Integer.parseInt(unosBrojaTaksiVozilaString);

                    DoublyLinkedList<Automobil> rezultatKombinovanePretrageAutomobila = ucitavanje.rezultatKombinovanePretrageAutomobili(unosModel, unosProizvodjac, unosGodinaProizvodnje, unosBrojaTaksiVozila, unosbrojRegistarskeOznake);

                    if(rezultatKombinovanePretrageAutomobila.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Automobil sa unesenim podacima ne postoji!","Greska",JOptionPane.WARNING_MESSAGE);
                    }else{
                        ProzorZaKombinovanuPretraguAutomobili prozorZaKombinovanuPretraguAutomobili = new ProzorZaKombinovanuPretraguAutomobili(rezultatKombinovanePretrageAutomobila);
                        prozorZaKombinovanuPretraguAutomobili.setVisible(true);
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                KombinovanaAutomobili.this.setVisible(false);
                KombinovanaAutomobili.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku\n";
        if (tmodel.getText().trim().equals("")){
            poruka += "Polje za model ne sme biti prazno!\n";
        }
        if (tproizvodjac.getText().trim().equals("")){
            poruka += "Polje za proizvodjaca ne sme biti prazno!\n";
        }
        if (tbrojRegistarskeOznake.getText().trim().equals("")){
            poruka += "Polje za broj registarske oznake ne sme biti prazno!\n";
        }
        try{
            Double.parseDouble(tgodinaProizvodnje.getText().trim());
        }catch (NumberFormatException e){
            poruka += "Godina proizvodnje mora biti broj! \n";
            ok = false;
        }
        try{
            Double.parseDouble(tbrojTaksiVozila.getText().trim());
        }catch (NumberFormatException e){
            poruka += "Broj taksi vozila mora biti broj! \n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Greska",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }

}
