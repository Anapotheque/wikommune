package fr.egloo.wikommune.web.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Commune {

	private String libelle;
	
	private String codePostal;
	
	public Commune(String libelle, String codePostal){
		this.libelle = libelle;
		this.codePostal = codePostal;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
}
