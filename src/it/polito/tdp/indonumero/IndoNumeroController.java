package it.polito.tdp.indonumero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class IndoNumeroController {
	
	private Model model;

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
    	
    	model.newGame();
   	
    	btnNuova.setDisable(true);
    	boxGioco.setDisable(false);
    	txtCurr.setText(model.getTentativi()+"");
    	txtMax.setText(model.getTMAX()+"");
    	txtLog.clear();
    	txtTentativo.clear();
    	
    	txtLog.setText("Indovina un numero tra 1 e "+model.getNMAX()+"\n");
    	
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
    	if (num == model.getSegreto()) {
    		txtLog.appendText("Hai indovinato!\n");
    		btnNuova.setDisable(false);
    		boxGioco.setDisable(true);
    		model.setInGame(false);

    	}
    	else {
    		model.setTentativi(model.getTentativi()+1);
    		txtCurr.setText(model.getTentativi()+"");
    		if (model.getTentativi()==model.getTMAX()) {
    			txtLog.appendText("Hai perso! Il numero era: "+model.getSegreto()+"\n");
    			btnNuova.setDisable(false);
        		boxGioco.setDisable(true);
        		model.setInGame(false);
    		}
    		else {
    			if (num<model.getSegreto()) {
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

	public void setModel(Model model) {
		this.model = model;
	}
    
    

}
