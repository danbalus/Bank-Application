/**
 * Clasa AccountListener primeste ca argument 2 obiecte de tip GUI,
 * si 1 obiect de tip Bank. In aceasta metoda vor fi toate Listenerurile
 * ferestrei AcccountGUI.
 */
package Controller;

import Model.*;
import View.AccountGUI;
import View.LogGUI;
import java.awt.event.*;

/**
 * @version mai 2017
 * @author Dan
 */
public class AccountListener {
	private LogGUI logGui;
	private AccountGUI accGui;
	private Bank bankk;

	/**
	 * Constructorul
	 * 
	 * @param gLog
	 *            obiect de tip LogGUI
	 * @param g
	 *            obiect de tip AccounGUI
	 * @param b
	 *            obiect de tip Bank
	 */
	public AccountListener(LogGUI gLog, AccountGUI g, Bank b) {
		logGui = gLog;
		accGui = g;
		bankk = b;
		g.adaugaWindowListener(windLsn);
		g.adaugaListenerBtnBack(acLisButExit);
		g.adaugaListenerBtnSet(acLisButSet);
		g.adaugaListenerBtnNewAcc1(acLisButNewAcc1);
		g.adaugaListenerBtnNewAcc2(acLisButNewAcc2);
		g.addCoBox1ItLis(addItemListener1);
		g.adaugaListenerBtnOKAdauga(acLisButOKAdauga);
		g.adaugaListenerBtnOKDepune(acLisButOKDepune);
		g.adaugaListenerBtnOKExtrag(acLisButOKExtrage);
		g.adaugaListenerBtnOKPlata(acLisButOKPlateste);
		g.adaugaListenerBtnGetInterest(acLisButAddInterest);
		g.addCoBoxDepune(addItemListenerDepune);

	}

	/**
	 * atunci cand inchidem fereastra.
	 */
	private WindowListener windLsn = new WindowListener() {
		public void windowClosing(WindowEvent arg0) {
			bankk.saveBank();
		}

		public void windowOpened(WindowEvent arg0) {
		}

		public void windowClosed(WindowEvent arg0) {
		}

		public void windowIconified(WindowEvent arg0) {
		}

		public void windowDeiconified(WindowEvent arg0) {
		}

		public void windowActivated(WindowEvent arg0) {
		}

		public void windowDeactivated(WindowEvent arg0) {
		}
	};

	private ActionListener acLisButNewAcc1 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			bankk.createAccount(Variabile.idLogin, null, 1);
			accGui.loadCoBox1(bankk);
			accGui.showCont(bankk);
			accGui.showError(0, "Ati creat un Saving Account!");
		}

	};
	private ActionListener acLisButNewAcc2 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			bankk.createAccount(Variabile.idLogin, null, 2);
			accGui.loadCoBox1(bankk);
			accGui.showCont(bankk);
			accGui.showError(0, "Ati creat un Spending Account!");
		}

	};

	private ActionListener acLisButExit = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			logGui.setVisible(true);
			accGui.setVisible(false);
		}

	};

	private ActionListener acLisButSet = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String prsonalDataGot[] = accGui.getPersonData();
			boolean validareCerinte[] = { true, true, true, true };
			String personalData[] = { "", "", "" };
			Person pers = bankk.readPerson(Variabile.idLogin);
			try {
				assert (prsonalDataGot[0].length() > 3);
			} catch (AssertionError ex) {
				accGui.showError(1, "Prenumele e prea scurt!");
				validareCerinte[0] = false;
			}
			try {
				assert (prsonalDataGot[1].length() > 3);
			} catch (AssertionError ex) {
				accGui.showError(1, "Numele e prea scurt!");
				validareCerinte[1] = false;
			}
			try {
				assert (prsonalDataGot[2].length() > 3);
			} catch (AssertionError ex) {
				accGui.showError(1, "Numele tarii e prea scurt!");
				validareCerinte[2] = false;
			}
			try {
				assert (prsonalDataGot[0].length() < 16);
			} catch (AssertionError ex) {
				accGui.showError(1, "Prenumele e prea lung!");
				validareCerinte[0] = false;
			}
			try {
				assert (prsonalDataGot[1].length() < 16);
			} catch (AssertionError ex) {
				accGui.showError(1, "Numele e prea lung!");
				validareCerinte[1] = false;
			}
			try {
				assert (prsonalDataGot[2].length() < 16);
			} catch (AssertionError ex) {
				accGui.showError(1, "NUmele tarii e prea lung!");
				validareCerinte[2] = false;
			}
			
			if ((prsonalDataGot[3].length()) == 0)
				validareCerinte[3] = false;
			
			if ((prsonalDataGot[3].length()) > 0)
				if ((prsonalDataGot[3].length()) < 4) {
					accGui.showError(1, "Parola e prea scurta! (minimum 4 caractere)");
					validareCerinte[3] = false;
				}
			
			if ((prsonalDataGot[3].length()) > 30) {
				accGui.showError(1, "Password is too long!");
				validareCerinte[3] = false;
			}
			
			if ((prsonalDataGot[3].compareTo(prsonalDataGot[4])) != 0) {
				accGui.showError(1, "Parolele nu sunt la fel!");
				validareCerinte[3] = false;
			}
			
			if (validareCerinte[0])
				if (prsonalDataGot[0].compareTo(pers.getFirstName()) != 0) 
				{
					accGui.showError(0, "Prenumele a fost schimbat!");
					personalData[0] = prsonalDataGot[0];
					pers.setPrenume(prsonalDataGot[0]);
				}
			
			if (validareCerinte[1])
				if (prsonalDataGot[1].compareTo(pers.getLastName()) != 0) {
					accGui.showError(0, "Numele a fost schimbat!");
					personalData[1] = prsonalDataGot[1];
					pers.setNume(prsonalDataGot[1]);
				}
			
			if (validareCerinte[2])
				if (prsonalDataGot[2].compareTo(pers.getCountry()) != 0) {
					accGui.showError(0, "Numele tarii a fost schimbat!");
					personalData[2] = prsonalDataGot[2];
					pers.setTara(prsonalDataGot[2]);
				}
			
			if (validareCerinte[3])
				if (prsonalDataGot[3].compareTo(pers.getPassword()) != 0) {
					accGui.showError(0, "Parola a fost schimbata!");
					pers.setPassword(prsonalDataGot[3]);
				}

			accGui.setPersonData(personalData);
		}

	};

	private ItemListener addItemListener1 = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				accGui.showCont(bankk);
			}
		}
	};

	private ItemListener addItemListenerDepune = new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == 1) {
				accGui.scrieContDepuneri();
			}
		}
	};

	private int[] verificaTranzactie(String suma[], int tipAcc1, int tipAcc2) {
		int data[] = { -1, 0, 0, 0 };
		int id = 0, id2 = 0;
		boolean eroare = false;
		int nr, nr2;
		

		try {
			nr = Integer.parseInt(suma[0]);
			Account acc = bankk.cautaAccount(nr);
			id = acc.getId();
			
			if ((tipAcc1 == 1) && (acc.getTipAccount() != 1))
				eroare = true;
			if ((tipAcc1 == 2) && (acc.getTipAccount() != 2))
				eroare = true;

			
			nr2 = Integer.parseInt(suma[2]);// destinatarul
			Account a2 = bankk.cautaAccount(nr2);
			id2 = a2.getId();
			
			if ((tipAcc2 == 1) && (a2.getTipAccount() != 1))
				eroare = true;
			if ((tipAcc2 == 2) && (a2.getTipAccount() != 2))
				eroare = true;
			
		} catch (Exception ex) {
			eroare = true;
		}
		if (eroare) {
			accGui.showError(1, "Invalid Account!");
			return data;
		}
		data[1] = id;
		data[3] = id2;

		nr = 0;
		try {
			nr = Integer.parseInt(suma[1]);
		} catch (Exception ex) {
			eroare = true;
		}
		if ((nr < 1) || (nr > 1000000))
			eroare = true;
		if (eroare) {
			accGui.showError(1, "Introduceti o suma de bani valida!");
			return data;
		}
		data[2] = nr;
		data[0] = 0;
		return data;
	}

	private ActionListener acLisButAddInterest = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int id = accGui.getAccount(bankk);
				Account acc = bankk.cautaAccount(id);
				SavingAccount sa = (SavingAccount) acc;
				bankk.addDobanda(sa);
				accGui.showBalances(acc);
				accGui.showError(0, "Dobanda a fost crescuta cu: " + Variabile.procDobanda + " %");
			} catch (Exception ex) {
				accGui.showError(1, "Cont Invalid!");
			}
		}

	};

	private ActionListener acLisButOKAdauga = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int nr[] = verificaTranzactie(accGui.getBaniAdauga(), 2, 0);
			if (nr[0] == -1)
				return;
			Account a = bankk.cautaAccount(nr[1]);
			a.setBalance(a.getBalance() + nr[2]);
			accGui.showBalances(a);
			bankk.makeTranzaction(bankk.cautaAccount(nr[1]), bankk.cautaAccount(nr[3]), nr[2]);
		}
	};
	
	private ActionListener acLisButOKPlateste = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int nr[] = verificaTranzactie(accGui.getBaniPlata(), 2, 0);
			if (nr[0] == -1)
				return;
			Account acc = bankk.cautaAccount(nr[1]);
			int bani = acc.getBalance();
			if (nr[2] > bani) {
				accGui.showError(1, "Suma insuficienta!:(");
				return;
			}

			acc.setBalance(acc.getBalance() - nr[2]);
			SpendingAccount sa = (SpendingAccount) acc;
			sa.addBalanceSpended(nr[2]);
			accGui.showBalances(acc);
			bankk.makeTranzaction(bankk.cautaAccount(nr[1]), bankk.cautaAccount(nr[3]), -nr[2]);
		}

	};
	private ActionListener acLisButOKDepune = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int nr[] = verificaTranzactie(accGui.getBaniDepuneri(), 2, 0);
			if (nr[0] == -1)
				return;
			Account a = bankk.cautaAccount(nr[1]);
			int bani = a.getBalance();
			if (nr[2] > bani) {
				accGui.showError(1, "Suma insuficienta!:(");
				return;
			}

			Account a2 = bankk.cautaAccount(nr[3]);
			a2.setBalance(a2.getBalance() + nr[2]);

			a.setBalance(a.getBalance() - nr[2]);
			accGui.showBalances(a);
			bankk.makeTranzaction(bankk.cautaAccount(nr[1]), bankk.cautaAccount(nr[3]), nr[2]);
		}

	};
	private ActionListener acLisButOKExtrage = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int nr[] = verificaTranzactie(accGui.getBaniExtrageri(), 1, 2);
			if (nr[0] == -1)
				return;
			Account acc = bankk.cautaAccount(nr[1]);
			int bani = acc.getBalance();
			if (nr[2] > bani) {
				accGui.showError(1, "Suma insuficienta!:(");
				return;
			}

			Account acc2 = bankk.cautaAccount(nr[3]);
			acc2.setBalance(acc2.getBalance() + nr[2]);

			acc.setBalance(acc.getBalance() - nr[2]);
			accGui.showBalances(acc);
			bankk.makeTranzaction(bankk.cautaAccount(nr[1]), bankk.cautaAccount(nr[3]), nr[2]);
		}

	};

}
