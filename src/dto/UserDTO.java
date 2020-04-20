package dto;

import java.io.Serializable;

import entity.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int uId;

	private String email;

	private String name;

	private String passwort;

	private String privilegien;

	private String vorname;

	public UserDTO() {
	}
	
	public UserDTO(User userEntity) {
		
		this.uId = userEntity.getUId();
		this.email = userEntity.getEmail();
		this.name = userEntity.getName();
		this.passwort = userEntity.getPasswort();
		this.privilegien = userEntity.getPrivilegien();
		this.vorname = userEntity.getVorname();
		
	}

	public int getUId() {
		return this.uId;
	}

	public void setUId(int uId) {
		this.uId = uId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPrivilegien() {
		return this.privilegien;
	}

	public void setPrivilegien(String privilegien) {
		this.privilegien = privilegien;
	}

	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

}