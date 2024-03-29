package beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import dao.UserDAO;
import dto.UserDTO;
import util.NotificationUtils;
import util.SessionUtils;

/**
 * 
 * CDI-Bean f�r login.xhtml und logout Prozess
 *
 * @author Felix & Silas
 *
 */
@Named("login")
@ApplicationScoped
public class LoginBean {

	@Inject
	UserDAO userDAO;

	UserDTO userDTO;

	// init Methode um userDTO zu initialisieren
	@PostConstruct
	public void init() {
		userDTO = new UserDTO();
	}

	public UserDTO getUserDTO() {
		return this.userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	// Abfrage ob angemeldeter User Manager ist
	public boolean isManager() {
		if(SessionUtils.getPrivilegien().equals("Manager")) {
			return true;
		} else {
			return false;
		}
	}

	// Abfrage ob angemeldeter User Admin ist
	public boolean isAdmin() {
		if(SessionUtils.getPrivilegien().equals("Admin")) {
			return true;
		} else {
			return false;
		}
	}

	// Abfrage ob angemeldeter User was h�herrangiges als Mitarbeiter ist
	public boolean isNotMitarbeiter() {
		if(SessionUtils.getPrivilegien().equals("Mitarbeiter")) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Eingaben werden validiert und Login-Prozess wird angesto�en Session wird ggf. aufgebaut
	 * Login
	 */
	public String validate() {
		if (!userDAO.valid(userDTO)) {
			NotificationUtils.showMessage(false, 1, "login:password", "E-Mail / Passwort falsch",
					"Die E-Mail oder das Passwort ist falsch.");
			return "login";
		}

		userDTO = userDAO.getByEmail(userDTO.getEmail());

		SessionUtils.setUid(userDTO.getUid());
		SessionUtils.setEmail(userDTO.getEmail());
		SessionUtils.setPrivilegien(userDTO.getPrivilegien());

		NotificationUtils.showMessage(true, 0, "", SessionUtils.getEmail() + " hat sich angemeldet", "");

		return "index";
	}

	/**
	 * Session wird zerst�rt / Logout
	 */
	public String invalidate() {
		NotificationUtils.showMessage(true, 0, "", SessionUtils.getEmail() + " hat sich abgemeldet.", "");
		SessionUtils.invalidate();
		this.userDTO = new UserDTO();

		return "login";
	}
}