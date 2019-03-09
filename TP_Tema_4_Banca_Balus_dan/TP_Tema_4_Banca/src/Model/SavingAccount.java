/**
 * clasa care contine un obiect de tip SavingAccount
 * extinde obiectul Account, are ca date o variabila totalInterests
 */
package Model;

import java.io.Serializable;

/**
 * @version mai 2017
 * @author Dan
 */
public class SavingAccount extends Account implements Serializable{
	//private static final long serialVersionUID = 6244027713008497279L;

    /**
	 * 
	 */
	private static final long serialVersionUID = 2238294249171268639L;
	private int totalDobanda;
    
    /**
     * constructorul 
     * @param id id-ul unic al persoanei
     */
    public SavingAccount(int id){
        super(id, 1);
        totalDobanda = 0;
    }
    
    public SavingAccount() {
		// TODO Auto-generated constructor stub
	}

	/**
     * se adauga suma castigata prin dobanda
     * @param bani suma care va fi adaugata
     */
    public void addInterests(int bani){
        totalDobanda += bani;
    }
    
    /**
     * @return suma castigata prin dobanda
     */
    public int getTotalInterests(){
        return totalDobanda;
    }
    
}
