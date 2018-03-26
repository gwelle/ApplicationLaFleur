
/**
 * 
 */
package application.controleurs;

import java.sql.SQLException;

import application.entites.Categorie;
import application.entites.Produit;
import application.modeles.Modele;
import application.vues.Vues;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

import javafx.stage.Stage;

/**
 * @author Olga
 *
 */
public class ControleurVueInsererProduit implements EventHandler<ActionEvent> {
	
	Vues vueInsererProduit ;
	Stage fenetre ;
	
	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurVueInsererProduit(Vues vue,Stage fenetre) {
		System.out.println("ControleurVueInsererProduit::Controleur()");
		this.vueInsererProduit = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}

	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		
		System.out.println("ControleurVueInsererProduit::ecouterEvenements()");
		this.vueInsererProduit.getbValiderProduit().addEventHandler(ActionEvent.ACTION, this);
		this.vueInsererProduit.getbAnnulerProduit().addEventHandler(ActionEvent.ACTION, this);
		this.vueInsererProduit.getbRetourFirstVue().addEventHandler(ActionEvent.ACTION, this);
	}

	@Override
	public void handle(ActionEvent evenement) {
		
		System.out.println("ControleurVueInsererProduit::handle()");
		
		if(evenement.getSource() == vueInsererProduit.getbValiderProduit()) {
			
			String nom = vueInsererProduit.getTfNomProduit().getText();
			
			String qteStock = null ;
			int qteStockConverti ;
			String prix = null ;
			float prixConverti ;
			
			try {
			
				prix = vueInsererProduit.getTfPrix().getText();
				prixConverti = Float.parseFloat(prix);
				
				qteStock = vueInsererProduit.getTfQteStock().getText();
				qteStockConverti = Integer.parseInt(qteStock);
			
			}
			
			catch(NumberFormatException erreur) {
				
				Alert info = new Alert(AlertType.WARNING);
				info.setHeaderText("Attention");
				info.setContentText("J'attendais un Float ou un Entier. "+prix+" n\'est pas une Valeur de type Float "
						+ "ou "+qteStock+"  n\'est pas une Valeur de type Entier.");
				info.showAndWait();
				return ;
			}
			
			ComboBox<Categorie> comboCategorie = vueInsererProduit.getComboCategories();
			String comboString = comboCategorie.getValue().getNom();
			
			int idCategorie = 0;
			
			try {
				
				idCategorie = Modele.verifierNomCategorie(comboString);
				Produit produit = new Produit(nom,prixConverti,qteStockConverti,idCategorie);
				
				Modele.ajouterUnProduit(produit,comboString);
				
				Alert info = new Alert(AlertType.INFORMATION);
				info.setHeaderText("Vous venez d\'ajouter un produit");
				info.setContentText("Récapitulatif : \n"+ "Nom : "+nom+"\n"+ "Prix : "+prixConverti+
						"€.\n"+"QtéStock : "+qteStockConverti+"\n"+"Nom de la Catégorie Selectionnée : "+comboString);
				info.showAndWait();
				
				vueInsererProduit.getTfNomProduit().setText("");
				vueInsererProduit.getTfPrix().setText("");
				vueInsererProduit.getTfQteStock().setText("");
				
				comboCategorie.setValue(null);
				
				//Récupération et Suppression de la Collection des données des Produits
				ObservableList<Produit> listeProduitsRemove = Modele.getMesProduits() ;
				listeProduitsRemove.clear();
				
				//Récupération et Mise à jour de la Collection des données des Produits
				ObservableList<Produit> listeCategorieMAJ = Modele.getMesProduits() ;
				this.vueInsererProduit.getTvProduits().setItems(listeCategorieMAJ);
			} 
			
			catch(NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			
			this.fenetre.setScene(this.vueInsererProduit.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueInsererProduit.activerItemsMenus();
		}
		
		else if(evenement.getSource() == vueInsererProduit.getbAnnulerProduit()) {
			
			vueInsererProduit.getTfNomProduit().setText("");
			vueInsererProduit.getTfPrix().setText("");
			vueInsererProduit.getTfQteStock().setText("");
		}
		
		else if(evenement.getSource() == vueInsererProduit.getbRetourFirstVue()) {
			
			this.fenetre.setScene(this.vueInsererProduit.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueInsererProduit.activerItemsMenus();
		}
	}
}