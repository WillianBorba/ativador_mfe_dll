package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import util.Constants;
import br.com.fandrauss.fx.gui.WindowControllerFx;
import dll_Services.ServicosMfe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OutrasOpcoesController extends WindowControllerFx implements Initializable{
	
	File file;
	String result = null;
	String xmlCancelamento = null;
	String chave = null;
	private BufferedReader leitor;
	
	@FXML
	private Button btEnviarTeste;
	
	@FXML
	private Button btSelecionarArquivo;
	
	@FXML
	private Button btExtrairLog;
	
	@FXML
	private Button btBloquear;
	
	@FXML
	private Button btDesbloquear;
	
	@FXML
	private Button btAtualizar;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	private Button btCancelarVenda;
	
	@FXML
	private Button btTrocarCodigo;
	
	@FXML
	private TextField txtAtivacao;
	
	@FXML
	private TextField txtVenda;
	
	@FXML
	private TextField txtCancelar;
	
	@FXML
	private TextArea areaConsole;
	
	@FXML
	private AnchorPane anchorPane;
	
	//Envia teste de venda
	public void onBtEnviarTesteAction(ActionEvent event) {
		String xml = lerArquivoDeTeste();		
		enviarVendaTeste(xml);		
	}
	//Seleciona o arquivo	
	public void onBtSelecionarArquivoAction(ActionEvent event) {
		selecionaArquivo();
	}
	//Extrai o log
	public void onBtExtrairLogAction(ActionEvent event) {
		extrairLog();
	}
	//Envia bloqueio
	public void onBtBloquearAction(ActionEvent event) {
		enviarComandoDeBloqueio();
	}
	//Envia desbloqueio
	public void onBtDesbloquarAction(ActionEvent event) {
		enviarComandoDeDesbloqueio();
	}
	//Envia atualizar
	public void onBtAtualizarAction(ActionEvent event) {
		enviarComandoDeAtualizarMFE();
	}
	//Cancela e volta ao menu principal
	public void onBtCancelarAction(ActionEvent event) {
		voltaParaMenuInicial(event);
	}
	//Cancela venda
	public void onBtCancelarVendaAction(ActionEvent event) {
		cancelaVenda(xmlCancelamento, chave);
	}
	//Abre a view de troca de codigo de ativação
	public void onBtTrocarCodigoAction(ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(Constants.LOCAL_TROCA_CODIGO));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.setTitle("Troca de código de ativação");
			stage.setScene(new Scene(root, 488, 429));
			stage.show();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		anchorPane.getStylesheets().add(Constants.LOCAL_STYLESHEET);
	}
	
	//Faz a leitura do arquivo xml para venda e salva em uma String só
	private String lerArquivoDeTeste() {
		String xml="";
		try{
			 FileReader reader = new FileReader(file.getAbsolutePath());  
	         leitor = new BufferedReader(reader);
	         String linha;
	 		while ((linha = leitor.readLine()) != null) {
	 			xml = xml+linha;
	 		}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return xml;
	}
	//pressiona o botao de enviar venda
	private void enviarVendaTeste(String xml) {
		result = ServicosMfe.enviarDadosVenda(txtAtivacao.getText(), xml);	
		areaConsole.setText(result);
	}
	//seleciona o arquivo para envio de venda e passa o caminho do xml de venda
	private void selecionaArquivo() {
		FileChooser fc = new FileChooser();
		file = fc.showOpenDialog(this.getWindow());
		txtVenda.setText(file.getPath());
	}
	//comando de extrair log
	private void extrairLog() {
		areaConsole.setText(ServicosMfe.extrairLogs(txtAtivacao.getText()));
	}
	//Envia o comando de bloqueio para o equipamento
	private void enviarComandoDeBloqueio() {
		areaConsole.setText(ServicosMfe.bloquearSAT(txtAtivacao.getText()));
	}
	//Envia o comando de desbloqueio para o equipamento
	private void enviarComandoDeDesbloqueio() {
		areaConsole.setText(ServicosMfe.desbloquearSAT(txtAtivacao.getText()));
	}
	//Envia o comando para atualização do equipamento
	private void enviarComandoDeAtualizarMFE() {
		areaConsole.setText(ServicosMfe.atualizarSoftwareSAT(txtAtivacao.getText()));
	}
	//Carrega a view de menu principal
	private void voltaParaMenuInicial(ActionEvent event) {
		loadView(Constants.LOCAL_MAIN_VIEW);	
	}
	//Manda o cancelamento da venda seguindo um xml de cancelamento
	private void cancelaVenda(String xmlCancelamento, String chave) {
		areaConsole.setText(ServicosMfe.cancelarVenda(txtAtivacao.getText(), chave, xmlCancelamento));
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
