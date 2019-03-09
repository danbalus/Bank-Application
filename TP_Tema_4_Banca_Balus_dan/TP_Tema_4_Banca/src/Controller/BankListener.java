/**
 * Clasa AccountListener primeste ca argument 2 obiecte de tip GUI,
 * si 1 obiect de tip Bank. In aceasta metoda vor fi toate Listenerurile
 * ferestrei BankGUI.
 */
package Controller;

import Model.Account;
import Model.Bank;
import Model.Person;
import View.BankGUI;
import View.LogGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @version mai 2017
 * @author Dan
 */
public class BankListener {

    private LogGUI guiLog;
    private BankGUI guiBank;
    private Bank bank;

    /**
     * Constructorul
     * @param gLog Obiect de tip LogGUI
     * @param g Obiect de tip BankGUI
     * @param b Obiect de tip Bank
     */
    public BankListener(LogGUI gLog, BankGUI g, Bank b) {
        guiLog = gLog;
        guiBank = g;
        bank = b;
        g.adaugaWindowListener(windowListener);
        g.adaugaListenerBtnRemove1(actionListenerStergeCrAcc);
        g.adaugaListenerBtnRemove2(actionListenerStergeLogAcc);
        g.adaugaListenerBtnRemove3(actionListenerStergeLogAdmin);
        g.adaugaListenerBtnBack(actionListenerExit);
        g.adaugaRadioListener(adaugareChangeListener);
        g.adaugaListenerBtnRemove3All(actionListenerStergeTotLogAdmin);
    }
    /**
     * atunci cand inchidem fereastra.
     */
    private WindowListener windowListener = new WindowListener(){
        public void windowClosing(WindowEvent arg0) {
            bank.saveBank();
        }
        public void windowOpened(WindowEvent arg0) {}
        public void windowClosed(WindowEvent arg0) {}
        public void windowIconified(WindowEvent arg0) {}
        public void windowDeiconified(WindowEvent arg0) {}
        public void windowActivated(WindowEvent arg0) {}
        public void windowDeactivated(WindowEvent arg0) {}
    };
    
 
    
    private ActionListener actionListenerStergeCrAcc = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String id = guiBank.getStringId();
                Person pers = bank.readPerson(id);
                bank.removePerson(pers);
            } catch (Exception ex) {
                guiBank.showError(1);
            }
            guiBank.showJos(bank, true);
        }
    };
    
    private ActionListener actionListenerStergeLogAcc = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = guiBank.getIntId();
                Account acc = bank.cautaAccount(id);
                bank.removeAccount(acc);
            } catch (Exception ex) {
                guiBank.showError(1);
            }
            guiBank.showJos(bank, true);
        }
    };
    
    private ActionListener actionListenerStergeLogAdmin = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String date = guiBank.getStringId();
                bank.removeTranzaction(date);
            } catch (Exception ex) {
                guiBank.showError(1);
            }
            guiBank.showJos(bank, true);
        }
    };
    
    private ActionListener actionListenerStergeTotLogAdmin = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                bank.removeAllTranzaction();
            } catch (Exception ex) {
                guiBank.showError(1);
            }
            guiBank.showJos(bank, true);
        }
    };
    
    private ActionListener actionListenerExit = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            guiLog.setVisible(true);
            guiBank.setVisible(false);
        }
    };
    

    private ChangeListener adaugareChangeListener = new ChangeListener() {

        @Override
        public void stateChanged(ChangeEvent e) {
            guiBank.showJos(bank, false);
        }
    };
}
