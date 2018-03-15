package it.polito.tdp.indonumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private int NMAX = 100;
	private int TMAX = 7;
	
	private int segreto;
	private int tentativi;
	
	private boolean inGame = false;

    @FXML
    private Button btnNuova;

    @FXML
    private TextField txtCurr;

    @FXML
    private TextField txtMax;

    @FXML
    private HBox boxGioco;

    @FXML
    private TextField txtTentativo;

    @FXML
    private TextArea txtLog;

    @FXML
    void handleNuova(ActionEvent event) {

    	this.segreto = (int) (Math.random()*NMAX+1);
    	
    	this.tentativi=0;
    	this.inGame=true;
    	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCurr.setText(this.tentativi+"");
    	txtMax.setText(this.TMAX+"");
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText("Indovina un numero tra 1 e "+NMAX+"\n");
    	
    }

    @FXML
    void handleProva(ActionEvent event) {
    	
    	String numS = txtTentativo.getText();
    	
    	if (numS.length()==0) {
    		txtLog.appendText("Devi inserire un numero!\n");
        	return;
    	}
    	
    	try {
    	int num = Integer.parseInt(numS);
    	if (num == this.segreto) {
    		txtLog.appendText("Hai indovinato!\n");
    		btnNuova.setDisable(false);
    		boxGioco.setDisable(true);
    		this.inGame=false;

    	}
    	else {
    		this.tentativi++;
    		txtCurr.setText(this.tentativi+"");
    		if (this.tentativi==this.TMAX) {
    			txtLog.appendText("Hai perso! Il numero era: "+this.segreto+"\n");
    			btnNuova.setDisable(false);
        		boxGioco.setDisable(true);
        		this.inGame=false;
    		}
    		else {
    			if (num<segreto) {
    				txtLog.appendText("Troppo basso\n");
    			}
    			else {
    				txtLog.appendText("Troppo alto\n");
    			}
    		}
    	}
    	} catch (NumberFormatException ex) {
    		txtLog.appendText("Il dato inserito non è numerico\n");
    		return;
    	}
    }

}
