/**
 * Sample Skeleton for 'Porto.fxml' Controller Class
 */

package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class PortoController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML // fx:id="choisAutoreA"
    private ChoiceBox<Creator> choisAutoreA; // Value injected by FXMLLoader

    @FXML // fx:id="choisAutoreB"
    private ChoiceBox<Creator> choisAutoreB; // Value injected by FXMLLoader

    @FXML
    void viewCluster(ActionEvent event) {

    }

    @FXML
    void viewCoautori(ActionEvent event) {

    }

    @FXML
    void viewDoc(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";
        assert choisAutoreA != null : "fx:id=\"choisAutoreA\" was not injected: check your FXML file 'Porto.fxml'.";
        assert choisAutoreB != null : "fx:id=\"choisAutoreB\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model m) {
		// TODO Auto-generated method stub
		this.model = m;
		model.creaGrafo();
		List<Creator> creators = model.getCreatorList();
		
		choisAutoreA.getItems().addAll(creators);
		choisAutoreB.getItems().addAll(creators);
		
	}
}
