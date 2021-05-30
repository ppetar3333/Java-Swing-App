package dispecer.pretragaVozaca;

import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoImenu extends JFrame{

    private JLabel pretragaPoImenu = new JLabel("Unesi ime");
    private JTextField tpretragaPoImenu = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Vozac vozac;

    public PoImenu(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Pretraga Vozaca Po Imenu");
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
        add(pretragaPoImenu);
        add(tpretragaPoImenu);
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
                    String unosImena = tpretragaPoImenu.getText().trim();

                    DoublyLinkedList<Vozac> rezultatPretrage = ucitavanje.nadjiVozacaPoImenu(unosImena);

                    if(rezultatPretrage.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Vozac sa imenom (" + unosImena + ") ne postoji!","Greska",JOptionPane.WARNING_MESSAGE);
                    }else{
                        ProzorZaPrikazRezultataPretragePoImenu prozor = new ProzorZaPrikazRezultataPretragePoImenu(rezultatPretrage);
                        prozor.setVisible(true);
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoImenu.this.setVisible(false);
                PoImenu.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku prilikom unosa!\n";
        if(tpretragaPoImenu.getText().equals("")){
            poruka += "Polje ne sme biti prazno! \n ";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
