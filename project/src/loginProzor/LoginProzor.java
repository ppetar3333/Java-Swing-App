package loginProzor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

import dispecer.MogucnostiDispecera;
import musterija.MogucnostiMusterije;
import net.miginfocom.swing.MigLayout;
import osobe.Dispecar;
import osobe.Musterija;
import osobe.Vozac;
import liste.Liste;
import taksiSluzba.TaksiSluzba;
import vozac.MogucnostiVozaca;

public class LoginProzor extends JFrame{

	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOK;
	private JButton btnCancel;
    private Liste ucitavanje;

    public LoginProzor(Liste ucitavanje) {
        this.ucitavanje = ucitavanje;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();
		setLocationRelativeTo(null);
    }
    private void initGUI() {

		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);

		this.lblPoruka = new JLabel("Dobrodošli. Molimo Vas da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisničko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Šifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");

		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
    }
    private void initActions() {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String korisnickoIme = txtKorisnickoIme.getText().trim();
                String sifra = new String(pfSifra.getPassword()).trim();

				try {
					FileWriter fw = new FileWriter("src/fajlovi/ulogovanKorisnik.txt",false);
					fw.write(korisnickoIme);
					fw.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}

				Vozac prijavljenVozac = ucitavanje.loginVozac(korisnickoIme, sifra);
				Musterija prijavljenMusterija = ucitavanje.loginMusterija(korisnickoIme, sifra);
				Dispecar prijavljenDispecar = ucitavanje.loginDispecar(korisnickoIme, sifra);

				if (prijavljenMusterija == null && prijavljenVozac == null && prijavljenDispecar == null) {
                    JOptionPane.showMessageDialog(null, "Neispravni login podaci", "Greska", JOptionPane.WARNING_MESSAGE);
                }
				else {
					LoginProzor.this.dispose();
					LoginProzor.this.setVisible(false);
                	JOptionPane.showMessageDialog(null, "Uspesno ste se prijavili!", "Uspesna prijava", JOptionPane.INFORMATION_MESSAGE);
                }

				if(prijavljenMusterija instanceof Musterija){
					JOptionPane.showMessageDialog(null,   "Uspesno ste se ulogovali kao musterija", "Musterija", JOptionPane.INFORMATION_MESSAGE);
					MogucnostiMusterije mogucnostiMusterije = new MogucnostiMusterije(ucitavanje,prijavljenMusterija);
					mogucnostiMusterije.setVisible(true);
				}else if(prijavljenDispecar instanceof Dispecar){
					JOptionPane.showMessageDialog(null, "Uspesno ste se ulogovali kao dispecer", "Dispecer", JOptionPane.INFORMATION_MESSAGE);
					MogucnostiDispecera mogucnostiDispecera = new MogucnostiDispecera(ucitavanje,prijavljenDispecar);
					mogucnostiDispecera.setVisible(true);
				}else if(prijavljenVozac instanceof Vozac){
					JOptionPane.showMessageDialog(null, "Uspesno ste se ulogovali kao vozac", "Vozac", JOptionPane.INFORMATION_MESSAGE);
					MogucnostiVozaca mogucnostiVozaca = new MogucnostiVozaca(ucitavanje,prijavljenVozac);
					mogucnostiVozaca.setVisible(true);
				}
            }
        });
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Uspesno ste izasli iz aplikacije", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
				LoginProzor.this.setVisible(false);
				LoginProzor.this.dispose();
			}
		});
    }
}
