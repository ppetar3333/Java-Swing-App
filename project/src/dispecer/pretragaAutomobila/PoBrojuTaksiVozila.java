package dispecer.pretragaAutomobila;

import automobili.Automobil;
import liste.Liste;
import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoBrojuTaksiVozila extends JFrame {

    public JLabel pretragaPoBrojuTaksiVozila = new JLabel("Unesi broj taksi vozila");
    private JTextField tpretragaPoBrojuTaksiVozila = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Automobil automobil;

    public PoBrojuTaksiVozila(Liste ucitavanje, Automobil automobil){
        this.ucitavanje = ucitavanje;
        this.automobil = automobil;
        setTitle("Pretraga automobila po broju taksi vozila");
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
        add(pretragaPoBrojuTaksiVozila);
        add(tpretragaPoBrojuTaksiVozila);
        add(new JLabel());
        add(btnOK, "split 2");
        add(cancel);
    }

    private void initListeners() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validacija() == true){
                    String unosBrojaTaksiVozilaString = tpretragaPoBrojuTaksiVozila.getText().trim();
                    int unosBrojaTaksiVozila = Integer.parseInt(unosBrojaTaksiVozilaString);

                    DoublyLinkedList<Automobil> rezultatPretrage = ucitavanje.nadjiAutomobilPoBrojuTaksiVozila(unosBrojaTaksiVozila);

                    if (rezultatPretrage.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Automobil sa brojem taksi vozila (" + unosBrojaTaksiVozila + ") ne postoji!","GRESKA",JOptionPane.WARNING_MESSAGE);
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
                PoBrojuTaksiVozila.this.setVisible(false);
                PoBrojuTaksiVozila.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        try {
            Integer.parseInt(tpretragaPoBrojuTaksiVozila.getText().trim());
        }catch (NumberFormatException e){
            poruka += "Broj taksi vozila mora biti broj!\n";
            ok = false;
        }
        if(tpretragaPoBrojuTaksiVozila.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Uneli ste pogresne podatke",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
