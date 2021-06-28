package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import javafx.stage.FileChooser;
import util.Alerts;
import util.Constants;
import util.ServicosString;

public class TestesController extends WindowControllerFx implements Initializable{
	
	String result = null;
	private BufferedReader leitor;
	
	@FXML
	private Button btConsultarMfe;
	
	@FXML
	private Button btStatus;
	
	@FXML
	private Button btTesteFimAFim;
	
	@FXML
	private TextArea areaResultado;
	
	@FXML
	private TextField txtAtivacao;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	private AnchorPane anchorPane;

	
	//Consulta mfe
	public void onBtConsultarMfeAction(ActionEvent event) {
		consultaMfe();
	}
	//Consulta status
	public void onBtStatusAction(ActionEvent event) {
		statusMfe();
	}
	//Teste fim a fim
	public void onBtTesteFimAFimAction(ActionEvent event) {
		testeFimAFim();
	}
	//Cancela e volta ao menu principal
	public void onBtCancelarAction(ActionEvent event) {
		voltaParaMenuInicial(event);
	}

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		anchorPane.getStylesheets().add(Constants.LOCAL_STYLESHEET);
	}
	//Manda o comando de consulta mfe e mostra como alerta o resultado que vem como String direto do equipamento
	private void consultaMfe() {
		result = ServicosMfe.consultarMfe();
		Alerts.mostraAlerta("Status", null, result, AlertType.INFORMATION);
	}
	//recebe o comando de status do mfe e imprime na textArea utilizada como console
	private void statusMfe() {
		result = ServicosString.imprimeStatus(ServicosMfe.consultarStatusOperacional(txtAtivacao.getText()));
		areaResultado.setText(result);
	}
	//Envia o comando de teste fim a fim a partir do xml selecionado. O resultado é imprimido na textArea utilizada como console
	private void testeFimAFim() {
		String xml = selecionaArquivoXml();
		
		areaResultado.setText(ServicosMfe.testeFimAFim(txtAtivacao.getText(), xml));
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
	//Seleciona o arquivo xml para ser utilizado
	private String selecionaArquivoXml() {
		FileChooser fc = new FileChooser();
		File file = fc.showOpenDialog(this.getWindow());
		String xml = null;
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

}
