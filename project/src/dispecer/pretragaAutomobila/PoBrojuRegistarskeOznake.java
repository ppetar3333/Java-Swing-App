package dispecer.pretragaAutomobila;

import automobili.Automobil;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoBrojuRegistarskeOznake extends JFrame {

    public JLabel pretragaPoBrojuRegistarskeOznake = new JLabel("Unesi broj registarske oznake automobila");
    private JTextField tpretragaPoBrojuRegistarskeOznake = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Automobil automobil;

    public PoBrojuRegistarskeOznake(Liste ucitavanje, Automobil automobil){
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;
        setTitle("Pretraga automobila po broju registarske oznake");
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
        add(pretragaPoBrojuRegistarskeOznake);
        add(tpretragaPoBrojuRegistarskeOznake);
        add(new JLabel());
        add(btnOK, "split 2");
        add(cancel);
    }

    private  void initListeners(){
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosBrojaRegistarskeOznake = tpretragaPoBrojuRegistarskeOznake.getText().trim();

                    DoublyLinkedList<Automobil> rezultatPretrage = ucitavanje.nadjiAutomobilPoBrojuRegistarskeOznake(unosBrojaRegistarskeOznake);

                    if (rezultatPretrage.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Automobil sa registarskom oznakom (" + unosBrojaRegistarskeOznake + ") ne postoji!","GRESKA",JOptionPane.WARNING_MESSAGE);
                    }else {
                        ProzorZaPretraguPoBrojuRegistarskeOznake prozorZaPretraguPoBrojuRegistarskeOznake = new ProzorZaPretraguPoBrojuRegistarskeOznake(rezultatPretrage);
                        prozorZaPretraguPoBrojuRegistarskeOznake.setVisible(true);
                    }
                }
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoBrojuRegistarskeOznake.this.setVisible(false);
                PoBrojuRegistarskeOznake.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        if(tpretragaPoBrojuRegistarskeOznake.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Uneli ste pogresne podatke",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
