package dispecer.pretragaVozaca;

import liste.doublyLinkedList.DoublyLinkedList;
import net.miginfocom.swing.MigLayout;
import osobe.Vozac;
import liste.Liste;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PoAutomobilu extends JFrame {

    private JLabel pretragaPoAutomobilu = new JLabel("Unesi model automobila");
    private JTextField tpretragaPoAutomobilu = new JTextField(20);
    private JButton btnOK = new JButton("Pretrazi");
    private JButton cancel = new JButton("Odustani");

    private Liste ucitavanje;
    private Vozac vozac;

    public PoAutomobilu(Liste ucitavanje, Vozac vozac){
        this.ucitavanje = ucitavanje;
        this.vozac = vozac;
        setTitle("Pretraga Vozaca Po Automobilu");
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
        add(pretragaPoAutomobilu);
        add(tpretragaPoAutomobilu);
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

                    String unosModela = tpretragaPoAutomobilu.getText().trim();

                    DoublyLinkedList<Vozac> rezultatPretrage = ucitavanje.nadjiVozacaPoAutomobilu(unosModela);

                    if(rezultatPretrage.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Korisnik sa modelom automobila (" + unosModela + ") ne postoji!","Greska",JOptionPane.WARNING_MESSAGE);
                    }else{
                        ProzorZaPrikazRezultataPretragePoAutomobilu prozor = new ProzorZaPrikazRezultataPretragePoAutomobilu(rezultatPretrage);
                        prozor.setVisible(true);
                    }
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Uspesno ste odustali od pretrage","Uspesno",JOptionPane.INFORMATION_MESSAGE);
                PoAutomobilu.this.setVisible(false);
                PoAutomobilu.this.dispose();
            }
        });
    }

    private boolean validacija(){
        boolean ok = true;
        String poruka = "Napravili ste gresku! \n";
        if(tpretragaPoAutomobilu.getText().equals("")){
            poruka += "Polje ne sme biti prazno!\n";
            ok = false;
        }
        if(ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Greska",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
