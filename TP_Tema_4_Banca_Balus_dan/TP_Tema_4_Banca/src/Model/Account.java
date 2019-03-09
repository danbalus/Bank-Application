/**
 * Clasa Account va salva un account care contine:
 * ID-ul unic, Tipul contului (spending/saving), data inregistrarii,
 * si suma de bani.
 */
package Model;

import java.io.Serializable;
import java.util.Date;

/**
 * @version mai 2017
 * @author Dan
 */
public abstract class Account implements Serializable{
	//private static final long serialVersionUID = -1525402736529087891L;

    /**
	 * 
	 */
	private static final long serialVersionUID = 5686024641515045986L;
	private Date data;
	private int balanta;
	private int id;
    private int tipAccount; //1 = SavingAccount, 2 = SpendingAccount
   
    
    /**
     * Constructorul care poate fi apelat doar de catre subclasele
     * SavingAccount si SpendingAccount
     * @param id - id-ul accountului
     * @param tip -tip-ul: Saving=1/Spending=2
     */
    public Account(int id, int tip){
        this.id = id;
        tipAccount = tip;
        balanta = 0;
        this.data = new Date();
    }
    public Account(){}
    
    
    /**
     * @return id-ul accountului 
     */
    public int getId(){
        return id;
    }
    
    
    /**
     * @return suma de bani 
     */
    public int getBalance(){
        return balanta;
    }
    
    
    /**
     * @return tipul accountului 1 sau 2 
     */
    public int getTipAccount(){
        return tipAccount;
    }
    
    /**
     * @return data inregistrarii accountului
     */
    public Date getDate(){
        return data;
    }
    
    /**
     * seteaza suma de bani
     * @param suma - suma care va fi resetata
     */
    public void setBalance(int suma){
        balanta = suma;
    }
    
    
    /**
     * seteaza data
     * @param data data care va fi setata
     */
    public void setDate(Date data){
       this.data = data;
    }
    
}
