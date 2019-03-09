/**
 * Clasa care salveaza o persoana cu urmatoarele date:
 * id-ul unic al persoanei, primul nume, al doilea nume, tara, parola,
 * nr de conturi pe care le detine, data crearii persoanei.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

/**
 * @version mai 2017
 * @author Dan
 */
public class Person implements Serializable, Observer{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2584289159851341501L;
	private String id, prenume, nume, tara, parola;
    private int nrAcc;
    private Date data;

    /**
     * Constructorul care creeaza o persoana
     * @param id id-ul unic de tip String
     * @param pass parola acelei persoane
     * @param nrAcc numarul de conturi initiale
     */
    public Person(String id, String pass, int nrAcc){
        this.id = id;
        this.nrAcc = nrAcc;
        prenume = "";
        nume = "";
        tara = "";
        data = new Date();
        parola = pass;
    }
    
    /**
     * Constructorul 2 de creare a unei persoane
     * @param id id-ul unic al persoanei
     * @param pass parola.
     */
    public Person(String id, String pass){
        this(id, pass, 1);
    }

    public String getId(){
        return id;
    }
    
    public String getFirstName(){
        return prenume;
    }
    
    public String getLastName(){
        return nume;
    }
    
    public String getCountry(){
        return tara;
    }
    
    public String getPassword(){
        return parola;
    }
    
    public Date getDate(){
        return data;
    }
    
    public int getNrAcc(){
        return nrAcc;
    }
    
    public void setPrenume(String pren){
        prenume = pren;
    }
    public void setNume(String num){
        nume = num;
    }
    public void setTara(String t){
        tara = t;
    }
    public void setPassword(String pass){
        parola = pass;
    }
    
    public void addNrAcc(){
        nrAcc++;
    }
    
    public void decNrAcc(){
        nrAcc--;
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Ma numesc " + nume +" " 
				+ prenume + " am id-ul: " + id +" si am suferit modificari");
		
	}
    
    
}
