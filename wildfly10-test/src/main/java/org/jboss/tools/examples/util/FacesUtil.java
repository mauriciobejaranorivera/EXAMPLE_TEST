package org.jboss.tools.examples.util;

import java.io.IOException;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author mauriciobejaranorivera
 *
 */
public class FacesUtil {

	private static final String ORIGINAL = "ÁáÉéÍíÓóÚúÑñÜü";
	private static final String REPLACEMENT = "AaEeIiOoUuNnUu";

	public static void infoMessage(String msg, String detalle) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, detalle));
	}

	public static void warnMessage(String msg) {
		FacesContext.getCurrentInstance()
				.addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, msg,
								"ADVERTENCIA"));
	}

	public static void errorMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "ERROR"));
	}

	public static void fatalMessage(String msg) {
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, msg,
						"ERROR FATAL"));
	}


	/**
	 * Redirecciona a una pagina contenida en /src/main/webapp
	 * 
	 * @param url Ej: /pages/index.xhtml
	 * @throws IOException excepcion de error
	 */
	public static void redirect(String url) throws IOException {
		//FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();
		context.getExternalContext().redirect(
				request.getContextPath() + url);
	}

	
	public static String getRequestContextPath() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
	}

	/**
	 * Quitar Acentos
	 * 
	 * @param str
	 * @return
	 */
	public static String stripAccents(Object str) {
		if (str == null) {
			return null;
		}
		char[] array = str.toString().toCharArray();
		for (int index = 0; index < array.length; index++) {
			int pos = ORIGINAL.indexOf(array[index]);
			if (pos > -1) {
				array[index] = REPLACEMENT.charAt(pos);
			}
		}
		return new String(array);
	}
	
	/**
	 * Generar Cadena
	 * @param longitud de la cadena que se generá
	 * @return
	 */
	public static String geerarCadenaAlfanumAleatoria(int longitud){
		StringBuffer cadenaAleatoria = new StringBuffer();
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i < longitud){
			char c = (char)r.nextInt(255);
			if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
				cadenaAleatoria.append(c);
				i ++;
			}
		}
		return cadenaAleatoria.toString();
	}

}
