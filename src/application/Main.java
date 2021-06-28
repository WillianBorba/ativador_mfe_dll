/*
 *   Autor: Willian_Borba
 *   
 *   Sistema: Exemplo de ativador MFE usando dll como comunicação
 *   
 *   Data: 13/05/2019
 *   
 */


package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane parent = loader.load();
			parent.setFitToHeight(true);
			parent.setFitToWidth(true);
			mainScene = new Scene(parent);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Ativador GerMfe");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Scene getMainScene() {
		return mainScene;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
