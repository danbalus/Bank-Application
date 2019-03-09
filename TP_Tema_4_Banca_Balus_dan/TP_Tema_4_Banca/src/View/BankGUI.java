/**
 * clasa de tip GUI.
 */
package View;

import Model.Bank;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 * @version mai 2017
 * @author Dan
 */
public class BankGUI extends JFrame{
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 358073909179566788L;
	private JPanel panelCentral;
    private JPanel pnSus;
    private JScrollPane scroolPanelJos;
    
    private int nrAles;
    
    private ButtonGroup bgroup = new ButtonGroup();
    
    private JRadioButton radio1 = new JRadioButton("Arata toate persoanele");
    private JRadioButton radio2 = new JRadioButton("SArata toate conturile");
    private JRadioButton radio3 = new JRadioButton("Arata toate tranzactiile");
    
    private JButton butonInapoi = new JButton("Inapoi");
    private JButton butonSterge1 = new JButton("Sterge");
    private JButton butonSterge2 = new JButton("Sterge");
    private JButton butonSterge3 = new JButton("Sterge");
    private JButton butonStergeTot = new JButton("Sterge TOT");
    private JTable tablou;
    
    private DefaultTableModel defTableModel;
    
    public BankGUI() {
        setTitle("Fereastra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 700));
        
        setVisible(false);
    }
    
    public void init(Bank bank){
		panelCentral = new JPanel();
		pnSus = new JPanel();
		// pnJos = new JPanel();
		scroolPanelJos = new JScrollPane();
		JPanel panel;
        setContentPane(panelCentral);
        panelCentral.setLayout(new GridLayout(2, 1));
        panelCentral.add(pnSus);
        panelCentral.add(scroolPanelJos);
        bgroup.add(radio1);
        bgroup.add(radio2);
        bgroup.add(radio3);
                
        pnSus.setLayout(new GridLayout(4, 1));
        //pnJos.setLayout(new GridLayout(1,1));
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(radio1);
        panel.add(butonSterge1);
        panel.add(new JLabel());
        pnSus.add(panel);   
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(radio2);
        panel.add(butonSterge2);
        panel.add(new JLabel());
        pnSus.add(panel);   
        
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(radio3);
		panel.add(butonSterge3);
		panel.add(butonStergeTot);
		pnSus.add(panel);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		panel.add(new JLabel());
		panel.add(butonInapoi);
		panel.add(new JLabel());
		pnSus.add(panel);
        
        
        butonSterge1.setEnabled(false);
        butonSterge2.setEnabled(false);
        butonSterge3.setEnabled(false);
        butonStergeTot.setEnabled(false);
        
        nrAles =0;
        
        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
        showJos(bank, false);
    }
    
	public void showJos(Bank bank, boolean fortat) {
		int nrVechiAles = nrAles;
		nrAles = 0;
		if (radio1.isSelected())
			nrAles = 1;
		else if (radio2.isSelected())
			nrAles = 2;
		else if (radio3.isSelected())
			nrAles = 3;
		if (nrAles == nrVechiAles)
			if (!fortat)
				return;
		butonSterge1.setEnabled(false);
		butonSterge2.setEnabled(false);
		butonSterge3.setEnabled(false);
		butonStergeTot.setEnabled(false);
		tablou = new JTable();
		if (nrAles == 0)
			return;
		repaint();

		if (nrAles == 1) 
		{
			butonSterge1.setEnabled(true);

			ArrayList<Object[]> lista = bank.getPersons();
			defTableModel = new DefaultTableModel(new Object[][] {}, new String[]
					{ "ID Persoana", "Data Creare",
							"Prenume", "Nume", "Tara", "Parola", "Numar conturi" });
			tablou.setModel(defTableModel);
			for (int i = 0; i < lista.size(); i++)
			{
				defTableModel.addRow(lista.get(i));
			}

		} 
		else if (nrAles == 2) 
		{
			butonSterge2.setEnabled(true);

			ArrayList<Object[]> lista = bank.getAccounts();
			defTableModel = new DefaultTableModel(new Object[][] {}, new String[] 
					{ "ID Account", "Data Creare",
							"Balanta", "Tip Account", "Dobanda Obtinuta", "Suma Cheltuita", "ID Persoana", "Nume Persoana" });
			tablou.setModel(defTableModel);
			for (int i = 0; i < lista.size(); i++) 
			{
				defTableModel.addRow(lista.get(i));
			}

		} 
		else
		{
			butonSterge3.setEnabled(true);
			butonStergeTot.setEnabled(true);

			ArrayList<Object[]> tranz = bank.getTranzactions();
			defTableModel = new DefaultTableModel(new Object[][] {},
					new String[] { "Data", "Account 1", "Persoana 1", "Account 2", "Persoana 2", "Suma" });
			tablou.setModel(defTableModel);
			for (int i = 0; i < tranz.size(); i++) 
			{
				defTableModel.addRow(tranz.get(i));
			}

		}

		scroolPanelJos.setViewportView(tablou);
		setPreferredSize(getSize());
		pack();
	}
    
    public void adaugaListenerBtnBack(ActionListener a){
        butonInapoi.addActionListener(a);
    } 
    
    public void adaugaListenerBtnRemove1(ActionListener a){
        butonSterge1.addActionListener(a);
    }
    
    public void adaugaListenerBtnRemove2(ActionListener a){
        butonSterge2.addActionListener(a);
    }
    
    public void adaugaListenerBtnRemove3(ActionListener a){
        butonSterge3.addActionListener(a);
    }
    
    public void adaugaListenerBtnRemove3All(ActionListener a){
        butonStergeTot.addActionListener(a);
    }
    
    public void adaugaWindowListener(WindowListener w){
        this.addWindowListener(w);
    }
    
    public void adaugaRadioListener(ChangeListener c){
        radio1.addChangeListener(c);
        radio2.addChangeListener(c);
        radio3.addChangeListener(c);
    }
    
	public void showError(int id) {
		if (id == 1) {
			JOptionPane.showMessageDialog(this, "Linie Invalida!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		if (id == 0) {
			JOptionPane.showMessageDialog(this, "S-a sters cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
		}
	}

    public String getStringId(){
        return (String)defTableModel.getValueAt(tablou.getSelectedRow(), 0);
    }
    public int getIntId(){
        return (int)defTableModel.getValueAt(tablou.getSelectedRow(), 0);
    }
    
}
