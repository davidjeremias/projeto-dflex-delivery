package com.u2d.projeto.util;

import com.u2d.projeto.exception.NegocioException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.Base64;
import java.util.List;

public class StringUtil {

    //---------------------------------------------------
    /** construtor private para evitar instanciação. */
    //---------------------------------------------------
    private StringUtil() { }

    //------------------------------------------------------------------
    /** Verifica se a string contem texto diferente de espaco em branco.
     *
     * @param string String a ser analisada
     * @return true se contem texto diferente de espaco em branco.
     */
    //------------------------------------------------------------------
    public static boolean contemTexto(String string) {
        if(string == null) {
            return false ;
        }
        if(string.trim().equals("")) {
            return false ;
        }
        return true ;
    }

    //----------------------------------------------
    /** Remove os caracteres . / - e _ da cadeia */
    //----------------------------------------------
    public static String removeMascara(String cadeia) {
        if(cadeia != null) {
            cadeia=cadeia.replace('.',' ');
            cadeia=cadeia.replace('(',' ');
            cadeia=cadeia.replace(')',' ');
            cadeia=cadeia.replace(':',' ');
            cadeia=cadeia.replace('/',' ');
            cadeia=cadeia.replace('-',' ');
            cadeia=cadeia.replace('_',' ');
            cadeia=cadeia.replaceAll(" ","");
        }
        return cadeia ;
    }

    //----------------------------------------------
    /** Remove os caracteres . / - e _ da cadeia */
    //----------------------------------------------
    public static String removeMascaraCPF(String cadeia) {
        if(cadeia != null) {
            cadeia=cadeia.replace('.',' ');
            cadeia=cadeia.replace('-',' ');
            cadeia=cadeia.replaceAll(" ","");
        }
        return cadeia ;
    }

    public static Boolean isCPF(String cadeia) {
        Boolean cpf = false;
        if(cadeia != null) {
            String numero = removeMascaraCPF(cadeia);
            if(numero.length() == 11) {
                return cpf = true;
            }else {
                return cpf = false;
            }
        }
        return cpf;
    }

    //----------------------------------------------
    /** Substitui as aspas por sublinhado da cadeia */
    //----------------------------------------------
    public static String trataAspas(String cadeia) {
        if(cadeia.equals("'")) {
            cadeia = "";
        }
        if(cadeia != null) {
            cadeia=cadeia.replaceAll("'","_");
        }
        return cadeia ;
    }

    //----------------------------------------------
    /** Mascara telefones com 8 e 9 digitos
     * @throws NegocioException */
    //----------------------------------------------
    public static String mascaraTelefone(String telefone) throws NegocioException {
        String novoTelefone = null;
        if(telefone != null && !telefone.isEmpty()){
            if(telefone.length() < 10 || telefone.length() > 11) {
                throw new NegocioException("Número de telefone com quantidade de caracteres inválido ex.: tel fix 99 9999 9999 tel mov 99 99999 9999");
            }
            if(telefone.length() == 10) {
                novoTelefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 6) + "-" + telefone.substring(6, 10);
            }else {
                novoTelefone = "(" + telefone.substring(0, 2) + ")" + telefone.substring(2, 7) + "-" + telefone.substring(7, 11);
            }
        }
        return novoTelefone ;
    }

    //--------------------------------------------------------------------
    /** Verifica se o valor informado pode ser convertido para inteiro */
    //--------------------------------------------------------------------
    public static boolean isInteiro(String valor) {
        try {
            Long.parseLong(valor);
            return true ;
        } catch(Exception e) {
            return false ;
        }
    }

    public static Boolean verificaNumero(String valor) {
        try {
            Integer.valueOf(valor);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }


    //-----------------------------------------------------------------------
    /** Gera uma string contendo os itens da lista, separados por virgula */
    //-----------------------------------------------------------------------
    public static String listaToString(List<String> lista) {
        StringBuffer retorno = new StringBuffer("");
        for (String itemLista : lista) {
            if(retorno.length() > 0){
                retorno.append(", ");
            }
            retorno.append(itemLista);
        }
        return retorno.toString();
    }


    public static boolean isDigit(String s) {
        if(s!= null) {
            return s.matches("[0-9]*");
        }
        return false;
    }


    //------------------------------------------------------------
    /** Remove os acentos da cadeia */
    //------------------------------------------------------------
    public static String removerAcentos(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    //------------------------------------------------------------
    /** Converte o valor para string, com duas casas decimais */
    //------------------------------------------------------------
    public static String bigDecimalToString(BigDecimal valor) {
        DecimalFormat df = new DecimalFormat( "##0.00" );
        return df.format(valor);
    }


    //------------------------------------------------------------
    /** Remove espacos duplos que aparecem no meio da cadeia */
    //------------------------------------------------------------
    public static String removeEspacosDuplos(String cadeia) {
        if(cadeia == null) return null ;
        int tamanho = 0 ;
        cadeia = cadeia.trim() ;
        while(tamanho != cadeia.length()) {
            tamanho = cadeia.length() ;
            cadeia = cadeia.replaceAll("  ", " ") ;
        }
        return cadeia ;
    }


    //--------------------------------------------------------------------------------------------------
    /** Retorna true se as duas cadeia contém o mesmo texto,  desconsiderando maiusculas/minusculas. */
    //--------------------------------------------------------------------------------------------------
    public static boolean contemTextoIgual(String cadeia1, String cadeia2) {
        if(!contemTexto(cadeia1)) return false ;
        if(!contemTexto(cadeia2)) return false ;
        return cadeia1.equalsIgnoreCase(cadeia2) ;
    }

    //-----------------------------------------------------------
    /** Retorna a string contendo caracteres maiusculos */
    //-----------------------------------------------------------
    public static String toUpperCase(String cadeia) {
        if(contemTexto(cadeia)) {
            cadeia = cadeia.toUpperCase().trim() ;
        }
        return cadeia ;
    }


    //-----------------------------------------------------------
    /** Retorna a string contendo caracteres maiusculos */
    //-----------------------------------------------------------
    public static String toLowerCase(String cadeia) {
        if(contemTexto(cadeia)) {
            cadeia = cadeia.toLowerCase().trim() ;
        }
        return cadeia ;
    }

//	/**
//	 * Caso o ponto informado não contenha '_', o ponto retornado e preposicionado por 'P_'
//	 *
//	 * @param ponto
//	 * @return
//	 */
//	public static String formataPonto(String ponto){
//		return formataPonto(ponto, true);
//	}

//	/**
//	 * Caso o ponto informado não contenha '_', o ponto retornado e preposicionado por 'P_'
//	 *
//	 * @param ponto
//	 * @return
//	 */
//    public static String formataPonto(String ponto, boolean lancarErro) {
//    	if(StringUtils.isNotBlank(ponto)) {
//    		ponto  = ponto.toUpperCase();
//    		if(ponto.indexOf("_") == -1)
//    			ponto = Usuario.PREFIXO_PONTO_FUNCIONARIO+ponto;
//    		if(ponto.length() > 10 && lancarErro)
//    			throw new RuntimeException("ponto inválido!");
//    	}
//        return ponto;
//    }

    public static String readUrl(String urlString) throws Exception {

        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                // No need to implement.
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                // No need to implement.
            }
        } };

        BufferedReader reader = null;

        try {

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            URL url = new URL(urlString.replace(" ", "%20"));

            URLConnection conn = url.openConnection(Proxy.NO_PROXY);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				/*
				System.setProperty("https.proxyHost", "proxy-internet.redecamara.camara.gov.br");
				System.setProperty("https.proxyPort", "80");
				System.setProperty("http.proxyUser", "usuariocotas");
				System.setProperty("http.proxyPassword", "semprealerta");

				reader = new BufferedReader(new InputStreamReader(url.openStream()));
				*/

            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        }  finally {
            if (reader != null)
                reader.close();
            reader = null;
        }

    }

    public static String concatenar(Object ... args) {

        if(args == null || args.length == 0)
            return "";

        StringBuilder retorno = new StringBuilder("");

        for (int i = 0; i < args.length; i++) {
            Object object = args[i];
            if(object !=null)
                retorno.append(object);
        }

        return retorno.toString();
    }

    public static String converterStringToBase64(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public static String converterBase64ToString(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        return new String(decodedBytes);
    }
}
