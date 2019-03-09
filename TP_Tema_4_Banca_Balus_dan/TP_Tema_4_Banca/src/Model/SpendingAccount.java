/**
 * clasa care contine u n obiect de tip SpendingAccount
 * extinde obiectul Account
 */
package Model;

import java.io.Serializable;

/**
 * @version mai 2017
 * @author Dan
 */
public class SpendingAccount extends Account implements Serializable{
	private static final long serialVersionUID = -4461502803654593174L;

    private int balantaCheltuieli;
    
    /**
     * constructorul
     * @param id id-ul unic al contului
     */
    public SpendingAccount(int id){
        super(id, 2);
        balantaCheltuieli = 0;
    }
    
    /**
     * @return suma cheltuita
     */
    public int getBalanceSpended(){
        return balantaCheltuieli;
    }
    
    /**
     * metoda care adauga suma cheltuita
     * @param bani suma de bani care va fi adaugata.
     */
    public void addBalanceSpended(int bani){
        balantaCheltuieli += bani;
    }
    
}
