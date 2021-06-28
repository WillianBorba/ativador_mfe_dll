package util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ServicosString {
	
	public ServicosString() {
		
	}
	//Divide o log pela pipeLine
	public static String [] splitLog(String log) {
		String [] splittedString = log.split(Constants.SPLIT_STR);
		return splittedString;
	}
	//converte valores de base64 para String
	public static String base64toString(String log) {
		byte[] valueDecoded = Base64.getDecoder().decode(log);	
		return "Decoded value is " + new String(valueDecoded);
	}
	/*Retorna uma String que vai ser distribuida por cada pipeline no vetor legenda,
	 * em cada posicão do array, vai pular uma linha. Os valores distribuídos virão do splitLog
	 */
	public static String imprimeStatus(String status){
        String[] textoStatusOperacional = status.split(Constants.SPLIT_STR);
                
        String[] legenda = (Constants.LEGENDA).split(Constants.SPLIT_STR);        
        String texto = "";
            for (int i = 0; i < textoStatusOperacional.length; i++){
                texto += legenda[i] + " = " + textoStatusOperacional[i] + "\n";
            }
            return texto;
	}
	//Acessar o stage aonde o controller do evento está
	public static Stage pegaStageAtual(ActionEvent event) {
		return (Stage)((Node)event.getSource()).getScene().getWindow();
	}
	//Pega o arquivo e salva. o nome do arquivo vai ser a data que ele for retirado
	public static void salvaArquivo(String toSave, String caminho) {
		File file = null;
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date data = new Date(System.currentTimeMillis());		
		String date = sdf.format(data);
		
		try {
			file = new File(caminho + "//" + date + ".txt");
	
		if (!file.exists()) {
			file.createNewFile();
		} 
	
		FileWriter fw = new FileWriter(file);

		fw.write(toSave);
		System.out.println("Salvo com sucesso");
		fw.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
