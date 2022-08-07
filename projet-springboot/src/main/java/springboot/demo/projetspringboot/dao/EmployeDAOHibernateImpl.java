package springboot.demo.projetspringboot.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.demo.projetspringboot.entity.Employe;

@Repository
public class EmployeDAOHibernateImpl implements EmployeDAO {
	
	//l'objet entityManager s'occupe de gérer la persistence, soit la possibilité d'effectuer
	// des opérations CRUD entre la classe Java et la BDD correspondante
	// cet objet est automatiquement créé par Springboot
	
	private EntityManager entityManager;
	
	//ici on utilise l'injection de dépendance via le constructeur 
	@Autowired
	public EmployeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public List<Employe> attrapezLesTous() {

		//récupérer la session Hibernate en cours
		Session sessionEnCours = entityManager.unwrap(Session.class);
		
		// créer la requête
		Query<Employe> laRequete = 
				sessionEnCours.createQuery("from Employe", Employe.class);
		
		// exécuter la requête
		List<Employe> employes = laRequete.getResultList();
		
		
		return employes;
	}

	@Override
	public Employe trouverParId(int id) {
		
		//récupérer la session Hibernate en cours
		Session sessionEnCours = entityManager.unwrap(Session.class);
		
		// trouver l'employe
		Employe e = sessionEnCours.get(Employe.class, id);
		
		//retourner l'employe
		return e;
	}

	@Override
	@Transactional
	public void sauvegarder(Employe employe) {

		//récupérer la session Hibernate en cours
				Session sessionEnCours = entityManager.unwrap(Session.class);
				
		//sauvegarder employe (si id=0 alors il sauvegarde/insere, sinon update)
		sessionEnCours.saveOrUpdate(employe);
		
	}

	@Override
	public void supprimerParId(int id) {
		
		//récupérer la session Hibernate en cours
		Session sessionEnCours = entityManager.unwrap(Session.class);
		
		//supprimer grace a la cle primaire
		Query laRequete =
				sessionEnCours.createNamedQuery("delete from Eploye whire id=:employeId");
		laRequete.setParameter("employeId",id); // attache int id a la valeur employeId
		laRequete.executeUpdate();
		
	}

}
