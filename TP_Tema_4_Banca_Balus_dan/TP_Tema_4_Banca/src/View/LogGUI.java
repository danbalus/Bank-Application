/**
 * clasa de tip GUI .
 */
package View;

import Model.Variabile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.*;

/**
 * @version mai 2017
 * @author Dan
 */
public class LogGUI extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -6433325712112893412L;
	private JButton butonAccLogin = new JButton("Logare Account");
    private JButton butonAccCreate = new JButton("Creare Account");
    private JButton butonAdmin = new JButton("Administrator");
    private JButton butonExit = new JButton("Exit");
    private JPanel panelCentral = new JPanel();
    
    /** Creates new form LogGUI */
    public LogGUI() {
        setTitle("Fereastra Principala");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        
        setContentPane(panelCentral);
        panelCentral.setLayout(new GridLayout(7,1));
        panelCentral.add(new JLabel("Optiune:",JLabel.CENTER));
        JPanel panel;
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 10, 10));
        panel.add(new JLabel());
        panel.add(butonAccCreate);
        panel.add(new JLabel());
        panelCentral.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 10, 10));
        panel.add(new JLabel());
        panel.add(butonAccLogin);
        panel.add(new JLabel());
        panelCentral.add(panel);
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 10, 10));
        panel.add(new JLabel());
        panel.add(butonAdmin);
        panel.add(new JLabel());
        panelCentral.add(panel);

        panelCentral.add(new JPanel());

        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 10, 10));
        panel.add(new JLabel());
        panel.add(butonExit);
        panel.add(new JLabel());
        panelCentral.add(panel);
        
 
        panelCentral.add(new JLabel());
        
        pack();
        setLocationRelativeTo(null);  
        setVisible(true);
    }
    
    public void addBtBankAcList(ActionListener a){
        butonAdmin.addActionListener(a);
    }
    
    public void addBtAccountLoginAcList(ActionListener a){
        butonAccLogin.addActionListener(a);
    }
    
    public void addBtAccountCreateAcList(ActionListener a){
        butonAccCreate.addActionListener(a);
    }

    public void addBtExitAcList(ActionListener a){
        butonExit.addActionListener(a);
    }
    
    public String[] getAccountLog(int id){
        String[] log = new String[3];
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JTextField jTextField = new JTextField("");
        JPasswordField jPassField1 = new JPasswordField("");
        JPasswordField jPassField2 = new JPasswordField("");
        jTextField.setForeground(Color.black);
        jPassField1.setForeground(Color.black);
        jPassField2.setForeground(Color.black);
        String numeFereastra;
        Object[] obj;
        if (id == 1)
        {
            label1.setText("Alege un nickname unic:");
            label2.setText("Alege o parola:");
            label3.setText("Repeta parola:");
            numeFereastra="Inregistrare account nou";
            obj = new Object[]{label1, jTextField, label2, jPassField1, label3, jPassField2};
        }
        else if (id == 2)
        {
            label1.setText("Nickname");
            label2.setText("Parola:");
            numeFereastra = "Logare Account";
            obj = new Object[]{label1, jTextField, label2, jPassField1};
        }
        else {
            label1.setText("Administrator Logare:");
            label2.setText("Administrator Parola:");
            numeFereastra = "Logare Admin";
            obj = new Object[]{label1, jTextField, label2, jPassField1};
            if (Variabile.autoAdminLogin)
            {
                jTextField.setText(Variabile.adminLogin);
                jPassField1.setText(Variabile.adminPass);
            }
        }
        int status; 
        status = JOptionPane.showConfirmDialog(this, obj, numeFereastra, JOptionPane.OK_CANCEL_OPTION);
        log[1] = String.valueOf(jTextField.getText());
        log[2] = String.valueOf(jPassField1.getPassword());
        log[0] = "0";
        if (id == 1)
            if (String.valueOf(jPassField1.getPassword()).compareTo(String.valueOf(jPassField2.getPassword()))!=0)
                log[0] = "2";
        if (jPassField1.getPassword().length < 4) 
        	log[0] = "4";
        if (jTextField.getText().length() < 4) 
        	log[0]="3";
        if (status != 0) 
        	log[0] = "-1";
        return log;
    }
    
    public void setLoginMessage(int id){
		if (id == 0)
			JOptionPane.showMessageDialog(this, "Logare cu succes!", "Succes", JOptionPane.INFORMATION_MESSAGE);
		else if (id == 2)
			JOptionPane.showMessageDialog(this, "Parolele nu coincid!", "Eroare", JOptionPane.ERROR_MESSAGE);
		else if (id == 3)
			JOptionPane.showMessageDialog(this, "Login prea scurt! (Minimum 4 caractere)", "Eroare", JOptionPane.ERROR_MESSAGE);
		else if (id == 4)
			JOptionPane.showMessageDialog(this, "Parola prea scurta! (Minimum 4 caractere)", "Eroare", JOptionPane.ERROR_MESSAGE);
		else if (id == 10)
			JOptionPane.showMessageDialog(this, "Autentificare invalida!", "Eroare", JOptionPane.ERROR_MESSAGE);
		else if (id == 11)
			JOptionPane.showMessageDialog(this, "Account Inexistent!", "Eroare", JOptionPane.ERROR_MESSAGE);
		else if (id == 12)
			JOptionPane.showMessageDialog(this, "Parola Invalida!", "Eroare", JOptionPane.ERROR_MESSAGE);
		else if (id == 15)
			JOptionPane.showMessageDialog(this, "Account ocupat!", "Eroare", JOptionPane.ERROR_MESSAGE);
    }

    public void adaugaWindowListener(WindowListener w){
        this.addWindowListener(w);
    }
    
}
