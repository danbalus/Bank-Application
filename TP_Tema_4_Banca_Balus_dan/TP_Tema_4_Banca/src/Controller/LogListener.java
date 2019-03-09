/**
 * Clasa LogListener primeste ca  argument 3 obiecte de tip GUI,
 * si 1 obiect de tip Bank. In aceasta metoda vor fi toate Listenerurile
 * ferestrei LogGUI.
 */
package Controller;

import Model.Bank;
import Model.Person;
import Model.Variabile;
import View.AccountGUI;
import View.BankGUI;
import View.LogGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @version mai 2017
 * @author Dan
 */
public class LogListener {
    
    private LogGUI gui;
    private BankGUI guiBank;
    private AccountGUI guiAcc;
    
    private Bank bank;
    
    /**
     * Constructorul
     * @param g Obiect de tip LogGUI
     * @param gBank Obiect de tip BankGUI
     * @param gAcc obiect de tip AccountGUI
     * @param b obiect de tip Bank
     */
    public LogListener(LogGUI g, BankGUI gBank, AccountGUI gAcc, Bank b){
        gui = g;
        bank = b;
        guiAcc = gAcc;
        guiBank = gBank;
        g.addBtBankAcList(acLisButAdmin);
        g.addBtAccountLoginAcList(acLisButAccountL);
        g.addBtAccountCreateAcList(acLisButAccountC);
        g.addBtExitAcList(acLisButExit);
        g.adaugaWindowListener(windowListener);
    }

    /**
     * atunci cand se inchide fereastra
     */
    private WindowListener windowListener = new WindowListener(){
        public void windowOpened(WindowEvent e) {}
        public void windowClosing(WindowEvent e) {
            bank.saveBank();
        }
        public void windowClosed(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
    };
    
	private ActionListener acLisButAccountL = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String accountDataGot[] = gui.getAccountLog(2);
			if (!"0".equals(accountDataGot[0])) 
			{
				gui.setLoginMessage(Integer.parseInt(accountDataGot[0]));
				return;
			}
			Person pers = bank.readPerson(accountDataGot[1]);
			if (pers == null) {
				gui.setLoginMessage(11);
				return;
			}
			String pass = pers.getPassword();
			if (pass.compareTo(accountDataGot[2]) != 0) {
				gui.setLoginMessage(12);
				return;
			}
			Variabile.idLogin = accountDataGot[1];
			gui.setVisible(false);
			guiAcc.init(bank);
		}
	};
    
    
	private ActionListener acLisButAccountC = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String log[] = gui.getAccountLog(1);
			if (!"0".equals(log[0])) {
				gui.setLoginMessage(Integer.parseInt(log[0]));
				return;
			}
			Person pers = bank.readPerson(log[1]);
			if (pers != null) {
				gui.setLoginMessage(15);
				return;
			}
			bank.createAccount(log[1], log[2], 2);
			Variabile.idLogin = log[1];
			gui.setVisible(false);
			guiAcc.init(bank);
		}

	};
    
    private ActionListener acLisButExit = new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            bank.saveBank();
            System.exit(0);
        }

    };

	private ActionListener acLisButAdmin = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String log[] = gui.getAccountLog(3);
			if (!"0".equals(log[0]))
			{
				gui.setLoginMessage(Integer.parseInt(log[0]));
				return;
			}
			if ((log[1].compareTo(Variabile.adminLogin) != 0) || (log[2].compareTo(Variabile.adminPass) != 0)) 
			{
				gui.setLoginMessage(10);
				return;
			}
			gui.setLoginMessage(0);
			gui.setVisible(false);
			guiBank.init(bank);
		}

	};    
    
    
}
