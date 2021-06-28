package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import util.Constants;
import br.com.fandrauss.fx.gui.WindowControllerFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class MainViewController	extends WindowControllerFx implements Initializable {
	
	@FXML
	private Button btAtivacao;
	
	@FXML
	private Button btTestes;
	
	@FXML
	private Button btConfiguracoes;
	
	@FXML
	private Button btOutrasOpcoes;
	
	@FXML
	private Pane paneBackground;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Label labelAtivador;
	//Carrega a tela de ativação
	public void onBtAtivacaoAction(ActionEvent event) {
		loadView(Constants.LOCAL_ATIVACAO);		
	}
	//Carrega a tela de testes
	public void onBtTestesAction(ActionEvent event) {
		loadView(Constants.LOCAL_TESTES);
	}
	//Carrega a tela de configurações
	public void onBtConfiguracoesAction(ActionEvent event) {
		loadView(Constants.LOCAL_CONFIGURACOES);
	}
	//Carrega a tela de outras opções
	public void onBtOutrasOpcoesAction(ActionEvent event) {
		loadView(Constants.LOCAL_OUTRAS_OPCOES);
	}
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	@Override
    public String getFXML() {
        return Constants.LOCAL_MAIN_VIEW;
    }
	//Carrega a view para cada botão clicado. absoluteName é o nome do fxml a ser carregado
	private synchronized void loadView(String absoluteName) {
		try {
			//Carrega o fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			AnchorPane newAnchorPane = loader.load();
			//Pega a scene principal e armazena
			Scene mainScene = Main.getMainScene();
			//Pega o AnchorPane que está dentro da scene principal e armazena
			AnchorPane mainAnchorPane = (AnchorPane) ((ScrollPane) mainScene.getRoot()).getContent();
			//Limpa o AnchorPane armazenado
			mainAnchorPane.getChildren().clear();
			//Carrega o AnchorPane armazenado com o fxml que foi carregado
			mainAnchorPane.getChildren().addAll(newAnchorPane.getChildren());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
