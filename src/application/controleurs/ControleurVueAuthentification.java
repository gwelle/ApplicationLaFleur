/**
 * 
 */
package application.controleurs;

import java.sql.SQLException;
import application.modeles.Modele;
import application.vues.Vues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * @author Olga
 *
 */
public class ControleurVueAuthentification implements EventHandler<ActionEvent> {
	
	Vues vueAuthentifiction ;
	Stage fenetre ;
	
	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurVueAuthentification(Vues vue,Stage fenetre) {
		System.out.println("ControleurVueAuthentification::ControleurVueAuthentification()");
		this.vueAuthentifiction = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}
	
	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		
		System.out.println("ControleurVueAuthentification::ecouterEvenements()");
		this.vueAuthentifiction.getbValiderConnexion().addEventHandler(ActionEvent.ACTION, this);
		this.vueAuthentifiction.getbAnnulerConnexion().addEventHandler(ActionEvent.ACTION, this);
		this.vueAuthentifiction.getbRetourViewAccueil().addEventHandler(ActionEvent.ACTION, this);
	}

	@Override
	public void handle(ActionEvent evenement) {
		
		System.out.println("ControleurVueAuthentification::handle()");
		
		if(evenement.getSource() == vueAuthentifiction.getbValiderConnexion()) {
			
			String login = vueAuthentifiction.getTfLogin().getText() ;
			String mdp = vueAuthentifiction.getPfMdp().getText() ;
		    
					
			try {
					
				Modele.seConnecter(login, mdp);
				this.fenetre.setScene(vueAuthentifiction.getSceneVuePrincipal());
				this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
				this.vueAuthentifiction.activerItemsMenus();
				this.vueAuthentifiction.afficherMessageInformations();
				
				} 
			
			catch (SQLException e) {
				this.vueAuthentifiction.afficherMessageErreurs();
			}
		}
		
		else if(evenement.getSource() == vueAuthentifiction.getbAnnulerConnexion()) {
			
			this.vueAuthentifiction.resetChamps();
		}
		
		else if(evenement.getSource() == vueAuthentifiction.getbRetourViewAccueil()) {
			
			this.fenetre.setScene(this.vueAuthentifiction.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueAuthentifiction.desactiverItemsMenus();
			this.vueAuthentifiction.resetChamps();
		}
	}
}