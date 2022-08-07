package springboot.demo.projetspringboot.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.demo.projetspringboot.dao.EmployeDAO;
import springboot.demo.projetspringboot.entity.Employe;

@RestController
@RequestMapping("/api")
public class EmployeRestController {
	
	// injection du DAO
	private EmployeDAO employeDAO;
	
	//constructeur pour injection
	
	@Autowired
	public EmployeRestController (EmployeDAO theEmployeDAO) {
		employeDAO = theEmployeDAO;
	}
	
	// créer le chemin vers la liste des employes
	@GetMapping("/employes")
	public List<Employe> attrapezLesTous(){
		return employeDAO.attrapezLesTous();
	}
	
	//créer le chemin pour la methode GET employe/employeParId
	
	@GetMapping("employes/{employeId}")
	public Employe trouverEmploye(@PathVariable int employeId) {
		
		Employe employe = employeDAO.trouverParId(employeId);
		
		if ( employe == null) {
			throw new RuntimeException("L'employe " + employeId +" n'a pas été trouvé");
		}
		return employe;
	}
	
	// ajouter un mapping pour POST /employes -- pour ajouter des employes
	
	@PostMapping("/employes")
	public Employe ajouterEmploye(@RequestBody Employe employe) {
		
		//au cas ou une ID soit passee dans l'objet JSON on la force
		// a etre a 0 pour enclencher la sauvegarde du nouvel objet
		
		employe.setId(0);
		
		employeDAO.sauvegarder(employe);
				
		return employe;
		
	}
	
	// ajouter un mapping pour PUT / employes - update 
	
	@PutMapping("/employes")
	public Employe majEmploye(@RequestBody Employe employe) {
		employeDAO.sauvegarder(employe);
		return employe;
	}
	
	//supprimer un employe avec un DELETE mapping pour /epmoyes/{employeId}
	
	@DeleteMapping("employes/{employeId}")
	public String supprimerEmploye(@PathVariable int employeId) {
	
	Employe tempEmploye = employeDAO.trouverParId(employeId);
	
	// renvoyer une exception si l'employe n existe pas
	
	if(tempEmploye==null) {
		throw new RuntimeException("Pas trouvé" + employeId);
	}
	employeDAO.supprimerParId(employeId);
	
	return employeId + " a été supprimé ";
	
	}
}






