package dispecer.pretragaVozaca;

import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kombinovana extends JFrame {

    private JLabel ime = new JLabel("Unesi ime");
    private JLabel prezime = new JLabel("Unesi prezime");
    private JLabel automobil = new JLabel("Unesi model automobila");
    private JLabel plata = new JLabel("Unesi platu");
    private JTextField time = new JTextField(20);
    private JTextField tprezime = new JTextField(20);
    private JTextField tautomobil = new JTextField(20);
    private JTextField tplata = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Vozac vozac;

    public Kombinovana(Liste ucitavanje,Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Kombinovana Pretraga");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initGui();
        initListeners();
        pack();
        setLocationRelativeTo(null);
    }

    private void initGui(){
        MigLayout layout = new MigLayout("wrap 2");
        setLayout(layout);
        add(ime);
        add(time);
        add(prezime);
        add(tprezime);
        add(plata);
        add(tplata);
        add(automobil);
        add(tautomobil);
        add(new JLabel());
        add(btnOK, "split 2");
        this.getRootPane().setDefaultButton(btnOK);
        add(cancel);
    }

    private void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validacija() == true){

                    String unosIme = time.getText().trim();
                    String unosPrezime = tprezime.getText().trim();
                    String unosModelAutomobila = tautomobil.getText().trim();
                    String unosPlataString = tplata.getText().trim();
                    double unosPlata = Double.parseDouble(unosPlataString);

                    DoublyLinkedList<Vozac> rezultatKombinovanePretrage = ucitavanje.rezultatKombinovanePretrage(unosIme,unosPrezime,unosModelAutomobila,unosPlata);

                    if(rezultatKombinovanePretrage.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Vozac sa unesenim podacima ne postoji!","Greska",JOptionPane.WARNING_MESSAGE);
                    }else{
                        ProzorZaPrikazKombinovanePretrage prozorZaPrikazKombinovanePretrage = new ProzorZaPrikazKombinovanePretrage(rezultatKombinovanePretrage);
                        prozorZaPrikazKombinovanePretrage.setVisible(true);
                    }

                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                Kombinovana.this.setVisible(false);
                Kombinovana.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        if(tplata.getText().trim().equals("")){
            poruka += "Polje za platu ne sme biti prazno!\n";
        }
        try{
            Double.parseDouble(tplata.getText().trim());
        }catch (NumberFormatException e){
            poruka += "Plata mora biti broj! \n";
            ok = false;
        }
        if(tautomobil.getText().equals("")){
            poruka += "Polje za automobil ne sme biti prazno!\n";
            ok = false;
        }
        if(time.getText().equals("")){
            poruka += "Polje za ime ne sme biti prazno! \n";
            ok = false;
        }
        if(tprezime.getText().equals("")){
            poruka += "Polje za prezime ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Greska",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
