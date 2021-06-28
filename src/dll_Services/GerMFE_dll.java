package dll_Services;

import util.Constants;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

public interface GerMFE_dll extends StdCallLibrary{
	
	GerMFE_dll INSTANCE = (GerMFE_dll) Native.loadLibrary(Constants.LIB_MFE, GerMFE_dll.class);
	
	public Pointer AtivarSAT(int numeroSessao, int subComando, String codigoDeAtivacao, String CNPJ, int cUF);
	public Pointer EnviarDadosVenda(int numeroSessao, String codigoDeAtivacao, String dadosVenda);
	public Pointer CancelarUltimaVenda(int numeroSessao, String codigoDeAtivacao, String chave, String dadosCancelamento);
	public Pointer ConsultarSAT(int numeroSessao);
	public Pointer TesteFimAFim(int numeroSessao, String codigoDeAtivacao, String dadosVenda);
	public Pointer ConsultarStatusOperacional(int numeroSessao, String codigoDeAtivacao);
	public Pointer ConsultarStatusOperacionalMFE(int numeroSessao, String codigoDeAtivacao);
	public Pointer ConsultarNumeroSessao(int numeroSessao, String codigoDeAtivacao, int cNumeroDeSessao);
	public Pointer ConfigurarInterfaceDeRedeMFE(int numeroSessao, String codigoDeAtivacao, String dadosConfiguracao);
	public Pointer AssociarAssinatura(int numeroSessao, String codigoDeAtivacao, String CNPJvalue, String assinaturaCNPJs);
	public Pointer ExtrairLogs(int numeroSessao, String codigoDeAtivacao);
	public Pointer BloquearSAT(int numeroSessao, String codigoDeAtivacao);
	public Pointer DesbloquearSAT(int numeroSessao, String codigoDeAtivacao);
	public Pointer TrocarCodigoDeAtivacao(int numeroSessao, String codigoDeAtivacao, int opcao, String novoCodigo, String confNovoCodigo);
	public Pointer AtualizarSoftwareSAT(int numeroSessao, String codigoDeAtivacao);
	
}
