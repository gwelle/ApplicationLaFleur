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
public class ControleurVueListeProduitsMC implements EventHandler<ActionEvent> {
	
	Vues vueListeProduitsMC ;
	Stage fenetre ;

	/**
	 * Constructeur
	 * @param vues
	 * @param primaryStage
	 */
	public ControleurVueListeProduitsMC(Vues vues, Stage primaryStage) {
		System.out.println("ControleurVueListeProduitsMC::ControleurVueListeProduitsMC");
		this.vueListeProduitsMC = vues ;
		this.fenetre = primaryStage ;
		this.ecouterEvenements();
	}
	
	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		System.out.println("ControleurVueListeProduitsMC::ecouterEvenements()");
		this.vueListeProduitsMC.getbRetourPP().addEventHandler(ActionEvent.ACTION,this);
	}

	@Override
	public void handle(ActionEvent evenement) {
		
		System.out.println("ControleurVueListeProduitsMC::handle()");
		
		if(evenement.getSource() == this.vueListeProduitsMC.getbRetourPP()) {
			
			this.fenetre.setScene(this.vueListeProduitsMC.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueListeProduitsMC.activerItemsMenus();
		}
	}
}