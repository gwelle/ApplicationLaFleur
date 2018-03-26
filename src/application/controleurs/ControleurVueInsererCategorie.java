/**
 * 
 */
package application.controleurs;

import application.entites.Categorie;
import application.modeles.Modele;
import application.vues.Vues;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * @author Olga
 *
 */
public class ControleurVueInsererCategorie implements EventHandler<ActionEvent> {
	
	Vues vueInsererCategorie ;
	Stage fenetre ;
	
	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurVueInsererCategorie(Vues vue,Stage fenetre) {
		System.out.println("ControleurVueInsererCategorie::ControleurVueInsererCategorie()");
		this.vueInsererCategorie = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}
	
	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		
		System.out.println("ControleurVueInsererCategorie::ecouterEvenements()");
		this.vueInsererCategorie.getbValiderCategorie().addEventHandler(ActionEvent.ACTION, this);
		this.vueInsererCategorie.getbAnnulerCategorie().addEventHandler(ActionEvent.ACTION, this);
		this.vueInsererCategorie.getbRetourPagePrincipal().addEventHandler(ActionEvent.ACTION, this);
		
	}

	@Override
	public void handle(ActionEvent evenement) {
		
		System.out.println("ControleurVueInsererCategorie::handle()");
		
		if(evenement.getSource() == vueInsererCategorie.getbValiderCategorie()) {
			
			String nom = vueInsererCategorie.getTfNomCategorie().getText();
			Categorie uneCategorie = new Categorie(nom);
			Modele.ajouterUneCategorie(uneCategorie);
			
			Alert info = new Alert(AlertType.INFORMATION);
			info.setHeaderText("Vous venez d\'ajouter une Catégorie");
			info.setContentText("Récapitulatif : \n"+ "Nom : "+nom+"\n.");
			info.showAndWait();
			
			vueInsererCategorie.getTfNomCategorie().setText("");
			
			//Récupération et Suppression de la Collection des données des Catégories
			ObservableList<Categorie> listeCategoriesRemove = Modele.getMesCacategories() ;
			listeCategoriesRemove.clear();
			
			//Récupération et Mise à jour de la Collection des données des Catégories
			ObservableList<Categorie> listeCategorieMAJ = Modele.getMesCacategories() ;
			this.vueInsererCategorie.getListViewCategorie().setItems(listeCategorieMAJ);
			
			//Récupération et Suppression de la ComboBox des données des Catégories
			ComboBox<Categorie> comboCategorieRemove = vueInsererCategorie.getComboCategories();
			 comboCategorieRemove.getItems().clear();
			
			//Récupération et Mise à jour de la ComboBox des données des Catégories
			ComboBox<Categorie> comboCategorieUpdate = vueInsererCategorie.getComboCategories();
			comboCategorieUpdate.setItems(Modele.getMesCacategories());
		
			this.fenetre.setScene(this.vueInsererCategorie.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueInsererCategorie.activerItemsMenus();
		}
		
		else if(evenement.getSource() == vueInsererCategorie.getbAnnulerCategorie()) {
			
			vueInsererCategorie.getTfNomCategorie().setText("");
		}
		
		else if(evenement.getSource() == vueInsererCategorie.getbRetourPagePrincipal()) {
			
			this.fenetre.setScene(this.vueInsererCategorie.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueInsererCategorie.activerItemsMenus();
		}
	}
	

}