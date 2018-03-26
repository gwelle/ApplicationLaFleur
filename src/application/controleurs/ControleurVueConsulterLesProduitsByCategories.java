/**
 * 
 */
package application.controleurs;

import application.entites.Categorie;
import application.entites.Produit;
import application.modeles.Modele;
import application.vues.Vues;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * @author Olga
 *
 */
public class ControleurVueConsulterLesProduitsByCategories implements EventHandler<ActionEvent> {
	
	Vues vueConsulterLesProduitsByCategories ;
	Stage fenetre ;

	/**
	 * Constructeur
	 * @param vue
	 */
	public ControleurVueConsulterLesProduitsByCategories(Vues vue,Stage fenetre) {
		System.out.println("ControleurVueConsulterLesProduitsByCategories::ControleurVueConsulterLesProduitsByCategories()");
		this.vueConsulterLesProduitsByCategories = vue;
		this.fenetre = fenetre ;
		this.ecouterEvenements();
	}

	/**
	 * Ecouter les Evenements
	 */
	private void ecouterEvenements() {
		System.out.println("ControleurVueConsulterLesProduitsByCategories::ecouterEvenements()");
		this.vueConsulterLesProduitsByCategories.getbRetourPrincipalPageAccueil().addEventHandler(ActionEvent.ACTION,this);
	}

	@Override
	public void handle(ActionEvent event) {
		
		 System.out.println("ControleurVueConsulterLesProduitsByCategories::handle()");
		
		 if(event.getSource() == vueConsulterLesProduitsByCategories.getbRetourPrincipalPageAccueil()) {
			
			this.fenetre.setScene(this.vueConsulterLesProduitsByCategories.getSceneVuePrincipal());
			this.fenetre.setTitle("Bienvenue sur l'application LaFleur");
			this.vueConsulterLesProduitsByCategories.activerItemsMenus();
		}
		 
		 else if(event.getSource() == vueConsulterLesProduitsByCategories.getbValiderUneCategorie()) {
			 
			 	this.fenetre.setScene(vueConsulterLesProduitsByCategories.getSceneVueConsulterLesProduitsByCategories());
				this.fenetre.setTitle("La liste des Produits selon une Catégorie sélectionée");
				
				ComboBox<Categorie> comboLesCategories = vueConsulterLesProduitsByCategories.getComboLesCategories();
				String comboString = comboLesCategories.getValue().getNom();
				
				TableView<Produit> tvProduits = vueConsulterLesProduitsByCategories.getTvProduitsByCategories();
				tvProduits.setItems(Modele.getProduitsCategorie(comboString));
				
			}
	}
}