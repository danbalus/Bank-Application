/**
 * Clasa Bank va  stoca un bank intreg care contine:
 * un tablou Hashtable cu toate accounturile create, precum si 
 * tranzactiile care au avut loc.
 */
package Model;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Observable;

import javax.swing.JOptionPane;

/**
 * @version mai 2017
 * @author Dan
 */
public class Bank extends Observable implements Serializable, BankInterfata{
	private static final long serialVersionUID = 4541751705973423562L;
	private ArrayList<Object[]> tranzactii;
    private Hashtable<Account, Person> tableH;
    
    
    /**
     * constructorul va instantia o variabila de tip Hashtable
     *  Va mai instantia, de asemenea,
     *  un obiect tranz de tip ArrayList<Object[]>
     */
    public Bank(){
    	
        tableH = new Hashtable<Account, Person>();
        
        Person pers;
		Account acc1, acc2;
		pers = new Person("DanB", "b", 3);
		pers.setPrenume("dan");
		pers.setNume("b");
		pers.setTara("Romania");
		acc1 = new SavingAccount(45363);
		tableH.put(acc1, pers);
		acc1 = new SavingAccount(44444);
		tableH.put(acc1, pers);
		acc1 = new SpendingAccount(45363);
		tableH.put(acc1, pers);
		acc1.setBalance(444);
		pers = new Person("1234", "argc", 1);
		pers.setPrenume("daddsa");
		pers.setNume("ion");
		pers.setTara("dsdasd");
		acc2 = new SavingAccount(5555);
		acc2.setBalance(234);
		tableH.put(acc2, pers);
		tranzactii = new ArrayList<Object[]>();
		makeTranzaction(acc1, acc2, 14);
		//this.addObserver(pers);
    }
    
    /**
     * metoda care creeaza un nou cont
     * @param idPerson Id-ul persoanei unice
     * @param pass - parola persoanei respective
     * @param tip - tipul de cont Saving/Spending
     */
    int idCreate, idCreate2;
    @Override
	public void createAccount(String idPerson, String pass, int tipAcc) {
		Person pers = readPerson(idPerson);
		Account acc;
		boolean liber;
		Enumeration<Account> conturi;
		
		if (pers == null)
			pers = new Person(idPerson, pass);
		else
			pers.addNrAcc();
		//int id;
	
		do {
			idCreate = (int) (Math.random() * 100000) + 100000;
			if (tipAcc == 2)
				idCreate += 100000;
			liber = true;
			conturi = tableH.keys();
			while (conturi.hasMoreElements()) 
			{
				acc = (Account) conturi.nextElement();
				if (idCreate == acc.getId())
					liber = false;
			}
		} while (!liber);
		idCreate2 = idCreate;
		
		if (tipAcc == 1) 
		{
			acc = new SavingAccount(idCreate);
		} else 
		{
			acc = new SpendingAccount(idCreate);
		}
		tableH.put(acc, pers);
		this.addObserver(pers);
	}

    /**
     * metoda care sterge un cont
     * @param acc contul care trebuie sters
     */
    @Override
	public void removeAccount(Account acc) {
		// Avem conturi in tabela de dispersie
		try {
			assert (dacaNuExista() == true);
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "Nu exista conturi", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		try {
			assert (acc != null);
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "Nu exista un cont valida", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		Person pers = (Person) tableH.get(acc);
		pers.decNrAcc();//decrementam nr de conturi
		tableH.remove(acc);
		setChanged();
		notifyObservers();
	}
    /**
     * metoda care sterge toate conturile si implicit persoanele
     * 
     */
    public void removeAllAccounts()
    {
    	tableH.clear();
    	setChanged();
		notifyObservers();
    }

    /**
     * metoda care citeste un cont
     * @param id id-ul contului care trebuie citit
     * @return un obiect de tip Account care are acel id
     */
    @Override
	public Account cautaAccount(int id) {
    	Enumeration<Account> conturi;
		Account acc;
		
		try {// Avem conturi in tabela de dispersie
			assert (dacaNuExista() == true);
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "Nu exista conturi", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		try {
			assert ((id >= 100000) && (id <= 999999));
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "id invalid", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		
		conturi = tableH.keys();
		while (conturi.hasMoreElements()) {
			acc = (Account) conturi.nextElement();
			if (id == acc.getId())
				return acc;
		}
		return null;
	}

    /**
     * metoda care returneaza Tranzactiile
     * @return obiectul tranz de tip ArrayList<Object[]>
     */
    @Override
    public ArrayList<Object[]> getTranzactions() {
        return tranzactii;
    }

    /**
     * metoda care adauga dobanzile in cont
     * @param sa un obiect de tip SavingAccount in care se va adauga dobanda
     */
    @Override
	public void addDobanda(SavingAccount sa) {
		int balance = sa.getBalance();
		int castig = (balance * Variabile.procDobanda / 100);
		sa.setBalance(balance + castig);
		sa.addInterests(castig);
		setChanged();
		notifyObservers();
	}

    /**
     * metoda care citeste datele unei persoane
     * @param id unicul al persoanei
     * @return un obiect de tip Persoana cu id-ul unic
     */
    @Override
	public Person readPerson(String id) {
		// Avem conturi in tabela de dispersie
		try {
			assert (dacaNuExista() == true);
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "Nu exista conturi", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		try {
			assert (id.length() > 3);
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "id invalid", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		Enumeration<Person> conturi;
		Person pers;
		conturi = tableH.elements();
		while (conturi.hasMoreElements()) {
			pers = (Person) conturi.nextElement();
			if (pers.getId().compareTo(id) == 0)
				return pers;
		}
		return null;
	}

    /**
     * metoda care salveaza acest obiect
     * SERIALIZARE
     */
    @Override
	public void saveBank() {
		Object obj[] = new Object[1];
		obj[0] = this;
		FileOutputStream fileOut;
		ObjectOutputStream streamOut;
		try {
			fileOut = new FileOutputStream(Variabile.fileDateName);
			streamOut = new ObjectOutputStream(fileOut);
			streamOut.writeObject(obj);
			streamOut.flush();
			streamOut.close();
			fileOut.close();
		} catch (Exception ex) {
			System.out.println("saveBank exception: \n" + ex.toString());
		}
	}

    /**
     * metoda care citeste obiectul Bank
     * DESERIALIZARE
     */
  
    
    @Override
	public void loadBank() {
		Object obj[];
		FileInputStream fileOut;
		ObjectInputStream streamOut;
		try {
			fileOut = new FileInputStream(Variabile.fileDateName);
			streamOut = new ObjectInputStream(fileOut);
			obj = (Object[]) streamOut.readObject();
			streamOut.close();
			fileOut.close();
			Bank b = (Bank) obj[0];
			tableH = b.tableH;
			tranzactii = b.tranzactii;
		} catch (Exception ex) {
			// date implicite in bank:
			System.out.println("getBank exception: \n" + ex.toString());
		}
	}

    /**
     * metoda care returneaza toate conturile unei persoane
     * @param idPerson id-ul persoanei care detine aceste conturi
     * @return un obiect ArrayList<Account>
     */
    @Override
	public ArrayList<Account> getAllAccounts(String idPerson) {
		ArrayList<Account> lista = new ArrayList<Account>();
		Enumeration<Account> conturi;
		Person pers;
		Account acc;
		conturi = tableH.keys();
		while (conturi.hasMoreElements()) 
		{
			acc = (Account) conturi.nextElement();
			pers = (Person) tableH.get(acc);
			if (pers.getId().compareTo(idPerson) == 0)
				lista.add(acc);
		}
		return lista;
	}

    /**
     * metoda care face o tranzactie
     * @param a1 primul cont
     * @param a2 al doilea cont
     * @param bani banii care sunt transmisi
     */
    @Override
	public void makeTranzaction(Account a1, Account a2, int bani) {
		Person pers;
		Object obj[] = new Object[6];
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		obj[0] = dateFormat.format(date);
		obj[1] = a1.getId();
		pers = (Person) tableH.get(a1);
		obj[2] = String.valueOf(pers.getId()) + "->" + pers.getFirstName() + " " + pers.getLastName();
		obj[3] = a2.getId();
		pers = (Person) tableH.get(a2);
		obj[4] = String.valueOf(pers.getId()) + "->" + pers.getFirstName() + " " + pers.getLastName();
		obj[5] = bani;
		tranzactii.add(obj);
		setChanged();
		notifyObservers();
	}

    //Invariantul:
	private boolean dacaNuExista() {
		boolean stare;
		if (tableH.isEmpty()) {
			stare = false;
		} else {
			stare = true;
		}
		return stare;
	}
    
    /**
     * metoda care returneaza toate conturile unice
     * @return un obiect ArryList<Object[]> care contine toate conturile
     */
    @Override
	public ArrayList<Object[]> getAccounts() {
		Object obj[];
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		Enumeration<Account> conturi;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Account acc;
		conturi = tableH.keys();
		while (conturi.hasMoreElements()) {
			obj = new Object[8];
			acc = (Account) conturi.nextElement();
			obj[0] = acc.getId();
			obj[1] = dateFormat.format(acc.getDate());
			obj[2] = acc.getBalance();
			obj[3] = (acc.getTipAccount() == 1) ? "Saving" : "Spending";
			if (acc.getTipAccount() == 1) 
			{
				SavingAccount sa = (SavingAccount) acc;
				obj[4] = sa.getTotalInterests();
				obj[5] = "";
			}
			else
			{
				SpendingAccount sa = (SpendingAccount) acc;
				obj[5] = sa.getBalanceSpended();
				obj[4] = "";
			}
			Person pers = (Person) tableH.get(acc);
			obj[6] = pers.getId();
			obj[7] = pers.getFirstName() + " " + pers.getLastName();
			lista.add(obj);
		}
		return lista;
	}

    /**
     * Metoda care returneaza toate persoanele unice.
     * @return un obiect ArrayList<Object[]>
     */
    @Override
	public ArrayList<Object[]> getPersons() {
		Object obj[];
		ArrayList<Object[]> lista = new ArrayList<Object[]>();
		Enumeration<Person> conturi;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Person pers;
		conturi = tableH.elements();
		while (conturi.hasMoreElements()) {
			obj = new Object[7];
			pers = (Person) conturi.nextElement();
			obj[0] = pers.getId();
			obj[1] = dateFormat.format(pers.getDate());
			obj[2] = pers.getFirstName();
			obj[3] = pers.getLastName();
			obj[4] = pers.getCountry();
			obj[5] = pers.getPassword();
			obj[6] = pers.getNrAcc();
			boolean exista = false;
			String s;
			for (int i = 0; i < lista.size(); i++) {
				s = (String) lista.get(i)[0];
				if (s.compareTo(pers.getId()) == 0) {
					exista = true;
					break;
				}
			}
			if (!exista)
				lista.add(obj);
		}
		return lista;
	}

    /**
     * metoda care sterge o persoana, impreuna cu toate conturile sale
     * @param pers obiectul Person 
     */
    @Override
	public void removePerson(Person pers) {
		// Avem conturi in tabela de dispersie
		try {
			assert (dacaNuExista() == true);
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "Nu exista conturi", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		try {
			assert (pers != null);
		} catch (AssertionError ex) {
			JOptionPane.showMessageDialog(null, "Nu exista o persoana valida", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		Enumeration<Account> conturi;
		Account acc;
		Person pers2;
		conturi = tableH.keys();
		while (conturi.hasMoreElements()) {
			acc = (Account) conturi.nextElement();
			pers2 = (Person) tableH.get(acc);
			if (pers2.getId().compareTo(pers.getId()) == 0) {
				tableH.remove(acc);
			}
		}
		setChanged();
		notifyObservers();
	}

    /**
     * metoda care sterge o tranzactie
     * @param date data cand a avut loc acea tranzactie
     */
    @Override
	public void removeTranzaction(String date) {
		for (int i = 0; i < tranzactii.size(); i++) 
		{
			String s = (String) tranzactii.get(i)[0];
			if (date.compareTo(s) == 0)
				tranzactii.remove(i);
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * metoda care sterge toate tranzactiile
	 */
	@Override
	public void removeAllTranzaction() {
		tranzactii.removeAll(tranzactii);
	}
	
	
	public Hashtable<Account, Person> getBank()
	{
		return tableH;
	}

	public int getIdCreate()
	{
		return idCreate;
	}
	public void setIdCreate(int idCr)
	{
		idCreate = idCr; 
		setChanged();
		notifyObservers();
	}
}
