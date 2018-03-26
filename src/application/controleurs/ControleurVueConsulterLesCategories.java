/**
 * 
 */
package application.controleurs;

import application.vues.Vues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * @author Olga
 *
 */
public class ControleurVueConsulterLesCategories implements EventHandler<ActionEvent> {
	
	Vues vueConsulterLesCategories ;
	Stage fenetre ;
	
	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurVueConsulterLesCategories(Vues vue,Stage fenetre) {
		System.out.println("ControleurVueConsulterLesCategories::ControleurVueConsulterLesCategories()");
		this.vueConsulterLesCategories = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}
	
	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		
		System.out.println("ControleurVueListeCategories::ecouterEvenements()");
		this.vueConsulterLesCategories.getCategories().addEventHandler(ActionEvent.ACTION, this);
		this.vueConsulterLesCategories.getbRetourPageAccueil().addEventHandler(ActionEvent.ACTION, this);
	}

	@Override
	public void handle(ActionEvent evenement) {
		
		System.out.println("ControleurVueConsulterLesCategories::handle()");
		
		if(evenement.getSource() == vueConsulterLesCategories.getCategories()) {
			
			this.fenetre.setScene(this.vueConsulterLesCategories.getSceneVueListeCategories());
			this.fenetre.setTitle("Liste des Catégories");
		}
		
		else if(evenement.getSource() == vueConsulterLesCategories.getbRetourPageAccueil()) {
			
			this.fenetre.setScene(this.vueConsulterLesCategories.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueConsulterLesCategories.activerItemsMenus();
		}
	}
}