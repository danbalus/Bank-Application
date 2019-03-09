/**
 * clasa de tip GUI
 */
package View;

import Model.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @version mai 2017
 * @author Dan
 */
public class AccountGUI extends JFrame{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1089062416294903180L;
	private JPanel panouCentral;
    private JPanel panouStanga;
    private JPanel panouDreapta;
    private JPanel[] pnCont = new JPanel[10];
    
    private JTextField txtID = new JTextField();
    private JTextField txtDate = new JTextField();
    private JTextField txtNrAcc = new JTextField();
    private JTextField txtPrenume = new JTextField();
    private JTextField txtNume = new JTextField();
    private JTextField txtTara = new JTextField();
    private JTextField txtExtrageri = new JTextField();
    private JTextField txtDepuneri = new JTextField();
    private JTextField txtPlati = new JTextField();
    private JTextField txtAdauga = new JTextField();
    private JTextField txtAltCont = new JTextField();
    private JTextField txtT1I1 = new JTextField();
    private JTextField txtT1I2 = new JTextField();
    private JTextField txtT1I3 = new JTextField();
    private JTextField txtT2I1 = new JTextField();
    private JTextField txtT2I2 = new JTextField();
    private JPasswordField pass1 = new JPasswordField();
    private JPasswordField pass2 = new JPasswordField();
    
    private JButton btBack = new JButton("Inapoi");
    private JButton btSet = new JButton("Seteaza");
    private JButton btNewAcc1 = new JButton("Creeaza un Saving Account");
    private JButton btNewAcc2 = new JButton("Creeaza un Spending Account");
    private JButton btOKExtrag = new JButton("OK");
    private JButton btOKDepune = new JButton("OK");
    private JButton btOKPlata = new JButton("OK");
    private JButton btOKAdauga = new JButton("OK");
    private JButton btObtineDobanda = new JButton("Obtine Dobanda");
    
    private JComboBox<String> coBox1 = new JComboBox<String>();
    private JComboBox<String> coBoxExtrageri = new JComboBox<String>();
    private JComboBox<String> coBoxDepuneri = new JComboBox<String>();
    
    public AccountGUI() {
        setTitle("Freastra Account");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));
        
        setVisible(false);
    }
    
	public void init(Bank b) {
		panouCentral = new JPanel();
		panouStanga = new JPanel();
		panouDreapta = new JPanel();
		JPanel panel;
		setContentPane(panouCentral);
		panouCentral.setLayout(new GridLayout(1, 2));
		panouCentral.add(panouStanga);
		panouCentral.add(panouDreapta);
		panouStanga.setLayout(new GridLayout(9, 1));

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" ID:"));
		panel.add(txtID);
		panouStanga.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Creare data cont:"));
		panel.add(txtDate);
		panouStanga.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Prenume:"));
		panel.add(txtPrenume);
		panouStanga.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Nume:"));
		panel.add(txtNume);
		panouStanga.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Tara:"));
		panel.add(txtTara);
		panouStanga.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Parola:"));
		panel.add(pass1);
		panouStanga.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Repeta Parola:"));
		panel.add(pass2);
		panouStanga.add(panel);

		panouStanga.add(new JSeparator());

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(btBack);
		panel.add(btSet);
		panouStanga.add(panel);

		Person p = b.readPerson(Variabile.idLogin);
		txtID.setText(String.valueOf(p.getId()));
		txtTara.setText(p.getCountry());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		txtDate.setText(dateFormat.format(p.getDate()));
		txtPrenume.setText(p.getFirstName());
		txtNume.setText(p.getLastName());
		txtID.setEditable(false);
		txtDate.setEditable(false);
		txtNrAcc.setEditable(false);
		loadCoBox1(b);

		//////////////// dreapta

		panouDreapta.setLayout(new GridLayout(12, 1));

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Nr. Total Conturi:"));
		panel.add(txtNrAcc);
		panouDreapta.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(btNewAcc1);
		panel.add(btNewAcc2);
		panouDreapta.add(panel);

		panouDreapta.add(new JSeparator());

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(new JLabel(" Selecteaza un cont:"));
		panel.add(coBox1);
		panouDreapta.add(panel);

		panouDreapta.add(new JSeparator());

		for (int i = 0; i < 7; i++) {
			pnCont[i] = new JPanel();
			panouDreapta.add(pnCont[i]);
		}

		pack();

		setLocationRelativeTo(null);
		setVisible(true);

		showCont(b);
	}

	public void showCont(Bank bank) {

		if (coBox1.getItemCount() < 2)
			return;

		for (int i = 0; i < 7; i++) {
			pnCont[i].removeAll();
			panouDreapta.remove(pnCont[i]);
		}

		repaint();

		String s = (String) coBox1.getSelectedItem();
		while (true) 
		{
			if (s.length() < 3)
				break;
			s = s.substring(0, 6);
			int id;
			try 
			{
				id = Integer.parseInt(s);
			} catch (Exception ex) 
			{
				System.out.println("Exception parseInt:\n" + ex.toString());
				break;
			}
			Account acc = bank.cautaAccount(id);
			if (acc == null)
				break;
			JPanel panel;
			JTextField txt;
			showBalances(acc);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			panel = new JPanel();
			panel.setLayout(new GridLayout(1, 2));
			panel.add(new JLabel(" Data Creare:"));
			txt = new JTextField(dateFormat.format(acc.getDate()));
			txt.setEditable(false);
			panel.add(txt);
			pnCont[0] = panel;
			if (acc.getTipAccount() == 1) 
			{
				//SavingAccount sa = (SavingAccount) acc;
				int w = 0;

				coBoxExtrageri.removeAllItems();
				ArrayList<Account> lista = bank.getAllAccounts(Variabile.idLogin);
				coBoxExtrageri.addItem("-");
				for (int i = 0; i < lista.size(); i++) {
					String z = String.valueOf(lista.get(i).getId());
					if (lista.get(i).getTipAccount() == 1)
						z += " (Saving)";
					else
						z += " (Spending)";
					if (lista.get(i).getId() != id)
						if (lista.get(i).getTipAccount() == 2)
							coBoxExtrageri.addItem((String) z);
				}

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 2));
				panel.add(new JLabel("Suma Depozitata:"));
				txtT1I1.setEditable(false);
				panel.add(txtT1I1);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 3));
				panel.add(new JLabel("Suma Castigata:"));
				txtT1I2.setEditable(false);
				panel.add(txtT1I2);
				panel.add(btObtineDobanda);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 2));
				panel.add(new JLabel("Bani Actuali:"));
				txtT1I3.setEditable(false);
				panel.add(txtT1I3);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 2));
				panel.add(new JLabel("Fa o Extragere in contul:"));
				panel.add(coBoxExtrageri);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 3));
				panel.add(new JLabel("Suma spre extragere:"));
				panel.add(txtExtrageri);
				panel.add(btOKExtrag);
				pnCont[++w] = panel;

			} else {
			//	SpendingAccount sa = (SpendingAccount) acc;
				int w = 0;

				coBoxDepuneri.removeAllItems();
				ArrayList<Account> lista = bank.getAllAccounts(Variabile.idLogin);
				coBoxDepuneri.addItem("-");
				for (int i = 0; i < lista.size(); i++) {
					String z = String.valueOf(lista.get(i).getId());
					if (lista.get(i).getTipAccount() == 1)
						z += " (Saving)";
					else
						z += " (Spending)";
					if (lista.get(i).getId() != id)
						coBoxDepuneri.addItem((String) z);
				}

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 2));
				panel.add(new JLabel("Bani de cheltuiala:"));
				txtT2I1.setEditable(false);
				panel.add(txtT2I1);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 2));
				panel.add(new JLabel("Total cheltuit:"));
				txtT2I2.setEditable(false);
				panel.add(txtT2I2);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 3));
				panel.add(new JLabel("Adauga in cont:"));
				panel.add(txtAdauga);
				panel.add(btOKAdauga);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 3));
				panel.add(new JLabel("Fa o plata:"));
				panel.add(txtPlati);
				panel.add(btOKPlata);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 3));
				panel.add(new JLabel("Depune in cont:"));
				panel.add(coBoxDepuneri);
				panel.add(txtAltCont);
				pnCont[++w] = panel;

				panel = new JPanel();
				panel.setLayout(new GridLayout(1, 3));
				panel.add(new JLabel("Suma spre depunere:"));
				panel.add(txtDepuneri);
				panel.add(btOKDepune);
				pnCont[++w] = panel;

			}

			break;
		}

		for (int i = 0; i < 7; i++) {
			panouDreapta.add(pnCont[i]);
		}

		setPreferredSize(getSize());
		pack();
	}
    
    public String[] getPersonData(){
        String s[] = new String[5];
        s[0] = txtPrenume.getText();
        s[1] = txtNume.getText();
        s[2] = txtTara.getText();
        s[3] = String.valueOf(pass1.getPassword());
        s[4] = String.valueOf(pass2.getPassword());
        return s;
    }
    
    public void showError(int id, String s){
        if (id == 0)
            JOptionPane.showMessageDialog(this, 
                    s, "Succes!",
                    JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(this,
                    s,"Error!", 
                    JOptionPane.ERROR_MESSAGE );
    }
    
    
	public void loadCoBox1(Bank bank) {

		coBox1.removeAllItems();
		ArrayList<Account> lista = bank.getAllAccounts(Variabile.idLogin);
		coBox1.addItem("-");
		for (int i = 0; i < lista.size(); i++) 
		{
			String s = String.valueOf(lista.get(i).getId());
			if (lista.get(i).getTipAccount() == 1)
				s += " (Saving)";
			else
				s += " (Spending)";
			coBox1.addItem((String) s);
		}
		Person p = bank.readPerson(Variabile.idLogin);
		txtNrAcc.setText(String.valueOf(p.getNrAcc()));
	}
    
	public void setPersonData(String s[]) {
		if (s[0].compareTo("") != 0)
			txtPrenume.setText(s[0]);
		if (s[1].compareTo("") != 0)
			txtNume.setText(s[1]);
		if (s[2].compareTo("") != 0)
			txtTara.setText(s[2]);
		pass1.setText("");
		pass2.setText("");
	}
    
	public void adaugaListenerBtnBack(ActionListener a) {
		btBack.addActionListener(a);
	}

	public void adaugaListenerBtnSet(ActionListener a) {
		btSet.addActionListener(a);
	}

	public void adaugaListenerBtnNewAcc1(ActionListener a) {
		btNewAcc1.addActionListener(a);
	}

	public void adaugaListenerBtnNewAcc2(ActionListener a) {
		btNewAcc2.addActionListener(a);
	}

	public void adaugaListenerBtnOKAdauga(ActionListener a) {
		btOKAdauga.addActionListener(a);
	}

	public void adaugaListenerBtnOKDepune(ActionListener a) {
		btOKDepune.addActionListener(a);
	}

	public void adaugaListenerBtnOKExtrag(ActionListener a) {
		btOKExtrag.addActionListener(a);
	}

	public void adaugaListenerBtnOKPlata(ActionListener a) {
		btOKPlata.addActionListener(a);
	}

	public void adaugaListenerBtnGetInterest(ActionListener a) {
		btObtineDobanda.addActionListener(a);
	}

	public void addCoBox1ItLis(ItemListener it) {
		coBox1.addItemListener(it);
	}

	public void addCoBoxDepune(ItemListener it) {
		coBoxDepuneri.addItemListener(it);
	}
    
    public void adaugaWindowListener(WindowListener w){
        this.addWindowListener(w);
    }

	public void scrieContDepuneri() {
		String z = "";
		String s = (String) coBoxDepuneri.getSelectedItem();
		if (s.length() > 5)
			z = s.substring(0, 6);
		txtAltCont.setText(z);
	}
    
	public int getAccount(Bank b) {
		try {
			String s = (String) coBox1.getSelectedItem();
			s = s.substring(0, 6);
			int nr = Integer.parseInt(s);
			Account a = b.cautaAccount(nr);
			return a.getId();
		} catch (Exception ex) {
			return -1;
		}
	}
    
	public String[] getBaniAdauga() {
		String[] s = new String[3];
		s[0] = (String) coBox1.getSelectedItem();
		if (s[0].length() > 6)
			s[0] = s[0].substring(0, 6);
		s[1] = txtAdauga.getText();
		s[2] = s[0];
		return s;
	}

	public String[] getBaniPlata() {
		String[] s = new String[3];
		s[0] = (String) coBox1.getSelectedItem();
		if (s[0].length() > 6)
			s[0] = s[0].substring(0, 6);
		s[1] = txtPlati.getText();
		s[2] = s[0];
		return s;
	}

	public String[] getBaniExtrageri() {
		String[] s = new String[3];
		s[0] = (String) coBox1.getSelectedItem();
		if (s[0].length() > 6)
			s[0] = s[0].substring(0, 6);
		s[1] = txtExtrageri.getText();
		s[2] = (String) coBoxExtrageri.getSelectedItem();
		if (s[2].length() > 6)
			s[2] = s[2].substring(0, 6);
		return s;
	}

	public String[] getBaniDepuneri() {
		String[] s = new String[3];
		s[0] = (String) coBox1.getSelectedItem();
		if (s[0].length() > 6)
			s[0] = s[0].substring(0, 6);
		s[1] = txtDepuneri.getText();
		s[2] = txtAltCont.getText();
		return s;
	}

	public void showBalances(Account acc) {
		if (acc.getTipAccount() == 1)
		{
			SavingAccount sa = (SavingAccount) acc;
			txtT1I1.setText(String.valueOf(acc.getBalance() - sa.getTotalInterests()));
			txtT1I2.setText(String.valueOf(sa.getTotalInterests()));
			txtT1I3.setText(String.valueOf(acc.getBalance()));
			txtExtrageri.setText("");
		}
		else 
		{
			SpendingAccount sa = (SpendingAccount) acc;
			txtT2I1.setText(String.valueOf(acc.getBalance()));
			txtT2I2.setText(String.valueOf(sa.getBalanceSpended()));
			txtDepuneri.setText("");
			txtPlati.setText("");
			txtAdauga.setText("");
		}
	}

}
