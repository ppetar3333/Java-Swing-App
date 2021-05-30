package dispecer.pretragaVozaca;

import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoPrezimenu extends JFrame{

    private JLabel pretragaPoPrezimenu = new JLabel("Unesi prezime");
    private JTextField tpretragaPoPrezimenu = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Vozac vozac;

    public PoPrezimenu(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Pretraga Vozaca Po Prezimenu");
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
        add(pretragaPoPrezimenu);
        add(tpretragaPoPrezimenu);
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
                    String unosPrezimena = tpretragaPoPrezimenu.getText().trim();

                    DoublyLinkedList<Vozac> rezultatPretrage = ucitavanje.nadjiVozacaPoPrezimenu(unosPrezimena);

                    if(rezultatPretrage.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Vozac sa prezimenom (" + unosPrezimena + ") ne postoji!","Greska",JOptionPane.WARNING_MESSAGE);
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
                PoPrezimenu.this.setVisible(false);
                PoPrezimenu.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        if(tpretragaPoPrezimenu.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Napravili ste gresku",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
