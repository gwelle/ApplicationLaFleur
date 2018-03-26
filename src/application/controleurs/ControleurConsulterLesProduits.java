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
public class ControleurConsulterLesProduits implements EventHandler<ActionEvent> {
	
	Vues vueListeProduits ;
	Stage fenetre ;
	
	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurConsulterLesProduits(Vues vue,Stage fenetre) {
		System.out.println("ControleurConsulterLesProduits::ControleurConsulterLesProduits()");
		this.vueListeProduits = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}
	
	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		
		System.out.println("ControleurVueListeCategories::ecouterEvenements()");
		this.vueListeProduits.getbRetourPrincipalPage().addEventHandler(ActionEvent.ACTION, this);
	}

	@Override
	public void handle(ActionEvent evenement) {
		
		System.out.println("ControleurConsulterLesProduits::handle()");
		
		if(evenement.getSource() == vueListeProduits.getbRetourPrincipalPage()) {
			
			this.fenetre.setScene(this.vueListeProduits.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueListeProduits.activerItemsMenus();
		}
	}
}