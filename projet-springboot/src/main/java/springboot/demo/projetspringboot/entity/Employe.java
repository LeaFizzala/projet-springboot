package springboot.demo.projetspringboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee") //!\ Chad a mis deux E à employé dans sa BDD !
public class Employe {
	
	// définir les champs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String prenom;
	
	@Column(name="last_name")
	private String nom;
	
	@Column(name="email")
	private String email;
	
	// générer le constructeur
	
	public Employe() {}  // Hibernate a besoin d'un constructeur vide

	public Employe(String prenom, String nom, String email) {
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
	}

	
	//générer les getters / setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	// générer la méthode toString()
	
	@Override
	public String toString() {
		return "Employe [id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", email=" + email + "]";
	};
	
	
	
	

}
