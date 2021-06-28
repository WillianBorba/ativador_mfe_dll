package dll_Services;

import java.util.Random;

import com.sun.jna.Pointer;

import util.Constants;
import util.ServicosString;



public class ServicosMfe {
	
	static GerMFE_dll mfe = GerMFE_dll.INSTANCE;
	
	static Random random = new Random();
	
	
	String result = null;
	
	public ServicosMfe() {
		
	}

	
	public static String consultarMfe() {
		
		Pointer funcao = mfe.ConsultarSAT(random.nextInt(999999));
		return funcao.getString(0);

	}
	
	public static String consultarStatusOperacional(String codigoAtivacao) {
		
		Pointer funcao = mfe.ConsultarStatusOperacionalMFE(random.nextInt(999999), codigoAtivacao);
		return funcao.getString(0);

	}
	
	public static String consultarNumeroSessao(String codigoAtivacao, int cSessao) {
		
		Pointer funcao = mfe.ConsultarNumeroSessao(random.nextInt(999999), codigoAtivacao, cSessao);
		return funcao.getString(0);
	
	}
	
	public static String ativarSAT(String codigoAtivacao, String cnpj) {
		
		Pointer funcao = mfe.AtivarSAT(random.nextInt(999999), 1, codigoAtivacao, cnpj, 23);
		return funcao.getString(0);
		
	}
	
	public static String associarAssinatura(String codigoAtivacao, String CNPJvalue, String assinaturaCNPJs) {
		Pointer funcao = mfe.AssociarAssinatura(random.nextInt(999999), codigoAtivacao, CNPJvalue, assinaturaCNPJs);
		return funcao.getString(0);
	}
	
	public static String extrairLogs(String codigoAtivacao) {
		Pointer funcao = mfe.ExtrairLogs(random.nextInt(999999), codigoAtivacao);
		String aux[] = ServicosString.splitLog(funcao.getString(0));
		try {
		return ServicosString.base64toString(aux[5]);	
		}
		catch(ArrayIndexOutOfBoundsException e) {
			return funcao.getString(0);
		}
	}
	
	public static String bloquearSAT(String codigoAtivacao) {
		Pointer funcao = mfe.BloquearSAT(random.nextInt(999999), codigoAtivacao);
		return funcao.getString(0);
	}
	
	public static String desbloquearSAT(String codigoAtivacao) {
		Pointer funcao = mfe.DesbloquearSAT(random.nextInt(999999), codigoAtivacao);
		return funcao.getString(0);
	}
	
	public static String trocarCodigoAtivacao(String codigoAtivacao,int tipo, String novoCodigoAtivacao, String confNovoCodigoAtivacao) {
		Pointer funcao = mfe.TrocarCodigoDeAtivacao(random.nextInt(999999), codigoAtivacao, tipo, novoCodigoAtivacao, confNovoCodigoAtivacao);
		return funcao.getString(0);
	}

	public static String atualizarSoftwareSAT(String codigoAtivacao) {
		Pointer funcao = mfe.AtualizarSoftwareSAT(random.nextInt(999999), codigoAtivacao);
		return funcao.getString(0);
	}
	
	public static String configurarInterfaceRedeMFE(String xml, String codigoAtivacao) {
		Pointer funcao = mfe.ConfigurarInterfaceDeRedeMFE(random.nextInt(999999), codigoAtivacao, xml);
		return funcao.getString(0);
	}
	
	public static String testeFimAFim(String codigoAtivacao, String venda) {
		Pointer funcao = mfe.TesteFimAFim(random.nextInt(999999), codigoAtivacao, venda);
		return funcao.getString(0);
	}
	
	public static String enviarDadosVenda(String codigoAtivacao, String venda) {
		Pointer funcao = mfe.EnviarDadosVenda(random.nextInt(999999), codigoAtivacao, venda);
		try{
			String [] splitted = funcao.getString(0).split(Constants.SPLIT_STR);
			ServicosString.salvaArquivo(splitted[8], "C:\\Users\\wborba\\Documents\\Vendas");
		}catch(Exception e) {
			
		}
		
		return funcao.getString(0);
	}
	
	public static String cancelarVenda(String codigoAtivacao,  String chave ,String dados) {
		Pointer funcao = mfe.CancelarUltimaVenda(random.nextInt(999999), codigoAtivacao, chave, dados);
		return funcao.getString(0);
	}
}
