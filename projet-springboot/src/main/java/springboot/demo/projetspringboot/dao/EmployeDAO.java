package springboot.demo.projetspringboot.dao;

import java.util.List;

import springboot.demo.projetspringboot.entity.Employe;

public interface EmployeDAO {
	
	//méthode simple pour lister les employés dans la BDD
	
	public List<Employe> attrapezLesTous();
	
	public Employe trouverParId(int id);
	
	public void sauvegarder(Employe employe);
	
	public void supprimerParId(int id);

}
