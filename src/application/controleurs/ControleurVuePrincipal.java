/**
 * 
 */
package application.controleurs;

import application.entites.Produit;
import application.modeles.Modele;
import application.vues.Vues;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * @author Olga
 *
 */
public class ControleurVuePrincipal implements EventHandler<ActionEvent>, ChangeListener {
	
	Vues vue ;
	Stage fenetre ;


	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurVuePrincipal(Vues vue,Stage fenetre) {
		System.out.println("ControleurVuePrincipal::ControleurVuePrincipal()");
		this.vue = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}
	
	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		
		System.out.println("ControleurVuePrincipal::ecouterEvenements()");
		this.vue.getSeConnecter().addEventHandler(ActionEvent.ACTION, this);
		this.vue.getSeDeconnecter().addEventHandler(ActionEvent.ACTION, this);
		this.vue.getQuitter().addEventHandler(ActionEvent.ACTION, this);
		this.vue.getCategorie().addEventHandler(ActionEvent.ACTION, this);
		this.vue.getProduit().addEventHandler(ActionEvent.ACTION, this);
		this.vue.getLesProduitsCodeCat().addEventHandler(ActionEvent.ACTION, this);
		this.vue.getUneCategorie().addEventHandler(ActionEvent.ACTION,this);
		this.vue.getLesProduitsMC().addEventHandler(ActionEvent.ACTION,this);
		this.vue.getTfRechercheMC().textProperty().addListener(this);
		
	}

	@Override
	public void handle(ActionEvent evenement) {
		
		System.out.println("ControleurVuePrincipal::handle()");
		
		if(evenement.getSource() == vue.getSeConnecter()) {
			
			fenetre.setScene(vue.getSceneVueAuthentification());
			fenetre.setTitle("Page d\'Authentification");
		}
		
		else if(evenement.getSource() == vue.getSeDeconnecter()) {
			
			Alert info = new Alert(AlertType.CONFIRMATION);
			info.setHeaderText("Tentative de Déconnexion");
			info.setContentText("Voulez - vous vraiment vous Déconnectez ?");
			info.showAndWait();
			
			this.fenetre.setScene(this.vue.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vue.desactiverItemsMenus();
			vue.getSeConnecter().setDisable(false);
			this.vue.resetChamps();
		}
		
		else if(evenement.getSource() == vue.getQuitter()) {
			
			Platform.exit();
		}

		else if(evenement.getSource() == vue.getCategorie()) {
			
			this.fenetre.setScene(this.vue.getSceneVueInsererCategorie());
			this.fenetre.setTitle("Ajouter une Catégorie");	
		}
		
		else if(evenement.getSource() == vue.getProduit()) {
			
			this.fenetre.setScene(vue.getSceneVueInsererProduit());
			this.fenetre.setTitle("Ajouter un Produit");
		}
		
		else if(evenement.getSource() == vue.getLesProduitsCodeCat()) {
			
			this.fenetre.setScene(this.vue.getSceneVueConsulterLesProduits());
			this.fenetre.setTitle("Liste des Produits avec le numéro de la Catégorie");
		}
		
		else if(evenement.getSource() == vue.getUneCategorie()) {
			
			this.fenetre.setScene(this.vue.getSceneVueConsulterUneCategorie());
			this.fenetre.setTitle("Selectionée une Catégorie");
		}
		
		else if(evenement.getSource() == vue.getLesProduitsMC()) {
			
			this.fenetre.setScene(this.vue.getSceneVueConsulterLesProduitsMC());
			this.fenetre.setTitle("Taper un Mot - Clé pour rechercher un ou plusieus Produits");
		}
	}

	@Override
	public void changed(ObservableValue observable, Object oldValue,Object newValue) {
		
		//Récuperer l'OberservableList des Produits depuis le Modèle
		ObservableList<Produit> listeProduits = Modele.getMesProduits() ;
		
		//Enveloppez ObservableList dans une FilteredList (affichez toutes les données initialement).
		FilteredList<Produit>donneesFiltrer = new FilteredList<Produit>(listeProduits, p -> true);
		
		donneesFiltrer.setPredicate(produit -> {
		
		// Si le texte du filtre est vide, affichez tous les Produits.
		if (newValue == null || ((String) newValue).isEmpty()) {
			
			return true;
		}
		
		// Comparer le nom du produit avec le texte du filtre.
		String lowerCaseFilter = ((String) newValue).toLowerCase();
		
		if (produit.getNomProduit().toLowerCase().indexOf(lowerCaseFilter) != -1) {
			
			System.out.println("Le filtre correspond bien au nom du Produit que vous recherchez");
			return true; 
		}
			
			System.out.println("Le filtre ne correspond pas au nom du Produit que vous recherchez");
			return false; 
		
		});
		
		//Enveloppez FilteredList dans une liste triée.
		SortedList<Produit> donneesTrier = new SortedList<>(donneesFiltrer);
		
		/*Liez le comparateur SortedList au comparateur TableView.
		Sinon, le tri de TableView n'aurait aucun effet.*/
		donneesTrier.comparatorProperty().bind(vue.getTvProduitsMC().comparatorProperty());
		
		//Ajoute des données triées (et filtrées) à la table
		vue.getTvProduitsMC().setItems(donneesTrier);
	}
		
}