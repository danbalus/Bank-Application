package Testt;

import Model.Account;
import Model.Bank;
import Model.Person;
import Model.SavingAccount;
import Model.SpendingAccount;

//import View.AccountGUI;
//import View.BankGUI;
//import View.LogGUI;

import java.util.Hashtable;

//import Controller.*;
import junit.framework.TestCase;


public class TestOp extends TestCase{

	
	public void testCreateAcc() { 
		 //Hashtable table = new Hashtable<Object, Object>();
		  Account  acc = new SavingAccount(100088);

		assertEquals(100088, acc.getId());
	}
	
	public void testAddPerson() {
		 Person p = new Person("DANb","b",3);
		assertEquals("DANb", p.getId());
	}
	
	
	
	public void testAddAccount() {
		Bank bank = new Bank();
		Hashtable<Account, Person> table = new Hashtable<Account, Person>();
		Person p = new Person("DanBalus", "boss", 3);
		
		SpendingAccount a = new SpendingAccount(100888);
		
		bank.createAccount("100888", "parola", 2);
		
		table = bank.getBank();
		table.put(a, p); 
		
		int size = bank.getBank().size();
		assertEquals(size, bank.getBank().size() );
	}
	
	
	public void testRemoveAccount() {
		Hashtable<Account, Person> table = new Hashtable<Account, Person>();
		Bank bank = new Bank();
		Person p = new Person("DanBalus", "boss", 3);
		
		SavingAccount acc1 = new SavingAccount(100888);
		SavingAccount acc2 = new SavingAccount(100889);
		SavingAccount acc3 = new SavingAccount(108888);
		
		bank.createAccount("100888", "parola", 1);
		bank.createAccount("100889", "parola", 1);
		bank.createAccount("108888", "parola", 1);
		
		table = bank.getBank();
		table.put(acc1, p); 
		table.put(acc2, p); 
		table.put(acc3, p); 
		
		int size = bank.getBank().size();//in mom asta size = 3
		bank.removeAccount(acc3);
		size = bank.getBank().size();//in mom asta size = 2
		assertEquals(size, bank.getBank().size() );
	}
	
	
	public void testRemovePerson() {
		
		Hashtable<Account, Person> table = new Hashtable<Account, Person>();
		Bank bank = new Bank();
		bank.removeAllAccounts();
		Person p = new Person("DanBalus", "boss", 3);
		Person p2 = new Person("DanutzBalus", "boss2", 3);
		
		SavingAccount acc1 = new SavingAccount(100888);
		SavingAccount acc2 = new SavingAccount(100889);
		SavingAccount acc3 = new SavingAccount(108888);
		
		bank.createAccount("100888", "parola", 1);
		bank.createAccount("100889", "parola", 1);
		bank.createAccount("108888", "parola", 1);
		
		table = bank.getBank();
		table.put(acc1, p); //1  2
		table.put(acc2, p); //3  4
		table.put(acc3, p2); //5  6
		bank.removePerson(p2);//raman 5
		assertEquals(5, bank.getBank().size() );
	}
}