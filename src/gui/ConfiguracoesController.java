package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import br.com.fandrauss.fx.gui.WindowControllerFx;
import dll_Services.ServicosMfe;
import dll_Services.ServicosXml;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import util.Alerts;
import util.Constants;

public class ConfiguracoesController extends WindowControllerFx implements Initializable {

	private ObservableList<String> obsList;
	List<String> list = new ArrayList<>();
	String[] prio1 = new String [14];
	String result = null;


	@FXML
	private Button btEnviarConfigs;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btPrioridade;

	@FXML
	private Button tab2BtPrioridade;
	
	@FXML
	private Button btEnviar3gConfigs;

	@FXML
	private ComboBox<String> comboInterface;

	@FXML
	private ComboBox<String> comboRede;

	@FXML
	private ComboBox<String> comboPrioridade;

	@FXML
	private ComboBox<String> tab2ComboPrioridade;
	
	@FXML
	private ComboBox<String> comboSeguranca;
	
	@FXML
	private ComboBox<String> comboSelectGPR;

	@FXML
	private TextField txtIp;

	@FXML
	private TextField txtMascara;

	@FXML
	private TextField txtGateway;

	@FXML
	private TextField txtTelefone;

	@FXML
	private TextField txtApn;

	@FXML
	private TextField txtUsuario;

	@FXML
	private TextField txtSenha;

	@FXML
	private TextField txtAtivacao;

	@FXML
	private TextField txtDNS1;

	@FXML
	private TextField txtDNS2;
	
	@FXML
	private TextField txtNomeWifi;
	
	@FXML
	private TextField txtSenhaWifi;
	
	@FXML
	private Label lbNomeWifi;
	
	@FXML
	private Label lbSenhaWifi;
	
	@FXML
	private Label lbSeguranca;
	
	@FXML
	private Pane pane;
	
	@FXML
	private AnchorPane anchorPane;

	public void onBtEnviarConfigsAction(ActionEvent event) {
		prio1 = configuracaoSelecionada(prio1);
		result = enviaConfiguracaoDeRede();
		Alerts.mostraAlerta("Enviar configuracoes de rede", null, result, AlertType.INFORMATION);
	}
	
	public void onBtEnviar3gConfigsAction(ActionEvent event) {
		prio1 = configuracao3gSelecionada(prio1);
		result = enviaConfiguracaoDeRede();
		Alerts.mostraAlerta("Enviar configuracoes de rede", null, result, AlertType.INFORMATION);
	}

	public void onBtCancelarAction(ActionEvent event) {
		voltaParaMenuInicial(event);
	}
	//Comando não implementado. 
	public void onBtPrioridadeAction(ActionEvent event) {
		
	}
	//Faz a seleção do tipo de rede fixo
	public void onComboRedeSelectedItem(ActionEvent event) {
		if (comboRede.getSelectionModel().getSelectedItem().equals("Fixo")) {
			habilitaCamposFixo();
		} else {
			desabilitaCamposFixo();
		}
	}
	//faz a seleção do tipo de rede wifi
	public void onComboInterfaceSelectedItem(ActionEvent event) {
		if(comboInterface.getSelectionModel().getSelectedItem().equals("WIFI")) {
			habilitaCamposWifi();
		}
		else {
			desabilitaCamposWifi();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializarCombos();
		anchorPane.getStylesheets().add(Constants.LOCAL_STYLESHEET);
		}
	//Realiza a configuração conforme a opção selecionada
	public String [] configuracaoSelecionada(String[] prio) {
		
		if (comboInterface.getSelectionModel().getSelectedItem().equals("Ethernet")) {
			configuraDadosEthernet(prio);
		}
		
		if(comboInterface.getSelectionModel().getSelectedItem().equals("WIFI")) {
			configuraDadosWifi(prio);
		}
		
		return prio;
	}	
	//Realiza a configuração de rede móvel
	public String[] configuracao3gSelecionada(String [] prio) {
		configuraDados3g(prio);
		
		return prio;
	}
	//Altera a view caso não for ip fixo
	private void desabilitaCamposFixo() {
		txtIp.setDisable(true);
		txtGateway.setDisable(true);
		txtMascara.setDisable(true);
		txtDNS1.setDisable(true);
		txtDNS2.setDisable(true);
	}
	//Altera a view caso for ip fixo
	private void habilitaCamposFixo() {
		txtIp.setDisable(false);
		txtGateway.setDisable(false);
		txtMascara.setDisable(false);
		txtDNS1.setDisable(false);
		txtDNS2.setDisable(false);
	}
	//Altera a view caso não for wifi
	private void desabilitaCamposWifi() {
		txtNomeWifi.setVisible(false);
		txtSenhaWifi.setVisible(false);
		lbNomeWifi.setVisible(false);
		lbSenhaWifi.setVisible(false);
		lbSeguranca.setVisible(false);
		comboSeguranca.setVisible(false);
	}
	//Altera a view caso for wifi
	private void habilitaCamposWifi() {
		txtNomeWifi.setVisible(true);
		txtSenhaWifi.setVisible(true);
		lbNomeWifi.setVisible(true);
		lbSenhaWifi.setVisible(true);
		lbSeguranca.setVisible(true);
		comboSeguranca.setVisible(true);
	}
	//Inicia todas as comboBox
	private void inicializarCombos() {
		inicializaComboInterface();
		inicializaComboRede();
		inicializaComboPrioridade();
		inicializaComboSeguranca();
		inicializaComboGPR();
	}
	//configura a comboBoxGPR
	private void inicializaComboGPR() {
		list.add("GPR1");
		list.add("GPR2");
		obsList = FXCollections.observableArrayList(list);
		comboSelectGPR.setItems(obsList);
	}
	//inicia a comboBox de criptografia do wifi
	private void inicializaComboSeguranca() {
		list.add("WPA-PERSONAL");
		list.add("WEP");
		obsList = FXCollections.observableArrayList(list);
		comboSeguranca.setItems(obsList);
		list.clear();
	}
	//inicia a comboBox de prioridades de acesso do mfe
	private void inicializaComboPrioridade() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		obsList = FXCollections.observableArrayList(list);
		comboPrioridade.setItems(obsList);
		tab2ComboPrioridade.setItems(obsList);
		list.clear();
	}
	//inicia a comboBox de tipo de rede
	private void inicializaComboRede() {
		list.add("DHCP");
		list.add("Fixo");
		obsList = FXCollections.observableArrayList(list);
		comboRede.setItems(obsList);
		list.clear();
	}
	//inicia a comboBox de interface de rede
	private void inicializaComboInterface() {
		list.add("Ethernet");
		list.add("WIFI");
		obsList = FXCollections.observableArrayList(list);
		comboInterface.setItems(obsList);
		list.clear();
	}
	//reúne as configurações digitadas/selecionadas para configuração wifi. Essas informações são enviadas para um Array de String
	private void configuraDadosWifi(String[] prio) {
		if (comboRede.getSelectionModel().getSelectedItem().equals("DHCP")) {
			prio[0] = "WIFI";
			prio[1] = comboRede.getSelectionModel().getSelectedItem();
			prio[7] = txtNomeWifi.getText();
			prio[8] = txtSenhaWifi.getText();
			prio[9] = comboSeguranca.getSelectionModel().getSelectedItem();
			
		}
		else if(comboRede.getSelectionModel().getSelectedItem().equals("Fixo")) {
			prio[0] = "ETHE";
			prio[1] = comboRede.getSelectionModel().getSelectedItem();
			prio[2] = txtIp.getText();
			prio[3] = txtMascara.getText();
			prio[4] = txtGateway.getText();
			prio[5] = txtDNS1.getText();
			prio[6] = txtDNS2.getText();
			prio[7] = txtNomeWifi.getText();
			prio[8] = txtSenhaWifi.getText();
			prio[9] = comboSeguranca.getSelectionModel().getSelectedItem();
		}
	}
	//reúne as configurações digitadas/selecionadas para configuração de rede Ethernet. Essas informações são enviadas para um Array de String
	private void configuraDadosEthernet(String[] prio) {
		if (comboRede.getSelectionModel().getSelectedItem().equals("DHCP")) {
			prio[0] = "ETHE";
			prio[1] = comboRede.getSelectionModel().getSelectedItem();
		} else if (comboRede.getSelectionModel().getSelectedItem().equals("Fixo")) {
			prio[0] = "ETHE";
			prio[1] = comboRede.getSelectionModel().getSelectedItem();
			prio[2] = txtIp.getText();
			prio[3] = txtMascara.getText();
			prio[4] = txtGateway.getText();
			prio[5] = txtDNS1.getText();
			prio[6] = txtDNS2.getText();
		}
	}
	//reúne as configurações digitadas/selecionadas para configuração de rede móvel. Essas informações são enviadas para um Array de String
	private void configuraDados3g(String[] prio) {
		prio[0] = comboSelectGPR.getSelectionModel().getSelectedItem();
		prio[1] = "DHCP";
		prio[10] = txtTelefone.getText();
		prio[11] = txtApn.getText();
		prio[12] = txtUsuario.getText();
		prio[13] = txtSenha.getText();
	}
	//Envia a configuração de rede para o equipamento
	private String enviaConfiguracaoDeRede() {
		return ServicosMfe.configurarInterfaceRedeMFE(ServicosXml.internetConfig(prio1)+"|||", txtAtivacao.getText());
	}
	
	@Override
	public String getFXML() {
		return Constants.LOCAL_CONFIGURACOES;
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
