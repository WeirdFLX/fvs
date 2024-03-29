package util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;


/**
 * Notification Generator!
 * 
 * @author Felix & Silas
 * 
 */
public class NotificationUtils {

	
	/**
	 * @param logOnly
	 * @param severity
	 * @param field
	 * @param summary
	 * @param details
	 * @param severity	: Meldeklasse
	 * @param display	: Anzeige der Meldung
	 * @param reason	: Grund f�r die Meldung
	 * @param bean		: Bean
	 */
	public static void showMessage(boolean logOnly, int severity, String field, String summary, String details){

		Severity ser;
		
		switch (severity) {
		case 0:
			ser = FacesMessage.SEVERITY_INFO; 
			System.out.println("Frontend INFOR. Feld: " + field + " - " + summary);
			break;
		case 1: 
			ser = FacesMessage.SEVERITY_WARN;
			System.out.println("Frontend WARNI. Feld: " + field + " - " + summary);
			break;
		case 2: 
			ser = FacesMessage.SEVERITY_ERROR;
			System.out.println("Frontend ERROR. Feld: " + field + " - " + summary);
			break;			
		case 3: 
			ser = FacesMessage.SEVERITY_FATAL;
			System.out.println("Frontend FATAL. Feld: " + field + " - " + summary);
			break;
		default:
			ser = FacesMessage.SEVERITY_INFO;
			System.out.println("Frontend ?????. Feld: " + field + " - " + summary);
			break;
		}
		if(!logOnly) {
			FacesContext.getCurrentInstance().addMessage(field, new FacesMessage(ser, summary, details));			
		}
	}
	
}
