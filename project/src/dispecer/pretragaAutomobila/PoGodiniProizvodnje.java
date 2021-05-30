package dispecer.pretragaAutomobila;

import automobili.Automobil;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoGodiniProizvodnje extends JFrame {
    public JLabel pretragaPoGodiniProizvodnje = new JLabel("Unesi godinu proizvodnje automobila!");
    private JTextField tpretragaPoGodiniProizvodnje = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Automobil automobil;

    public PoGodiniProizvodnje(Liste ucitavanje, Automobil automobil){
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;
        setTitle("Pretraga automobila po godini proizvodnje");
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
        add(pretragaPoGodiniProizvodnje);
        add(tpretragaPoGodiniProizvodnje);
        add(new JLabel());
        add(btnOK, "split 2");
        add(cancel);
    }

    private void initListeners() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosGodineProizvodnjeString = tpretragaPoGodiniProizvodnje.getText().trim();
                    int unosGodineProizvodnje = Integer.parseInt(unosGodineProizvodnjeString);

                    DoublyLinkedList<Automobil> rezultatPretrage = ucitavanje.nadjiAutomobilPoGodiniProizvodnje(unosGodineProizvodnje);

                    if (rezultatPretrage.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Automobil sa godinom proizvodnje (" + unosGodineProizvodnje + ") ne postoji!","GRESKA",JOptionPane.WARNING_MESSAGE);
                    }else {
                        ProzorZaPretraguPoGodiniProizvodnje prozorZaPretraguPoGodiniProizvodnje = new ProzorZaPretraguPoGodiniProizvodnje(rezultatPretrage);
                        prozorZaPretraguPoGodiniProizvodnje.setVisible(true);
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoGodiniProizvodnje.this.setVisible(false);
                PoGodiniProizvodnje.this.dispose();
            }
        });
    }
    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        try {
            Integer.parseInt(tpretragaPoGodiniProizvodnje.getText().trim());
        }catch (NumberFormatException e){
            poruka += "Broj godine proizvodnje vozila mora biti broj!\n";
            ok = false;
        }
        if(tpretragaPoGodiniProizvodnje.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Uneli ste pogresne podatke",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
