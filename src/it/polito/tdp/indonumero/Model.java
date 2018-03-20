package it.polito.tdp.indonumero;

import java.security.InvalidParameterException;

public class Model {
	
	private int NMAX = 100;
	private int TMAX = 7;
	
	private int segreto;
	private int tentativi;
	
	private boolean inGame = false;
	
	
	public Model() {
		this.inGame = false;
	}
	
	/**
	 * Avvia una nuova partita, generando un nuovo numero segreto
	 */
	public void newGame() {
		
		this.segreto = (int) (Math.random()*NMAX+1);
    	
    	this.tentativi=0;
    	this.inGame=true;
    	
	}
	
	/**
	 * Fai un tentativo di indovinare il numero segreto
	 * @param t valore numerico del tentativo
	 * @return 0 se è indovinato, -1 se troppo piccolo, +1 se è troppo grande.
	 */
	public int tentativo (int t) {
		
		if(!inGame) {
			throw new IllegalStateException("Partita non attiva");
		}
		if(t<1 || t>this.NMAX) {
			throw new InvalidParameterException("Tentativo fuori range");
		}
		
		this.tentativi++;
		if (this.tentativi==this.TMAX) {
			this.inGame=false;
		}
		
		if(t==this.segreto) {
			this.inGame=false;
			return 0;
		}
		if (t<this.segreto)
			return -1;
		return +1;
	}
	
	public boolean isInGame() {
		return this.inGame;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativi() {
		return tentativi;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public void setTentativi(int tentativi) {
		this.tentativi = tentativi;
	}
	
	/**
	 * Controlla se il tentativo fornito rispetta le regole formali del gioco, cioè se è nel range
	 * [1, NMAX]
	 * @param tentativo
	 * @return
	 */
	public boolean valoreValido(int tentativo) {
		return tentativo>=1 && tentativo<=this.NMAX;
	}

}
