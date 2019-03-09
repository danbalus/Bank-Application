
/**
 * Clasa MainC porneste programul.
 * Creeaza 1 obiect model de tip bank,
 * 3 obicte de tip GUI si alte 3 obiecte de tip Listener.
 * Listenerurile primesc ca argumente obiectele de tip GUI ca sa poata interactiona
 * intre ele si acel obiect de tip Bank.
 */

import Controller.AccountListener;
import Controller.BankListener;
import Controller.LogListener;
import Model.Bank;
import View.AccountGUI;
import View.BankGUI;
import View.LogGUI;

/**
 * @version mai 2017
 * @author Dan
 */
public class MainC {
    
    
    /**
     * metoda main care este apelata la deschiderea programului java
     * @param args argumentele care in cazul nostru nu sunt folosite
     */
	public static void main(String[] args) {
		newStyleNimbus();
		Bank bank = new Bank();
		bank.loadBank();
		LogGUI logGui = new LogGUI();
		BankGUI bankGui = new BankGUI();
		AccountGUI accountGui = new AccountGUI();
		@SuppressWarnings("unused")
		LogListener logList = new LogListener(logGui, bankGui, accountGui, bank);
		@SuppressWarnings("unused")
		AccountListener accList = new AccountListener(logGui, accountGui, bank);
		@SuppressWarnings("unused")
		BankListener bankList = new BankListener(logGui, bankGui, bank);
	}

    /**
     * metoda care schimb a stilul ferestrelor grafice
     * in cazul in care stilul Nimbus nu a fost gasit se va afisa stilul default
     */
    private static void newStyleNimbus(){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                }
            }
        } catch (Exception ex) {
            System.out.println("getStyle exception: \n"+ex.toString());
        }
    }
    
    
}
