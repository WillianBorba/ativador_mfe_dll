package dll_Services;

public class ServicosXml {
	static String cabecalho = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	public static String internetConfig(String[] parametros) {
		String xmlConfig = "";
		String tipoInter = parametros[0];
		String tipoLan = parametros[1];
		String proxy = "0";
		String lanIP = parametros[2];
		String lanMask = parametros[3];
		String lanGW = parametros[4];
		String lanDNS1 = parametros[5];
		String lanDNS2 = parametros[6];
		String ssid = parametros[7];
		String codigo = parametros[8];
		String seg = parametros[9];
		String phone = parametros[10];
		String apn = parametros[11];
		String usuario = parametros[12];
		String senha = parametros[13];

		String redePadrao = cabecalho + "<config><tipoInter>" + tipoInter +
				"</tipoInter><tipoLan>" + tipoLan + "</tipoLan>";
		
		if(tipoLan.equals("DHCP")) {
			if(tipoInter.equals("ETHE")) {
				xmlConfig = redePadrao + "<proxy>" + proxy + "</proxy></config>";
			}
			else if(tipoInter.equals("WIFI")) {
				xmlConfig = redePadrao + "<proxy>" + proxy + "</proxy>"
						+ "<SSID>" + ssid + "</SSID><codigo>" + codigo + "</codigo><seg>"
						+ seg + "</config>";
			}
		}
		else if(tipoLan.equals("Fixo")) {
			if(tipoInter.equals("ETHE")) {
			xmlConfig = redePadrao +
					"<lanIP>" + lanIP + "</lanIP><lanMask>" + lanMask +
					"</lanMask><lanGW>" + lanGW + "</lanGW><lanDNS1>" +
					lanDNS1 + "</lanDNS1><lanDNS2>" + lanDNS2 + "</lanDNS2>" 
					+ "<proxy>" + proxy + "</proxy></config>";
			}
			else if(tipoInter.equals("WIFI")) {
				xmlConfig = redePadrao +
						"<lanIP>" + lanIP + "</lanIP><lanMask>" + lanMask +
						"</lanMask><lanGW>" + lanGW + "</lanGW><lanDNS1>" +
						lanDNS1 + "</lanDNS1><lanDNS2>" + lanDNS2 + "</lanDNS2>" 
						+ "<proxy>" + proxy + "</proxy>" + "<SSID>" + ssid + "</SSID><codigo>" + codigo + "</codigo><seg>"
						+ seg + "</config>";
			}
			
		}
		else if(tipoLan.equals("GPR1") || tipoLan.equals("GPR2")) {
			xmlConfig = redePadrao + "<phone>" + phone + "</phone><apn>" + apn +
					"</apn><usuario>" + usuario + "</usuario><senha>" + senha + "</senha>"
					+ "<proxy>" + proxy + "</proxy></config>";
		}
		
		return xmlConfig;
	}
	

}
