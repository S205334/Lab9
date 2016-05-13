/**
 * Sample Skeleton for 'Porto.fxml' Controller Class
 */

package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Article;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class PortoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="choisAutoreA"
    private ChoiceBox<Creator> choisAutoreA; // Value injected by FXMLLoader

    @FXML // fx:id="choisAutoreB"
    private ChoiceBox<Creator> choisAutoreB; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    private Model model;

    @FXML
    void viewCluster(ActionEvent event) {
    	txtResult.clear();
    	List<List<Creator>> clusters = model.viewCluster();
    	
    	for(List<Creator> l : clusters)
			txtResult.appendText("Numero componenti connesse: "+l.size()+"\n"+l+"\n\n");
    }

    @FXML
    void viewCoautori(ActionEvent event) {
    	txtResult.clear();
    	if(choisAutoreA.getValue()== null) {
    		txtResult.appendText("Non hai scelto l'autore!\n");
    		return;
    	}
    	
    	List<Creator> coautori = model.viewCoautori(choisAutoreA.getValue());
    	
    	if(!coautori.isEmpty())
	    	for(Creator c : coautori)
	    		txtResult.appendText(c+"\n");
    	else
    		txtResult.appendText("Nessuno coautore trovato!");

    }

    @FXML
    void viewDoc(ActionEvent event) {
    	txtResult.clear();
    	if(choisAutoreA.getValue()== null || choisAutoreB.getValue()== null) {
    		txtResult.appendText("Non hai selezionato uno dei autori!\n");
    		return;
    	}
    	
    	List<Article> art = model.viewArticle(choisAutoreA.getValue(),choisAutoreB.getValue());
    	
    	if(!art.isEmpty())
    		for(Article a : art)
    			txtResult.appendText(a+"\n");
    	else
    		txtResult.appendText("Nessuno collaborazione trovata");

    }
    
	public void setModel(Model model) {
		// TODO Auto-generated method stub
		this.model = model;
		
		try {
			model.creaGrafo();
			
			List<Creator> creators = model.getCreatorList();
			
			// System.out.println(creators);
			choisAutoreA.getItems().add(null);
			choisAutoreB.getItems().add(null);
			
			choisAutoreA.getItems().addAll(creators);
			choisAutoreB.getItems().addAll(creators);
		
		}catch (RuntimeException e) {
			txtResult.setText(e.getMessage());
		}
		
	}

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert choisAutoreA != null : "fx:id=\"choisAutoreA\" was not injected: check your FXML file 'Porto.fxml'.";
        assert choisAutoreB != null : "fx:id=\"choisAutoreB\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }
}
