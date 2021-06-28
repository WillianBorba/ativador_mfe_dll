package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.com.fandrauss.fx.gui.WindowControllerFx;
import dll_Services.ServicosMfe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import util.Alerts;
import util.Constants;

public class TrocarCodigoController extends WindowControllerFx implements Initializable{
	
	List <String> list = new ArrayList<>();
	private ObservableList<String> obsList;
	
	@FXML
	private TextField textFieldCodigoAtual;
	
	@FXML
	private TextField textFieldNovoCodigo;
	
	@FXML
	private TextField textFieldConfirmacaoNovoCodigo;
	
	@FXML
	private ComboBox<String> comboBoxTipoDeCodigo;
	
	@FXML
	private Button btTrocarCodigo;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	private Pane pane;
	
	//cancela e fecha a view
	public void onBtCancelarAction(ActionEvent event) {
		btCancelar.getScene().getWindow().hide();
	}
	//Pega o código de ativação atual selecionado, pega o novo código e a confirmação do novo código e realiza a troca
	public void onBtTrocarCodigoAction(ActionEvent event) {		
		String result = ServicosMfe.trocarCodigoAtivacao(textFieldCodigoAtual.getText(), selecionaTipo(), textFieldNovoCodigo.getText(), textFieldConfirmacaoNovoCodigo.getText());
		Alerts.mostraAlerta("Troca de código de ativação", null, result, AlertType.INFORMATION);
	}
	//Coloca campos no comboBox de tipo de código de ativação
	public void inicializaComboTipo() {
		list.add("Código de ativação");
		list.add("Código de ativação emergencial");
		obsList = FXCollections.observableArrayList(list);
		comboBoxTipoDeCodigo.setItems(obsList);
	}
	
	//Pega o valor de cada seleção feita na comboBox
	private int selecionaTipo() {
		if(comboBoxTipoDeCodigo.getSelectionModel().getSelectedItem().equals("Código de ativação")) {
			return 1;
		}
		
		if(comboBoxTipoDeCodigo.getSelectionModel().getSelectedItem().equals("Código de ativação emergencial")) {
			return 2;
		}
		
		else {
			return 1;
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializaComboTipo();
		}
	@Override
	public String getFXML() {
		return Constants.LOCAL_TROCA_CODIGO;
	}
}
