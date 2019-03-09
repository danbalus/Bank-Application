/**
 * Interfata BankInterfata  care contine toate operatiile pe care
 * trebuie sa le implementeze clasa Bank.
 */
package Model;

import java.util.ArrayList;

/**
 * @version mai 2017
 * @author Dan
 */
public interface BankInterfata {
	public Person readPerson(String id);
	
	public Account cautaAccount(int id);
    
    public void createAccount(String idPerson, String pass, int tip);
    public void removeAccount(Account a);
    
    public void makeTranzaction(Account a1, Account a2, int bani);
    public void removePerson(Person p);
    public void removeTranzaction(String date);
    public void removeAllTranzaction();
    
    public ArrayList<Object[]> getTranzactions();
    public ArrayList<Object[]> getAccounts();
    public ArrayList<Object[]> getPersons();
    
    public void addDobanda(SavingAccount sa);
    public void saveBank();
    public void loadBank();
    
    public ArrayList<Account> getAllAccounts(String idPerson);
    
}
