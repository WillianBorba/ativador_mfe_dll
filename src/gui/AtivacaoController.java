package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import br.com.fandrauss.fx.gui.WindowControllerFx;
import dll_Services.ServicosMfe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import util.Alerts;
import util.Constants;
import util.ServicosString;

public class AtivacaoController extends WindowControllerFx implements Initializable {
	
	ServicosString utils = new ServicosString();
	String result = null;

	
	@FXML
	private TextField tab1TxtContribuinte;
	
	@FXML
	private TextField tab1TxtConfirmaCodigo; 
	
	@FXML
	private TextField tab2TxtContribuinte;
	
	@FXML
	private TextField tab2TxtSoftwareHouse;
	
	@FXML
	private TextArea tab2AreaAssinatura;
	
	@FXML
	private Button tab1BtAtivar;
	
	@FXML
	private Button tab1BtCancelar;
	
	@FXML
	private Button tab2BtCancelar;
	
	@FXML
	private Button tab2BtAssociarAssinatura;
	
	@FXML
	private TextField tab1TxtAtivacao;
	
	@FXML
	private TextField tab2TxtAtivacao;
	
	@FXML
	private Pane pane;
	
	@FXML
	private AnchorPane anchorPane;
	
	//Ativa o equipamento
	public void onBtAtivarAction(ActionEvent event) {
		result = ativaSAT();
		Alerts.mostraAlerta("Ativacao", "", result, AlertType.INFORMATION);
	}
	//Associa a assinatura
	public void onBtAssociarAssinaturaAction(ActionEvent event) {
		result = associaAssinaturaDoAC();
		Alerts.mostraAlerta("Associar assinatura", "", result, AlertType.INFORMATION);
	}
	
	//Cancela a operação e volta para o menu principal
	public void onBtCancelarAction(ActionEvent event) {
		voltaParaMenuInicial(event);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		anchorPane.getStylesheets().add(Constants.LOCAL_STYLESHEET);
	}
	
	//Pega o código de ativação e o cnpj do contribuinte e envia o comando de ativação para o equipamento
	private String ativaSAT() {
		return ServicosMfe.ativarSAT(tab1TxtAtivacao.getText(), tab1TxtContribuinte.getText());
	}
	//Pega o código de ativação, cnpj da software house, o cnpj do contribuinte e a assinaturaAC e envia o comando de associar assinatura 
	private String associaAssinaturaDoAC() {
		return ServicosMfe.associarAssinatura(tab2TxtAtivacao.getText(), tab2TxtSoftwareHouse.getText() + tab2TxtContribuinte.getText(), tab2AreaAssinatura.getText());
	}
	
	private void voltaParaMenuInicial(ActionEvent event) {
		loadView(Constants.LOCAL_MAIN_VIEW);
	}
	//Veja MainViewController linha - 70
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			ScrollPane newScrollPane = loader.load();
			AnchorPane newAnchorPane = (AnchorPane) newScrollPane.getContent();

			Scene mainScene = Main.getMainScene();
			AnchorPane mainAnchorPane = (AnchorPane) ((ScrollPane) mainScene.getRoot()).getContent();
			mainAnchorPane.getChildren().clear();
			mainAnchorPane.getChildren().addAll(newAnchorPane.getChildren());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
